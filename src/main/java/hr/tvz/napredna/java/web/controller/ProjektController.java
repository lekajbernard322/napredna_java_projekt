package hr.tvz.napredna.java.web.controller;

import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projekt")
public class ProjektController {

	@Autowired
	ProjektRepository projekti;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		List<Projekt> pro = new ArrayList<>();
		for(Projekt p : projekti.findAll())
			pro.add(p);

		model.addAttribute("projekti", pro);

		return "projekt/lista";
	}

}
