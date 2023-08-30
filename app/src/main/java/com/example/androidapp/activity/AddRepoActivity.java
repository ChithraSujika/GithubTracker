package com.example.androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapp.R;
import com.example.androidapp.model_class.Repository;

import java.util.List;

public class AddRepoActivity extends AppCompatActivity {
   private String TAG = AddRepoActivity.class.getSimpleName();
    private EditText ownerEditText;
    private EditText repoNameEditText;
    private Button addButton;
    List<Repository> repositoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repo);

        ownerEditText = findViewById(R.id.ownerEditText);
        repoNameEditText = findViewById(R.id.repoNameEditText);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch owner and repo name from EditText fields
                String owner = ownerEditText.getText().toString();
                Log.d(TAG,"owner1"+owner);
                String repoName = repoNameEditText.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("owner", owner);
                Log.d(TAG,"owner2"+owner);
                resultIntent.putExtra("repoName", repoName);
                setResult(RESULT_OK, resultIntent);
                finish();

                // Fetch repository data from GitHub API
                // ...

                // Add repository data to local storage
                // ...

                // Update repository list in MainActivity
                // ...
            }
        });
    }
}
