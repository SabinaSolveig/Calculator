package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorLogics calculator;
    private TextView text;
    private TextView operation;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numberIds = new int []{
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9
        };

        int[] actionsIds = new int []{
                R.id.buttonAdd,
                R.id.buttonSub,
                R.id.buttonMul,
                R.id.buttonDiv,
                R.id.buttonEqual,
                R.id.buttonClear,
                R.id.buttonBackSpace
        };

        text = findViewById(R.id.number);
        operation = findViewById(R.id.operation);

        calculator = new CalculatorLogics();

        OnClickListener numberButtonClickListener = view -> {
            calculator.onNumPressed(view.getId());

            operation.setText(calculator.getOperation());
            text.setText(calculator.getText());
        };

        OnClickListener actionButtonClickListener = view -> {
            calculator.onActionPressed(view.getId());

            operation.setText(calculator.getOperation());
            text.setText(calculator.getText());
        };

        for(int i = 0; i < numberIds.length; i++){
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for(int i = 0; i < actionsIds.length; i++){
            findViewById(actionsIds[i]).setOnClickListener(actionButtonClickListener);
        }
    }
}