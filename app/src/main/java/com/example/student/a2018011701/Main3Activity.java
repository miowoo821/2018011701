package com.example.student.a2018011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.a2018011701.data.Main2Activity;
import com.example.student.a2018011701.data.student;

import static com.example.student.a2018011701.MainActivity.dao;

public class Main3Activity extends AppCompatActivity {
    TextView tv1;
EditText ed1;
EditText ed2;
EditText ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv1=(TextView)findViewById(R.id.textView2);
        ed1=(EditText)findViewById(R.id.editText4);
        ed2=(EditText)findViewById(R.id.editText5);
        ed3=(EditText)findViewById(R.id.editText6);
        student s;

        //Intent it = Main3Activity.this.getIntent();
        //int pos=it.getIntExtra("position",0);//這兩行不如下面一行
        int pos=getIntent().getIntExtra("position",0);//傳過來的資料是int一定要用getIntExtra
        s=MainActivity.dao.getStudent(pos);

        Log.d("GGGGGGGGGGGGGGG","56455");

        tv1.setText(String.valueOf(s.id));
        ed2.setText(String.valueOf(s.name));
        ed3.setText(String.valueOf(s.score));
}
    public void clickedit(View v){
        int id=Integer.valueOf(tv1.getText().toString());
        String name=ed2.getText().toString();
        int score=Integer.valueOf(ed3.getText().toString());

        MainActivity.dao.update(new student(id,name,score));
        Toast.makeText(Main3Activity.this,"修改完成",Toast.LENGTH_SHORT).show();

        finish();
    }
    public void clickdelete(View v){
        int id=Integer.valueOf(tv1.getText().toString());
        MainActivity.dao.delete(id);
        Toast.makeText(Main3Activity.this,"已刪除",Toast.LENGTH_SHORT).show();
        finish();
    }
    public void clickback(View v){
        Intent it=new Intent(Main3Activity.this,MainActivity.class);
        startActivity(it);
    }
}
