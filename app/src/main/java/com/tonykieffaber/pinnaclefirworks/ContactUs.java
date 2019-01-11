package com.tonykieffaber.pinnaclefirworks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Button send = findViewById(R.id.uxSendButton);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String to="tkjay777@gmail.com";
                String subject = "Fireworks";
                //String name = ((EditText)findViewById(R.id.uxNameBox)).getText().toString();
               // String email = ((EditText)findViewById(R.id.uxEmailBox)).getText().toString();
                //String message = ((EditText)findViewById(R.id.uxMessageBox)).getText().toString();
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                mail.putExtra(Intent.EXTRA_SUBJECT, subject);
                //mail.putExtra(Intent.EXTRA_TEXT, message+'\n'+'\n'+"From: "+name+'\n');
                mail.setType("message/rfc822");
                startActivity(Intent.createChooser(mail, "Send email via:"));







            }
        });



        Button facebook = findViewById(R.id.uxFacebookLink);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/PinnacleFireworks/"));
                startActivity(intent);
            }
        });




    }









}
