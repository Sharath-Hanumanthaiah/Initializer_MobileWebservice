package com.initializers.api.resolver.query;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.initializers.api.model.UserDetails;
import com.initializers.api.service.UserDetailsService;

@Component
public class UserDetailsQueryResolver implements GraphQLQueryResolver {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	public UserDetails getUserById(Long id) {
		UserDetails userDetail = userDetailsService.getUser(id);
		String idValue = userDetail.getPreviousApiId()+":UserDetails";
		userDetail.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
		return userDetail;
	}

}
