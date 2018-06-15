package hr.tvz.napredna.java.web.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hr.tvz.napredna.java.model.Komentar;
import hr.tvz.napredna.java.model.KomentarFormModel;
import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Zadatak;
import hr.tvz.napredna.java.model.ZadatakFormModel;
import hr.tvz.napredna.java.repository.KomentarRepository;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import hr.tvz.napredna.java.repository.ProjektRepository;
import hr.tvz.napredna.java.repository.ZadatakRepository;

@Controller
@RequestMapping("/zadatak")
public class ZadatakController {
	
	@Autowired
	private ConversionService conversionService;

	@Autowired
	ProjektRepository projektRepository;
	
    @Autowired
	KorisnikRepository korisnikRepository;
    
    @Autowired
	ZadatakRepository zadatakRepository;
    
    @Autowired
	KomentarRepository komentarRepository;
	
    @InitBinder
    public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
        new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                    setValue(new Timestamp(parsedDate.getTime()));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });
    }
    
	@GetMapping("/novi")
	public String noviZadatak(Model model) {
		
		model.addAttribute("projekti", projektRepository.findAll());
		model.addAttribute("korisnici", korisnikRepository.findAll());
		model.addAttribute("zadatakFormModel", new ZadatakFormModel());
		
		return "zadatak/novi";
	}
	
	@PostMapping("/novi")
	public String spremiNoviZadatak(@Valid ZadatakFormModel zadatakFormModel, BindingResult result, Model model, Principal principal) {
		
		if (result.hasErrors()) {
			model.addAttribute("projekti", projektRepository.findAll());
			model.addAttribute("korisnici", korisnikRepository.findAll());
			return "zadatak/novi";
		}
		
		Zadatak zadatak = conversionService.convert(zadatakFormModel, Zadatak.class);
		zadatak.setReporter(korisnikRepository.findByKorisnickoIme(principal.getName()));
		zadatak.setStanje("TO DO");
		zadatak.setDatumStvoren(new Timestamp(System.currentTimeMillis()));
		zadatak.setDatumUredivan(new Timestamp(System.currentTimeMillis()));
		
		zadatakRepository.save(zadatak);
		
		return "redirect:/zadatak/detalji?id="+zadatak.getId();
	}
	
	@GetMapping("/detalji")
	public String edtaljiZadatka(@RequestParam Integer id, Model model, Principal principal) {
		Zadatak zadatak = zadatakRepository.findById(id).orElse(null);
		List<Komentar> komentari = new ArrayList<>(); 
		for(Komentar k : zadatak.getKomentari())
			komentari.add(k);
		model.addAttribute("zadatak", zadatak);
		model.addAttribute("komentari", komentari);
		model.addAttribute("principal", principal);
		model.addAttribute("KomentarFormModel", new KomentarFormModel());
		return "zadatak/detalji";
	}
	
	@PostMapping("/noviKomentar")
	public String spremiKomentar(KomentarFormModel komentarFormModel, Model model, Principal principal) {
		Korisnik k = korisnikRepository.findByKorisnickoIme(principal.getName());
		Integer zadatakId = komentarFormModel.getZadatakId();
		Komentar komentar = new Komentar(komentarFormModel.getTekst(), zadatakRepository.findById(zadatakId).orElse(null), k, new Timestamp(System.currentTimeMillis()));
		komentarRepository.save(komentar);
		model.addAttribute("KomentarFormModel", new KomentarFormModel());
		return "redirect:/zadatak/detalji?id="+zadatakId;
	}
}