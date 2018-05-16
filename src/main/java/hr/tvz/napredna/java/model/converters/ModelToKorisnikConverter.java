package hr.tvz.napredna.java.model.converters;

import org.springframework.core.convert.converter.Converter;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.KorisnikFormModel;

public class ModelToKorisnikConverter implements Converter<KorisnikFormModel, Korisnik>{
	
	@Override
	public Korisnik convert(KorisnikFormModel model) {
		Korisnik korisnik = new Korisnik();
		
		korisnik.setId(model.getId());
		korisnik.setKorisnickoIme(model.getKorisnickoIme());
		korisnik.setIme(model.getIme());
		korisnik.setPrezime(model.getPrezime());
		korisnik.setLozinka(model.getLozinka());
		
		return korisnik;
	}

}
