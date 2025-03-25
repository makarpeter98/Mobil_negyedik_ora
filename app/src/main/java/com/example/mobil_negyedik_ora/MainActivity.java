package com.example.mobil_negyedik_ora;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    // Óra teszt!

    TextView itemsList;
    private static final String STATE_KEY_ISLISTEMPTY = "hu.unideb.inf.mobil.islistempty";
    private static final String STATE_KEY_ITEMS_LIST_TEXTVIEW = "hu.unideb.inf.mobil.itemslisttextview";
    private boolean isListEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       itemsList = findViewById(R.id.itemsList);
       if(savedInstanceState != null)
       {
           isListEmpty = savedInstanceState.getBoolean(STATE_KEY_ISLISTEMPTY);
           itemsList.setText(savedInstanceState.getString(STATE_KEY_ITEMS_LIST_TEXTVIEW));
       }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_KEY_ISLISTEMPTY, isListEmpty);
        outState.putString(STATE_KEY_ITEMS_LIST_TEXTVIEW, itemsList.getText().toString());
    }
    private ActivityResultLauncher <Intent> activityResultSearch =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.google.com/search?q=" + result.getData().getStringExtra(ItemsActivity.ITEM_KEY)));
                            startActivity(intent);

                        }
                    }
            );

    private ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            if (itemsList.getText().toString().equals(getString(R.string.empty_list)))
                                itemsList.setText("");
                            itemsList.append(result.getData().getStringExtra(ItemsActivity.ITEM_KEY) + "\n");
                        }
                    }
            );
    public void addButtonClicked(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        //startActivity(intent);
        activityResultLauncher.launch(intent);
    }

    public void searchButtonClicked(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        activityResultSearch.launch(intent);
    }
}