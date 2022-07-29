package com.example.ksumkin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Values values;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_VALUE = "value";
    public static final String INT_FIRST = "IntFirst";
    public static final String OPERATION = "Operation";
    public static final String TOP_HINT = "TopHint";
    public static final String INPUT_HINT = "InputHint";
    SharedPreferences mSettings;

    private enum Values {
        PLUS, MINUS, MULTIPLY, DIVIDE, SQUARE
    }

    private int int1, int2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_VALUE)) {
            TextView textView = (TextView) findViewById(R.id.tv);
            if (textView == null) {
                return;
            }
            textView.setText(mSettings.getString(APP_PREFERENCES_VALUE, ""));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EditText editText = (EditText) findViewById(R.id.editText);

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_VALUE, getString(R.string.SomeValue));
        editor.putInt(INT_FIRST, int1);
        editor.putInt(OPERATION, values.ordinal());
        editor.putString(INPUT_HINT, editText.getHint().toString());
//        editor.putString(INPUT_HINT, );
        editor.apply();
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.plus:
                values = Values.PLUS;
                break;
            case R.id.minus:
                values = Values.MINUS;
                break;
            case R.id.multiply:
                values = Values.MULTIPLY;
                break;
            case R.id.divide:
                values = Values.DIVIDE;
                break;
            case R.id.square:
                values = Values.SQUARE;
                break;

        }
        EditText editText = (EditText) findViewById(R.id.editText);
        TextView textView1 = (TextView) findViewById(R.id.tv);
        if (editText == null) {
            return;
        }
        String s1 = editText.getText().toString().trim();
        if (s1.isEmpty()) {
            return;
        }
        if (int1 == 0) {
            int1 = Integer.parseInt(s1);
            switch (values) {
                case MINUS:
                    editText.setHint(String.valueOf(int1) + getResources().getString(R.string.minus));
                    break;
                case PLUS:
                    editText.setHint(String.valueOf(int1) + getResources().getString(R.string.button));
                    break;
                case MULTIPLY:
                    editText.setHint(String.valueOf(int1) + getResources().getString(R.string.multiply));
                    break;
                case DIVIDE:
                    editText.setHint(String.valueOf(int1) + getResources().getString(R.string.divide));
                    break;
                case SQUARE:
                    OnClickRavno(null);
                    return;

            }
            editText.setText("");
        }
        if (textView1 == null) {
            return;
        }
        textView1.setText(getResources().getText(R.string.helper2));
    }

    public void OnClickRavno(View view) {
        if (int1 == 0) {
            return;
        }
        EditText editText = (EditText) findViewById(R.id.editText);
        if (editText == null) {
            return;
        }
        String s1 = editText.getText().toString().trim();
        if (s1.isEmpty()) {
            return;
        }

        switch (values) {
            case MINUS:
                int2 = int1 - Integer.parseInt(s1);
                break;

            case PLUS:
                int2 = Integer.parseInt(s1) + int1;
                break;

            case MULTIPLY:
                int2 = Integer.parseInt(s1) * int1;
                break;
            case DIVIDE:
                if (Integer.parseInt(s1) != 0) {
                    int2 = int1 / Integer.parseInt(s1);
                } else {
                    editText.setText("");
                    editText.setHint(R.string.zerodivide);
                    setTextView();
                    int2 = 0;
                    int1 = 0;
                    return;
                }
                break;
            case SQUARE:
                int2 = int1 * int1;
                break;
        }


        setTextView();
        editText.setText(String.valueOf(int2));
        int2 = 0;
        int1 = 0;
    }


    private void minus() {
        values = Values.MINUS;
    }


    private void plus() {
        values = Values.PLUS;
    }

    private void setTextView() {
        TextView textView = (TextView) findViewById(R.id.tv);
        if (textView == null) {
            return;
        }
        textView.setText(getResources().getText(R.string.helper3));
    }

}
