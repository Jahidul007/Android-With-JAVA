package com.jahid.recyclerview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jahid.recyclerview.Adapter.MyAdapter;
import com.jahid.recyclerview.Model.ListItem;
import com.jahid.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

//        ListItem item1 = new ListItem("Movie1","It's about someone crazy","wow! great!");
//        ListItem item2 = new ListItem("Movie2","It's about someone you","wow!!");
        for (int i = 0; i < 10; i++) {

            ListItem item = new ListItem(
                    "Item" + (i + 1),
                    "Description",
                    "Excellent"
            );

            listItems.add(item);
    }

        adapter = new MyAdapter(this,listItems);
        recyclerView.setAdapter(adapter);
    }
}
