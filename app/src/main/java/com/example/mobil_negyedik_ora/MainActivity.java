package com.example.mobil_negyedik_ora;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
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
                            if (itemsTextView.getText().toString().equals(getString(R.string.empty_list)))
                                itemsTextView.setText("");
                            itemsTextView.append(result.getData().getStringExtra(ItemsActivity.ITEM_KEY) + "\n");
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