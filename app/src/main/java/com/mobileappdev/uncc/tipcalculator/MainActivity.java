package com.mobileappdev.uncc.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Double bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText billValue = findViewById(R.id.bill_value_input);
        final TextView totalCost = findViewById(R.id.total_cost);
        final TextView tipCost = findViewById(R.id.tip_total_cost);
        final RadioGroup tipGroup = findViewById(R.id.tip_radio_group);

        billValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    bill = Double.parseDouble(billValue.getText().toString());
                    totalCost.setText(bill.toString());
                } catch (Exception e) {
                    Log.d("TipCalc", "Value entered cannot be parsed to an double");
                }
            }
        });

        tipGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            double percent;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("TipCalc",checkedId+"");

                if(checkedId == R.id.ten_percent_radio){
                    percent = .10;
                } else if(checkedId == R.id.fifteen_percent_radio){
                    percent = .15;
                } else if(checkedId == R.id.eighteen_percent_radio){
                    percent = .18;
                } else {
                    percent = 1;
                    Log.d("TipCalc", "Tip entered as custom");
                }

                double tipAmount = Math.round((bill * percent) * 100.0) / 100.0;
                tipCost.setText(tipAmount+"");

                double total = Math.round((tipAmount * bill) * 100.0 )/ 100.0;
                totalCost.setText(total+"");
            }
        });

        //Finish the activity when the exit button is clicked...
        Button button = findViewById(R.id.button_exit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
