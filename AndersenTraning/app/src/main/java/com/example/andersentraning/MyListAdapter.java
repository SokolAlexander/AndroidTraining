package com.example.andersentraning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.list_item_text);
        }

        public void setContent(String text) {
            textView.setText(text);
        }
    }

    private List<ListItem> items = new ArrayList();
    private int listLength = 10;

    MyListAdapter() {
        createList();
    }

    private void createList() {
        for (int i = 0; i < listLength; i++) {
            items.add(new ListItem("SomeText", i));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setContent(items.get(position).text);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
