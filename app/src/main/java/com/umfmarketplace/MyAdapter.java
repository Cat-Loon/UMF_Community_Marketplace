/* Authored by: Jared Suave
University of Michigan-Flint
Winter 2019 Capstone Project

 */

package com.umfmarketplace;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<Listing> theListings;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MyAdapter(ArrayList<Listing> p){
        theListings = p;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cardview, viewGroup, false);
        viewHolder = new myViewHolder(view, mListener);
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
        myViewHolder.SellerEmail.setText(theListings.get(i).getSellerEmail());
    }


    @Override
    public int getItemCount() {
        return theListings.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder
    {
        public TextView Author, Book, ClassName, Condition, ISBNasString, PriceasString, SellerEmail;
        public myViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            Author = itemView.findViewById(R.id.TheAuthor);
            Book = itemView.findViewById(R.id.TheBook);
            ClassName = itemView.findViewById(R.id.ClassUsed);
            Condition = itemView.findViewById(R.id.Condition);
            ISBNasString = itemView.findViewById(R.id.ISBNasString);
            PriceasString = itemView.findViewById(R.id.PriceasString);
            SellerEmail = itemView.findViewById(R.id.SellerEmail);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
