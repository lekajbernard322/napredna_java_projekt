package hr.tvz.napredna.java.web.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.tvz.napredna.java.model.Zadatak;
import hr.tvz.napredna.java.model.ZadatakFormModel;
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
	
    @InitBinder
    public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
        new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    Date parsedDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(value);
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
	public String spremiNoviZadatak(ZadatakFormModel zadatakFormModel, Principal principal) {
				
		Zadatak zadatak = conversionService.convert(zadatakFormModel, Zadatak.class);
		zadatak.setReporter(korisnikRepository.findByKorisnickoIme(principal.getName()));
		zadatak.setStanje("TO DO");
		zadatak.setDatumStvoren(new Timestamp(System.currentTimeMillis()));
		zadatak.setDatumUredivan(new Timestamp(System.currentTimeMillis()));
		
		zadatak = zadatakRepository.save(zadatak);
		
		return "redirect:/kanban";
	}	
}