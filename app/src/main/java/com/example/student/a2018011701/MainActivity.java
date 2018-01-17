package com.example.student.a2018011701;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.student.a2018011701.data.Main2Activity;
import com.example.student.a2018011701.data.StudentSourceDAO;

public class MainActivity extends AppCompatActivity {
    final public static StudentSourceDAO dao = new StudentSourceDAO();//static可以讓整個APP都可以用，不用NEW
    ArrayAdapter<String> adapter;
    Context context;
    StudentSourceDAO stu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,stu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {//假設點擊的menu裡面add(+)的選項就執行下列程式碼
            Intent it = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
