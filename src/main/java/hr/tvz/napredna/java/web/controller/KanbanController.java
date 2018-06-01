package hr.tvz.napredna.java.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.tvz.napredna.java.model.Zadatak;
import hr.tvz.napredna.java.repository.ZadatakRepository;

@Controller
@RequestMapping("/kanban")
public class KanbanController {
	
	@Autowired
	private ZadatakRepository zadatakRepository;
	
	@GetMapping
	public String mainView(Model model) {
		
		List<Zadatak> zadaci = zadatakRepository.findAll();
		
		List<String> stanja = new ArrayList<>();
		stanja.add("To do");
		stanja.add("Implementation");
		stanja.add("Testing");
		stanja.add("Done");
		
		model.addAttribute("zadaci", zadaci);
		model.addAttribute("stanja", stanja);
		
		return "kanban/ploca";
	}
	
}
