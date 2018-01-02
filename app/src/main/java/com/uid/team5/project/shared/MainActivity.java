package com.uid.team5.project.shared;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.uid.team5.project.R;
import com.uid.team5.project.bottom_nav_fragments.OverviewFragment;
import com.uid.team5.project.bottom_nav_fragments.TransactionsFragment;
import com.uid.team5.project.helpers.BottomNavigationViewHelper;
import com.uid.team5.project.shared.drawer_activities.FamilyGroupFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TransactionsFragment.OnFragmentInteractionListener,
        OverviewFragment.OnFragmentInteractionListener, FamilyGroupFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hamburger menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //bottom bottom_navigation

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);

        //Manually displaying the first fragment(transactions) - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, TransactionsFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically(the transactions)
        bottomNavigation.getMenu().getItem(0).setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Changed Bottom Page");

            AlertDialog dialog = builder.create();
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_transaction:
                   selectedFragment = TransactionsFragment.newInstance();
                   break;
                case R.id.navigation_overview:
                    selectedFragment = OverviewFragment.newInstance();
                    break;
                case R.id.navigation_wishlist:
                    builder.setMessage("Need to create a new fragment for Wishlist");
                    builder.create();
                    builder.show();
                    return true;
                case R.id.navigation_assistant:
                    builder.setMessage("Need to create a new fragment for Assistant");
                    builder.create();
                    builder.show();
                    return true;
                case R.id.navigation_add:
                    builder.setMessage("Need to create a new fragment for Add");
                    builder.create();
                    builder.show();
                    return true;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle bottom_navigation view item clicks here.
        int id = item.getItemId();
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);

        Fragment selectedFragment  = null;
        if (id == R.id.nav_home) {
            selectedFragment = TransactionsFragment.newInstance();
            bottomNavigation.setVisibility(View.VISIBLE);
        }
        else if (id == R.id.nav_family_group) {
            selectedFragment = FamilyGroupFragment.newInstance();
            bottomNavigation.setVisibility(View.GONE);
        } else if (id == R.id.nav_rec_payments) {

        } else if (id == R.id.nav_categories) {

        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, selectedFragment);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //for comunicating between fragments(if necessary)
    }
}
