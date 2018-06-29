package hr.tvz.napredna.java.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjektControllerTest {
	
	@Autowired
	private MockMvc mockMvc;	

	@Test
	public void testListaProjekata() throws Exception{
		this.mockMvc
			.perform(get("/projekt/lista")
					.with(user("admin").password("password").roles("USER", "ADMIN")))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("projekti", "korisnik"))
			.andExpect(view().name("projekt/lista"));	
	}
	
	@Test
	public void testNoviProjekt() throws Exception{
		this.mockMvc
			.perform(get("/projekt/novi")
					.with(user("admin").password("password").roles("USER", "ADMIN")))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("sviKorisnici", "projektFormModel"))
			.andExpect(view().name("projekt/stvoriUredi"));
	}
	
	@Test
	public void testSpremiNoviProjekt() throws Exception {
		this.mockMvc
		.perform(post("/projekt/novi")
				.param("ime", "Test ime")
				.param("opis", "Test opis")
				.with(user("admin").password("password").roles("USER", "ADMIN")))
		.andExpect(status().is3xxRedirection());
	}
	
	@Test(expected = NestedServletException.class)
	public void testSpremiUrediProjektBacaException() throws Exception {
		this.mockMvc
		.perform(get("/projekt/uredi/100")
				.with(user("admin").password("password").roles("USER", "ADMIN")));
	}
	
}
