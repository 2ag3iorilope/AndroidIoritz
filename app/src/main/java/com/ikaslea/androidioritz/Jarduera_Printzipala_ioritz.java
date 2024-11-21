package com.ikaslea.androidioritz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Jarduera_Printzipala_ioritz extends AppCompatActivity {

    EditText editTextIzena;
    EditText editTextAbizen1;
    EditText editTextAbizen2;
    EditText editTextJaioturtea;
    Button buttonhurrengo;
    Button buttonhustu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_printzipala_ioritz);

        editTextIzena = findViewById(R.id.editTextText);
        editTextAbizen1 = findViewById(R.id.editTextText2);
        editTextAbizen2 = findViewById(R.id.editTextText3);
        editTextJaioturtea = findViewById(R.id.editTextText4);
        buttonhurrengo = findViewById(R.id.button);
        buttonhustu = findViewById(R.id.button2);

        buttonhurrengo.setOnClickListener(view -> {
            String izena = editTextIzena.getText().toString().trim();
            String abizena1 = editTextAbizen1.getText().toString().trim();
            String abizena2 = editTextAbizen2.getText().toString().trim();
            String jaioturteaStr = editTextJaioturtea.getText().toString().trim();

            if (izena.isEmpty() || abizena1.isEmpty() || abizena2.isEmpty() || jaioturteaStr.isEmpty()) {
                Toast.makeText(this, "Mesedez, datu guztiak bete.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int jaioturtea = Integer.parseInt(jaioturteaStr);

                if (jaioturtea <= 1900) {
                    Toast.makeText(this, "Jaioturtea 1900 baino handiago izan behar du.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int adina = kalkulatuAdina(jaioturtea);

                if (adina < 18 || adina > 65) {
                    Toast.makeText(this, "Adinak 18 eta 65 artean egon behar du.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Adin egokia: " + adina + " ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, Jarduera_Bigarrena_Ioritz.class);
                    intent.putExtra("Izena", izena);
                    intent.putExtra("Abizena", abizena1);
                    intent.putExtra("Abizena2", abizena2);
                    startActivity(intent);
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Mesedez sartu urte egoki bat.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonhustu.setOnClickListener(view -> {
            kontrolak_hustu();
            Toast.makeText(this, "Garbituta.", Toast.LENGTH_SHORT).show();
        });
    }

    private void kontrolak_hustu() {
        editTextIzena.setText("");
        editTextAbizen1.setText("");
        editTextAbizen2.setText("");
        editTextJaioturtea.setText("");
        Toast.makeText(this, "Garbituta.", Toast.LENGTH_SHORT).show();
    }

    private int kalkulatuAdina(int jaioturtea) {
        int UrteaOrain = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return UrteaOrain - jaioturtea;
    }
}
