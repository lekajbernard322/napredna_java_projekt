package hr.tvz.napredna.java.web.controller;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.KorisnikProjekt;
import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.repository.KorisnikProjektRepository;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import hr.tvz.napredna.java.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/projekt")
public class ProjektController {

	@Autowired
	ProjektRepository projekti;
	
    @Autowired
	KorisnikRepository korisnici;
    
    @Autowired
	KorisnikProjektRepository korisniciProjekt;

	@GetMapping("/lista")
	public String lista(Principal principal, Model model) {
		Korisnik korisnik = korisnici.findByKorisnickoIme(principal.getName());

		model.addAttribute("projekti", projekti.findAllByKorisnici(korisnik));
		model.addAttribute("korisnik", korisnik.getKorisnickoIme());

		return "projekt/lista";
	}
	@GetMapping("/detalji")
	public String detail(Model model,Integer id) {
		List<Projekt> pro = new ArrayList<>();
		List<Korisnik> kor = new ArrayList<>();
		Integer odabran = 0;
		List<KorisnikProjekt> korpro = new ArrayList<>();
		for(Projekt p : projekti.findAll())
			pro.add(p);
		for(KorisnikProjekt kp : korisniciProjekt.findAll())

		    if ( kp.getProjekt_id().equals(id)) {
                kor.add(korisnici.getOne(kp.getKorisnik_id()));
            }
        model.addAttribute("projekt",pro.get(id-1));
        model.addAttribute("korisnici",kor);


		return "projekt/detalji";
	}

}
