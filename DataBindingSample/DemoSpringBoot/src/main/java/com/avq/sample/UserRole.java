package com.aeoncredit.aeonpay.oauth.config;



import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority, Serializable {

 	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String authority = null;
		
	 	public UserRole (String authority) {
	 		this.authority = authority;
	 	}
 	
		@Override
		public String getAuthority() {
			return this.authority;
		}
 	
 }
