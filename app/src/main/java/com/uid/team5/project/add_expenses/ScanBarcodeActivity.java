package com.uid.team5.project.add_expenses;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.ExpensesCategoriesSpinnerAdapter;
import com.uid.team5.project.models.Expense;
import com.uid.team5.project.shared.MainActivity;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScanBarcodeActivity extends AppCompatActivity {

    AppDataSingleton appDataSingleton;
    private String cameraId;
    private Size previewSize;
    private TextureView cameraTextureView;
    private CameraDevice cameraDevice;

    private CaptureRequest previewCaptureRequest;
    private CaptureRequest.Builder previewCaptureRequestBuilder;
    private CameraCaptureSession cameraCaptureSession;

    AlertDialog.Builder builder;
    AlertDialog popup;
    View progressView;

    private ImageReader imageReader;
    private ImageReader.OnImageAvailableListener imageReaderCallback = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
            Image image = reader.acquireNextImage();
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.capacity()];
            buffer.get(bytes);
            Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);

            Matrix matrix = new Matrix();
            matrix.postRotate(-90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmapImage, 0, 0, bitmapImage.getWidth(), bitmapImage.getHeight(), matrix, true);
            image.close();

            popup = builder.create();
            View v = getLayoutInflater().inflate(R.layout.popup_confirm_image, null);
            ImageView iv = v.findViewById(R.id.image_preview_snapshot);
            iv.setImageBitmap(rotatedBitmap);

            Button yesButton = v.findViewById(R.id.image_confirm_yes);
            Button noButton = v.findViewById(R.id.image_confirm_no);

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cameraDevice.close();
                    popup.dismiss();

                    progressView.setVisibility(View.VISIBLE);
                    progressView.animate().setDuration(2000).alpha(1).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            progressView.setVisibility(View.GONE);

                            popup = builder.create();
                            View v = getLayoutInflater().inflate(R.layout.popup_expense_insertion, null);
                            final EditText description = v.findViewById(R.id.new_expense_description);
                            final EditText price = v.findViewById(R.id.new_expense_price);
                            final Spinner category = v.findViewById(R.id.new_expense_category);
                            ExpensesCategoriesSpinnerAdapter ecsa = new ExpensesCategoriesSpinnerAdapter(appDataSingleton.getExpenseCategories(), v.getContext());
                            category.setAdapter(ecsa);
                            description.setText("Electricity Bill");
                            price.setText("84.26");
                            category.setSelection(1);

                            Button confirmButton = v.findViewById(R.id.new_expense_confirm_button);
                            Button cancelButton = v.findViewById(R.id.new_expense_cancel_button);

                            cancelButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    popup.dismiss();
                                }
                            });

                            confirmButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayList<Expense> exp = appDataSingleton.getExpenses();
                                    appDataSingleton.getExpenses().add(new Expense((int)exp.size(),
                                            description.getText().toString(),
                                            Float.parseFloat(price.getText().toString()),
                                            "House",
                                            (int)category.getSelectedItemId()));
                                    popup.dismiss();
                                    finish();
                                }
                            });
                            popup.setView(v);
                            popup.show();
                        }
                    });
                }
            });

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popup.dismiss();
                }
            });
            popup.setView(v);

            builder.setView(R.layout.popup_confirm_image);
            popup.show();

        }
    };

    private CameraCaptureSession.CaptureCallback cameraCaptureSessionCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureStarted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, long timestamp, long frameNumber) {
            super.onCaptureStarted(session, request, timestamp, frameNumber);

        }
    };

    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;
            createCameraPreviewSession();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            camera.close();
            cameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            camera.close();
            cameraDevice = null;
        }
    };

    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            setupCamera(width, height);
            openCamera();
        }
        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {}
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {return false;}
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
        cameraTextureView = findViewById(R.id.camera_texture_view);
        cameraTextureView.setSurfaceTextureListener(surfaceTextureListener);
        progressView = findViewById(R.id.scan_receipt_progress);
        builder = new AlertDialog.Builder(this);
        appDataSingleton = AppDataSingleton.getInstance();
    }

    private void setupCamera(int width, int height) {
        CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            for(String camId : cameraManager.getCameraIdList()) {
                CameraCharacteristics chars = cameraManager.getCameraCharacteristics(camId);
                if (chars.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT) {
                    continue;
                }
                StreamConfigurationMap map = chars.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                previewSize = getPreferredPreviewSize(map.getOutputSizes(SurfaceTexture.class), width, height);
                cameraId = camId;
                imageReader = ImageReader.newInstance(previewSize.getWidth(), previewSize.getHeight(), ImageFormat.JPEG, 1);
                imageReader.setOnImageAvailableListener(imageReaderCallback, null);
                return;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private Size getPreferredPreviewSize(Size[] mapSizes, int width, int height) {
        List<Size> collectorSizes = new ArrayList<Size>();
        for (Size option : mapSizes) {
            if (option.getWidth() > width && option.getHeight() > height) {
                collectorSizes.add(option);
            }
        }

        if (collectorSizes.size() > 0) {
            return Collections.min(collectorSizes, new Comparator<Size>() {
                @Override
                public int compare(Size o1, Size o2) {
                    return Long.signum(o1.getWidth() * o1.getHeight() - o2.getWidth() * o2.getHeight());
                }
            });
        }

        return collectorSizes.get(0);
    }

    private void openCamera() {
        CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraManager.openCamera(cameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void createCameraPreviewSession() {
        try {
            SurfaceTexture surfaceTexture = cameraTextureView.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(cameraTextureView.getWidth(), cameraTextureView.getHeight());
            Surface previewSurface = new Surface(surfaceTexture);
            previewCaptureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            previewCaptureRequestBuilder.addTarget(previewSurface);
            ArrayList<Surface> surfaces = new ArrayList<>();
            surfaces.add(previewSurface);
            surfaces.add(imageReader.getSurface());

            cameraDevice.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    if (cameraDevice == null) {
                        return;
                    }

                    try {
                        previewCaptureRequest = previewCaptureRequestBuilder.build();
                        cameraCaptureSession = session;
                        cameraCaptureSession.setRepeatingRequest(
                                previewCaptureRequest,
                                cameraCaptureSessionCallback,
                                null
                        );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            }, null);

        } catch (Exception e) {

        }
    }

    public void captureStillImage(View v) {
        try {
            CaptureRequest.Builder captureStillBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureStillBuilder.addTarget(imageReader.getSurface());

            cameraCaptureSession.capture(
                    captureStillBuilder.build(),
                    new CameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                            super.onCaptureCompleted(session, request, result);
                            System.out.println("CAPTURE COMPLETED>>>!!!");
                        }
                    },
                    null
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
