package hr.tvz.napredna.java.web.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistracijaControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegistracijaView() throws Exception{
        this.mockMvc
                .perform(get("/registracija"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegistracijaNakonLogina() throws Exception{
        this.mockMvc
                .perform(get("/registracija")
                        .with(user("admin").password("password").roles("USER", "ADMIN")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testKreirajKorisnika() throws Exception{
        this.mockMvc
                .perform(post("/registracija")
                    .param("korisnickoIme", "Test123")
                    .param("ime", "TestIme")
                    .param("prezime", "TestPrezime")
                    .param("lozinka", "TestLozinka")
                    .param("lozinkaPotvrda", "TestLozinka"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testNepotpunoKreirajKorisnika() throws Exception{
        this.mockMvc
                .perform(post("/registracija")
                        .param("korisnickoIme", "Test123")
                        .param("ime", "TestIme")
                        .param("prezime", "TestPrezime"))
                .andExpect(view().name("registracija/registracija.html"));
    }

    @Test
    public void testValidacijaPodatakaKorisnika() throws Exception{
        this.mockMvc
                .perform(post("/registracija")
                        .param("korisnickoIme", "T")
                        .param("ime", "T")
                        .param("prezime", "TestPrezime")
                        .param("lozinka", "TestLozinka")
                        .param("lozinkaPotvrda", "TestLozinka"))
                .andExpect(view().name("registracija/registracija.html"));
    }


}
