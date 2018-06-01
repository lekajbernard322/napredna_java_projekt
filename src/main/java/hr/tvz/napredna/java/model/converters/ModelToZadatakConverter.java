package hr.tvz.napredna.java.model.converters;


import org.springframework.core.convert.converter.Converter;

import hr.tvz.napredna.java.model.Zadatak;
import hr.tvz.napredna.java.model.ZadatakFormModel;

public class ModelToZadatakConverter implements Converter<ZadatakFormModel, Zadatak> {
	@Override
	public Zadatak convert(ZadatakFormModel model) {
		Zadatak zadatak = new Zadatak();
		
		if (model.getId() != null)
			zadatak.setId(model.getId());
			zadatak.setIme(model.getIme());
			zadatak.setOpis(model.getOpis());
			zadatak.setTip(model.getTip());
			zadatak.setPrioritet(model.getPrioritet());
			zadatak.setProcjenaVremena(model.getProcjenaVremena());
			zadatak.setDatumOcekivano(model.getDatumOcekivano());
			zadatak.setAssignee(model.getAssignee());
			zadatak.setProjekt(model.getProjekt());
		
		return zadatak;
	}
}