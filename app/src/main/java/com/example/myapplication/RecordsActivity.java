package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.myapplication.MainActivity.Record;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        ArrayList<Record> records = this.getIntent().getParcelableArrayListExtra("records");
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableLayout tbl = (TableLayout) findViewById(R.id.tableView);
        Button goBack = findViewById(R.id.backBtn);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecordsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        TableRow titles = new TableRow(this);
        TextView nameTitle = new TextView(this);
        TextView intentTitle = new TextView(this);
        nameTitle.setText("Nombre");
        intentTitle.setText("Intentos");
        nameTitle.setLayoutParams(params1);
        intentTitle.setLayoutParams(params2);
        titles.addView(nameTitle);
        titles.addView(intentTitle);
        titles.setLayoutParams(params2);
        tbl.addView(titles);
        for (Record r: records) {
            TableRow row = new TableRow(this);
            TextView txt1 = new TextView(this);
            TextView txt2 = new TextView(this);
            txt1.setText(r.getName());
            txt2.setText(String.valueOf(r.getIntents()));
            txt1.setLayoutParams(params1);
            txt2.setLayoutParams(params2);
            row.addView(txt1);
            row.addView(txt2);
            row.setLayoutParams(params2);
            tbl.addView(row);
        }
    }
}