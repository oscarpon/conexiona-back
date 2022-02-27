package com.opbaquero.conexionaback.utils;

import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReplacementExporterExcel {

    private XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFSheet sheet = this.workbook.createSheet("Historial_Reposiciones");
    private List<ReplacementDataExportDTO> dataExport;

    public ReplacementExporterExcel(List<ReplacementDataExportDTO> dataExport) {
        this.dataExport = dataExport;
    }

    private void writeHeader(){

        Row row = this.sheet.createRow(0);

        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Date", style);
        createCell(row, 1, "Usuario", style);
        createCell(row, 2, "Almacén", style);
        createCell(row, 3, "Edificio", style);
        createCell(row, 4, "Hospital", style);
        createCell(row, 5, "Cuenta", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        this.sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void createDataLines(){
        int count = 1;

        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        for (ReplacementDataExportDTO replacement : dataExport){
            Row row = this.sheet.createRow(count++);
            int columCount = 0;
            createCell(row, columCount++, String.valueOf(replacement.getDate()), style);
            createCell(row, columCount++, replacement.getUserName(), style);
            createCell(row, columCount++, replacement.getWareHouseName(), style);
            createCell(row, columCount++, replacement.getBuildingName(), style);
            createCell(row, columCount++, replacement.getHospitalName(), style);
            createCell(row, columCount++, replacement.getAccountName(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        writeHeader();
        createDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        this.workbook.write(outputStream);
        this.workbook.close();
        outputStream.close();
    }

}
