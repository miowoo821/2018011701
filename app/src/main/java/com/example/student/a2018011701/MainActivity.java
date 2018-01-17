package com.example.student.a2018011701;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.student.a2018011701.data.Main2Activity;
import com.example.student.a2018011701.data.StudentSourceDAO;
import com.example.student.a2018011701.data.student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final public static StudentSourceDAO dao = new StudentSourceDAO();//static可以讓整個APP都可以用，不用NEW
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {//回來的時候會啟動(比onCreate適合)
        super.onResume();
        lv=(ListView)findViewById(R.id.listview);
        ArrayList<String> studentName=new ArrayList<>();
        for(student s:dao.getList()){
            studentName.add(s.name);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,studentName);
        lv.setAdapter(adapter);
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
