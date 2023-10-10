package com.example.myapplication;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static Random random = new Random();
    private static int randomNumber;
    private int intentos = 0;
    private static ArrayList<Record> userRecords = new ArrayList<Record>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.clickMeButton);
        EditText fieldNumerito = findViewById(R.id.ponNumerito);
        TextView textoIntentos = findViewById(R.id.intentosText);

        randomNumber = random.nextInt(100);

        fieldNumerito.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.ACTION_DOWN) {
                    fieldNumerito.setNextFocusDownId(R.id.ponNumerito);
                    MainActivity.this.fesIntent(fieldNumerito, textoIntentos);
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.fesIntent(fieldNumerito, textoIntentos);
            }
        });
    }
    public void fesIntent(EditText campNumero, TextView txtIntents) {
        String numerito = campNumero.getText().toString();
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
                final EditText input = new EditText(this);
                builder.setView(input);
                builder.setMessage("Has ganado con "+intentos+ " intentos\n" +
                                   "Introduce tu nombre");
                builder.setTitle("Enhorabuena!");
                builder.setNegativeButton("Vuelve a jugar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        intentos = 0;
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Consultar ranking", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Record player = new Record(input.getText().toString(), intentos);
                        userRecords.add(player);
                        Intent intent = new Intent(MainActivity.this, RecordsActivity.class);
                        intent.putParcelableArrayListExtra("records", userRecords);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                campNumero.setText("");
            } else if (Integer.parseInt(numerito) > randomNumber) {
                text = "Te has pasado!";
            } else if (Integer.parseInt(numerito) < randomNumber) {
                text = "El número tiene que ser más grande!";
            }
        }
        txtIntents.setText("Intentos: " + intentos);
        campNumero.setText("");
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }
    static class Record implements Parcelable {
        private String name;
        private int intents;

        public Record (String name, int intents) {
            this.name = name;
            this.intents = intents;
        }

        protected Record(Parcel in) {
            name = in.readString();
            intents = in.readInt();
        }

        public static final Creator<Record> CREATOR = new Creator<Record>() {
            @Override
            public Record createFromParcel(Parcel in) {
                return new Record(in);
            }

            @Override
            public Record[] newArray(int size) {
                return new Record[size];
            }
        };

        public String getName() {
            return this.name;
        }
        public int getIntents() {
            return this.intents;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeInt(intents);
        }

        @Override
        public String toString() {
            return "Record{" +
                    "name='" + name + '\'' +
                    ", intents=" + intents +
                    '}';
        }
    }
}