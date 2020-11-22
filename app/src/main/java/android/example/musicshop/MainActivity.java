package android.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Spinner spinner;
EditText userNameEditText;
ArrayList spinnerArrayList;
ArrayAdapter spinnerAdapter;
HashMap goodsMap;
int quantity =0;
String goodsName;
double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText=findViewById(R.id.name);

       createSpinner();
       createHashMap();
    }
    void createSpinner(){
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList= new ArrayList();
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Drum");
        spinnerArrayList.add("Keyboard");

        spinnerAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void createHashMap(){
        goodsMap= new HashMap();
        goodsMap.put("Guitar",500.0);
        goodsMap.put("Drum",1500.0);
        goodsMap.put("Keyboard",1000.0);
    }

    public void increaseQuantity(View view) {
        quantity=quantity+1;
        TextView quantityTextView=findViewById(R.id.quantity);
        quantityTextView.setText(" "+quantity);
        TextView priceTextView=findViewById(R.id.price);
        priceTextView.setText(" "+ quantity*price);
    }

    public void decreaseQuantity(View view) {
        quantity= quantity-1;
        if(quantity<0){
            quantity=0;
        }
        TextView quantityTextView=findViewById(R.id.quantity);
        quantityTextView.setText(" "+quantity);
        TextView priceTextView=findViewById(R.id.price);
        priceTextView.setText(" "+ quantity*price);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName=spinner.getSelectedItem().toString();
        price=(double)goodsMap.get(goodsName);
        TextView priceTextView=findViewById(R.id.price);
        priceTextView.setText(" "+ quantity*price);

        ImageView goodsImageView=findViewById(R.id.goodsImage);
        switch(goodsName) {
            case "Guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "Drum":
                goodsImageView.setImageResource(R.drawable.drum);
                break;
            case "Keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
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
        order.price = price;
        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        startActivity(orderIntent);
    }
}