package hr.tvz.napredna.java.web.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testNoviFilter() throws Exception{
		this.mockMvc
		.perform(get("/filter/novi")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("sviKorisnici", "sviProjekti"))
		.andExpect(model().attribute("sviKorisnici", Matchers.notNullValue()))
		.andExpect(model().attribute("sviProjekti", Matchers.notNullValue()))
		.andExpect(view().name("filter/stvoriUredi"));
	}
	
	@Test
	public void testSpremiNoviFilter() throws Exception{
		this.mockMvc
		.perform(post("/filter/novi")
				.param("ime", "Time")
				.param("opis", "Topis")
				.param("korisnici", "1")
				.param("projekti", "1")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().is3xxRedirection());
		
		this.mockMvc
		.perform(post("/filter/novi")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
				.andExpect(redirectedUrl("/filter/lista"));

	}

}
