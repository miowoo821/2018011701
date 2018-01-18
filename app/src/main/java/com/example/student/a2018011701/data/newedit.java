package com.example.student.a2018011701.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.a2018011701.Main3Activity;
import com.example.student.a2018011701.MainActivity;
import com.example.student.a2018011701.R;

public class newedit extends AppCompatActivity {
    TextView tv1;
    EditText ed1;
    EditText ed2;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newedit);

        tv1=(TextView)findViewById(R.id.textView3);
        ed1=(EditText)findViewById(R.id.editText7);
        ed2=(EditText)findViewById(R.id.editText8);

        id=getIntent().getIntExtra("position",0);

        student s;
        s= MainActivity.dao.getStudent(id);
        tv1.setText(String.valueOf(s.id));
        ed1.setText(String.valueOf(s.name));
        ed2.setText(String.valueOf(s.score));
    }
    public void clickcheck(View v){
        String name=ed1.getText().toString();
        int score=Integer.valueOf(ed2.getText().toString());

        MainActivity.dao.update(new student(id,name,score));
        Toast.makeText(newedit.this,"修改完成",Toast.LENGTH_SHORT).show();

        finish();
    }
    public void clickcancel(View v) {
        finish();
    }
}
