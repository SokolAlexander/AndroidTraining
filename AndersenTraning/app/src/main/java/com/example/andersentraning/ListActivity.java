package com.example.andersentraning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements OnItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String additionalText;
    private TextView header;
    private final int listLength = 10;
    private List<ListItem> items = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        createList();

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MyListAdapter(this, items);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void createList() {
        for (int i = 0; i < listLength; i++) {
            items.add(new ListItem("SomeText", i));
        }
    }

    public void onItemClick(int position) {
        ListItem item = items.get(position);

        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        Fragment prev = this.getSupportFragmentManager().findFragmentByTag(MyDialogFragment.TAG);

        if (prev != null) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);
        DialogFragment newFragment = new MyDialogFragment(item.text + ", " + item.number);
        newFragment.show(ft, MyDialogFragment.TAG);
    }
}
