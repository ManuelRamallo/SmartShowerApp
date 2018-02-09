package com.mramallo.pruebagradiente.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mramallo.pruebagradiente.Constant.PreferencesKeys;
import com.mramallo.pruebagradiente.R;
import com.mramallo.pruebagradiente.fragments.HistorialFragment;
import com.mramallo.pruebagradiente.fragments.LogrosFragment;
import com.mramallo.pruebagradiente.fragments.ResumenFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tvUserName, tvUserEmail;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = HomeActivity.this.getSharedPreferences("datos", Context.MODE_PRIVATE);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        getFragmentManager().beginTransaction().add(R.id.contenedor,new ResumenFragment()).commit();



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
        getMenuInflater().inflate(R.menu.home, menu);
        tvUserName = findViewById(R.id.textViewUserNameHome);
        tvUserEmail = findViewById(R.id.textViewUserEmailHome);


        String nombre = prefs.getString(PreferencesKeys.USER_NAME, "Sin nombre");
        String apellidos = prefs.getString(PreferencesKeys.USER_SURNAME, "Sin apellidos");
        String email = prefs.getString(PreferencesKeys.USER_EMAIL, "Sin email");

        tvUserName.setText(nombre + " " +apellidos);
        tvUserEmail.setText(email);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment f = null;

        if (id == R.id.nav_camera) {
            f = new ResumenFragment();
        } else if (id == R.id.nav_gallery) {
            f = new HistorialFragment();
        } else if (id == R.id.nav_slideshow) {
            f = new LogrosFragment();
        } else if (id == R.id.nav_manage) {
                    Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                    startActivity(intent);
        } else if (id == R.id.nav_cerrarSesion) {
            //Se elimina el Token de usuario de las peferencias
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PreferencesKeys.USER_TOKEN, null);

            Intent cerrarSesion = new Intent(HomeActivity.this, LoginRegisterActivity.class);
            startActivity(cerrarSesion);
            finish();
        }

        if(f!=null) {
            getFragmentManager().beginTransaction().replace(R.id.contenedor,f).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
