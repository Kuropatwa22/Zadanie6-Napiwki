package com.example.kalkulator_napiwku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

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
    Group groupResult;

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
        groupResult = findViewById(R.id.groupResult);

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

        btnCalculate.setOnClickListener(v -> calculate());
        btnClear.setOnClickListener(v -> clear());
    }

    private void calculate() {

        String amountText = etAmount.getText().toString().trim();
        String peopleText = etPeople.getText().toString().trim();

        if (amountText.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_amount,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (peopleText.isEmpty()) {
            Toast.makeText(this, R.string.error_people,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double amount;
        int people;

        try {
            amount = Double.parseDouble(
                    amountText.replace(',', '.'));
            people = Integer.parseInt(peopleText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.error_amount,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (people <= 0) {
            Toast.makeText(this, R.string.error_people,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double total = amount
                + amount * sbPercent.getProgress() / 100.0;

        if (cbRound.isChecked()) {
            total = Math.ceil(total);
        }

        double perPerson = total / people;

        tvResult.setText(
                getString(R.string.result_format, total));

        tvPerPerson.setText(
                getString(R.string.person_format, perPerson));

        groupResult.setVisibility(View.VISIBLE);
    }

    private void clear() {

        etAmount.setText("");
        etPeople.setText(R.string.start_people);
        sbPercent.setProgress(10);
        cbRound.setChecked(false);
        groupResult.setVisibility(View.GONE);
    }
}
