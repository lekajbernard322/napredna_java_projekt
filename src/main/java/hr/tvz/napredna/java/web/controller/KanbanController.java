package hr.tvz.napredna.java.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kanban")
public class KanbanController {
	
	@GetMapping
	public String mainView(Model model) {
		
		List<String> stanja = new ArrayList<>();
		stanja.add("To do");
		stanja.add("Implementation");
		stanja.add("Testing");
		stanja.add("Done");
		
		List<Zadatak> zadaci = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Zadatak zadatak = new Zadatak();
			zadatak.setId(i);
			zadatak.setNaslov("Zadatak" + i);
			zadatak.setOpis("Lorem ipsum dolor sit amet, consectetur adipiscing elit." + i);
			zadatak.setStanje(stanja.get(i % stanja.size()));
			zadaci.add(zadatak);
		}
		
		model.addAttribute("zadaci", zadaci);
		model.addAttribute("stanja", stanja);
		
		return "kanban/ploca";
	}
	
	static class Zadatak {
		private Integer id;
		private String naslov;
		private String opis;
		private String stanje;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNaslov() {
			return naslov;
		}
		public void setNaslov(String naslov) {
			this.naslov = naslov;
		}
		public String getOpis() {
			return opis;
		}
		public void setOpis(String opis) {
			this.opis = opis;
		}
		public String getStanje() {
			return stanje;
		}
		public void setStanje(String stanje) {
			this.stanje = stanje;
		}
	}
	
}
