package com.initializers.api.resolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.initializers.api.model.UserDetails;

@Component
public class UserDetailsResolver implements GraphQLResolver<UserDetails> {

}
