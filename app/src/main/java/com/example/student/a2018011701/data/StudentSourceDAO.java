package com.example.student.a2018011701.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */

public class StudentSourceDAO {

    public ArrayList<student> mylist;
    //新增一個內含student(類別)變數的物件，因為不只一種型態的變數，
    // 所以無法把所有變數都用int String Object之類的物件去賦予型態，
    //因此用<student>,一次賦予int Strt的型態給其class裡面相對應的變數

    public StudentSourceDAO()    {        mylist = new ArrayList<>();    }

    public void add(student s)    { //創造一個方法，並設定若要使用這個方法必須要填入student的物件當作引數，例：mylist.add(s);
        mylist.add(s);
        //Q：為什麼ArrayList物件可以使用這個方法(add)？
        //A：因為你誤會了，這邊的mylist.add所使用的是ArrayList自己的方法，
        // 其意思是，執行StudentSourceDAO物件的add(此討論的方法)方法時，會執行ArrayList的add方法，把student物件當作引數新增進ArrayList
        //例：StudentSourceDAO.add(new student(int,String,int))，
        // 此意思是，新增一個student物件，裡面含有三個引數，分別是int變數,String變數,int變數，
        //再把含有這三個變數的student物件丟進StudentSourceDAO.add的方法所需求的引數中
        // 接著執行StudentSourceDAO的add方法，所以最終執行的結果就會是ArrayList物件裡面多了一個list(有三欄)
    }
    public ArrayList<student> getList()    {
        return mylist;
    }
    public student getStudent(int id)  {
        for (student s : mylist)
        {
            if (s.id == id){
                return s;
            }
        }
        return null;
    }
    public boolean update(student s) {
        //要傳入完整的變數就用student s，
        // 意思就是新增一個student物件,命名為s，
        // 爾後在使用這個方法的時候，就會需要輸入student的完整建構式
        for (student t : mylist) {
            //新增一個student物件,命名為t，掛載mylist
            //
            if (t.id == s.id) {
                t.name = s.name;//把mylist的name,改為新的s物件裡面的name
                t.score = s.score;//把mylist的score,改為新的s物件裡面的score
                return true;
            }
        }
        return false;
    }
    public boolean delete(int id) {//什麼時候用(student s)，什麼時候用(int id)???
        //參數預設只有一個int id，爾後只要輸入id就可以使用這個方法
        for (int i=0;i<mylist.size();i++) {
            if (mylist.get(i).id==id) {//以id查找mylist裡面相符的
                mylist.remove(i);//然後移除
                return true;
            }
        }
        return false;
    }

}
