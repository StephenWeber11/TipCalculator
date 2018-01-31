package com.mobileappdev.uncc.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Double bill = 0.00;
    boolean otherIsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText billValue = findViewById(R.id.bill_value_input);
        final TextView totalCost = findViewById(R.id.total_cost);
        final TextView tipCost = findViewById(R.id.tip_total_cost);
        final RadioGroup tipGroup = findViewById(R.id.tip_radio_group);
        final SeekBar seekBar = findViewById(R.id.custom_tip_seekbar);
        final TextView seekPercentage = findViewById(R.id.percentage_view);

        billValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TipCalc","Entered onClick method");
                try {
                    bill = Double.parseDouble(billValue.getText().toString());

                    if (bill <= 0.00) {
                        billValue.setError("Enter Bill Total");
                        bill = 0.00;
                    }

                    totalCost.setText(bill.toString());

                } catch (Exception e) {
                    billValue.setError("Enter Bill Total");
                    Log.d("TipCalc", "Value entered cannot be parsed to an double");
                }
            }
        });

        tipGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            double percent;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("TipCalc",checkedId+"");
                Log.d("TipCalc","The bill is: " + bill);
                if(bill != null || bill == -1) {
                    if (checkedId == R.id.ten_percent_radio) {
                        percent = .10;
                        otherIsChecked = false;
                    } else if (checkedId == R.id.fifteen_percent_radio) {
                        percent = .15;
                        otherIsChecked = false;
                    } else if (checkedId == R.id.eighteen_percent_radio) {
                        percent = .18;
                        otherIsChecked = false;
                    } else {
                        percent = 0;
                        otherIsChecked = true;
                        Log.d("TipCalc", "Tip entered as custom");
                    }

                    double tipAmount = 1;
                    if(percent != 0) {
                        tipAmount = Math.round((bill * percent) * 100.0) / 100.0;
                        tipCost.setText(tipAmount + "");

                        double total = Math.round((tipAmount + bill) * 100.0) / 100.0;
                        totalCost.setText(total + "");
                    } else {
                        tipCost.setText(0.0+"");

                        double total = Math.round((tipAmount + bill) * 100.0) / 100.0;
                        totalCost.setText(total + "");
                    }
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(otherIsChecked){
                    double percent = (double) progress / 100;
                    Log.d("TipCalc", "Percent is: " + percent);
                    double tipAmount = Math.round((bill * percent) * 100.0) / 100.0;
                    tipCost.setText(tipAmount + "");

                    double total = Math.round((tipAmount + bill) * 100.0) / 100.0;
                    totalCost.setText(total + "");
                }
                seekPercentage.setText(progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Finish the activity when the exit button is clicked...
        Button button = findViewById(R.id.button_exit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(billValue.getText().toString().length() == 0){
                   billValue.setError("Enter Bill Total");
               } else {
                   finish();
               }
            }
        });

    }
}
