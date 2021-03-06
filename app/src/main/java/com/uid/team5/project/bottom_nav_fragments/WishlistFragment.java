package com.uid.team5.project.bottom_nav_fragments;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uid.team5.project.R;

import java.util.ArrayList;
import java.util.List;

import wishlist.AddNewGoalFragment;
import wishlist.Goal;
import wishlist.GoalAdapter;
import wishlist.GoalService;

/**
 * Created by mara.tatar on 1/5/2018.
 */

public class WishlistFragment extends Fragment implements AddNewGoalFragment.OnFragmentInteractionListener{
    private WishlistFragment.OnFragmentInteractionListener mListener;
    private List<Goal> goals;
    private List<Goal> personalGoals;
    private GoalAdapter goalsAdapter;
    private GoalAdapter personalGoalsAdapter;
    private GoalService goalService;
    private Context context;
    private ListView goalsListView;

    public WishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WishlistFragment.
     */

    public static WishlistFragment newInstance() {
        WishlistFragment fragment = new WishlistFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public void setGoals(List<Goal> goals){
        this.goals=goals;
        personalGoals=new ArrayList<Goal>();
        for (Goal g:goals){
            if (g.isPersonal()){
                personalGoals.add(g);
            }
        }

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personalGoalsAdapter=new GoalAdapter(personalGoals,context);
        goalsAdapter=new GoalAdapter(goals,context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wishlist, container, false);
        goalsListView=view.findViewById(R.id.goalsListView);
        goalsListView.setAdapter(personalGoalsAdapter);
        FloatingActionButton fab=view.findViewById(R.id.addGoalButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewGoalFragment fragment= AddNewGoalFragment.newInstance();
                fragment.setGoals(goals);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame_layout, fragment);
                transaction.commit();
            }
        });
        TabLayout tabLayout =view.findViewById(R.id.wishlist_tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==1){
                    goalsListView.setAdapter(goalsAdapter);
                }else{
                    goalsListView.setAdapter(personalGoalsAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;

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
        this.context=context;
        if (context instanceof WishlistFragment.OnFragmentInteractionListener) {
            mListener = (WishlistFragment.OnFragmentInteractionListener) context;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

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
