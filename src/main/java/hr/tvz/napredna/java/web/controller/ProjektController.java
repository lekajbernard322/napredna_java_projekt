package hr.tvz.napredna.java.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.tvz.napredna.java.enums.StanjeZadatka;
import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.model.ProjektFormModel;
import hr.tvz.napredna.java.model.Zadatak;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import hr.tvz.napredna.java.repository.ProjektRepository;
import hr.tvz.napredna.java.repository.ZadatakRepository;
import hr.tvz.napredna.java.util.SecurityUtils;


@Controller
@RequestMapping("/projekt")
public class ProjektController {
	
	@Autowired
	private ConversionService conversionService;

	@Autowired
	private ProjektRepository projektRepository;
	
    @Autowired
	private KorisnikRepository korisnikRepository;

    @Autowired
    private ZadatakRepository zadatakRepository;

	@GetMapping("/lista")
	public String lista(Principal principal, Model model, Authentication auth) {
		Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());
        if (SecurityUtils.hasAnyRole(auth, "ROLE_ADMIN")){
		model.addAttribute("projekti", projektRepository.findAll());}else{
		    model.addAttribute("projekti", projektRepository.findAllByKorisnici(korisnik));}
		model.addAttribute("korisnik", korisnik.getKorisnickoIme());

		return "projekt/lista";
	}
	
	@GetMapping("/detalji")
	public String detail(Model model,Integer id, Principal principal) {
		Optional<Projekt> pro = projektRepository.findById(id);
		List<Zadatak> zadaci = new ArrayList<>();
		pro.ifPresent(p -> zadaci.addAll(zadatakRepository.findAllByProjekt(p)));
		
		pro.orElseThrow(() -> new RuntimeException("Projekt sa tim ID ne postoji!"));

        model.addAttribute("projekt",pro.get());
        model.addAttribute("korisnici",pro.get().getKorisnici());
        
        model.addAttribute("zadaci", zadaci);
		model.addAttribute("stanja", StanjeZadatka.values());
		model.addAttribute("showFilter", Boolean.FALSE);

		return "projekt/detalji";
	}
	
	@GetMapping("/novi")
	public String noviProjekt(Model model) {
		
		List<Korisnik> sviKorisnici = korisnikRepository.findAll();
		
		model.addAttribute("sviKorisnici", sviKorisnici);
		model.addAttribute("projektFormModel", new ProjektFormModel());
		model.addAttribute("isEdit", Boolean.FALSE);
		
		return "projekt/stvoriUredi";
	}
	
	@PostMapping("/novi")
	public String spremiNoviProjekt(ProjektFormModel projektFormModel) {
		
		Projekt projekt = conversionService.convert(projektFormModel, Projekt.class);
		
		projekt = projektRepository.saveAndFlush(projekt);
		
		return "redirect:/projekt/lista";
	}
	
	@GetMapping("/uredi/{id}")
	public String urediProjekt(Model model, @PathVariable("id") Integer id) throws RuntimeException {
		
		Optional<Projekt> projekt = projektRepository.findById(id);
		List<Korisnik> sviKorisnici = korisnikRepository.findAll();
		
		Projekt p = projekt.orElseThrow(() -> new RuntimeException("Projekt sa id " + id + " ne postoji!"));
		
		ProjektFormModel projektFormModel = conversionService.convert(p, ProjektFormModel.class);
		
		model.addAttribute("sviKorisnici", sviKorisnici);
		model.addAttribute("projektFormModel", projektFormModel);
		model.addAttribute("isEdit", Boolean.TRUE);
		
		return "projekt/stvoriUredi";
	}
	
	@PostMapping("/uredi")
	public String spremiUredeniProjekt(ProjektFormModel projektFormModel) throws RuntimeException {
		
		Optional<Projekt> projekt = projektRepository.findById(projektFormModel.getId());

		Projekt p = projekt.orElseThrow(() -> new RuntimeException("Projekt sa id " + projektFormModel.getId() + " ne postoji!"));
		
		p.setIme(projektFormModel.getIme());
		p.setOpis(projektFormModel.getOpis());
		p.getKorisnici().clear();
		p.getKorisnici().addAll(projektFormModel.getKorisnici());
		
		projektRepository.save(p);
		
		return "redirect:/projekt/lista";
	}

}