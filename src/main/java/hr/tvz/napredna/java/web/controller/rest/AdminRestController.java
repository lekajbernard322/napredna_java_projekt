package hr.tvz.napredna.java.web.controller.rest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.napredna.java.model.KorisnikFormModel;
import hr.tvz.napredna.java.util.SecurityUtils;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
	
	@Autowired
	private SessionRegistry sessionRegistryImpl;

	@PostMapping("/invalidirajSesiju")
	public ResponseEntity<?> invalidirajSesiju(@RequestBody KorisnikFormModel korisnikFormModel) {
		
		if (korisnikFormModel.getKorisnickoIme() == null)
			throw new RuntimeException("Korisničko ime je potrebno za invalidaciju sesije.");
		
		SecurityUtils.invalidirajSesiju(
				sessionRegistryImpl, 
				korisnikFormModel.getKorisnickoIme());
		
		return new ResponseEntity<>(
				Collections.singletonMap(
						"poruka", "Sesija uspješno invalidirana."),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/listaUlogiranihKorisnika")
	public ResponseEntity<?> listaUlogiranihKorisnika() {
		List<String> listaKorisnika = sessionRegistryImpl
				.getAllPrincipals()
					.stream()
						.map(o -> (User)o)
						.map(u -> u.getUsername())
						.collect(Collectors.toList());
		
		return new ResponseEntity<>(
				Collections.singletonMap(
						"korisnici", listaKorisnika),
				HttpStatus.OK);
	}
	
	@GetMapping("/dohvatiSesijeZaKorisnika")
	public ResponseEntity<?> dohvatiSesijeZaKorisnika(@RequestParam("korisnickoIme") String korisnickoIme) {
		Optional<Object> user = sessionRegistryImpl
				.getAllPrincipals()
				.stream()
					.filter(u -> ((User)u).getUsername().equals(korisnickoIme))
					.findFirst();
		
		user.orElseThrow(() -> new RuntimeException("Ne postoji Principal za tog korisnika."));
		
		return new ResponseEntity<>(
				Collections.singletonMap(
						"sesije", sessionRegistryImpl.getAllSessions(user.get(), false)), 
				HttpStatus.OK);
		
	}
	
}
