package com.uid.team5.project.shared.drawer_fragments.family_group;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uid.team5.project.R;
import com.uid.team5.project.models.Member;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FamilyGroupAddMemberFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FamilyGroupAddMemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyGroupAddMemberFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FamilyGroupAddMemberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FamilyGroupAddMemberFragment.
     * @param membersList
     */
    // TODO: Rename and change types and number of parameters
    public static FamilyGroupAddMemberFragment newInstance(ArrayList<Member> membersList) {
        FamilyGroupAddMemberFragment fragment = new FamilyGroupAddMemberFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View rootView =  inflater.inflate(R.layout.fragment_family_group_add_member, container, false);

        Button saveButton  = (Button) rootView.findViewById(R.id.family_group_add_member_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMemberAndReturn();
            }
        });
        return rootView;
    }

    private void createMemberAndReturn() {
        getFragmentManager().popBackStack();
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
