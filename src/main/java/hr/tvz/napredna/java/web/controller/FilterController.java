package hr.tvz.napredna.java.web.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.tvz.napredna.java.model.Filter;
import hr.tvz.napredna.java.model.FilterFormModel;
import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.repository.FilterRepository;
import hr.tvz.napredna.java.repository.KorisnikRepository;
import hr.tvz.napredna.java.repository.ProjektRepository;

@Controller
@RequestMapping("/filter")
public class FilterController {

        @Autowired
        private ConversionService conversionService;

        @Autowired
        FilterRepository filterRepository;

        @Autowired
        KorisnikRepository korisnikRepository;

        @Autowired
        ProjektRepository projektRepository;

        @GetMapping("/lista")
        public String lista(Principal principal, Model model) {
            Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());
            if (korisnik.getKorisnickoIme().equalsIgnoreCase("admin")){
                model.addAttribute("filtri", filterRepository.findAll());}else{
                model.addAttribute("filtri", korisnik.getFiltri());}

            return "filter/lista";
        }


        @GetMapping("/novi")
        public String noviFilter(Model model) {
            List<Korisnik> sviKorisnici = korisnikRepository.findAll();
            List<Projekt> sviProjekti = projektRepository.findAll();
            model.addAttribute("filterFormModel", new FilterFormModel());
            model.addAttribute("sviKorisnici", sviKorisnici);
            model.addAttribute("sviProjekti", sviProjekti);
            return "filter/stvoriUredi";
        }

        @PostMapping("/novi")
        public String spremiNoviFilter(FilterFormModel filterFormModel) {

            Filter filter = conversionService.convert(filterFormModel, Filter.class);

            filter = filterRepository.save(filter);

            return "redirect:/filter/lista";
        }
/*
    @GetMapping("/uredi")
    public String uredi(Model model, Integer id) throws Exception {

        Optional<Filter> filter = filterRepository.findById(id);

        filter.orElseThrow(() -> new Exception("Nema filtera!!"));

       // FilterFormModel filterFormModel = conversionService.convert(filter, FilterFormModel.class);

     ///   model.addAttribute("filterFormModel", filterFormModel);

        return "filter/stvoriUredi";
    }
*/




/*
        @GetMapping("/lista/{id}")
        public String filtriraj(Model model, @PathVariable("id") Integer id) throws Exception {

            Optional<Filter> filter = filterRepository.findById(id);



            ProjektFormModel projektFormModel = conversionService.convert(projekt, ProjektFormModel.class);

            model.addAttribute("projektFormModel", projektFormModel);

            return "projekt/stvoriUredi";

            Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());
            if (korisnik.getKorisnickoIme().equalsIgnoreCase("admin")){
                model.addAttribute("projekti", projektRepository.findAll());}else{
                model.addAttribute("projekti", projektRepository.findAllByKorisnici(korisnik));}
            model.addAttribute("korisnik", korisnik.getKorisnickoIme());

        }
*/
    }

