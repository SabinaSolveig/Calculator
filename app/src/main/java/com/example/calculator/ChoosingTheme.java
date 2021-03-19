package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

abstract class ChoosingThemeActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "MAIN";

    private static final String AppTheme = "APP_THEME";
    protected static final int RedStyle = 0;
    protected static final int MarineStyle = 1;
    protected static final int MidnightStyle = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.RedStyle));
        setContentView(R.layout.activity_choosing_theme);
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case MidnightStyle:
                return R.style.MidnightStyle;
            case MarineStyle:
                return R.style.MarineStyle;
            default:
                return R.style.RedStyle;
        }
    }
}
