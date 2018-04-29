package com.xantrix.webapp.views;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.xantrix.webapp.domain.Articoli;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class PdfView extends MyAbstractPdfView
{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		final String Titolo = "Test Creazione Documento PDF";
		 
		 // change the file name 
        response.setHeader("Content-Disposition", "attachment; filename=\"prodotti.pdf\"");
 
        List<Articoli> articoli = (List<Articoli>) model.get("Articoli");
      
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);
      
 
        // define font for table header row 
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(Color.WHITE);
 
        // define table header cell 
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
 
        // write table header 
        cell.setPhrase(new Phrase("Codice", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Descrizione", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Prezzo", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("UM", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Categoria", font));
        table.addCell(cell);
 
        for(Articoli articolo : articoli)
        {
            table.addCell(articolo.getCodArt());
            table.addCell(articolo.getDescrizione());
            table.addCell(articolo.getPrezzo().toString());
            table.addCell(articolo.getUm());
            table.addCell(articolo.getDesFamAss());
        } 
 
        document.addTitle(Titolo);
        document.add(new Paragraph("Documento Creato il " + LocalDate.now()));
       // document.setPageCount(0);
     
        document.add(table);
    } 
}
