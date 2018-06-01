package hr.tvz.napredna.java.web.controller;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.KorisnikFormModel;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/korisnik")
public class PostavkeController {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/postavke")
    public String registracija(Model model) {
        model.addAttribute("korisnikFormModel", new KorisnikFormModel());
        return "korisnik/postavke";
    }

    @PostMapping("/postavke")
    public String registracija(@Valid KorisnikFormModel korisnikFormModel, BindingResult result, Principal principal, @RequestParam(name = "staraLozinka", required = false)String staraLozinka) {

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());

        if (!korisnikFormModel.getLozinka().equals(korisnikFormModel.getLozinkaPotvrda())) {
            result.rejectValue(
                    "lozinkaPotvrda",
                    "lozinkaPotvrda",
                    "Lozinke se ne podudaraju!");
            return "korisnik/postavke";
        }
        if(result.hasFieldErrors("lozinka")){
            return "korisnik/postavke";
        }


        korisnik.setLozinka(
                passwordEncoder.encode(korisnikFormModel.getLozinka()));
        korisnikRepository.save(korisnik);

        return "redirect:/projekt/lista";
    }

}
