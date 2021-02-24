package com.example.contact_sukhvirsandhu_0785858_android;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {
    TextView edname;
    EditText edemail, ednumber;
    Button submit,delete;
    String fname,email,number;
    long id;
    DBHelper db;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        id= getIntent().getExtras().getLong("Id");
        fname= getIntent().getExtras().getString("fname");
         email= getIntent().getExtras().getString("email");
         number= getIntent().getExtras().getString("Number");
        System.out.println("hello world oncreate " +fname+" "+email+" "+number);
        submit= findViewById(R.id.submit);
        delete= findViewById(R.id.delete);
        edname = findViewById(R.id.edname);
        edemail=findViewById(R.id.edemail);
        ednumber=findViewById(R.id.ednumber);

        edname.setText(fname);
        edemail.setText(email);
        ednumber.setText(number);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = null;
                db= new DBHelper(context);
                System.out.println("hello world " +fname+" "+email+" "+number);
                int update = db.updateData(fname, email, number);
                if (update>=0)
                {
                    Intent intent = new Intent(EditContactActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor del = db.deleteData(edname.toString(), number);

            }
        });

    }

}
