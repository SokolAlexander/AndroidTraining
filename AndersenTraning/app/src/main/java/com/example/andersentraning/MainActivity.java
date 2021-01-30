package com.example.andersentraning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
        this.button = findViewById(R.id.button2);

        button.setOnClickListener(this);
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

    private void showToast() {
        Toast toast = Toast.makeText(getApplicationContext(),
                textView.getText(), Toast.LENGTH_LONG);
        toast.show();
    }

    public void onClick(View view) {
        showToast();
    }
}
