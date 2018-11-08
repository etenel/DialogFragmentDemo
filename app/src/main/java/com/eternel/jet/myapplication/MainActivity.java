package com.eternel.jet.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tv_text);
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditDialog editDialog = new EditDialog();
                editDialog.show(getSupportFragmentManager(), "EditDialog");
                editDialog.setOnItemClickListener(new EditDialog.itemClick() {
                    @Override
                    public void onItemClickListener(View view) {
                        switch (view.getId()) {
                            case R.id.tv_published:
                                Toast.makeText(MainActivity.this, editDialog.getInputText(), Toast.LENGTH_SHORT).show();
                                tvText.setText(editDialog.getInputText());
                                editDialog.dismiss();
                                break;
                            case R.id.textView:
                                Toast.makeText(MainActivity.this, "回复", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }
        });
    }
}
