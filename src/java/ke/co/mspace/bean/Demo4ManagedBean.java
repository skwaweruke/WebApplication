/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.bean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import ke.co.mspace.data.DBConnection;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author mspace
 */
@ManagedBean
@RequestScoped
public class Demo4ManagedBean {

    /**
     * Creates a new instance of Demo4ManagedBean
     */
    public Demo4ManagedBean() {
        System.out.println("Called...");
    }

    List<List<String>> tableData = new ArrayList<>();
    List<String> columnNames = new ArrayList<>();
    private String filterValue, filteredData;

    @PostConstruct
    public void init() {

        try (Connection connection = DBConnection.getConnection3()) {

            Statement statement = connection.createStatement();
            String checkinout;
            checkinout = "SELECT CONVERT(date, GETDATE()) AS BIOMETRICDATE, CONVERT(VARCHAR(5), CHECKINOUT.CHECKTIME,108) AS BIOMETRICTIME, USERINFO.Badgenumber, USERINFO.PAGER, USERINFO.Name FROM CHECKINOUT\n"
                    + "INNER JOIN USERINFO ON CHECKINOUT.USERID  = USERINFO.USERID WHERE CHECKTIME >= DATEADD(day,-7, GETDATE()) and CHECKINOUT.CHECKTYPE = 'I' order by LOGID DESC";
            ResultSet resultSet = statement.executeQuery(checkinout);

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
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

    public void postProcessXLS(Object document) {

        System.out.println("Called method postProcessXLS");

        try {
            HSSFWorkbook wb = (HSSFWorkbook) document;
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow header = sheet.getRow(0);
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
                header.getCell(i).setCellStyle(cellStyle);
            }

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=processedFile.xls");

            wb.write(response.getOutputStream());
            facesContext.responseComplete();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {

        System.out.println("Called method preProcessPDF");

        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline");
    }

    public String getFilterValue() {
        return filterValue;
    }
    public String getFilteredData() {
        return filteredData;
    }
    
    

}
