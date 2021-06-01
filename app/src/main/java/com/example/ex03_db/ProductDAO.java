package com.example.ex03_db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Context context;//컨텍스트
    SQLiteDatabase db;

    public ProductDAO(Context context) {
        this.context = context;
    }

    public SQLiteDatabase dbConn() {
        //db생성
        db = context.openOrCreateDatabase("product.db", Context.MODE_PRIVATE, null);
        String sql = "create table if not exists product "
                + " (id integer primary key autoincrement, " +
                "product_name varchar(50) not null, " +
                "price int not null, " +
                " amount int not null )";
        db.execSQL(sql);//
        return db;
    }

    public void insert(ProductDTO dto) {
        SQLiteDatabase db = null;
        try {
            db = dbConn();
            String sql = String.format("insert into product " +
                            "(product_name,price,amount) " +
                    "values ('%s', %d , %d)",
                    dto.getProduct_name(), dto.getPrice(), dto.getAmount());
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
    }

    public List<ProductDTO> list() {
        List<ProductDTO> items = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;//결과셋을 탐색
        try {
            db = dbConn();
            String sql = "select id,product_name,price,amount " +
                    " from product order by product_name";
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {//다음 레코드가 있으면 true
                //cursor.get자료형(필드 인덱스)
                int id = cursor.getInt(0);
                String product_name = cursor.getString(1);
                int price = cursor.getInt(2);
                int amount = cursor.getInt(3);
                items.add(new ProductDTO(id, product_name, price, amount));//추가
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
        return items;

    }

    public void update(ProductDTO dto) {
        SQLiteDatabase db = null;
        try {
            db = dbConn();
            String sql = String.format("update product " +
                            "set product_name='%s', price=%d, amount=%d where id=%d"
                    , dto.getProduct_name(), dto.getPrice(), dto.getAmount(), dto.getId());
            db.execSQL(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
    }

    public void delete(int id) {
        SQLiteDatabase db = null;
        try {
            db = dbConn();
            String sql = "delete from product where id=" + id;
            db.execSQL(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
    }
}
