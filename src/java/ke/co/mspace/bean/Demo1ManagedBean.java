package ke.co.mspace.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ke.co.mspace.data.DBConnection;

@ManagedBean
@RequestScoped
public class Demo1ManagedBean implements Serializable{

    public Demo1ManagedBean() {
    }

    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection2()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, "tEmail", null);

            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");

                // Exclude the "passwd"and "id" column from being displayed
                if (!("passwd".equalsIgnoreCase(columnName) || "id".equalsIgnoreCase(columnName))) {
                        columnNames.add(columnName);
                    }
            }
        } catch (Exception e) {
            // Log the exception or handle it based on your application's requirements
            System.out.println("Error fetching column names: " + e.getMessage());
        }

        return columnNames;
    }

    public List<List<String>> getTableData() {
        List<List<String>> tableData = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection2()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tEmail");

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);

                    // Exclude the "passwd" and "id" column from being displayed
                    if (!("passwd".equalsIgnoreCase(columnName)|| "id".equalsIgnoreCase(columnName))) {
                        rowData.add(resultSet.getString(i));
                    }
                }
                tableData.add(rowData);
            }
        } catch (Exception e) {
            // Log the exception or handle it based on your application's requirements
            System.out.println("Error fetching table data: " + e.getMessage());
        }

        return tableData;
    }
}
