package com.example.mobil_negyedik_ora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView itemsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       itemsTextView = findViewById(R.id.itemsTextView);
    }

    public void addButtonClicked(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        startActivity(intent);
    }

    public void searchButtonClicked(View view) {
    }
}