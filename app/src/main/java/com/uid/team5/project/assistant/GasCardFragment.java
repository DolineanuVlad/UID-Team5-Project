package com.uid.team5.project.assistant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.uid.team5.project.R;
import com.uid.team5.project.bottom_nav_fragments.AssistantFragment;

import java.util.ArrayList;

import im.dacer.androidcharts.BarView;

/**
 * Created by marat on 14-Jan-18.
 */

public class GasCardFragment extends Fragment implements OnMapReadyCallback {
    private GasCardFragment.OnFragmentInteractionListener mListener;
    public GasCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionsFragment.
     */
    public static GasCardFragment newInstance() {
        GasCardFragment fragment = new GasCardFragment();
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
        LinearLayout view=(LinearLayout)inflater.inflate(R.layout.card, container, false);
        FragmentManager myFragmentManager = this.getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) myFragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        BarView barView = (BarView)view.findViewById(R.id.bar_view);
        ArrayList<String> str=new ArrayList<>();
        str.add("Sat");str.add("Sun");str.add("Mon"); str.add("Tue"); str.add("Wed");
        ArrayList<Integer> intLis=new ArrayList<>();
        intLis.add(10); intLis.add(20); intLis.add(75);intLis.add(80);intLis.add(85);

        barView.setBottomTextList(str);
        barView.setDataList(intLis,100);
        Button mar=(Button)view.findViewById(R.id.mar);
        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("cardInvisible",true);
                AssistantFragment fragment= AssistantFragment.newInstance();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame_layout, fragment);
                transaction.commit();
            }
        });
        return view;
    }

    private void moveToCurrentLocation(LatLng currentLocation,GoogleMap googleMap)
    {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


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
        if (context instanceof GasCardFragment.OnFragmentInteractionListener) {
            mListener = (GasCardFragment.OnFragmentInteractionListener) context;
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


    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng latlng=new LatLng(46.7717, 23.6269);
        moveToCurrentLocation(latlng,map);
        map.addMarker(new MarkerOptions().position(latlng).title("Marker"));
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