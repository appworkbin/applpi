/******************************************************************************* 
 * ACSS CONFIDENTIAL 
 * 
 * [2017] Aeon Credit Services Systems Philippines - All Rights Reserved. 
 * ALL INFORMATION HEREIN REMAINS THE PROPERTY OF ACSS, ANY USE OR DUPLICATION 
 * IS PROHIBITED WITHOUT EXPRESS OR WRITTEN PERMISSION FROM ACSS. THE INTELLECTUAL  
 * AND TECHNICAL CONCEPTS CONTAINED HEREIN ARE PROPRIETARY TO ACSS AND PROTECTED  
 * BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. ANY USE OF THE WORK OTHER THAN AS  
 * AUTHORIZED UNDER THIS LICENSE OR COPYRIGHT LAW IS PROHIBITED. 
 *********************************************************************************/
package com.aeoncredit.aeonpay.merchant.api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		//	.and().authorizeRequests().anyRequest().permitAll();
		
    	// TODO: THIS IS WORKING
    	//http.authorizeRequests()
		//	.antMatchers("/mvisa/**").access("#oauth2.hasScope('view')");
    	/* result:
    	 *      "error": "insufficient_scope",
    			"error_description": "Insufficient scope for this resource",
    			"scope": "view"
    	 * */
    	
    	// TODO: THIS IS WORKING
    	//http.authorizeRequests()
		//	.antMatchers("/mvisa/**").hasAnyRole("MERCHANT", "CONSUMER");
    	/* 
    	 * Result:
    	 * {
    			"error": "access_denied",
    			"error_description": "Access is denied"
			}
    	 * */
    	
    	String CONSUMER_MEMBER = "CONSUMER_MEMBER";
    	http.authorizeRequests()
			.antMatchers("/**").access("#oauth2.hasScope('consumer') and hasAuthority('" + CONSUMER_MEMBER +"')");
    	    	
		// @formatter:on
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(masterTokenServices());
    }

    @Bean("masterTokenService")
    public DefaultTokenServices masterTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(masterTokenStore());
        return defaultTokenServices;
    }

    // JDBC token store configuration

    @Bean("masterDataSource")
    public DataSource masterDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.master.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.master.url"));
        dataSource.setUsername(env.getProperty("jdbc.master.user"));
        dataSource.setPassword(env.getProperty("jdbc.master.pass"));
        return dataSource;
    }

    @Bean("masterTokenStore")
    public TokenStore masterTokenStore() {
        return new JdbcTokenStore(masterDataSource());
    }

}
