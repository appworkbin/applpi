package com.aeoncredit.aeonpay.oauth.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider {
 
    @Override
    public Authentication authenticate(Authentication auth) 
      throws AuthenticationException {
  
        String username = null;
        String password = auth.getCredentials().toString();
        
        if (auth.getName() != null && auth.getName().trim().length() > 0) {
        	username = auth.getName().trim().toLowerCase();
        	//username = auth.getName();
        }
        
        // TODO: check if the username and password match
        // Do Users table query here and also the password encryption and salt
        // 
        Collection<UserRole> roles = new HashSet<UserRole>();
        roles.add(new UserRole("ROLE_CONSUMER"));
        User user = new User(username, password, roles);
        
        
        // if match
        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        
        // else
        // throw new BadCredentialsException(); 
       
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
    
 
}