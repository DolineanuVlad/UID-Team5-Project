package com.uid.team5.project.shared.drawer_fragments.recurring_payments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.RecurringPaymentAdapter;
import com.uid.team5.project.models.RecurringPayment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecurringPaymentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecurringPaymentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecurringPaymentsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ListView mListView;
    private FloatingActionButton mAddMember;

    AppDataSingleton appData;

    public RecurringPaymentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment RecurringPaymentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecurringPaymentsFragment newInstance() {
        RecurringPaymentsFragment fragment = new RecurringPaymentsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appData = AppDataSingleton.getInstance();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.recurring_payments_view, container, false);

        mListView = rootView.findViewById(R.id.recurring_payments_list);

        RecurringPaymentAdapter adapter = new RecurringPaymentAdapter(this.getContext(), appData.getRecurringPayments());
        mListView.setAdapter(adapter);

        mAddMember = rootView.findViewById(R.id.floatingActionButtonAddPayment);

        mAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddExpenseView(view);
            }
        });


        return rootView;
    }

    private void openAddExpenseView(View view) {

        Fragment addPaymentFragment = RecurringPaymentsAddPaymentFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, addPaymentFragment);
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
