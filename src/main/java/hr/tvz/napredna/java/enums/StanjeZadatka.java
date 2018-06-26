package hr.tvz.napredna.java.enums;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public enum StanjeZadatka {
	TO_DO, IMPLEMENTATION, TESTING, DONE;
	
	static NavigableSet<StanjeZadatka> stanja = 
			new TreeSet<>(Arrays.asList(StanjeZadatka.values()));
	
	public static StanjeZadatka getNextState(StanjeZadatka stanje) {
		return stanja.higher(stanje);
	}
}
