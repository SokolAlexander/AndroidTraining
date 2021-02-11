package com.example.andersentraning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andersentraning.FirstFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private TextView textView;
    private Button button;
    private Button showDialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, FirstFragment.class, null)
                    .commit();
        }
        this.editText = findViewById(R.id.editText);
        this.textView = findViewById(R.id.textView);
        this.button = findViewById(R.id.button3);
        this.showDialogBtn = findViewById(R.id.button2);

        button.setOnClickListener(this);
        showDialogBtn.setOnClickListener(this);
        editText.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("textWatcher", s.toString());
            textView.setText(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("textAfter", s.toString());
        }
    };

    private void showToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(),
                text, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Option 3");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                showToast(item.getTitle().toString());
                return true;
            case R.id.help:
                showToast(item.getTitle().toString() + " " + item.getItemId());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(MyDialogFragment.TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = new MyDialogFragment("Message");
        newFragment.show(ft, MyDialogFragment.TAG);
    }

    public void onClick(View view) {
        if (view.getId() == showDialogBtn.getId()) {
            showDialog();
            return;
        }
        // showToast();
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("additionalText", textView.getText().toString());
        startActivity(intent);
    }
}
