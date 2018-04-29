package com.xantrix.webapp.testunit;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
 
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.runners.MethodSorters;

import com.xantrix.webapp.Application;
import com.xantrix.webapp.entities.Promo;
import com.xantrix.webapp.repository.PromoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PromoRestTest
{
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private PromoRepository promoRepository;

	@Before
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	
	@Test
	public void A_testSelTutti() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/promo")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andDo(print());
	}
	
	@Test
	public void B_testInserimento() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/promo/inserisci").contentType(MediaType.APPLICATION_JSON)
				.content("{    \"idPromo\": \"959325BE-B4F0-4F95-9DA4-0A3E5F3858A2\",    \"anno\": 2018,    \"codice\": \"AP01\",    \"descrizione\": \"PROMO TEST 1\",    \"dettPromo\": [        {            \"id\": \"-1\",            \"riga\": 1,            \"codart\": \"000016901\",            \"codfid\": null,            \"inizio\": \"2018-04-01\",            \"fine\": \"2018-05-30\",            \"oggetto\": \"0,29\",            \"isfid\": \"No\",            \"tipoPromo\": {                \"descrizione\": \"TAGLIO PREZZO\",                \"id\": \"1\"            }        }    ],    \"depRifPromo\": []}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
		
		assertThat(promoRepository.findAll()).hasSize(4);
		
		assertThat(promoRepository.findByIdPromo("959325BE-B4F0-4F95-9DA4-0A3E5F3858A2"))
		.extracting(Promo::getIdPromo)
		.containsOnly("959325BE-B4F0-4F95-9DA4-0A3E5F3858A2", "959325BE-B4F0-4F95-9DA4-0A3E5F3858A2");
	}
	
	@Test
	public void C_listPromoById() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/promo/id/959325BE-B4F0-4F95-9DA4-0A3E5F3858A2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.idPromo").exists())
				.andExpect(jsonPath("$.idPromo").value("959325BE-B4F0-4F95-9DA4-0A3E5F3858A2"))
				.andExpect(jsonPath("$.anno").exists())
				.andExpect(jsonPath("$.anno").value("2018"))
				.andExpect(jsonPath("$.dettPromo[0].id").exists())
				.andExpect(jsonPath("$.dettPromo[0].riga").exists())
				.andExpect(jsonPath("$.dettPromo[0].riga").value("1")) 
				.andExpect(jsonPath("$.dettPromo[0].tipoPromo.descrizione").exists())
				.andExpect(jsonPath("$.dettPromo[0].tipoPromo.descrizione").value("TAGLIO PREZZO")) 
				.andDo(print());
	}
	
	@Test
	public void D_testModifica() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/promo/inserisci").contentType(MediaType.APPLICATION_JSON)
				.content("{    \"idPromo\": \"959325BE-B4F0-4F95-9DA4-0A3E5F3858A2\",    \"anno\": 2018,    \"codice\": \"AP01\",    \"descrizione\": \"PROMO TEST 2\",    \"dettPromo\": [        {            \"id\": \"-1\",            \"riga\": 1,            \"codart\": \"000016901\",            \"codfid\": 67500123,            \"inizio\": \"2018-04-01\",            \"fine\": \"2018-05-30\",            \"oggetto\": \"0,29\",            \"isfid\": \"No\",            \"tipoPromo\": {                \"descrizione\": \"TAGLIO PREZZO\",                \"id\": \"1\"            }        }    ],    \"depRifPromo\": []}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
		
		assertThat(promoRepository.findByIdPromo("959325BE-B4F0-4F95-9DA4-0A3E5F3858A2"))
		.extracting(Promo::getDescrizione)
		.containsOnly("PROMO TEST 2", "PROMO TEST 2");
		
	}
	
	@Test
	public void E_PromoExceptionTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/promo/id/959325BE-B4F0-4F95-9DA4-0A3E5F3858S2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(print());
	}
	
	@Test
	public void F_deletePromo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.delete("/promo/959325BE-B4F0-4F95-9DA4-0A3E5F3858A2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(print());
		
		assertThat(promoRepository.findAll()).hasSize(3);
	}
}
