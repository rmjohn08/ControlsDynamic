package net.rmj.android.controlsdinamic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private DrawerLayout mDrawyerLayout;
    private String[] mPlanetTitles;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = getSupportActionBar().getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.operating_systems);
        Log.i("PlanetSizes", "Planet array:"+mPlanetTitles.length);

        mDrawyerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawyerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view){
                getSupportActionBar().setTitle(title);

            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Open Drawer");
            }
        };

        mDrawyerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);



    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id )
        {
            selectItem(position);

        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstance) {

        super.onPostCreate(savedInstance);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);


    }

    /* original code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /* int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        */
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(this, "Action refresh selected", Toast.LENGTH_SHORT)
                        .show();

                break;
            case R.id.action_settings:
                Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /* swaps fragments in the main content view */
    private void selectItem(int position)
    {
        Bundle args = new Bundle();

        Fragment fragment = null;

        if (position == 0) {
            //args.putString(OperatingSystemFragment.ARG_FRAG, OperatingSystemFragment.STR_TEXT);
            fragment = setQuakesListFragment();

        } else {
            //args.putString(OperatingSystemFragment.ARG_FRAG, OperatingSystemFragment.ARG_FRAG);
            fragment = setOperatingSystemFragment(position);
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

        mDrawerList.setItemChecked(position, true);
        getActionBar().setTitle((mPlanetTitles[position]));
        title = mPlanetTitles[position];

        mDrawyerLayout.closeDrawer(mDrawerList);

    }

    private Fragment setOperatingSystemFragment(int position)
    {

        Fragment fragment = new OperatingSystemFragment();
        Bundle args = new Bundle();

        args.putString(OperatingSystemFragment.ARG_OS, mPlanetTitles[position]);

        fragment.setArguments(args);

        return fragment;
    }

    private Fragment setQuakesListFragment()
    {
        Fragment fragment = new QuakesListFragment();
        Bundle args = new Bundle();

        args.putString(OperatingSystemFragment.ARG_FRAG, OperatingSystemFragment.STR_QUAKES);
        fragment.setArguments(args);

        return fragment;

    }


}
