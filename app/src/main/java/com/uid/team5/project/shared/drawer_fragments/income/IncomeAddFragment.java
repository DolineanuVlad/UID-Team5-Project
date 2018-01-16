package com.uid.team5.project.shared.drawer_fragments.income;

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
import com.uid.team5.project.models.ExpenseCategory;
import com.uid.team5.project.models.Income;
import com.uid.team5.project.models.Member;
import com.uid.team5.project.shared.drawer_fragments.expense_categories.ExpenseCategoriesAddCategoryFragment;

import java.util.ArrayList;


public class IncomeAddFragment extends Fragment {
    AppDataSingleton appData;

    private OnFragmentInteractionListener mListener;

    public IncomeAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExpenseCategoriesAddCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeAddFragment newInstance() {
        IncomeAddFragment fragment = new IncomeAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        appData = AppDataSingleton.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_income_add,
                container, false);

        Button saveButton  = rootView.findViewById(R.id.addIncomeB);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIncomeAndReturn(rootView);
            }
        });
        return rootView;

    }

    private void addIncomeAndReturn(View rootView) {
        TextView name = (TextView)rootView.findViewById(R.id.incomeNameField);
        Spinner occurance = (Spinner) rootView.findViewById(R.id.incomeOccuranceField);
        TextView value = (TextView)rootView.findViewById(R.id.incomeValueField);
        ArrayList<Income> incomes = appData.getIncomes();
        Income income=new Income(name.getText().toString(), value.getText().toString(), occurance.getSelectedItem().toString());
        incomes.add(income);
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
