package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.clickMeButton);
        EditText fieldNumerito = findViewById(R.id.ponNumerito);

        Random random = new Random();
        int randomNumber = random.nextInt(100);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numerito = fieldNumerito.getText().toString();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;
                if (numerito.equals("")) {
                    text = "Introduce un número!";
                } else {
                    if (Integer.parseInt(numerito) == randomNumber) {
                        text = "Has acertado!";
                    } else if (Integer.parseInt(numerito) > randomNumber) {
                        text = "Te has pasado!";
                    } else if (Integer.parseInt(numerito) < randomNumber) {
                        text = "El número tiene que ser más grande!";
                    }
                }
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });

    }
}