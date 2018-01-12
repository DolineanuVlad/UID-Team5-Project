package wishlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.uid.team5.project.R;
import com.uid.team5.project.bottom_nav_fragments.WishlistFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by mara.tatar on 1/5/2018.
 */

public class AddNewGoalFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private AddNewGoalFragment.OnFragmentInteractionListener mListener;
    private DatePickerDialog dpd;
    private TextView dateTextView;
    private ImageButton imgButton;
    private List<Goal> goals;
    public AddNewGoalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionsFragment.
     */
    public static AddNewGoalFragment newInstance() {
        AddNewGoalFragment fragment = new AddNewGoalFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout view=(LinearLayout)inflater.inflate(R.layout.fragment_add_new_goal, container, false);
        dateTextView = view.findViewById(R.id.date_textview);

        view.findViewById(R.id.date_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                /*
                It is recommended to always create a new instance whenever you need to show a Dialog.
                The sample app is reusing them because it is useful when looking for regressions
                during testing
                 */
                if (dpd == null) {
                    dpd = DatePickerDialog.newInstance(
                            (DatePickerDialog.OnDateSetListener) AddNewGoalFragment.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                } else {
                    dpd.initialize(
                            (DatePickerDialog.OnDateSetListener) AddNewGoalFragment.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                }
                dpd.show(getActivity().getFragmentManager(),"DatepickerDialog");
            }
        });
        imgButton=view.findViewById(R.id.imageButton);
        imgButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        view.findViewById(R.id.save_goal).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Goal goal=new Goal();
              EditText name= view.getRootView().findViewById(R.id.inputGoalName);
              goal.setName(name.getText().toString());

              Spinner group=(Spinner)view.getRootView().findViewById(R.id.groupSpinner);
              if (group.getSelectedItem().toString().equals("Personal")) goal.setPersonal(true);
              else goal.setPersonal(false);

              Spinner category=(Spinner)view.getRootView().findViewById(R.id.categorySpinner);
              goal.setCategory(category.getSelectedItem().toString());

              Spinner priority=(Spinner)view.getRootView().findViewById(R.id.prioritySpinner);
              goal.setPriority(priority.getSelectedItem().toString());

                EditText sum= (EditText)view.getRootView().findViewById(R.id.inputGoalSum);
                goal.setTargetSum(Double.valueOf(sum.getText().toString()));

                Spinner savingplan=(Spinner)view.getRootView().findViewById(R.id.timespanSpinner);
                goal.setSavingPlan(savingplan.getSelectedItem().toString());

                TextView date= (TextView)view.getRootView().findViewById(R.id.date_textview);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date result = null;
                try {
                    result = format.parse(date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                goal.setDate(result);
                Fragment fragment = WishlistFragment.newInstance();
                goals.add(goal);
                ((WishlistFragment) fragment).setGoals(goals);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame_layout, fragment);
                transaction.commit();

            }
        });
        return view;
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        dateTextView.setText(date);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddNewGoalFragment.OnFragmentInteractionListener) {
            mListener = (AddNewGoalFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(targetUri));
                imgButton.setImageBitmap(bitmap);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imgButton.getLayoutParams();
                params.height = 500;
                params.width= ViewGroup.LayoutParams.MATCH_PARENT;
                params.leftMargin=0;
                params.topMargin=0;

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
