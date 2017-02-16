package com.ben.muchab.materialdesign40;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class NavigationDrawerFragment extends Fragment {


    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mdrawerLayout;

    //Indicates whether the user is aware of the drawer or not
    private boolean mUserLearnedDrawer;

    //Indicates whether frag is being started for the first time or its comming back from a rotation
    private boolean mFromSavedInstanceState;


    public static final String PREF_FILE_NAME = "textpref";

    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_frawer";

    private View containerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));

        if(savedInstanceState == null)
        {
            //
            mFromSavedInstanceState=true;
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolBar)
    {

        containerView = getActivity().findViewById(fragmentId);

        mdrawerLayout = drawerLayout;

        mdrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolBar,R.string.drawer_open,R.string.drawer_closed)
        {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUserLearnedDrawer)
                {
                    //
                    mUserLearnedDrawer = true;

                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }

                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                if (slideOffset<0.5)
                {
                    toolBar.setAlpha(1 - slideOffset);
                }

                if (slideOffset==1)
                {
                    Toast.makeText(getContext(), "Nav bar is Open",Toast.LENGTH_SHORT).show();
                }

                if (slideOffset==0)
                {
                    Toast.makeText(getContext(), "Nav bar is Closed",Toast.LENGTH_SHORT).show();
                }

            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState)
        {
            //
            mdrawerLayout.openDrawer(containerView);
        }

        mdrawerLayout.setDrawerListener(mdrawerToggle);

        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {

                mdrawerToggle.syncState();

            }
        });

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue)
    {
        //
        SharedPreferences preffs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = preffs.edit();

        edit.putString(preferenceName,preferenceValue);

        edit.apply();

    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue)
    {
        //
        SharedPreferences preffs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        return preffs.getString(preferenceName, defaultValue);

    }

}
