package com.uid.team5.project.shared.drawer_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uid.team5.project.R;
import com.uid.team5.project.adapters.FamilyGroupAdapter;
import com.uid.team5.project.models.Member;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FamilyGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FamilyGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyGroupFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ListView mListView;


    public FamilyGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FamilyGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FamilyGroupFragment newInstance() {
        FamilyGroupFragment fragment = new FamilyGroupFragment();
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
        View rootView =  inflater.inflate(R.layout.fragment_family_group, container, false);

        mListView = (ListView) rootView.findViewById(R.id.family_group_list);
// 1
        final ArrayList<Member> membersList = new ArrayList<Member>();

        membersList.add(new Member("Dianne", "Sister", R.drawable.member_diane_kruger));
        membersList.add(new Member("Leo", "Brother", R.drawable.member_leonardo_dicaprio));
// 2
        String[] listItems = new String[membersList.size()];
// 3
        for(int i = 0; i < membersList.size(); i++){
            Member member = membersList.get(i);
            listItems[i] = member.getName();
        }
// 4
        FamilyGroupAdapter adapter = new FamilyGroupAdapter(this.getContext(), membersList);
        mListView.setAdapter(adapter);

        return rootView;
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
