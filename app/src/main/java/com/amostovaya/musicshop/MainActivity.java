package com.amostovaya.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    TextView textQuantity;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuantity = findViewById(R.id.textQuantity);
        userNameEditText = findViewById(R.id.editTextTextPersonName);
        createSpinner();
        createGoodsMap();
    }

    public void increaseQuantity(View view) {
        quantity++;
        textQuantity.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.textPrice);
        priceTextView.setText("" + quantity * price);
    }

    public void decreaseQuantity(View view) {
        if (quantity == 0) {
            quantity = 0;
        }
        else {
            quantity--;
        }

        TextView textQuantity = findViewById(R.id.textQuantity);
        textQuantity.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.textPrice);
        if (quantity!=0) {
            priceTextView.setText("" + quantity * price);
        }
    }

    void  createGoodsMap() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 450.0);
        goodsMap.put("keyboard", 1000.0);
    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("keyboard");
        spinnerArrayList.add("drums");
        spinerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.textPrice);
        if (quantity == 0) {
           quantity = 1;
           textQuantity.setText(""+quantity);
        }
        priceTextView.setText("" + quantity * price);
        ImageView goodsImageView = findViewById(R.id.imageViewItem);
        switch (goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar_1);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = price*quantity;
        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);

        startActivity(orderIntent);


    }
}