package com.example.student.a2018011701.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

//將StudentSourceDAO的處理方式改為可以存檔，而非只在記憶體
public class StudentFileDAO implements StudentFileDAO2 { //implements硬性規定你一定要有哪些方法
    Context context;//因為Context
    //要使用getFilesDir的方法必須繼承AppCompatActivity，而AppCompatActivity的getFilesDir又是來自他的父不知道幾代的Context類別
    //所以乾脆創造一個Context物件，透過這個物件使用getFilesDir方法
    // 為什麼用繼承不好，繼承只能一個，所以要留給其他用?

    public ArrayList<student> mylist;
    //新增一個內含student(類別)變數的物件，因為不只一種型態的變數，
    // 所以無法把所有變數都用int String Object之類的物件去賦予型態，
    //因此用<student>,一次賦予int Strt的型態給其class裡面相對應的變數

    public StudentFileDAO(Context context)    {//建構式記得加入context，才有辦法從外面讀進來(?)
        this.context=context;
        mylist = new ArrayList<>();

    }

    private void saveFile(){
        File f=new File(context.getFilesDir(), "mydata.txt");//File必須要兩個引數，一個是路徑，一個是檔名
        //getFilesDir方法，可取得 App "內部儲存體"存放檔案的目錄
        FileWriter fw=null;

        try {
            fw=new FileWriter(f);//FileWriter的引數是寫進哪個檔案
            Gson gson = new Gson();//記得Gradle GSON
            String data=gson.toJson(mylist);//把mylist轉成GSON還是JSON的資料後，丟進data
            fw.write(data);//剛剛準備好寫入的fw，現在正式把data寫進去fw剛剛給的引數的f
            fw.close();//寫完關閉
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void loadFile(){
        File f=new File(context.getFilesDir(),"mydata.txt");
        FileReader fr=null;//(為什麼這邊不能直接給他NEW?其實可以拉，要記得加try catch

        try {
             fr= new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            mylist=gson.fromJson(str,new TypeToken<ArrayList<student>>(){}.getType());
            br.close();//寫完關閉
            fr.close();//寫完關閉
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean add(student s)    { //創造一個方法，並設定若要使用這個方法必須要填入student的物件當作引數，例：mylist.add(s);

        mylist.add(s);
        //Q：為什麼ArrayList物件可以使用這個方法(add)？
        //A：因為你誤會了，這邊的mylist.add所使用的是ArrayList自己的方法，
        // 其意思是，執行StudentSourceDAO物件的add(此討論的方法)方法時，會執行ArrayList的add方法，把student物件當作引數新增進ArrayList
        //例：StudentSourceDAO.add(new student(int,String,int))，
        // 此意思是，新增一個student物件，裡面含有三個引數，分別是int變數,String變數,int變數，
        //再把含有這三個變數的student物件丟進StudentSourceDAO.add的方法所需求的引數中
        // 接著執行StudentSourceDAO的add方法，所以最終執行的結果就會是ArrayList物件裡面多了一個list(有三欄)
        saveFile();
        return true;
    }
    public ArrayList<student> getList()    {
        loadFile();//先load才有辦法顯示，不然是空的
        return mylist;
    }
    public student getStudent(int id)  {
        for (student s : mylist)
        {
            loadFile();//先load才有辦法顯示，不然是空的
            if (s.id == id){
                return s;
            }
        }
        return null;
    }
    public boolean update(student s) {//假設我需要回傳的值是 TRUE或FALSE就用boolean,但現在其實用不到
        //要傳入完整的變數就用student s，
        // 意思就是新增一個student物件,命名為s，
        // 爾後在使用這個方法的時候，就會需要輸入student的完整建構式
        loadFile();//先load才有辦法顯示，不然是空的
        for (student t : mylist) {
            //新增一個student物件,命名為t，掛載mylist

            if (t.id == s.id) {
                //下面是要update的東西
                t.name = s.name;//把mylist的name,改為新的s物件裡面的name
                t.score = s.score;//把mylist的score,改為新的s物件裡面的score

                saveFile();//記得存檔
                return true;//假設他找的到這個ID，就回傳true，但現在這個true沒有被使用?
                //一個return就會中斷整個方法，不可能回傳多個值 (嗎)
            }
        }
        return false;//沒有找到就回傳false
    }
    public boolean delete(int id) {//什麼時候用(student s)，什麼時候用(int id)???
        //參數預設只有一個int id，爾後只要輸入id就可以使用這個方法
        loadFile();
        for (int i=0;i<mylist.size();i++) {
            if (mylist.get(i).id==id) {//以id查找mylist裡面相符的
                mylist.remove(i);//然後移除
                saveFile();
                return true;
            }
        }
        return false;
    }


}
