package com.jahid.recyclerview.Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jahid.recyclerview.Activity.DetailsActivity;
import com.jahid.recyclerview.Model.ListItem;
import com.jahid.recyclerview.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItems ;

    public MyAdapter(Context context, List<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        ListItem item = listItems.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.rating.setText(item.getRating());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView description;
        public TextView rating;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            rating = (TextView) itemView.findViewById(R.id.rating);
        }

        @Override
        public void onClick(View v) {

            //Get position of the row clicked or tapped
            int position = getAdapterPosition();

            ListItem item = listItems.get(position);
            Intent intent = new Intent(context, DetailsActivity.class);

            intent.putExtra("title", item.getTitle());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("rating", item.getRating());

            context.startActivity(intent);
            Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();

        }
    }
}
