package com.xantrix.webapp.views;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.xantrix.webapp.domain.Articoli;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CsvView extends MyAbstractCsvView
{

	@Override
	protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{

		response.setHeader("Content-Disposition", "attachment; filename=\"prodotti.csv\"");

		@SuppressWarnings({ "unchecked" })
		List<Articoli> articoli = (List<Articoli>) model.get("Articoli");
		
		String[] header = { "CodArt", "Descrizione", "Prezzo", "Um", "IDFAMASS"};
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		csvWriter.writeHeader(header);

		for (Articoli articolo : articoli)
		{
			csvWriter.write(articolo, header);
		}
		
		csvWriter.close();

	}
}
