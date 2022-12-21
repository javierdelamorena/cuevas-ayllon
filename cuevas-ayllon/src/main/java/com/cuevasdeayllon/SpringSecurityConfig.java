package com.cuevasdeayllon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cuevasdeayllon.service.UsuarioDetailsService;
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired 
	@Qualifier("UsuarioDetailsService")
	private UsuarioDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().antMatchers("/,/registrarse,/home,/galeriaFotografica,/historiaPueblo,/toRutas,/tablonAnuncios,/casasRurales").permitAll()
		.antMatchers("/login*").permitAll()
		.antMatchers("/usuario").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.ADMIN)
		.antMatchers("/propuesta").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.ADMIN)
		.antMatchers("/editarUsuario").hasAnyAuthority(AuthoritiesConstants.USER,AuthoritiesConstants.ADMIN)
		.antMatchers("/todosUsuarios").hasAnyAuthority(AuthoritiesConstants.ADMIN)
		.antMatchers("/fotosGaleriaLista").hasAnyAuthority(AuthoritiesConstants.ADMIN)
		.antMatchers("/listaPropuestas").hasAnyAuthority(AuthoritiesConstants.ADMIN)
		.antMatchers("/anuncios").hasAnyAuthority(AuthoritiesConstants.ADMIN)
		.antMatchers("/listaAnunciosAdmin").hasAnyAuthority(AuthoritiesConstants.ADMIN)
		.antMatchers("/votacionPropuestas").hasAnyAuthority(AuthoritiesConstants.ADMIN)
		
		
		.and()
		.exceptionHandling().accessDeniedPage("/error_403")
		.and()		
		.formLogin()
		.loginPage("/login").permitAll()
		.usernameParameter("nombre")
		.passwordParameter("password")
		.defaultSuccessUrl("/usuario",true)
		.permitAll()		
		 .and()
		 .logout()
		 .invalidateHttpSession(true)
		 .clearAuthentication(true)
		 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 .logoutSuccessUrl("/login?logout")
		 .permitAll()
		 .and()
		 .headers().addHeaderWriter(
			        new XFrameOptionsHeaderWriter(
			            XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		 
		 ;
			
//		 .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		 .logoutSuccessUrl("/home").deleteCookies("JSESSIONID")
//		 .invalidateHttpSession(true) 
         //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         
         //.invalidateHttpSession(true)
        
         
         
		//http.sessionManagement().sessionFixation().none();
	
		//.exceptionHandling().accessDeniedPage("/error_403");;
		//http.formLogin().successForwardUrl("/login_success_handler");
		//http.formLogin().failureForwardUrl("/login_failure_handler");
		
		
	}          
	                                          	             

	}
	
	    
	



