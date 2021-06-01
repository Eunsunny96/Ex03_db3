package com.example.ex03_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    Button btnAdd;
    RecyclerView rv;
    ProductDAO dao;
    List<ProductDTO> items;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        btnAdd = findViewById(R.id.btnAdd);
        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(),DividerItemDecoration.VERTICAL));
        dao=new ProductDAO(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(
                        ProductActivity.this, ProductAddActivity.class);
                startActivity(intent);

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        items=dao.list();
        myAdapter = new MyAdapter();
        rv.setAdapter(myAdapter);
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
            return new MyAdapter.ViewHolder(rowItem);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            ProductDTO dto=items.get(position);
            holder.txtProductName.setText(dto.getProduct_name());
            holder.txtPrice.setText("가격:"+dto.getPrice() +"원");
            holder.txtAmount.setText("수량" +dto.getAmount());
        }

        @Override
        public int getItemCount() {
            Log.i("test", "자료개수:" +items.size()+ "");

            return items.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView txtProductName, txtPrice, txtAmount;

            public ViewHolder(View view){
                super(view);
                view.setOnClickListener(this);

                this.txtProductName = view.findViewById(R.id.txtProductName);
                this.txtPrice = view.findViewById(R.id.txtPrice);
                this.txtAmount = view.findViewById(R.id.txtAmount);

            }

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductActivity.this, ProductEditActivity.class);
                ProductDTO dto=items.get(getLayoutPosition());
                intent.putExtra("dto",dto);
                startActivity(intent);

            }
        }
    }
}