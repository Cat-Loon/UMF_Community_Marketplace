package com.umfmarketplace;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<Listing> theListings;

    public MyAdapter(ArrayList<Listing> p){
        theListings = p;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cardview, viewGroup, false);
        viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final myViewHolder myViewHolder = (myViewHolder) viewHolder;
        myViewHolder.Author.setText(theListings.get(i).getTheAuthor());
        myViewHolder.Book.setText(theListings.get(i).getTheBook());
        myViewHolder.ClassName.setText(theListings.get(i).getClassUsed());
        myViewHolder.Condition.setText(theListings.get(i).getCondition());
        myViewHolder.ISBNasString.setText(theListings.get(i).getISBNasString());
        myViewHolder.PriceasString.setText(theListings.get(i).getPriceasString());
    }


    @Override
    public int getItemCount() {
        return theListings.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder
    {
        public TextView Author, Book, ClassName, Condition, ISBNasString, PriceasString;
        public myViewHolder(View itemView){
            super(itemView);
            Author = itemView.findViewById(R.id.TheAuthor);
            Book = itemView.findViewById(R.id.TheBook);
            ClassName = itemView.findViewById(R.id.ClassUsed);
            Condition = itemView.findViewById(R.id.Condition);
            ISBNasString = itemView.findViewById(R.id.ISBNasString);
            PriceasString = itemView.findViewById(R.id.PriceasString);
        }
    }
}
