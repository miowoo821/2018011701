package com.example.student.a2018011701.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.a2018011701.MainActivity;
import com.example.student.a2018011701.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void clickadd(View v){
        EditText ed1=(EditText)findViewById(R.id.editText);
        EditText ed2=(EditText)findViewById(R.id.editText3);
        EditText ed3=(EditText)findViewById(R.id.editText2);
        int id=Integer.valueOf(ed1.getText().toString());
        String name=ed1.getText().toString();
        int score=Integer.valueOf(ed3.getText().toString());

        MainActivity.dao.add(new student(id,name,score));//有static才抓的到MainActivity.dao
        finish();
    }
}
