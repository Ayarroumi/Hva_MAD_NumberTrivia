package com.example.anass.numbertrivia;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    APIInterface apiInterface;
    public List<RandomNumberFact> mRandomFacts = new ArrayList<>();
    private RandomNumberAdapter mAdapter;
    private RecyclerView mRandomNumberRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mGameRecyclerView = findViewById(R.id.randomNumberList);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        mGameRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RandomNumberAdapter(this,mRandomFacts);
        mGameRecyclerView.setAdapter(mAdapter);


        fab = findViewById(R.id.addRandomNumber);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                requestRandomFact(getRandomNumber());
            }
        });
    }


    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new RandomNumberAdapter(this, mRandomFacts );
            mRandomNumberRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mRandomFacts);
        }
    }

    public int getRandomNumber(){
        Random n = new Random();
        return n.nextInt(1000) + 1;
    }

    private void requestRandomFact(final int randomNumber){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<RandomNumberFact> call = apiInterface.getFact(randomNumber);

        call.enqueue(new Callback<RandomNumberFact>() {
            @Override
            public void onResponse(Call<RandomNumberFact> call, Response<RandomNumberFact> response) {
                RandomNumberFact randomNumberFact = response.body();
                mRandomFacts.add(randomNumberFact);
                updateUI();
            }

            @Override
            public void onFailure(Call<RandomNumberFact> call, Throwable t) {
                Log.d("error",t.toString());
                call.cancel();
            }
        });


    }
}
