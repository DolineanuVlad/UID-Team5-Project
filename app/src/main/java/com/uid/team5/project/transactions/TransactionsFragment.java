package com.uid.team5.project.transactions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.TransactionsAdapter;
import com.uid.team5.project.models.Expense;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionsFragment extends Fragment {

    private AppDataSingleton dataService;
    TransactionsAdapter transactionsAdapter;
    private OnFragmentInteractionListener mListener;
    ListView mTransactionsListView;

    public TransactionsFragment() {
        dataService = AppDataSingleton.getInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionsFragment.
     */
    public static TransactionsFragment newInstance() {
        TransactionsFragment fragment = new TransactionsFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.transactions_tab_layout);
        mTransactionsListView = (ListView) rootView.findViewById(R.id.transactions_list_view);

        transactionsAdapter = new TransactionsAdapter(this.getContext(), dataService.getExpensesByCurrentUser(), dataService.getExpenseCategories());
        mTransactionsListView.setAdapter(transactionsAdapter);

        mTransactionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataService.setCurrentlyEdittedTransaction(dataService.getExpenses().get(i));
                startActivityForResult(new Intent(TransactionsFragment.this.getContext(), EditTransactionActivity.class), 1);
            }
        });

        tabLayout.addOnTabSelectedListener(new MyOnTabSelectedListener());
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        TransactionsAdapter transactionsAdapter = (TransactionsAdapter) mTransactionsListView.getAdapter();
        transactionsAdapter.notifyDataSetChanged();
    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class MyOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getPosition() == 0) {

                ArrayList<Expense> usersExpenses  = dataService.getExpensesByCurrentUser();

                TransactionsFragment.this.transactionsAdapter.setmExpenses(usersExpenses);
                TransactionsFragment.this.transactionsAdapter.notifyDataSetChanged();

            } else {
                ArrayList<Expense> allExpenses  = dataService.getExpenses();

                TransactionsFragment.this.transactionsAdapter.setmExpenses(allExpenses);
                TransactionsFragment.this.transactionsAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }

    @Override
    public void onResume() {
        TransactionsAdapter ta = (TransactionsAdapter) mTransactionsListView.getAdapter();
        ta.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onStart() {
        TransactionsAdapter ta = (TransactionsAdapter) mTransactionsListView.getAdapter();
        ta.notifyDataSetChanged();
        super.onStart();
    }
}
