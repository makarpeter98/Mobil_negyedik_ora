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

    ActivityResultLauncher activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult reply) {
                    if (reply.getResultCode() == RESULT_OK) {
                        if (itemsTextView.getText().toString().equals(getString(R.string.no_items)))
                            itemsTextView.setText("");
                        itemsTextView.append(reply.getData().getStringExtra("ITEM") + "\n");
                    }
                }
            }
    );

    TextView itemsTextView;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        itemsTextView = findViewById(R.id.itemsList);
        addButton.setOnClickListener(
                //v -> startActivity(new Intent(this, ItemsActivity.class))
                v -> activityResultLauncher.launch(new Intent(this, ItemsActivity.class))
        );
    }

    /*private static final String STATE_KEY_ISLISTEMPTY = "hu.unideb.inf.mobil.islistempty";
    private static final String STATE_KEY_ITEMS_LIST_TEXTVIEW = "hu.unideb.inf.mobil.itemslisttextview";
    TextView itemList;
	private boolean isListEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = findViewById(R.id.itemsList);

		if(savedInstanceState != null)
		{
			isListEmpty = savedInstanceState.getBoolean(STATE_KEY_ISLISTEMPTY);
			itemList.setText(savedInstanceState.getString(STATE_KEY_ITEMS_LIST_TEXTVIEW));
		}
    }

	@Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_KEY_ISLISTEMPTY,  isListEmpty);
        outState.putString(STATE_KEY_ITEMS_LIST_TEXTVIEW, itemList.getText().toString());
    }

    private ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            if (itemList.getText().toString().equals(getString(R.string.empty_list)))
							{

                                itemList.setText("");
								isListEmpty = false;
							}
                            itemList.append(result.getData().getStringExtra(ItemsActivity.ITEM_KEY) + "\n");
                        }
                    }
            );

    private ActivityResultLauncher<Intent> activityResultLauncherSearch =
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
    public void addButtonClicked(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        //startActivity(intent);
        activityResultLauncher.launch(intent);
    }

    public void searchButtonClicked(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        activityResultLauncherSearch.launch(intent);
    }*/
}