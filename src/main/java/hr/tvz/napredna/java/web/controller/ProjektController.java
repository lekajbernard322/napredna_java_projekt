package hr.tvz.napredna.java.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projekt")
public class ProjektController {
	
	@GetMapping("/lista")
	public String lista() {
		return "projekt/lista.html";
	}

}
