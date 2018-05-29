package hr.tvz.napredna.java.model.converters;

import org.springframework.core.convert.converter.Converter;

import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.model.ProjektFormModel;

public class ModelToProjektConverter implements Converter<ProjektFormModel, Projekt> {

	@Override
	public Projekt convert(ProjektFormModel model) {
		Projekt projekt = new Projekt();
		
		if (model.getId() != null)
			projekt.setId(model.getId());
		projekt.setIme(model.getIme());
		projekt.setOpis(model.getOpis());
		projekt.getKorisnici().addAll(model.getKorisnici());
		
		return projekt;
	}

}
