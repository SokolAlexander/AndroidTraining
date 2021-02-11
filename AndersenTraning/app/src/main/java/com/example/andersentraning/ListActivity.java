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

public class ListActivity extends AppCompatActivity implements OnItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String additionalText;
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("header", "==========");
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MyListAdapter(this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void onItemClick(View v) {
        TextView textView = (TextView) v;
        Log.d("clicked", textView.getText().toString());

        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        Fragment prev = this.getSupportFragmentManager().findFragmentByTag(MyDialogFragment.TAG);

        if (prev != null) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);
        DialogFragment newFragment = new MyDialogFragment(textView.getText().toString());
        newFragment.show(ft, MyDialogFragment.TAG);
        return;
    }
}
