package com.example.maytinh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private double firstValue = 0;
    private String operator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
    }

    // Xử lý khi bấm phím số (0-9 và dấu chấm)
    public void onNumberClick(View view) {
        Button button = (Button) view;
        String number = button.getText().toString();
        String currentContent = tvResult.getText().toString();

        if (isNewOp || currentContent.equals("0")) {
            tvResult.setText(number);
        } else {
            tvResult.setText(currentContent + number);
        }
        isNewOp = false;
    }

    // Xử lý khi bấm các phép tính (+, -, *, /)
    public void onOperatorClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();

        String currentVal = tvResult.getText().toString();
        if (!currentVal.isEmpty()) {
            firstValue = Double.parseDouble(currentVal);
        }
        isNewOp = true;
    }

    // Xử lý nút AC (Xóa hết)
    public void onClearClick(View view) {
        tvResult.setText("0");
        firstValue = 0;
        operator = "";
        isNewOp = true;
    }

    // Xử lý nút bằng (=)
    public void onEqualClick(View view) {
        String currentVal = tvResult.getText().toString();
        if (currentVal.isEmpty() || operator.isEmpty()) return;

        double secondValue = Double.parseDouble(currentVal);
        double result = 0;

        switch (operator) {
            case "+": result = firstValue + secondValue; break;
            case "-": result = firstValue - secondValue; break;
            case "×": result = firstValue * secondValue; break;
            case "÷":
                if (secondValue != 0) result = firstValue / secondValue;
                else { tvResult.setText("Error"); return; }
                break;
        }

        // Định dạng hiển thị: Nếu là số nguyên thì bỏ đuôi .0
        if (result % 1 == 0) {
            tvResult.setText(String.valueOf((long) result));
        } else {
            tvResult.setText(String.valueOf(result));
        }

        operator = "";
        isNewOp = true;
    }
}