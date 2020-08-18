package ro.danserboi.moby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstDescendantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_descendant);
    }

    public void goToSiblingActivity(View view) {
        Intent intent = new Intent(this, SecondDescendantActivity.class);
        startActivity(intent);
    }
}