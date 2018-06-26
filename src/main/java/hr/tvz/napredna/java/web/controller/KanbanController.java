package hr.tvz.napredna.java.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hr.tvz.napredna.java.enums.StanjeZadatka;
import hr.tvz.napredna.java.model.Filter;
import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Zadatak;
import hr.tvz.napredna.java.repository.FilterRepository;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import hr.tvz.napredna.java.repository.ZadatakRepository;

@Controller
@RequestMapping("/kanban")
public class KanbanController {
	
	@Autowired
	private ZadatakRepository zadatakRepository;
	
	@Autowired
	private FilterRepository filterRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@GetMapping
	public String mainView(@RequestParam("filterId") Optional<Integer> filterId, Model model, Principal principal) {
		Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());
		List<Zadatak> zadaci = new ArrayList<>();
		if (filterId.isPresent()) {
			if (korisnik == null) throw new RuntimeException("Korisnik sa tim korisničkim imenom ne postoji.");
			Filter filter = filterRepository.findByIdAndKorisnik(filterId.get(), korisnik)
					.orElseThrow(() -> 
						new RuntimeException("Filter sa id=" + filterId + " i korisnik=" + korisnik.getKorisnickoIme() + " ne postoji."));
			
			zadaci = zadatakRepository.findAllByAssigneeInAndProjektIn(filter.getKorisnici(), filter.getProjekti());
		} else {
			zadaci = zadatakRepository.findAllByAssignee(korisnik);
		}
		
		model.addAttribute("zadaci", zadaci);
		model.addAttribute("stanja", StanjeZadatka.values());
		model.addAttribute("filtri", filterRepository.findAllByKorisnik(korisnik));
		model.addAttribute("showFilter", Boolean.TRUE);
		
		return "kanban/ploca";
	}
	
}
