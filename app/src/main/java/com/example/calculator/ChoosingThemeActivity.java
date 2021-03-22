package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class ChoosingThemeActivity extends AppCompatActivity {

    private static final String NAME_SHARED_PREFERENCE = "MAIN";
    private static final String APP_THEME = "APP_THEME";

    protected static final int RedStyle = 0;
    protected static final int MarineStyle = 1;
    protected static final int MidnightStyle = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(getAppTheme(R.style.RedStyle));
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choosing_theme);

        initThemeChooser();

        Button buttonReturn = findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(APP_THEME, getCodeStyle(R.style.RedStyle));
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

    protected int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME, codeStyle);
    }

    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }

    protected static int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case MidnightStyle:
                return R.style.MidnightStyle;
            case MarineStyle:
                return R.style.MarineStyle;
            default:
                return R.style.RedStyle;
        }
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonRedStyle),
                RedStyle);
        initRadioButton(findViewById(R.id.radioButtonMarineStyle),
                MarineStyle);
        initRadioButton(findViewById(R.id.radioButtonMidnightStyle),
                MidnightStyle);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton) rg.getChildAt(getCodeStyle(RedStyle))).setChecked(true);
    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(v -> {
            setAppTheme(codeStyle);
            recreate();
        });
    }
}
