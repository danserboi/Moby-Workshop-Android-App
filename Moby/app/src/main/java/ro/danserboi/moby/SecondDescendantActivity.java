package ro.danserboi.moby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Implements a basic RecyclerView that displays a list of generated words.
 * - Clicking an item marks it as clicked.
 * - Clicking the fab button adds a new word to the list.
 */
public class SecondDescendantActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyListAdapter mAdapter;
    private static final LinkedList<String> ITEMS = new LinkedList<>(
            Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_descendant);
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new MyListAdapter(this, ITEMS);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goToFirstDesc(View view) {
        Intent intent = new Intent(this, FirstDescendantActivity.class);
        startActivity(intent);
    }

    public void click(View view) {
        Toast.makeText(this, "Click!", Toast.LENGTH_LONG).show();
    }
}