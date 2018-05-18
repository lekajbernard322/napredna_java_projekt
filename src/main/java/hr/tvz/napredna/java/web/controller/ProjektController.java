package hr.tvz.napredna.java.web.controller;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.KorisnikProjekt;
import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.repository.KorisnikProjektRepository;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import hr.tvz.napredna.java.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

//TODO kada promjenis CrudRepository u JpaRepository findAll() ce ti vracati direktno ArrayList, pa tu listu onda mozes odmah staviti u model
//TODO staviti naslov na projekt/lista.html
//TODO unutar <tr> elementa su dozvoljeni samo <td> i <th> elementi, tako da makni ovaj <a> element

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
	public String lista(Model model) {
		List<Projekt> pro = new ArrayList<>();
		for(Projekt p : projekti.findAll())
			pro.add(p);

		model.addAttribute("projekti", pro);

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
