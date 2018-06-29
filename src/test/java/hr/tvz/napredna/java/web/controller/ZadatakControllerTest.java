package hr.tvz.napredna.java.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ZadatakControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testNoviZadatak() throws Exception{
		this.mockMvc
		.perform(get("/zadatak/novi")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("projekti", "korisnici"))
		.andExpect(model().attribute("projekti", Matchers.hasSize(5)))
		.andExpect(model().attribute("korisnici", Matchers.hasSize(6)))
		.andExpect(view().name("zadatak/novi"));
	}
	
	@Test
	public void testSpremiNoviZadatak() throws Exception{
		this.mockMvc
		.perform(post("/zadatak/novi")
				.param("projekt", "1")
				.param("ime", "Test ime")
				.param("opis", "Test opis")
				.param("tip", "Bug")
				.param("prioritet", "High")
				.param("procjenaVremena", "5")
				.param("datumOcekivano", "2018-07-01")
				.param("assignee", "1")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().is3xxRedirection());
		
		this.mockMvc
		.perform(post("/zadatak/novi")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(view().name("zadatak/novi"));	
	}
	
	@Test
	public void testDetaljiZadatka() throws Exception{
		this.mockMvc
		.perform(get("/zadatak/detalji?id=1")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("zadatak", "komentari"));	
	}
	
	@Test
	public void testSpremiKomentar() throws Exception{
		this.mockMvc
		.perform(post("/zadatak/noviKomentar")
				.param("tekst", "test komentar")
				.param("zadatakId", "1")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testPromijeniStanje() throws Exception{
		this.mockMvc
		.perform(post("/zadatak/promijeniStanje")
				.param("id", "1")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().is3xxRedirection());
	}

}
