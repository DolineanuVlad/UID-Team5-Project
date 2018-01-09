package com.uid.team5.project.shared.drawer_fragments.expense_categories;

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
import java.util.List;

import com.uid.team5.project.R;
import com.uid.team5.project.adapters.ExpenseCategoryAdapter;
import com.uid.team5.project.models.ExpenseCategory;
import com.uid.team5.project.services.ExpenseCategoryService;
import com.uid.team5.project.shared.drawer_fragments.family_group.FamilyGroupAddMemberFragment;
import com.uid.team5.project.shared.drawer_fragments.recurring_payments.RecurringPaymentsFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpenseCategoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExpenseCategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseCategoriesFragment extends Fragment {

    private ListView mListView;
    private FloatingActionButton mAddCategory;
    private OnFragmentInteractionListener mListener;
    private ExpenseCategoryService expenseCategoryService;
    List<ExpenseCategory> mlist;

    public ExpenseCategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExpenseCategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpenseCategoriesFragment newInstance() {
        ExpenseCategoriesFragment fragment = new ExpenseCategoriesFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mlist=expenseCategoryService.getExpenses();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_expense_categories, container, false);

        mListView = (ListView) rootView.findViewById(R.id.expensesList);

        ExpenseCategoryAdapter adapter = new ExpenseCategoryAdapter(mlist, this.getContext());
        mListView.setAdapter(adapter);

        mAddCategory = (FloatingActionButton)rootView.findViewById(R.id.floatingActionButtonAddExpense);

        mAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddCategoryView(view);
            }
        });


        return rootView;
    }

    private void openAddCategoryView(View view) {

        Fragment addCategoryFragmnet = ExpenseCategoriesAddCategoryFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, addCategoryFragmnet);
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
