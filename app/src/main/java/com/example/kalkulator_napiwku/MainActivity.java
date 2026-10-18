package com.example.kalkulator_napiwku;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    EditText etPeople;
    SeekBar sbPercent;
    CheckBox cbRound;
    TextView tvPercentValue;
    TextView tvResult;
    TextView tvPerPerson;
    Button btnCalculate;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etPeople = findViewById(R.id.etPeople);
        sbPercent = findViewById(R.id.sbPercent);
        cbRound = findViewById(R.id.cbRound);
        tvPercentValue = findViewById(R.id.tvPercentValue);
        tvResult = findViewById(R.id.tvResult);
        tvPerPerson = findViewById(R.id.tvPerPerson);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);

        sbPercent.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {

                        tvPercentValue.setText(
                                getString(R.string.percent_format, progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });

    }
}
