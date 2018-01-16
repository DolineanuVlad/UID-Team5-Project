package com.uid.team5.project.shared.drawer_fragments.expense_categories;

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
import com.uid.team5.project.models.Member;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpenseCategoriesAddCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExpenseCategoriesAddCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseCategoriesAddCategoryFragment extends Fragment {
    AppDataSingleton appData;

    private OnFragmentInteractionListener mListener;

    public ExpenseCategoriesAddCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExpenseCategoriesAddCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpenseCategoriesAddCategoryFragment newInstance() {
        ExpenseCategoriesAddCategoryFragment fragment = new ExpenseCategoriesAddCategoryFragment();
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
        final View rootView =  inflater.inflate(R.layout.fragment_expense_categories_add_category,
                container, false);

        Button saveButton  = rootView.findViewById(R.id.addCategoryB);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExpenseAndReturn(rootView);
            }
        });
        return rootView;

    }

    private void addExpenseAndReturn(View rootView) {
        TextView name = (TextView)rootView.findViewById(R.id.categoryNameField);
        TextView description = (TextView)rootView.findViewById(R.id.categoryDescriptionField);
        ArrayList<ExpenseCategory> ecs = appData.getExpenseCategories();
        ExpenseCategory ec=new ExpenseCategory(ecs.size(), name.getText().toString(), description.getText().toString(), R.drawable.ic_money);
        ecs.add(ec);
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
