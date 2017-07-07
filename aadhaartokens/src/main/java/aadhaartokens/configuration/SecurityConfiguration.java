package aadhaartokens.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		    .userDetailsService(userDetailsService)
		    .passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		    .authorizeRequests()
		           .antMatchers("/").permitAll()
		           .antMatchers("/enrolmentform").permitAll()
		           .antMatchers("/login").permitAll()
		           .antMatchers("/branch/**").hasAuthority("Branch")
		           .antMatchers("/state/**").hasAuthority("State")
		           .antMatchers("/admin/**").hasAuthority("Admin")
		           .anyRequest()
		           .authenticated().and().csrf().disable().formLogin()
		           .loginPage("/login").failureUrl("/login?error=true")
		           .defaultSuccessUrl("/default")
		           .usernameParameter("email")
		           .passwordParameter("password")
		           .and().logout()
		           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		           .logoutSuccessUrl("/").and().exceptionHandling()
		           .accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	
		web
		   .ignoring()
		   .antMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**");
		
	}
	
	
	
}