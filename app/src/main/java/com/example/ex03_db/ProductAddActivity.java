package com.example.ex03_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductAddActivity extends AppCompatActivity {
    EditText editProductName, editPrice, editAmount;
    Button btnSave;
    ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        editProductName=findViewById(R.id.editProductName);
        editPrice = findViewById(R.id.editPrice);
        editAmount=findViewById(R.id.editAmount);
        btnSave=findViewById(R.id.btnSave);
        dao = new ProductDAO(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name = editProductName.getText().toString();
                int price = Integer.parseInt(editPrice.getText().toString());
                int amount=Integer.parseInt(editAmount.getText().toString());
                ProductDTO dto= new ProductDTO(product_name,price,amount);
                dao.insert(dto);
                Toast.makeText(ProductAddActivity.this,"저장되었습니다.",Toast.LENGTH_SHORT).show();
                finish();//현재 화면 종료
            }
        });
    }
}