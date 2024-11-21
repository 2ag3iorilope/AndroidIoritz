package com.ikaslea.androidioritz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Jarduera_Bigarrena_Ioritz extends AppCompatActivity {

    RadioGroup radiogroup;
    RadioButton semelabagaberadio, semealababatradio, bibainogehiagoradio;
    EditText editTextNumberDecimal;
    Button Erabakia, hustu, buttonirten;
    TextView Erantzuna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bigarrena_ioritz);
        Intent intent = getIntent();
        String izena = intent.getStringExtra("Izena");
        String abizena = intent.getStringExtra("Abizena");
        String abizena2 = intent.getStringExtra("Abizena2");
        radiogroup = findViewById(R.id.radiogroup);
        semelabagaberadio = findViewById(R.id.radioButton);
        semealababatradio = findViewById(R.id.radioButton2);
        bibainogehiagoradio = findViewById(R.id.radioButton3);
        Erabakia = findViewById(R.id.button3);
        hustu = findViewById(R.id.button4);
        buttonirten = findViewById(R.id.button5);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        Erantzuna = findViewById(R.id.textView5);

        Erabakia.setOnClickListener(v -> {
            try {

                String dirusarreraText = editTextNumberDecimal.getText().toString();
                if (dirusarreraText.isEmpty()) {
                    Toast.makeText(this, "Mesedez, diru-sarrera sartu.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double dirusarrerak = Double.parseDouble(dirusarreraText);
                int semekop = LortuSemeKopurua();

                if (EskeraEbaluatu(semekop, dirusarrerak)) {
                    Erantzuna.setText("Eskaera onartua! Zorionak, " + izena + " " + abizena + " " + abizena2 + ".");
                } else {
                    Erantzuna.setText("Eskaera baztertua. Saiatu berriro.");
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Zenbaki balioduna sartu.", Toast.LENGTH_SHORT).show();
            }
        });
        buttonirten.setOnClickListener(v -> {
            Intent intent2 = new Intent(Jarduera_Bigarrena_Ioritz.this, Jarduera_Printzipala_ioritz.class);
            startActivity(intent2);
            finish();
        });

        hustu.setOnClickListener(view -> {
            kontrolak_hustu();
            Toast.makeText(this, "Garbituta.", Toast.LENGTH_SHORT).show();
        });
    }

    private int LortuSemeKopurua() {
        if (semelabagaberadio.isChecked()) {
            return 1;
        } else if (bibainogehiagoradio.isChecked()) {
            return 2;
        }
        return 0;
    }
    private void kontrolak_hustu() {
        semealababatradio.setChecked(false);
        semelabagaberadio.setChecked(false);
        bibainogehiagoradio.setChecked(false);
        editTextNumberDecimal.setText("");
        Toast.makeText(this, "Garbituta.", Toast.LENGTH_SHORT).show();
    }

    public boolean EskeraEbaluatu(int semekopurua, double dirusarrera) {
        if (dirusarrera < 9000) {
            return true;
        } else if (dirusarrera >= 9001 && dirusarrera <= 20000 && semekopurua >= 1) {
            return true;
        } else if (dirusarrera >= 20001 && dirusarrera <= 30000 && semekopurua > 1) {
            return true;
        }
        return false;
    }
}
