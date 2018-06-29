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
public class PostavkeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testNovaLozinkaSifra() throws Exception{
		this.mockMvc
		.perform(get("/korisnik/postavke")
				.with(user("korisnik1").password("password").roles("USER", "KORISNIK")))
		.andExpect(status().isOk())
		.andExpect(view().name("korisnik/postavke"));
	}
	
	@Test
	public void testSpremiNovuLozinku() throws Exception{
		this.mockMvc
		.perform(post("/korisnik/postavke")
				.param("lozinka", "password1")
				.param("lozinkaPotvrda", "password1")
				.with(user("korisnik1").password("password").roles("USER", "KORISNIK")))
		.andExpect(status().is3xxRedirection());

	}

	@Test
	public void testLozinka() throws Exception{
		this.mockMvc
				.perform(get("/korisnik/postavke")
						.with(user("korisnik1").password("password1").roles("USER", "KORISNIK")))
				.andExpect(status().isOk())
				.andExpect(view().name("korisnik/postavke"));
	}
}
