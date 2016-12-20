package org.weitblicker.weitblickapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProjectListFragment.OnProjectSelectListener, NewsListFragment.OnNewsArticleSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        FontAwesomeDrawable.FontAwesomeDrawableBuilder builder
                = new FontAwesomeDrawable.FontAwesomeDrawableBuilder(getBaseContext());
        builder.setColor(R.color.wb_darkgrey);
        builder.setSize(22);

        // news icon
        MenuItem newsItem = menu.findItem(R.id.nav_news);
        newsItem.setIcon(builder.build(R.string.fa_newspaper_o));

        // projects icon
        MenuItem projectsItem = menu.findItem(R.id.nav_projects);
        projectsItem.setIcon(builder.build(R.string.fa_globe));

        // bicycle icon
        MenuItem bicycleItem = menu.findItem(R.id.nav_bicycle);
        bicycleItem.setIcon(builder.build(R.string.fa_bicycle));

        // bicycle icon
        MenuItem campaignItem = menu.findItem(R.id.nav_campaign);
        campaignItem.setIcon(builder.build(R.string.fa_rocket));
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
        getMenuInflater().inflate(R.menu.menu, menu);
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

    String currentTitle = null;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = new Fragment();
        String title = getString(R.string.app_name);


        if (id == R.id.nav_news){
            title = "News";
            fragment = new NewsListFragment();
        }else if (id == R.id.nav_projects) {
            title = "Projekte";
            fragment = new ProjectListFragment();

        } else if (id == R.id.nav_bicycle) {
            title = "Radeln";
            fragment = new MapsFragment();
        } else if (id == R.id.nav_campaign) {
            title  = "Aktionen";

        } else if (id == R.id.nav_share) {
            title  = "Share";

        } else if (id == R.id.nav_send) {
            title  = "Send";

        }

        loadFragment(title, fragment);
        return true;
    }

    int cnt = 0;

    public void loadFragment(String title, Fragment fragment){
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_menu, fragment);

            Log.i("debug", "loadFragment: " + title + " cnt: " + cnt + " fragment:" + fragment.toString());
            ft.replace(R.id.content_menu, fragment);
            ft.addToBackStack(cnt++ + "cnt");
            ft.commit();

            if(!title.equals(currentTitle)){
                currentTitle = title;
               // ft.commit();

            }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }


    @Override
    public void onProjectSelect(Project project) {
        // TODO load project fragment

        Fragment fragment = ProjectFragment.newInstance(project);

        loadFragment(project.getName(), fragment);

    }


    @Override
    public void onNewArticleSelect(NewsArticle newsArticle) {

        Fragment fragment = NewsArticleFragment.newInstance(newsArticle);

        loadFragment("News", fragment);

    }
}
