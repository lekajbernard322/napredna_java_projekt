package hr.tvz.napredna.java.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http
			.csrf()
				.disable()
			.antMatcher("/api/**")
				.authorizeRequests()
					.anyRequest()
						.authenticated()
			.and()
				.antMatcher("/api/admin/**")
					.authorizeRequests()
						.anyRequest()
							.hasAnyRole("ADMIN")
			.and()
				.httpBasic()
					.authenticationEntryPoint(authenticationEntryPoint());
	}
	
	@Bean
	public BasicAuthenticationEntryPoint authenticationEntryPoint() {
		
		class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

			@Override
		    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
		      throws IOException, ServletException {
		        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		        PrintWriter writer = response.getWriter();
		        writer.println("HTTP status " + response.getStatus() + ": " + authEx.getMessage());
		    }

			@Override
		    public void afterPropertiesSet() throws Exception {
		        setRealmName("api_realm");
		        super.afterPropertiesSet();
		    }

		}
		
		return new AuthenticationEntryPoint();
	}
	
}
