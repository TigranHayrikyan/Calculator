package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAC, btnDel, btnPlus, btnMinus, btnDivide, btnMultiple, btnEquals, btnDoth;
    private TextView textViewHistory, textViewResult;
    private String number;
    double firsNumber = 0;
    double lastNumber = 0;
    String status = null;
    boolean operator = false;
    DecimalFormat myFormatter = new DecimalFormat("######.######");
    String history, currentResult;
    boolean doth = true;
    boolean btnACcontrol = true;
    boolean btnEqualsControl = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializations();
        onClickListeners();

    }

    public void initializations() {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDoth = findViewById(R.id.btnDoth);
        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnEquals = findViewById(R.id.btnEquals);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiple = findViewById(R.id.btnMultiple);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        textViewHistory = findViewById(R.id.textViewHistory);
        textViewResult = findViewById(R.id.textViewResult);
    }

    @SuppressLint("SetTextI18n")
    public void onClickListeners() {
        btn0.setOnClickListener(v -> {
            numberClick("0");
        });
        btn1.setOnClickListener(v -> {
            numberClick("1");
        });
        btn2.setOnClickListener(v -> {
            numberClick("2");
        });
        btn3.setOnClickListener(v -> {
            numberClick("3");
        });
        btn4.setOnClickListener(v -> {
            numberClick("4");
        });
        btn5.setOnClickListener(v -> {
            numberClick("5");
        });
        btn6.setOnClickListener(v -> {
            numberClick("6");
        });
        btn7.setOnClickListener(v -> {
            numberClick("7");
        });
        btn8.setOnClickListener(v -> {
            numberClick("8");
        });
        btn9.setOnClickListener(v -> {
            numberClick("9");
        });
        btnPlus.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "+");

            if (operator) {
                if (status == "multiple") {
                    multiply();
                } else if (status == "division") {
                    divide();
                } else if (status == "minus") {
                    minus();
                } else {

                    plus();
                }
            }
            status = "plus";
            operator = false;
            number = null;

        });

        btnMinus.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "-");

            if (operator) {
                if (status == "multiple") {
                    multiply();
                } else if (status == "division") {
                    divide();
                } else if (status == "plus") {
                    plus();
                } else {

                    minus();
                }
            }
            status = "minus";
            operator = false;
            number = null;
        });

        btnMultiple.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "*");

            if (operator) {
                if (status == "minus") {
                    minus();
                } else if (status == "division") {
                    divide();
                } else if (status == "plus") {
                    plus();
                } else {
                    multiply();
                }
            }
            status = "multiple";
            operator = false;
            number = null;

        });

        btnDivide.setOnClickListener(v -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "/");

            if (operator) {
                if (status == "multiple") {
                    multiply();
                } else if (status == "minus") {
                    minus();
                } else if (status == "plus") {
                    plus();
                } else {
                    divide();
                }
            }
            status = "division";
            operator = false;
            number = null;

        });
        btnEquals.setOnClickListener(v -> {
            if (operator) {
                if (status == "multiple") {
                    multiply();
                } else if (status == "minus") {
                    minus();
                } else if (status == "plus") {
                    plus();
                } else if (status == "division") {
                    divide();
                } else {
                    firsNumber = Double.parseDouble(textViewResult.getText().toString());
                }
            }
            operator = false;
            btnEqualsControl = true;
        });
        btnAC.setOnClickListener(v -> {
            number = null;
            status = null;
            textViewResult.setText("0");
            textViewHistory.setText("");
            firsNumber = 0;
            lastNumber = 0;
            doth = true;
        });
        btnDel.setOnClickListener(v -> {
            if (btnACcontrol) {
                textViewResult.setText("0");
            } else {
                number = number.substring(0, number.length() - 1);
                if (number.length() == 0) {
                    btnDel.setClickable(false);
                } else if (number.contains(".")) {
                    doth = false;
                } else {
                    doth = true;
                }
                textViewResult.setText(number);
            }
        });
        btnDoth.setOnClickListener(v -> {
            if (doth) {
                if (number == null) {
                    number = "0.";
                } else {
                    number = number + ".";
                }
            }
            doth = false;
            textViewResult.setText(number);
        });

    }

    public void numberClick(String view) {
        if (number == null) {
            number = view;
        } else if (btnEqualsControl) {
            firsNumber = 0;
            lastNumber = 0;
            number = view;
        } else {
            number = number + view;
        }
        textViewResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDel.setClickable(true);
        btnEqualsControl = false;
    }

    @SuppressLint("SetTextI18n")
    public void plus() {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firsNumber = firsNumber + lastNumber;
        textViewResult.setText(myFormatter.format(firsNumber));
        doth = true;

    }

    @SuppressLint("SetTextI18n")
    public void minus() {

        if (firsNumber == 0) {
            firsNumber = Double.parseDouble(textViewResult.getText().toString());
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firsNumber = firsNumber - lastNumber;
        }
        textViewResult.setText(myFormatter.format(firsNumber));
        doth = true;

    }

    @SuppressLint("SetTextI18n")
    public void multiply() {

        if (firsNumber == 0) {
            firsNumber = 1;
        }
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firsNumber = firsNumber * lastNumber;
        textViewResult.setText(myFormatter.format(firsNumber));
        doth = true;

    }

    @SuppressLint("SetTextI18n")
    public void divide() {

        if (firsNumber == 0) {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firsNumber = lastNumber;
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firsNumber = firsNumber / lastNumber;
        }
        textViewResult.setText(myFormatter.format(firsNumber));
        doth = true;

    }
}