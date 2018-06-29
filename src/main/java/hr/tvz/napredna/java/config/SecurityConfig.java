package hr.tvz.napredna.java.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(
					"select korisnicko_ime username, lozinka password, aktivan enabled from korisnik where korisnicko_ime=?")
			.authoritiesByUsernameQuery(
					"select korisnik username, uloga role from korisnik_uloga where korisnik=?")
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/registracija").anonymous()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/prijava")
					.defaultSuccessUrl("/projekt/lista", true)
					.failureUrl("/prijava?error=true")
					.permitAll()
			.and()
				.logout()
					.permitAll()
					.invalidateHttpSession(true);
		
		http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).expiredUrl("/login");
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
}
