package com.uid.team5.project.shared;

import android.app.AlertDialog;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.WelcomeActivity;
import com.uid.team5.project.add_expenses.ManualAdditionActivity;
import com.uid.team5.project.auth.LoginActivity;
import com.uid.team5.project.bottom_nav_fragments.AssistantFragment;
import com.uid.team5.project.bottom_nav_fragments.OverviewFragment;
import com.uid.team5.project.bottom_nav_fragments.WishlistFragment;
import com.uid.team5.project.helpers.BottomNavigationViewHelper;
import com.uid.team5.project.models.User;
import com.uid.team5.project.shared.drawer_fragments.expense_categories.ExpenseCategoriesAddCategoryFragment;
import com.uid.team5.project.shared.drawer_fragments.expense_categories.ExpenseCategoriesFragment;
import com.uid.team5.project.shared.drawer_fragments.family_group.FamilyGroupAddMemberFragment.OnFragmentInteractionListener;
import com.uid.team5.project.shared.drawer_fragments.family_group.FamilyGroupFragment;
import com.uid.team5.project.shared.drawer_fragments.recurring_payments.RecurringPaymentsAddPaymentFragment;
import com.uid.team5.project.shared.drawer_fragments.recurring_payments.RecurringPaymentsFragment;
import com.uid.team5.project.transactions.TransactionsFragment;

import org.w3c.dom.Text;

import java.util.List;

import wishlist.AddNewGoalFragment;
import wishlist.Goal;
import wishlist.GoalService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TransactionsFragment.OnFragmentInteractionListener,
        OverviewFragment.OnFragmentInteractionListener,WishlistFragment.OnFragmentInteractionListener,
        AssistantFragment.OnFragmentInteractionListener,FamilyGroupFragment.OnFragmentInteractionListener ,
        AddNewGoalFragment.OnFragmentInteractionListener, OnFragmentInteractionListener,
        RecurringPaymentsFragment.OnFragmentInteractionListener,
        RecurringPaymentsAddPaymentFragment.OnFragmentInteractionListener,
        ExpenseCategoriesAddCategoryFragment.OnFragmentInteractionListener,
        ExpenseCategoriesFragment.OnFragmentInteractionListener{

    Toolbar mToolbar;
    private AppDataSingleton dataService;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Changed Bottom Page");

            final AlertDialog dialog = builder.create();
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_transaction:
                   selectedFragment = TransactionsFragment.newInstance();
                    mToolbar.setTitle("Transactions");
                   break;
                case R.id.navigation_overview:
                    selectedFragment = OverviewFragment.newInstance();
                    mToolbar.setTitle("Overview");

                    break;
                case R.id.navigation_wishlist:
                    selectedFragment = WishlistFragment.newInstance();
                    GoalService goalService=new GoalService();
                    List<Goal> goals=goalService.getGoals();
                    ((WishlistFragment) selectedFragment).setGoals(goals);
                    mToolbar.setTitle("Wishlist");

                    break;
                case R.id.navigation_assistant:
                    mToolbar.setTitle("Assistant");

                    selectedFragment= AssistantFragment.newInstance();
                    break;
                case R.id.navigation_add:
                    final AlertDialog popup = builder.create();
                    View addExpensePopup = (View) getLayoutInflater().inflate(R.layout.popup_add_expense, null);
                    Button scanReceipt = (Button) addExpensePopup.findViewById(R.id.scan_receipt);
                    Button scanBarcode = (Button) addExpensePopup.findViewById(R.id.scan_barcode);
                    Button manuallyInsert = (Button) addExpensePopup.findViewById(R.id.manual_insertion);
                    Button cancelButton = (Button) addExpensePopup.findViewById(R.id.cancel_adding_expense);



                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popup.dismiss();
                        }
                    });

                    manuallyInsert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popup.dismiss();
                            Intent intent = new Intent(MainActivity.this, ManualAdditionActivity.class);
                            startActivity(intent);
                        }
                    });

                    popup.setTitle(null);
                    popup.setView(addExpensePopup);
                    popup.show();
                    return true;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataService = AppDataSingleton.getInstance();

        //hamburger menu
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Transactions");
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        User currentUser = dataService.getCurrentUser();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                View headerView =  drawerView.findViewById(R.id.nav_drawer_head);
                TextView userName =  headerView.findViewById(R.id.drawer_user_name);
                TextView userEmail = headerView.findViewById(R.id.drawer_user_email);
                userEmail.setText(dataService.getCurrentUser().getEmail());
                userName.setText(dataService.getCurrentUser().getName());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
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
        else if(id == R.id.menu_log_out)
        {
            dataService.setCurrentUserId(null);
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if(id == R.id.menu_reset_app)
        {
            startActivity(new Intent(this, WelcomeActivity.class));
            AppDataSingleton.setInstance(null);
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
            selectedFragment = RecurringPaymentsFragment.newInstance();
            bottomNavigation.setVisibility(View.GONE);
        } else if (id == R.id.nav_categories) {
            selectedFragment = ExpenseCategoriesFragment.newInstance();
            bottomNavigation.setVisibility(View.GONE);
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

    @Override
    protected void onPause() {
        AppDataSingleton.saveToFile(getApplicationContext());
        super.onPause();
    }

    @Override
    protected void onStop() {
        AppDataSingleton.saveToFile(getApplicationContext());
        super.onStop();
    }
}
