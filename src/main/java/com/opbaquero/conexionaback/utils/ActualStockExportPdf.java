package com.opbaquero.conexionaback.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opbaquero.conexionaback.models.service.dto.ActualStockDTO;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ActualStockExportPdf {

    public List<ActualStockDTO> dataExport;

    public ActualStockExportPdf(List<ActualStockDTO> dataExport) {
        this.dataExport = dataExport;
    }

    private void writeHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Almacén", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Stock", font));
        table.addCell(cell);

    }

    private void writeTableContent(PdfPTable table){
        for(ActualStockDTO stock : dataExport){
            table.addCell(stock.getWareHouse());
            table.addCell(stock.getProduct());
            table.addCell(String.valueOf(stock.getStock()));
        }
    }

    public void exportDocument(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(16);
        font.setColor(Color.BLACK);

        Paragraph paragraph = new Paragraph("Stock de almacén:", font);
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

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2f, 2f, 2.5f});
        table.setSpacingBefore(5);

        writeHeader(table);
        writeTableContent(table);

        document.add(table);
        document.close();
    }


}
