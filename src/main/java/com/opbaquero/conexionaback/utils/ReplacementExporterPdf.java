package com.opbaquero.conexionaback.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReplacementExporterPdf {

    public List<ReplacementDataExportDTO> dataExport;

    public ReplacementExporterPdf(List<ReplacementDataExportDTO> dataExport) {
        this.dataExport = dataExport;
    }

    private void writeHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Usuario", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Almacén", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Edificio", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Hospital", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cuenta", font));
        table.addCell(cell);

    }

    private void writeTableContent(PdfPTable table){
        for(ReplacementDataExportDTO replacement : dataExport){
            table.addCell(String.valueOf(replacement.date));
            table.addCell(replacement.userName);
            table.addCell(replacement.wareHouseName);
            table.addCell(replacement.buildingName);
            table.addCell(replacement.hospitalName);
            table.addCell(replacement.accountName);
        }
    }

    public void exportDocument(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(16);
        font.setColor(Color.BLACK);

        Paragraph paragraph = new Paragraph("Historial de reposiciones:", font);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String now = dateFormat.format(new Date());

        Font fontDate = FontFactory.getFont(FontFactory.COURIER);
        fontDate.setSize(8);
        fontDate.setColor(Color.BLACK);

        Paragraph paragraphDate = new Paragraph(now, fontDate);
        paragraphDate.setAlignment(Paragraph.ALIGN_RIGHT);

        document.add(paragraphDate);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2f, 2f, 2.5f, 3.0f, 3f, 3f});
        table.setSpacingBefore(5);

        writeHeader(table);
        writeTableContent(table);

        document.add(table);
        document.close();
    }

}
