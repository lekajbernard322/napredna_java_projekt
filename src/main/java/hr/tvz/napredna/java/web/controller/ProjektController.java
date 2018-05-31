package hr.tvz.napredna.java.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.model.ProjektFormModel;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import hr.tvz.napredna.java.repository.ProjektRepository;


@Controller
@RequestMapping("/projekt")
public class ProjektController {
	
	@Autowired
	private ConversionService conversionService;

	@Autowired
	ProjektRepository projektRepository;
	
    @Autowired
	KorisnikRepository korisnikRepository;


	@GetMapping("/lista")
	public String lista(Principal principal, Model model) {
		String korisnickoIme = principal.getName();

        if (korisnickoIme.equalsIgnoreCase("admin")){
			model.addAttribute("projekti", projektRepository.findAll());}
		else{
		    model.addAttribute("projekti", projektRepository.findAllByKorisnici_KorisnickoIme(korisnickoIme));}

		model.addAttribute("korisnik", korisnickoIme);

		return "projekt/lista";
	}
	
	@GetMapping("/detalji")
	public String detail(Model model,Integer id) {
		List<Projekt> pro = projektRepository.findAll();


        model.addAttribute("projekt",pro.get(id-1));
        model.addAttribute("korisnici",pro.get(id-1).getKorisnici());


		return "projekt/detalji";
	}
	
	@GetMapping("/novi")
	public String noviProjekt(Model model) {
		
		List<Korisnik> sviKorisnici = korisnikRepository.findAll();
		
		model.addAttribute("sviKorisnici", sviKorisnici);
		model.addAttribute("projektFormModel", new ProjektFormModel());
		
		return "projekt/stvoriUredi";
	}
	
	@PostMapping("/novi")
	public String spremiNoviProjekt(ProjektFormModel projektFormModel) {
		
		Projekt projekt = conversionService.convert(projektFormModel, Projekt.class);
		
		projekt = projektRepository.save(projekt);
		
		return "redirect:/projekt/detalji?id=" + projekt.getId();
	}
	
	@GetMapping("/uredi/{id}")
	public String urediProjekt(Model model, @PathVariable("id") Integer id) throws Exception {
		
		Optional<Projekt> projekt = projektRepository.findById(id);
		
		projekt.orElseThrow(() -> new Exception("Projekt sa id " + id + " ne postoji!"));
		
		ProjektFormModel projektFormModel = conversionService.convert(projekt, ProjektFormModel.class);
		
		model.addAttribute("projektFormModel", projektFormModel);
		
		return "projekt/stvoriUredi";
	}

}
