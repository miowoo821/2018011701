package com.example.student.a2018011701;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.a2018011701.data.Main2Activity;
import com.example.student.a2018011701.data.newedit;
import com.example.student.a2018011701.data.student;

import static com.example.student.a2018011701.MainActivity.dao;

public class Main3Activity extends AppCompatActivity {
    student s;
    TextView tv1;
    EditText ed1;
    EditText ed2;
    EditText ed3;
int id;//寫在這邊當成員變數，本頁面的大家就都可以用，不用每個方法都要弄一個
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv1=(TextView)findViewById(R.id.textView2);
        ed1=(EditText)findViewById(R.id.editText4);
        ed2=(EditText)findViewById(R.id.editText5);
        ed3=(EditText)findViewById(R.id.editText6);

        //Intent it = Main3Activity.this.getIntent();
        //int pos=it.getIntExtra("position",0);//這兩行不如下面一行
        id=getIntent().getIntExtra("position",0);//傳過來的資料是int一定要用getIntExtra
        //一次在這邊給他，以後就可以直接拿來用
        //student s;//放到最上面當成員變數比較好，建一次大家都能用
        s=MainActivity.dao.getStudent(id);

        Log.d("GGGGGGGGGGGGGGG","56455");
        tv1.setText(String.valueOf(s.id));
        ed2.setText(String.valueOf(s.name));
        ed3.setText(String.valueOf(s.score));
}
    public void clickedit(View v){
       // int id=Integer.valueOf(tv1.getText().toString());
        //因為本頁只會有一個id,不會同時存在兩個不同的ID，所以把id升上去當成員變數是合理的，不會衝突其他ID，
        //只要在onCreate定義一次就夠了，可以少寫一行程式碼
        String name=ed2.getText().toString();
        int score=Integer.valueOf(ed3.getText().toString());

        MainActivity.dao.update(new student(id,name,score));
        Toast.makeText(Main3Activity.this,"修改完成",Toast.LENGTH_SHORT).show();
        if( MainActivity.dao.update(new student(id,name,score)))

        finish();
    }
    public void clickdelete(View v){
//        int id=Integer.valueOf(tv1.getText().toString());
// 因為本頁只會有一個id,不會同時存在兩個不同的ID，所以把id升上去當成員變數是合理的，不會衝突其他ID
//只要在onCreate定義一次就夠了，可以少寫一行程式碼
        AlertDialog.Builder builder=new AlertDialog.Builder(Main3Activity.this);//新增會彈出對話框的物件
        builder.setTitle("刪除確認");//對話框標題
        builder.setMessage("是否要刪除本筆資料?");//對話框訊息
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {//設定對話況右邊的鈕為確認，並設一個監聽器，監聽點擊事件
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.dao.delete(id);
                Toast.makeText(Main3Activity.this,"已刪除",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        finish();
    }
    public void clickback(View v){
//        Intent it=new Intent(Main3Activity.this,MainActivity.class);
//        startActivity(it);
//       用intent不好，會一層一層疊上去，之後返回會有一堆頁面，所以要用finish
        finish();
    }

    public void clicknewedit(View v){
        Intent it=new Intent(Main3Activity.this,newedit.class);
        it.putExtra("position",id);

        startActivity(it);
    }
    protected void onResume() {//第一次也會執行，依序是onCreate、onStart、onResume；對話框結束不會跑onResume的樣子欸
        super.onResume();
        //student s;//放到最上面當成員變數比較好，建一次大家都能用
        s=MainActivity.dao.getStudent(id);
        ed2.setText(String.valueOf(s.name));
        ed3.setText(String.valueOf(s.score));
        Log.d("GGGGGGGGGGGGG",s.name);
    }
}
