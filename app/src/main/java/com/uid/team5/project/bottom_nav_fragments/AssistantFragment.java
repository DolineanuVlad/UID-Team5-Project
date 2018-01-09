package com.uid.team5.project.bottom_nav_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import wishlist.AddNewGoalFragment;

/**
 * Created by mara.tatar on 1/9/2018.
 */

public class AssistantFragment extends Fragment {
private boolean enabled;
    private AssistantFragment.OnFragmentInteractionListener mListener;

    public AssistantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssistantFragment newInstance() {
        AssistantFragment fragment = new AssistantFragment();
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
        View view=(View)inflater.inflate(R.layout.fragment_assistant, container, false);
        Switch onOffSwitch = (Switch)  view.findViewById(R.id.enableAssistant);
        if (AppDataSingleton.getInstance().isEnabledAssistant()){
            onOffSwitch.setChecked(true);
            view.findViewById(R.id.cards).setVisibility(View.VISIBLE);
        }
        else{
            onOffSwitch.setChecked(false);
            view.findViewById(R.id.cards).setVisibility(View.INVISIBLE);
        }
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
                   AppDataSingleton.getInstance().setEnabledAssistant(true);
                   buttonView.getRootView().findViewById(R.id.cards).setVisibility(View.VISIBLE);
               } else{
                   AppDataSingleton.getInstance().setEnabledAssistant(false);
                   buttonView.getRootView().findViewById(R.id.cards).setVisibility(View.INVISIBLE);
               }
            }

        });

        return view;
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
        if (context instanceof AssistantFragment.OnFragmentInteractionListener) {
            mListener = (AssistantFragment.OnFragmentInteractionListener) context;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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