package com.example.mobil_negyedik_ora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemsActivity extends AppCompatActivity {

    //public static String ITEM_KEY = "hu.unideb.inf.android.item_key.default";
    public static String ITEM_KEY = "ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
    }

    public void addItem(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(ITEM_KEY, ((Button)view).getText());
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
