package com.uid.team5.project.shared.drawer_fragments.family_group;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.FamilyGroupAdapter;
import com.uid.team5.project.add_expenses.ManualAdditionActivity;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FamilyGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FamilyGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyGroupFragment extends Fragment {
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    private ListView mListView;
    private FloatingActionButton mAddMember;
    private ImageButton mButton;
    AppDataSingleton appData;
    private PopupWindow mPopupWindow;
    private RelativeLayout rl;
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
        appData = AppDataSingleton.getInstance();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_family_group, container, false);

        mListView = (ListView) rootView.findViewById(R.id.family_group_list);

        FamilyGroupAdapter adapter = new FamilyGroupAdapter(this.getContext(), appData.getMembers());
        mListView.setAdapter(adapter);

        mAddMember = (FloatingActionButton)rootView.findViewById(R.id.floatingActionButtonAddMember);
        mAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddMemberView(view);
            }
        });

        View viewForEdit= inflater.inflate(R.layout.family_member_list_item, container, false);
        mButton=(ImageButton) viewForEdit.findViewById(R.id.family_group_list_item_edit);
        rl=(RelativeLayout) viewForEdit.findViewById(R.id.rl);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPopup(view);
            }
        });

        return rootView;
    }

    private void openPopup(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog popup = builder.create();
        View optionsPopup = (View) getLayoutInflater().inflate(R.layout.poput_layout, null);
        Button income = (Button) optionsPopup.findViewById(R.id.incomeB);
        Button budget = (Button) optionsPopup.findViewById(R.id.budgetB);
        Button cancelButton = (Button) optionsPopup.findViewById(R.id.cancelB);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });


        popup.setTitle(null);
        popup.setView(optionsPopup);
        popup.show();
    }
    private void openAddMemberView(View view) {

        Fragment addMemberFragment = FamilyGroupAddMemberFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, addMemberFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
