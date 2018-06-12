package hr.tvz.napredna.java.util;

import java.util.Optional;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {
	
	public static void invalidirajSesiju(SessionRegistry sessionRegistryImpl, String korisnickoIme) {
		Optional<User> user = sessionRegistryImpl
				.getAllPrincipals()
				.stream()
					.map(o -> (User)o)
					.filter(u -> u.getUsername().equals(korisnickoIme))
					.findFirst();
			
			user.orElseThrow(() -> new RuntimeException("Ne postoji Principal za tog korisnika."));
			
			sessionRegistryImpl
				.getAllSessions(user.get(), false)
				.forEach(s -> s.expireNow());
	}

}
