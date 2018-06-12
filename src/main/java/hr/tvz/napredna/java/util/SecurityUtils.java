package hr.tvz.napredna.java.util;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.core.Authentication;
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
	
	public static boolean hasAnyRole(Authentication auth, String... roles) {
		
		if (Arrays.stream(roles).anyMatch(role -> !role.startsWith("ROLE_")))
			throw new IllegalArgumentException("Uloge moraju pocinjati sa 'ROLE_'");
		
		boolean hasAnyRole = 
				auth.getAuthorities()
					.stream()
					.anyMatch(a -> Arrays.asList(roles).contains(a.getAuthority()));
		
		return hasAnyRole;
	}

}
