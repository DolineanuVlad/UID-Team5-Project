package com.uid.team5.project.shared.drawer_fragments.income;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.ExpenseCategoryAdapter;
import com.uid.team5.project.adapters.IncomeAdapter;
import com.uid.team5.project.models.Income;
import com.uid.team5.project.services.IncomeService;
import com.uid.team5.project.shared.drawer_fragments.expense_categories.ExpenseCategoriesAddCategoryFragment;

import java.util.List;


public class IncomeFragment extends Fragment {
    private ListView mListView;
    private FloatingActionButton mAddCategory;
    private OnFragmentInteractionListener mListener;
    private IncomeService incomeService;
    List<Income> mlist;
    AppDataSingleton appData;

    public IncomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     */
    // TODO: Rename and change types and number of parameters
    public static IncomeFragment newInstance() {
        IncomeFragment fragment = new IncomeFragment();
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
        View rootView =  inflater.inflate(R.layout.fragment_income, container, false);

        mListView = (ListView) rootView.findViewById(R.id.incomeList);

        IncomeAdapter adapter = new IncomeAdapter(appData.getIncomes(), this.getContext());
        mListView.setAdapter(adapter);

        mAddCategory = (FloatingActionButton)rootView.findViewById(R.id.floatingActionButtonAddExpense);

        mAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddIncomeView(view);
            }
        });
        return rootView;
    }

    private void openAddIncomeView(View view) {
        Fragment addIncomeFragment = IncomeAddFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, addIncomeFragment);
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
