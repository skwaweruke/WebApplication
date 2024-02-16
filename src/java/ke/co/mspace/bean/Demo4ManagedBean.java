/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.bean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
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
@SessionScoped
public class Demo4ManagedBean {

    /**
     * Creates a new instance of Demo4ManagedBean
     */
    public Demo4ManagedBean() {
    }

    List<List<String>> tableData = new ArrayList<>();
    List<List<String>> filteredData = new ArrayList<>();
    Date date;

    String filterValue;

    @PostConstruct
    public void init() {

        try (Connection connection = DBConnection.getConnection3()) {

            Statement statement = connection.createStatement();
            String checkinout;
            checkinout = "SELECT CONVERT(date, GETDATE()) AS BIOMETRICDATE, CONVERT(VARCHAR(5), CHECKINOUT.CHECKTIME,108) AS BIOMETRICTIME, USERINFO.Badgenumber, USERINFO.PAGER, USERINFO.Name FROM CHECKINOUT\n"
                    + "INNER JOIN USERINFO ON CHECKINOUT.USERID  = USERINFO.USERID WHERE CHECKTIME >= DATEADD(day,-30, GETDATE()) and USERINFO.PAGER IS NOT NULL order by LOGID DESC";
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

        filteredData.clear();

        if (filterValue == null || filterValue.trim().isEmpty()) {
            return tableData;
        } else {
            for (List<String> row : tableData) {
                for (String cell : row) {
                    if (cell.contains(filterValue)) {
                        filteredData.add(row);
                        break;
                    }
                }
            }
            return filteredData;
        }
    }

    public List<List<String>> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(List<List<String>> filteredData) {
        this.filteredData = filteredData;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
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

    public void preProcessPDF(Object document) throws DocumentException, BadElementException, MalformedURLException, IOException {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LEGAL.rotate());
        pdf.setMargins(-50.10f, -50.10f, 80.5f, 50.5f);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        if (!pdf.isOpen()) {
            pdf.open();
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
