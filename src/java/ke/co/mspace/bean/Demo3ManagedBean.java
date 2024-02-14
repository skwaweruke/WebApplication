/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.bean;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ke.co.mspace.data.DBConnection;

/**
 *
 * @author mspace
 */
@ManagedBean
@RequestScoped
public class Demo3ManagedBean {

    /**
     * Creates a new instance of Demo3ManagedBean
     */
    public Demo3ManagedBean() {
    }
    List<List<String>> tableData = new ArrayList<>();
    List<String> columnNames = new ArrayList<>();

    @PostConstruct
    public void init() {

        try (Connection connection = DBConnection.getConnection3()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MINZU, USERID, Badgenumber, Name, Gender, TITLE, PAGER, email, department, notify  from USERINFO WHERE PAGER  IS NOT NULL");

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    rowData.add(resultSet.getString(i));

                }
                tableData.add(rowData);
            }
        } catch (Exception e) {
            // Log the exception or handle it based on your application's requirements
            System.out.println("Error fetching table data: " + e.getMessage());
        }

    }

    public List<List<String>> getTableData() {

        System.out.println("Called method getTableData");

        return tableData;
    }

}
