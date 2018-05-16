package hr.tvz.napredna.java.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.KorisnikFormModel;
import hr.tvz.napredna.java.repository.KorisnikRepository;

@Controller
public class RegistracijaController {
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/registracija")
	public String registracija(Model model) {
		model.addAttribute("korisnikFormModel", new KorisnikFormModel());
		return "registracija/registracija.html";
	}
	
	@PostMapping("/registracija")
	public String registracija(@Valid KorisnikFormModel korisnikFormModel,
			BindingResult result) {
		
		Korisnik korisnikPoKorisnickomImenu = 
				korisnikRepository.findByKorisnickoIme(korisnikFormModel.getKorisnickoIme());
		if (korisnikPoKorisnickomImenu != null) {
			result.rejectValue(
					"korisnickoIme",
					"korisnickoIme", 
					"Korisničko ime već postoji.");
		}
		
		if (!korisnikFormModel.getLozinka().equals(korisnikFormModel.getLozinkaPotvrda())) {
			result.rejectValue(
					"lozinkaPotvrda",
					"lozinkaPotvrda",
					"Lozinke se ne podudaraju!");
		}
		
		if (result.hasErrors()) {
			return "registracija/registracija.html";
		}
		
		Korisnik korisnik = 
				conversionService.convert(korisnikFormModel, Korisnik.class);
		korisnik.setLozinka(
				passwordEncoder.encode(korisnik.getLozinka()));
		korisnik.getUloge().add("ROLE_KORISNIK");
		korisnikRepository.save(korisnik);
		
		return "redirect:/prijava/prijava.html";
	}

}
