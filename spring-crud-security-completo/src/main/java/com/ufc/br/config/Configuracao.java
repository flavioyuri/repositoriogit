package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ufc.br.security.UserDeatilsServicee;


@Configuration
@EnableWebSecurity
public class Configuracao extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDeatilsServicee userDetailsImplementacao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				.antMatchers("/inicio").permitAll()
				.antMatchers("/usuario/cadastrarUsuario").permitAll()
				.antMatchers("/produto/galeria").permitAll()
				.antMatchers("/produto/cadastrarProduto").hasAnyRole("ADMIN")
				.antMatchers("/produto/listar").hasAnyRole("ADMIN") 
				//.antMatchers("/carrinho").hasRole("USER")
				//.antMatchers("/carrinho/add").hasRole("USER")
				.antMatchers("/usuario/salvar").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/usuario/logar") 
				.permitAll() 
				.and().logout().logoutSuccessUrl("/usuario/logar?logout") 
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}

}