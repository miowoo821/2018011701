package com.example.student.a2018011701;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.student.a2018011701.data.DBType;
import com.example.student.a2018011701.data.Main2Activity;
import com.example.student.a2018011701.data.StudentFileDAO;
import com.example.student.a2018011701.data.StudentFileDAO2;
import com.example.student.a2018011701.data.StudentFileDAOFactory;
import com.example.student.a2018011701.data.StudentSourceDAO;
import com.example.student.a2018011701.data.student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //改用StudentFileDAO了,有存檔功能
    //final public static StudentSourceDAO dao = new StudentSourceDAO();//static可以讓整個APP都可以用，不用NEW
    //final public static StudentFileDAO dao= new StudentFileDAO();
    // 因為現在有引數必須填進去(上面那個的建構式沒寫引數所以可以在這區給)，所以不能在這邊給了，要在onCreate給

    public static StudentFileDAO2 dao;//
    DBType dbType;
    ListView lv;

    ArrayList<String> studentName;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //換成業界作法dao=new StudentFileDAO(MainActivity.this);//把這個頁面當作引數給這個類別，可以達到繼承的效果?
        dbType = DBType.CLOUD; // 1:記憶體 2:檔案 3:SQLite
        dao = StudentFileDAOFactory.getDAOInstance(this, dbType);

        studentName=new ArrayList<>();
        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,studentName);
        lv=(ListView)findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent it=new Intent(MainActivity.this,Main3Activity.class);
                it.putExtra("position",dao.getList().get(i).id);
                //因為getList是回傳mylist(Arratlist物件)所以有get的方法
                //為什麼要用dao.getList().get(i).id？不直接用i+1
                //因為這是抓  mylist  裡面第i個位置的資料，不一定是數字，也不一定連號，用i+1也行只是剛好而已
                startActivity(it);
            }
        });
    }

    @Override
    protected void onResume() {//回來的時候會啟動(比onCreate適合)
        super.onResume();
        refreshData();
        //lv=(ListView)findViewById(R.id.listview);
        //修正，把賦值拉到oncreat，因為onResume沒必要每次都新增一次,一次就好，onResume呼叫就好
        //ArrayList<String> studentName=new ArrayList<>();//新增一個叫做studentName的ArrayList
        //修正，把新增升級到最外層，因為onResume沒必要每次都新增一次
        //studentName=new ArrayList<>();
        //再修正，把賦值拉到oncreat，因為onResume沒必要每次都新增一次,一次就好，onResume呼叫就好
//        for(student s:dao.getList()){//把dao.getList得到的長度丟回s,藉此決定迴圈次數
//            studentName.add(s.name);//每次add一個
//        }
        //移至新方法refreshData
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,studentName);
        //修正，把新增升級到最外層，因為onResume沒必要每次都新增一次
        //adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,studentName);
        //再修正，把賦值拉到oncreat，因為onResume沒必要每次都新增一次,一次就好，onResume呼叫就好

        //ArrayAdapter
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Intent it=new Intent(MainActivity.this,Main3Activity.class);
//                it.putExtra("position",dao.getList().get(i).id);
//
//                startActivity(it);
//            }
//        });
// 修正，提升到oncreate做
    }
    public void refreshData(){
        studentName.clear();
        for(student s:dao.getList()){//把dao.getList得到的長度丟回s,藉此決定迴圈次數
            studentName.add(s.name);//每次add一個
        }
        adapter.notifyDataSetChanged();//一定要加這個
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
