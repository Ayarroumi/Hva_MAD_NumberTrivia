package com.example.anass.numbertrivia;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RandomNumberViewHolder extends RecyclerView.ViewHolder {

    public TextView randomNumber;
    public TextView descriptiveText;

    public RandomNumberViewHolder(@NonNull View itemView) {
        super(itemView);
        this.randomNumber = itemView.findViewById(R.id.randomNumber);
        this.descriptiveText = itemView.findViewById(R.id.descriptiveText);
    }
}
