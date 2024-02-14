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
public class Demo2ManagedBean {

    List<List<String>> tableData = new ArrayList<>();
    List<String> columnNames = new ArrayList<>();

    /**
     * Creates a new instance of demo2ManagedBean
     */
    public Demo2ManagedBean() {

    }

    @PostConstruct
    public void init() {

        try (Connection connection = DBConnection.getConnection2()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tLogins");

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);

                    // Exclude the "guid" and "password" and "id" and "httpRealm" column from being displayed
                    if (!("guid".equalsIgnoreCase(columnName) || "password".equalsIgnoreCase(columnName) || "id".equalsIgnoreCase(columnName) || "httpRealm".equalsIgnoreCase(columnName))) {
                        rowData.add(resultSet.getString(i));
                    }
                }
                tableData.add(rowData);
            }
        } catch (Exception e) {
            // Log the exception or handle it based on your application's requirements
            System.out.println("Error fetching table data: " + e.getMessage());
        }

        try (Connection connection = DBConnection.getConnection2()) {

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, "tLogins", null);

            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");

                System.out.println(resultSet.getString(1));

                // Exclude the "guid" and "password" and "id" and "httpRealm" column from being displayed
                if (!("guid".equalsIgnoreCase(columnName) || "password".equalsIgnoreCase(columnName) || "id".equalsIgnoreCase(columnName) || "httpRealm".equalsIgnoreCase(columnName))) {
                    columnNames.add(columnName);
                }
            }
        } catch (Exception e) {
            // Log the exception or handle it based on your application's requirements
            System.out.println("Error fetching column names: " + e.getMessage());
        }

    }

    public List<String> getColumnNames() {

        System.out.println("Called method getColumnNames");

        return columnNames;
    }

    public List<List<String>> getTableData() {

        System.out.println("Called method getTableData");

        return tableData;
    }

}
