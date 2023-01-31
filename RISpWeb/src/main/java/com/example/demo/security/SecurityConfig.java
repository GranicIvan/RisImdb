package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
		@Autowired
	    @Qualifier("customUserDetailsService")
	    UserDetailsService userDetailsService;
	 
			@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		      auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
			   
		}

	
			@Override
			protected void configure(HttpSecurity http) throws Exception {
			    http.authorizeRequests()
			  //  .antMatchers("/","/Predstave/loginPage", "/Predstave/registerUser", "/Predstave/register").permitAll() 
			    .antMatchers("/auth/**").permitAll()
			    .anyRequest().authenticated()
//			    .anyRequest().permitAll()
		        .and()
		        .formLogin()
		        .loginPage("/auth/loginPage")
		        .permitAll()
		        .loginProcessingUrl("/login")
		        .defaultSuccessUrl("/index") // dodali smo .jsp
//		        .failureForwardUrl("/auth/greska")
		        .and()
		        .csrf().disable();
//		        .and().exceptionHandling()
//		        .accessDeniedPage("/access_denied");
			}
	
			
			// ORIGINAL ---------------
//			protected void configure(HttpSecurity http) throws Exception {
//			    http.authorizeRequests()
//			  //  .antMatchers("/","/Predstave/loginPage", "/Predstave/registerUser", "/Predstave/register").permitAll() 
//			    .antMatchers("/auth/**").permitAll()
//			    .anyRequest().authenticated()
//		        .and()
//		        .formLogin()
//		        .loginPage("/auth/loginPage")
//		        .permitAll()
//		        .loginProcessingUrl("/login")
////		        .defaultSuccessUrl("/Predstave/pocetna")
////		        .failureForwardUrl("/auth/greska")
//		        .and()
//		        .csrf();
////		        .and().exceptionHandling()
////		        .accessDeniedPage("/access_denied");
//			}
//			@Override
//			protected void configure(HttpSecurity http) throws Exception {		 
//			    http.csrf().disable().authorizeRequests()
//			    .antMatchers("/auth/**") //svaka putanja koja pocinje sa auth je dostupna svima
//			    .permitAll()	//dostupno svima
//			    .antMatchers("/clanovi/**", "/knjige/**", "/reports/**", "/unos/**")
//			    .hasAnyRole("BIBLIOTEKAR","ADMIN")
//		        .and()
//		        .formLogin()
//		        .loginPage("/auth/loginPage")
//		        .loginProcessingUrl("/login")
//		        .usernameParameter("username")
//		        .passwordParameter("password")
//		        .defaultSuccessUrl("/auth/pocetna");
//		  //      .failureForwardUrl("/index.html")
//		  //      .and().exceptionHandling()
//		  //      .accessDeniedPage("/access_denied");
////		        .and()
////		        .logout()
////		        .logoutSuccessUrl("/")
////		        .and()
////	            .rememberMe().key("uniqueAndSecret");
//
//			}
			
 

}
