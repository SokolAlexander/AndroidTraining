package com.example.andersentraning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
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
        /*Intent intent = getIntent();
        additionalText = getIntent().getStringExtra("additionalText");
        Log.d("header", "---------");
        header = findViewById(R.id.header);
        Log.d("header", "---------1");

        CharSequence text = header.getText();
        Log.d("header", "---------2");

        Log.d("header", additionalText);
        header.setText(additionalText.toString());*/

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MyListAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
