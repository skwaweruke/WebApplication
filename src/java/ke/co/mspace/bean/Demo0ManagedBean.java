/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.bean;


/**
 *
 * @author mspace
 */
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import ke.co.mspace.data.MyData1;

@ManagedBean(name="demo0ManagedBean")
@ViewScoped
public class Demo0ManagedBean implements Serializable{

    private List<MyData1> dataList;

    @PostConstruct
    public void init() {
        // Initialize data directly in the code
        dataList = new ArrayList<>();
        dataList.add(new MyData1("John", "Doe","MSPACE","male","Nairobi","Married", 30));
        dataList.add(new MyData1("Jane", "Smith","MSPACE","male","Nairobi","Married", 25));
        dataList.add(new MyData1("Bob", "Johnson","MSPACE","male","Nairobi","Married", 35));
        dataList.add(new MyData1("John", "Doe","MSPACE","male","Nairobi","Married", 30));
        dataList.add(new MyData1("Jane", "Smith","MSPACE","male","Nairobi","Married", 25));
        dataList.add(new MyData1("Bob", "Johnson","MSPACE","male","Nairobi","Married", 35));
        dataList.add(new MyData1("John", "Doe","MSPACE","male","Nairobi","Married", 30));
        dataList.add(new MyData1("Jane", "Smith","MSPACE","male","Nairobi","Married", 25));
        dataList.add(new MyData1("Bob", "Johnson","MSPACE","male","Nairobi","Married", 35));
        dataList.add(new MyData1("John", "Doe","MSPACE","male","Nairobi","Married", 30));
        dataList.add(new MyData1("Jane", "Smith","MSPACE","male","Nairobi","Married", 25));
        dataList.add(new MyData1("Bob", "Johnson","MSPACE","male","Nairobi","Married", 35));
        dataList.add(new MyData1("John", "Doe","MSPACE","male","Nairobi","Married", 30));
        dataList.add(new MyData1("Jane", "Smith","MSPACE","male","Nairobi","Married", 25));
        dataList.add(new MyData1("Bob", "Johnson","MSPACE","male","Nairobi","Married", 35));
    }

    // Getter for dataList
    public List<MyData1> getDataList() {
        return dataList;
    }

    // Setter for dataList
    public void setDataList(List<MyData1> dataList) {
        this.dataList = dataList;
    }
    
    
}
