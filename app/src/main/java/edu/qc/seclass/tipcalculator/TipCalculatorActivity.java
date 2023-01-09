package edu.qc.seclass.tipcalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TipCalculatorActivity extends AppCompatActivity {
    double Amount;
    int Size;

    //declaration of edit texts/identifiers.
    EditText checkAmount;
    EditText partySize;
    Button Computebutton;

    EditText Tip15Value;
    EditText Tip20Value;
    EditText Tip25Value;

    EditText Total15Value;
    EditText Total20Value;
    EditText Total25Value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intents
        checkAmount = findViewById(R.id.checkAmountValue);
        partySize = findViewById(R.id.partySizeValue);

        Computebutton = findViewById(R.id.buttonCompute);
        Computebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();

//check for exception. Toast.
//if check amount and party size is wrong throw toast
                if (checkAmount.getText().toString().length() <= 0 || partySize.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a correct number", Toast.LENGTH_LONG).show();
                }
//if checkamount and party size empty
                if (checkAmount.getText().toString().isEmpty() || partySize.getText().toString().isEmpty()) {
                    Toast.makeText(TipCalculatorActivity.this, "Empty or incorrect value(s)!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //inititalizes all editText Id's to corresponding objects
                    Tip15Value = findViewById(R.id.fifteenPercentTipValue);
                    Tip20Value = findViewById(R.id.twentyPercentTipValue);
                    Tip25Value = findViewById(R.id.twentyfivePercentTipValue);

                    Total15Value = findViewById(R.id.fifteenPercentTotalValue);
                    Total20Value = findViewById(R.id.twentyPercentTotalValue);
                    Total25Value = findViewById(R.id.twentyfivePercentTotalValue);

                    Amount = Double.parseDouble(checkAmount.getText().toString());
                    Size = Integer.parseInt((partySize.getText().toString()));
                }
//calculate the outputs
                double perPerson = Amount / Size;

                int Tip15 = (int) Math.ceil(perPerson * 0.15);
                int Tip20 = (int) Math.ceil(perPerson * 0.20);
                int Tip25 = (int) Math.ceil(perPerson * .25);

                int Total15 = (int) Math.ceil(perPerson + Tip15);
                int Total20 = (int) Math.ceil(perPerson + Tip20);
                int Total25 = (int) Math.ceil(perPerson + Tip25);

                //calls tip
                Tip15Value.setText(String.valueOf(Tip15));
                Tip20Value.setText(String.valueOf(Tip20));
                Tip25Value.setText(String.valueOf(Tip25));

                //calls total
                Total15Value.setText(String.valueOf(Total15));
                Total20Value.setText(String.valueOf(Total20));
                Total25Value.setText(String.valueOf(Total25));
            }
        });
    }
    //closes keyboard.
    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}