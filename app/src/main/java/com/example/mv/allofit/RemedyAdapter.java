package com.example.mv.allofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RemedyAdapter extends RecyclerView.Adapter<RemedyAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<remedy> remedyList;
    private List<remedy> filteredremedyList;
    private RemedyAdapterListener listener;

    public RemedyAdapter(Context context, List<remedy> remedyList,RemedyAdapterListener listener){
        this.context=context;
        this.remedyList=remedyList;
        this.listener=listener;
        this.filteredremedyList=remedyList;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnRemedySelected(filteredremedyList.get(getAdapterPosition()));
                }
            });
        }
    }

    @NonNull
    @Override
    public RemedyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemedyAdapter.ViewHolder holder, int position) {
            final remedy Remedy=filteredremedyList.get(position);
            holder.name.setText(Remedy.getName());
    }

    @Override
    public int getItemCount() {
        return filteredremedyList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredremedyList = remedyList;
                } else {
                    List<remedy> filteredList = new ArrayList<>();
                    for (remedy row : remedyList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filteredremedyList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredremedyList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filteredremedyList=(ArrayList<remedy>)filterResults.values;
                    notifyDataSetChanged();
            }
        };
    }

    public interface RemedyAdapterListener{
        void OnRemedySelected(remedy Remedy);
    }
}
