package android.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String subject = "News app";
    String emailText;
    TextView orderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");
        orderTextView = findViewById(R.id.orderView);



    Intent receiveIntent =getIntent();
    String userName=receiveIntent.getStringExtra("userNameForIntent");
    String goodsName=receiveIntent.getStringExtra("goodsName");
    int quantity=receiveIntent.getIntExtra("quantity",0);
    double price=receiveIntent.getDoubleExtra("price",0);
    double orderPrice=receiveIntent.getDoubleExtra("orderPrice",0);




    orderTextView.setText("Customer name: "+userName+"\n"+
            "Goods name: "+goodsName+""+"\n"+
            "Quantity: "+quantity+"\n"+
            "Price: "+price+"\n"+
            "Total Price: "+orderPrice);

    emailText="Customer name: "+userName+"\n"+
            "Goods name: "+goodsName+""+"\n"+
            "Quantity: "+quantity+"\n"+
            "Price: "+price+"\n"+
            "Total Price: "+orderPrice;
    }



    public void submitOrder(View view) {
        //method 1 : Doesn't work with extra subject and extra text
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//
//        intent.setData(Uri.parse("mailto:"+"adi788048@gmail.com")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT,new String[] {"Order from Music Shop"});
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intent.putExtra(Intent.EXTRA_TEXT,emailText);

//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

        //Method 2 : It opens all implicit intent app
//        Intent email = new Intent(Intent.ACTION_SEND);
//        email.putExtra(Intent.EXTRA_EMAIL,new String[] {"Order from Music Shop"} );
//        email.putExtra(Intent.EXTRA_SUBJECT,"subject");
//        email.putExtra(Intent.EXTRA_TEXT, "Hii text");
//
//        //need this to prompts email client only
//        email.setType("message/rfc822");
//
//        startActivity(Intent.createChooser(email, "Choose an Email client :"));

//method 3: working fine with subject and extra text intent

        Intent send = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode("pjs788048@gmail.com") +
                "?subject=" + Uri.encode("Order from Music Shop") +
                "&body=" + Uri.encode(emailText);
        Uri uri = Uri.parse(uriText);

        send.setData(uri);
        startActivity(Intent.createChooser(send, "Send mail..."));
    }
}