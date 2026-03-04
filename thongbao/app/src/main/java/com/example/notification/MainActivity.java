package com.example.notification;
import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import android.widget.Toast;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnSubmit;
    Button btnDialog;

    Button btnCustomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        event();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void event(){
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(this, "Đây là Toast!", Toast.LENGTH_SHORT).show();
        });

        btnDialog = findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(e -> {
            openDialog();
        });

        btnCustomDialog = findViewById(R.id.btnCustomDialog);
        btnCustomDialog.setOnClickListener(e -> {
            openCustomDialog();
        });
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn tiếp tục không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            Toast.makeText(MainActivity.this, "Bạn đã chọn Có", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Không", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private void openCustomDialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }
}