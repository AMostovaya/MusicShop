package com.amostovaya.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"amostovaya86@gmail.com"};
    String subject = "Order from Music Shop";
    TextView tvCustomerName;
    TextView tvGoodsName;
    TextView tvQuantity;
    TextView tvPrice;
    TextView tvOrderPrice;
    String orderForEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent orderIntent = getIntent();
        String userName = orderIntent.getStringExtra("userName");
        String goodsName = orderIntent.getStringExtra("goodsName");
        int quantity = orderIntent.getIntExtra("quantity", 0);
        double price = orderIntent.getDoubleExtra("price", 0.0);
        double orderPrice = orderIntent.getDoubleExtra("orderPrice", 0.0);

        orderForEmail = "Cutomer name: " + userName + "\n" +
                " Goods name: " + goodsName + "\n"+
                " Quantity: " + quantity + "\n" +
                " Order price: " + orderPrice;

        tvCustomerName = findViewById(R.id.tvCustomerName);
        tvCustomerName.setText("Customer name: " + userName);

        tvGoodsName = findViewById(R.id.tvGoodsName);
        tvGoodsName.setText("Goods name: " + goodsName);

        tvPrice = findViewById(R.id.tvPrice);
        tvPrice.setText("Price: " + Double.toString(price));

        tvQuantity = findViewById(R.id.tvQuantity);
        tvQuantity.setText("Quantity: " + Integer.toString(quantity));

        tvOrderPrice = findViewById(R.id.tvOrderPrice);
        tvOrderPrice.setText("Order price: " + Double.toString(orderPrice));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void submitOrder(View view) {
         Intent intent = new Intent(Intent.ACTION_SENDTO);
         intent.setData(Uri.parse("mailto:")); // only email apps should handle this
         intent.putExtra(Intent.EXTRA_EMAIL, addresses);
         intent.putExtra(Intent.EXTRA_SUBJECT, subject);
         intent.putExtra(Intent.EXTRA_TEXT, orderForEmail);
         if (intent.resolveActivity(getPackageManager()) != null) {
              startActivity(intent);
         }
    }

}
