package com.initializers.api.resolver.mutation;

import java.util.Base64;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.initializers.api.constant.IdTypes;
import com.initializers.api.model.UserDetails;
import com.initializers.api.model.input.UserDetailsInput;
import com.initializers.api.model.output.UserDetailsOutput;
import com.initializers.api.service.UserDetailsService;

@Component
public class UserDetailsMutationResolver implements GraphQLMutationResolver{

	@Autowired
	UserDetailsService userDetailsService;
	
	public UserDetailsOutput updateUser(UserDetailsInput userDetailsInput) {
		UserDetails userDetails = new UserDetails();   
		BeanUtils.copyProperties(userDetailsInput, userDetails);
		userDetails = userDetailsService.updateUserDetail(userDetails);
		String idValue = userDetails.getPreviousApiId() + IdTypes.UserDetailsType;
		userDetails.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
		UserDetailsOutput userDetailsOutput = new UserDetailsOutput();
		userDetailsOutput.setUserDetails(userDetails);
		return userDetailsOutput;
	}
}
