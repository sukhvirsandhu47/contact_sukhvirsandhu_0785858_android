package com.example.contact_sukhvirsandhu_0785858_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add;
    ListView userlist;
    DBHelper db;
    ArrayList<String> nameitem;
    ArrayList<String> numberitem;
    ArrayList<String> emailitem;
    TextView count;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DBHelper(this);
        add= findViewById(R.id.add);
        userlist = findViewById(R.id.display);
        nameitem = new ArrayList<>();
        numberitem = new ArrayList<>();
        emailitem = new ArrayList<>();
        count = findViewById(R.id.count);
        viewData();
        Integer number = userlist.getCount();
        count.setText(number.toString());
        System.out.println("hello world"+userlist.getCount());
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text= userlist.getItemAtPosition(position).toString();
                String e= emailitem.get(position);
                String n= numberitem.get(position);
                Intent intent = new Intent(MainActivity.this,EditContactActivity.class);
                intent.putExtra("Id",id);
                intent.putExtra("fname",text);
                intent.putExtra("email",e);
                intent.putExtra("Number",n);
                startActivity(intent);


            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });
    }

    private void viewData() {
        Cursor cursor = db.viewData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data",Toast.LENGTH_LONG).show();
        }
        else
        {
            while (cursor.moveToNext()){
                nameitem.add(cursor.getString(0));
                emailitem.add(cursor.getString(2));
                numberitem.add(cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameitem);
            userlist.setAdapter(adapter);
        }
    }
    private void viewDat(Cursor cursor) {
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data",Toast.LENGTH_LONG).show();
        }
        else
        {
            while (cursor.moveToNext()){
                nameitem.add(cursor.getString(0));
                emailitem.add(cursor.getString(2));
                numberitem.add(cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameitem);
            userlist.setAdapter(adapter);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu ){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchItem = menu.findItem(R.id.itemsearch);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> userslist= new ArrayList<>();
                for (String user: nameitem)
                {
                    if (user.toLowerCase().contains(newText.toLowerCase())){
                        userslist.add(user);
                    }
                }
                ArrayAdapter<String> adapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,userslist);
                userlist.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}