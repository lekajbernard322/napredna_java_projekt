package hr.tvz.napredna.java.model.converters;

import org.springframework.core.convert.converter.Converter;

import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.model.ProjektFormModel;

public class ProjektToModelConverter implements Converter<Projekt, ProjektFormModel> {

	@Override
	public ProjektFormModel convert(Projekt projekt) {
		ProjektFormModel model = new ProjektFormModel();
		
		model.setId(projekt.getId());
		model.setIme(projekt.getIme());
		model.setOpis(projekt.getOpis());
		model.getKorisnici().addAll(projekt.getKorisnici());
		
		return model;
	}

}
