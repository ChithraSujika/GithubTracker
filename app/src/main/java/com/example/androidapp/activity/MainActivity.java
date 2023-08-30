package com.example.androidapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.adapter.RepositoryAdapter;
import com.example.androidapp.model_class.Repository;
import com.example.androidapp.utility.Api;
import com.example.androidapp.utility.AppUtils;
import com.example.androidapp.utility.ListOrganizationRepositoriesInput;
import com.example.androidapp.utility.ListOrganizationRepositoriesOutput;
import com.example.androidapp.utility.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationListener {
    private String TAG = MainActivity.class.getSimpleName();
    private List<Repository> repositories;
    private RecyclerView recyclerView;
    private RepositoryAdapter.OnItemClickListener onItemClickListener;
    private RepositoryAdapter adapter;
    private ImageView imageView;
    Repository repository;
    private NavigationListener navigationListener;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img_add);
        recyclerView = findViewById(R.id.recyclerView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRepoActivity.class);
                startActivityForResult(intent, 19);
            }
        });

        repositories = new ArrayList<>(); // Initialize repository list
        repositories.add(new Repository("Myfavor","happiness occure when you smile"));
        repositories.add(new Repository("Hey","be bore"));
        repositories.add(new Repository("hello","I'm not!"));
        repositories.add(new Repository("Crazy","take a pen"));
        repositories.add(new Repository("hii","ok then"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RepositoryAdapter adapter = new RepositoryAdapter(repositories, onItemClickListener);
        recyclerView.setAdapter(adapter);


        Retrofit retrofit = Api.getRetrofitBuilder(this);
        WebService webServices = retrofit.create(WebService.class);
        ListOrganizationRepositoriesInput userAddNewInput = new ListOrganizationRepositoriesInput(
        ("alcoats"),"hello");

        webServices.listOrganization(userAddNewInput)
                .enqueue(new Callback< ListOrganizationRepositoriesOutput >() {
                    @Override
                    public void onResponse(Call<ListOrganizationRepositoriesOutput> call, Response<ListOrganizationRepositoriesOutput> response) {
                        if (!AppUtils.analyseResponse(response)) {

                            Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_LONG).show();

                            return;
                        }
                        ListOrganizationRepositoriesOutput result = response.body();


                    }

                    @Override
                    public void onFailure(Call <ListOrganizationRepositoriesOutput> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_LONG).show();
                        t.printStackTrace();

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
             /*   String owner = ;
                Log.d(TAG,"owner3"+owner);
                String repoName = ;
                Log.d(TAG,"owner4"+repoName);*/
                repositories.add(new Repository(
                        data.getStringExtra("owner"),
                        data.getStringExtra("repoName")
                ));

                Log.d(TAG,"owner5"+repositories);
                adapter.updateList(repositories);
            }
          /*  if (resultCode != RESULT_OK) {
                adapter.updateList();
            }*/
        }
    }

    @Override
    public void share(int adapterPosition) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_SEND);
    String shareBody = "Here is the share content body";
    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
    intent.setType("text/plain");
    if (intent.resolveActivity(getPackageManager())!=null) {
    startActivity(intent);
    }
    }

  /*  public List<Repository> getListData() {

    }
*/

    // Other methods for adding repos, opening links, sharing, etc.

}