package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static Random random = new Random();
    private static int randomNumber;
    private int intentos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.clickMeButton);
        EditText fieldNumerito = findViewById(R.id.ponNumerito);
        TextView textoIntentos = findViewById(R.id.intentosText);

        randomNumber = random.nextInt(100);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numerito = fieldNumerito.getText().toString();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;
                if (numerito.equals("")) {
                    text = "Introduce un número!";
                } else {
                    intentos += 1;
                    if (Integer.parseInt(numerito) == randomNumber) {
                        text = "Has acertado!";
                        randomNumber = random.nextInt(100);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Has ganado con "+intentos+ " intentos");
                        builder.setTitle("Enhorabuena!");
                        builder.setPositiveButton("Empieza de nuevo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        intentos = 0;
                        fieldNumerito.setText("");
                    } else if (Integer.parseInt(numerito) > randomNumber) {
                        text = "Te has pasado!";
                    } else if (Integer.parseInt(numerito) < randomNumber) {
                        text = "El número tiene que ser más grande!";
                    }
                }
                textoIntentos.setText("Intentos: " + intentos);
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });

    }
}