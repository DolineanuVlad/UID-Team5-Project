package com.uid.team5.project.shared.drawer_fragments.recurring_payments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.models.Member;
import com.uid.team5.project.models.RecurringPayment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecurringPaymentsAddPaymentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecurringPaymentsAddPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecurringPaymentsAddPaymentFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    AppDataSingleton appData;

    public RecurringPaymentsAddPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecurringPaymentsAddMemberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecurringPaymentsAddPaymentFragment newInstance() {
        RecurringPaymentsAddPaymentFragment fragment = new RecurringPaymentsAddPaymentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        final View rootView =  inflater.inflate(R.layout.fragment_recurring_payments_add_payment, container, false);

        Button saveButton  = (Button) rootView.findViewById(R.id.recurring_payment_add_payment_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPaymentAndReturn(rootView);
            }
        });
        return rootView;
    }

    private void createPaymentAndReturn(View rootView) {
        TextView name = (TextView)rootView.findViewById(R.id.recurring_payment_add_name);
        TextView budget = (TextView)rootView.findViewById(R.id.recurring_payment_add_amount);
        Spinner role = (Spinner)rootView.findViewById(R.id.recurring_payment_add_category);
        TextView date = (TextView)rootView.findViewById(R.id.recurring_payment_add_date);

        RecurringPayment newPayment = new RecurringPayment(name.getText().toString(), date.getText().toString(),budget.getText().toString());

        ArrayList<RecurringPayment> payments = appData.getRecurringPayments();
        payments.add(newPayment);

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
