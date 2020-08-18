package ro.danserboi.moby;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioButton firstRB;
    private RadioButton secondRB;
    private RadioButton thirdRB;
    private CheckBox firstCB;
    private CheckBox secondCB;
    private ToggleButton toggleButton;
    private EditText editText;
    private Button button;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Input controls
        firstRB = findViewById(R.id.first_rb);
        secondRB = findViewById(R.id.second_rb);
        thirdRB = findViewById(R.id.third_rb);
        firstCB = findViewById(R.id.first_cb);
        secondCB = findViewById(R.id.second_cb);
        toggleButton = findViewById(R.id.toggleButton);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        // 1. Elemente de Drawer Layout
        // adaugare de App Bar cu Options Menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // instantiere de Drawer Layout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (drawer != null) {
            // afisare Drawer cand se apasa pe hamburger icon
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
        // setare de listener pe Navigation View
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.surprise, Snackbar.LENGTH_LONG).setAction(R.string.dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // do nothing
                    }
                }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        Intent intent = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_first_desc:
                intent = new Intent(this, FirstDescendantActivity.class);
                startActivity(intent);
                return true;
            case R.id.nav_second_desc:
                intent = new Intent(this, SecondDescendantActivity.class);
                startActivity(intent);
                return true;
            case R.id.nav_maps:
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("geo:44.435065,26.047774");
                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Find an activity to handle the intent, and start that activity.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this map intent!");
                }
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_information:
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(MainActivity.this);
                // Set the dialog message.
                myAlertBuilder.setMessage(getString(R.string.dialog_message));
                // Add the dialog buttons.
                myAlertBuilder.setPositiveButton(R.string.ok, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked OK button.
                                Toast.makeText(getApplicationContext(), R.string.pressed_ok,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                myAlertBuilder.setNegativeButton(R.string.cancel, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog.
                                Toast.makeText(getApplicationContext(), R.string.pressed_cancel,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                // Create and show the AlertDialog.
                myAlertBuilder.show();
                // Set the dialog title and message.
                return true;
            case R.id.action_toast:
                displayToast(getString(R.string.dialog_message));
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void inputControlsState(View view) {
        StringBuilder state = new StringBuilder();
        state.append("First RB is ").append(status(firstRB));
        state.append("Second RB is ").append(status(secondRB));
        state.append("Third RB is ").append(status(thirdRB));
        state.append("First CB is ").append(status(firstCB));
        state.append("Second CB is ").append(status(secondCB));
        state.append("Toggle button is ").append(status(toggleButton));
        state.append("Text is ").append(editText.getText());
        Log.v(TAG, state.toString());
    }
    
    public String status(CompoundButton cb) {
        if(cb instanceof RadioButton || cb instanceof CheckBox) {
            if(cb.isChecked()) {
                return "selected; ";
            } else {
                return "unselected; ";
            }
        } else {
            if(cb.isChecked()) {
                return "ON; ";
            } else {
                return "OFF; ";
            }
        }
    }

}