package com.example.anass.numbertrivia;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RandomNumberAdapter extends RecyclerView.Adapter<RandomNumberViewHolder> {

    private Context context;
    public List<RandomNumberFact> listRandomNumberFacts;

    public RandomNumberAdapter(Context context, List<RandomNumberFact> listRandomNumberFacts) {
        this.context = context;
        this.listRandomNumberFacts = listRandomNumberFacts;
    }

    @NonNull
    @Override
    public RandomNumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_grid,viewGroup,false);
        return new RandomNumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomNumberViewHolder randomNumberViewHolder, int position) {

        System.out.println(position);
        final RandomNumberFact randomNumberFact = listRandomNumberFacts.get(position);
        randomNumberViewHolder.randomNumber.setText(randomNumberFact.getRandomNumber().toString());
        randomNumberViewHolder.descriptiveText.setText(randomNumberFact.getDescriptiveText().toString());


    }

    @Override
    public int getItemCount() {
        return listRandomNumberFacts.size();
    }

    public void swapList (List<RandomNumberFact> newList) {
        listRandomNumberFacts = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
