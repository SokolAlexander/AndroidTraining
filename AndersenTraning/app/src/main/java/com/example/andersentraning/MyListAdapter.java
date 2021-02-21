package com.example.andersentraning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ViewHolder(View view, final OnItemClickListener listener) {
            super(view);

            textView = view.findViewById(R.id.list_item_text);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void setContent(String text, int position) {
            int number = position + 1;
            textView.setText(number + ": " + text);
        }
    }

    private OnItemClickListener listener;
    private List<ListItem> items;

    MyListAdapter(OnItemClickListener listener, List<ListItem> items) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setContent(items.get(position).text, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
