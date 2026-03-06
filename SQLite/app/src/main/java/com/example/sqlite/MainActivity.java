package com.example.sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Khai báo các thành phần giao diện
    EditText edtId, edtName, edtAddress, edtPhone;
    Button btnAdd, btnView, btnUpdate, btnDelete;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ View từ XML
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Khởi tạo đối tượng xử lý Database
        db = new DatabaseHandler(this);

        // 1. CHỨC NĂNG THÊM (ADD)
        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String address = edtAddress.getText().toString();
            String phone = edtPhone.getText().toString();

            if (!name.isEmpty()) {
                // Thêm mới không cần truyền ID (ID tự tăng)
                Student student = new Student(0, name, address, phone);
                db.addStudent(student);
                Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Vui lòng nhập tên sinh viên", Toast.LENGTH_SHORT).show();
            }
        });

        // 2. CHỨC NĂNG XEM DANH SÁCH (VIEW)
        btnView.setOnClickListener(v -> {
            List<Student> list = db.getAllStudents();
            StringBuilder data = new StringBuilder();

            for (Student s : list) {
                data.append(s.getId()).append(" - ")
                        .append(s.getName()).append(" - ")
                        .append(s.getPhoneNumber()).append("\n");
            }

            if (list.isEmpty()) {
                Toast.makeText(this, "Danh sách trống!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // 3. CHỨC NĂNG CẬP NHẬT (UPDATE)
        btnUpdate.setOnClickListener(v -> {
            String idStr = edtId.getText().toString();
            if (!idStr.isEmpty()) {
                Student student = new Student(
                        Integer.parseInt(idStr),
                        edtName.getText().toString(),
                        edtAddress.getText().toString(),
                        edtPhone.getText().toString()
                );
                db.updateStudent(student);
                Toast.makeText(this, "Đã cập nhật ID: " + idStr, Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Nhập ID để cập nhật!", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. CHỨC NĂNG XÓA (DELETE)
        btnDelete.setOnClickListener(v -> {
            String idStr = edtId.getText().toString();
            if (!idStr.isEmpty()) {
                db.deleteStudent(Integer.parseInt(idStr));
                Toast.makeText(this, "Đã xóa ID: " + idStr, Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Nhập ID để xóa!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hàm xóa sạch các ô nhập liệu sau khi thao tác xong
    private void clearFields() {
        edtId.setText("");
        edtName.setText("");
        edtAddress.setText("");
        edtPhone.setText("");
    }
}