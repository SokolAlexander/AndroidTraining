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

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> implements View.OnClickListener {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private int number;
        private AppCompatActivity activity;

        ViewHolder(View view, View.OnClickListener listener) {
            super(view);

            textView = view.findViewById(R.id.list_item_text);
            textView.setOnClickListener(listener);
            this.activity = activity;
        }

        public void setContent(String text, int position) {
            int number = position + 1;
            textView.setText(number + ": " + text);
            this.number = number;
        }

/*        @Override
        public void onClick(View v) {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            Fragment prev = activity.getSupportFragmentManager().findFragmentByTag(MyDialogFragment.TAG);

            if (prev != null) {
                ft.remove(prev);
            }

            ft.addToBackStack(null);
            DialogFragment newFragment = new MyDialogFragment(textView.getText().toString(), number);
            newFragment.show(ft, MyDialogFragment.TAG);
            return;
        }*/
    }

    private OnItemClickListener listener;
    private List<ListItem> items = new ArrayList();
    final private int listLength = 10;
    // private AppCompatActivity activity;

    MyListAdapter(OnItemClickListener listener) {
        createList();
        this.listener = listener;
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
        // view.setOnClickListener(this);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setContent(items.get(position).text, position);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
