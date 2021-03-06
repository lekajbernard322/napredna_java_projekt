package hr.tvz.napredna.java.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ZadatakFormModel {
	
	private Integer id;
	
	@NotNull(message = "{validacija.not_null}")
	@Size(min = 5, max = 50, message = "{validacija.size_5_50}")
	private String ime;
	
	@Size(max = 500, message = "{validacija.size_500}")
	private String opis;
	
	private String tip;
	
	private String prioritet;
	
	private int procjenaVremena;
	
	private Timestamp datumOcekivano;
	
	private Korisnik assignee;
	
	private Projekt projekt;
	
	public ZadatakFormModel() { }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getProcjenaVremena() {
		return procjenaVremena;
	}

	public void setProcjenaVremena(int procjenaVremena) {
		this.procjenaVremena = procjenaVremena;
	}

	public Korisnik getAssignee() {
		return assignee;
	}

	public void setAssignee(Korisnik assignee) {
		this.assignee = assignee;
	}

	public Timestamp getDatumOcekivano() {
		return datumOcekivano;
	}

	public void setDatumOcekivano(Timestamp datumOcekivano) {
		this.datumOcekivano = datumOcekivano;
	}

	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getPrioritet() {
		return prioritet;
	}

	public void setPrioritet(String prioritet) {
		this.prioritet = prioritet;
	}

}
