package hr.tvz.napredna.java.web.controller.rest;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.KorisnikFormModel;
import hr.tvz.napredna.java.repository.KorisnikRepository;

@RestController
@RequestMapping("/api/postavke")
public class PostavkeRestController {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;	
	
	@PostMapping("/promjenaLozinke")
	public ResponseEntity<?> promjenaLozinke(@RequestBody KorisnikFormModel korisnikFormModel, Principal principal) {
		Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());
		
		if (korisnik == null)
			throw new RuntimeException("Korisnik sa tim ID ne postoji.");
		String lozinka = korisnikFormModel.getLozinka();
		String lozinkaPotvrda = korisnikFormModel.getLozinkaPotvrda();
		
		if (lozinka == null || lozinkaPotvrda == null)
			throw new RuntimeException("Lozinka nije prisutna.");
		
		if (!lozinka.equals(lozinkaPotvrda))
			throw new RuntimeException("Lozinke se ne podudaraju.");
		
		korisnik.setLozinka(passwordEncoder.encode(lozinka));
		
		korisnikRepository.save(korisnik);
		
		return new ResponseEntity<>(
				Collections.singletonMap("poruka", "Lozinka uspješno promijenjena."),
				HttpStatus.OK);
	}
	
}
