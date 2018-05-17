package hr.tvz.napredna.java.web.controller;

import hr.tvz.napredna.java.model.Projekt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrijavaController {

	@GetMapping("/prijava")
	public String prijava() {
		return "prijava/prijava.html";
	}
	
}
