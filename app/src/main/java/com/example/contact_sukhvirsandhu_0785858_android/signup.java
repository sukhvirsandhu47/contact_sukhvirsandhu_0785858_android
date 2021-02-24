package com.example.contact_sukhvirsandhu_0785858_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText fname, lname, email,email1, phnumber,number, add;
    Button submit;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email= findViewById(R.id.email);
        email1= findViewById(R.id.email1);
        phnumber = findViewById(R.id.number);
        number = findViewById(R.id.number1);
        add = findViewById(R.id.addres);
        submit = findViewById(R.id.submit);
        DB= new DBHelper(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fname= fname.getText().toString();
                String Lname= lname.getText().toString();
                String Email= email.getText().toString();
                String Email1= email1.getText().toString();
                String Number1= number.getText().toString();
                String Number = phnumber.getText().toString();
                String Add = add.getText().toString();

                if(Fname.equals("")||Lname.equals("")||Email.equals("")||Number.equals("")||Add.equals(""))
                {
                    Toast.makeText(signup.this, "Please enter all the required feild", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(Email1.equals("")) Email1="";
                    if(Number1.equals("")) Number1="";
                    Boolean insert = DB.insertData(Fname,Lname,Email,Email1,Number,Number1,Add);
                    if (insert== true){
                        Toast.makeText(signup.this,"Registration Sucess",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(signup.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(signup.this,"Registration failed",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}