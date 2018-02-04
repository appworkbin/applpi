package com.aeoncredit.aeonpay.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


@Configuration
@EnableWebSecurity
@PropertySource({ "classpath:persistence.properties" })
public class  OAuthWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private Environment env;
        
    @Autowired
    private CustomAuthenticationProvider customAuthProvider;
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Primary
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
                
        dataSource.setDriverClassName(env.getProperty("jdbc.master.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.master.url"));
        dataSource.setUsername(env.getProperty("jdbc.master.user"));
        dataSource.setPassword(env.getProperty("jdbc.master.pass"));
        return dataSource;
    }
    
    @Bean("hostDataSource")
    public DataSource hostDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
                
        dataSource.setDriverClassName(env.getProperty("jdbc.host.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.host.url"));
        dataSource.setUsername(env.getProperty("jdbc.host.user"));
        dataSource.setPassword(env.getProperty("jdbc.host.pass"));
        return dataSource;
    }
    
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		//PasswordEncoder encoder = new BCryptPasswordEncoder();
		//String encodedPassword = encoder.encode("abc123");
		//System.out.println("Encoded Password: " + encodedPassword);
		
		auth.authenticationProvider(customAuthProvider);
	  
		/*
		auth.jdbcAuthentication().dataSource(dataSource())
	    .passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select u.username, r.role from users u, roles r, user_roles ur where u.user_id = ur.user_id and r.role_id = ur.role_id and u.username=?");
	  	*/
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		return encoder;
	}
	    
	/*
	@Override
	public void configure(WebSecurity web) throws Exception {
	    //web.ignoring().antMatchers(UriTag.CONSUMER_REGISTRATION + "/**");
	}*/
		
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
    	http.csrf().disable();
    	
	    http.anonymous().disable()
		//.and().authorizeRequests().antMatchers("/accounts/validate").access("#oauth2.isClient()")
		//.and().authorizeRequests().antMatchers("/secure/users/**")
		//.and().authorizeRequests().anyRequest().authenticated()
	    //.authorizeRequests().antMatchers(UriTag.CONSUMER_REGISTRATION + "/**").access("#oauth2.hasScope('register')")
	    //.and()
	    //.authorizeRequests().antMatchers("/info/{username}").access("#oauth2.hasScope('read')")
	    //.and().authorizeRequests().antMatchers("/info/**").permitAll()
		//.and()
		.authorizeRequests().antMatchers("/**").permitAll()
		//.anyRequest().authenticated()
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
		// @formatter:on
    }
    
}
