package com.saminthebox.info.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.saminthebox.info.fragment.HomeFragment;
import com.saminthebox.info.fragment.CategoryFragment;
import com.saminthebox.info.fragment.FeatureFragment;
import com.saminthebox.info.fragment.AccountFragment;
import com.saminthebox.info.helper.BottomNavigationViewHelper;
import com.saminthebox.info.R;

public class MainActivity extends AppCompatActivity {

    private Fragment[] fragments;
    private int currentFragment = 0;
    
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

       @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    showFragment(0);
                    return true;

                case R.id.nav_category:
                    showFragment(1);
                    return true;

                case R.id.nav_feature:
                    showFragment(2);
                    return true;
					
				case R.id.nav_account:
                    showFragment(3);
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

		//fragments
        HomeFragment homeFragment = new HomeFragment();
       
        CategoryFragment categoryFragment = new CategoryFragment();
   
        FeatureFragment featureFragment = new FeatureFragment();
		
		AccountFragment accountFragment = new AccountFragment();
       
        fragments = new Fragment[] { homeFragment, categoryFragment, featureFragment, accountFragment };

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFragment)
                .add(R.id.fragment_container, categoryFragment)
				.add(R.id.fragment_container, featureFragment)
                .add(R.id.fragment_container, accountFragment)
                .hide(categoryFragment)
				.hide(featureFragment)
                .hide(accountFragment)
                .show(homeFragment).commit();

		//navigation
        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
		navigation.setItemIconTintList(null);
    
        BottomNavigationViewHelper.removeShiftMode(navigation);
    }

    protected void showFragment(int index)
    {
        if (index != currentFragment)
        {
            currentFragment = index;
        }
    
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    
        for (int i = 0; i < fragments.length; i++)
        {
            fragmentTransaction.hide(fragments[i]);
        }
    
        if (!fragments[index].isAdded())
        {
            fragmentTransaction.add(R.id.fragment_container, fragments[index]);
        }
    
        fragmentTransaction.show(fragments[index]).commit();
    }
}
