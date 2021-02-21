package com.initializers.api.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class ItemNotFoundException extends RuntimeException implements GraphQLError{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3728032499817267527L;
	private final HttpStatus errorCode;

    public ItemNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorCode = HttpStatus.NOT_FOUND;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();

        customAttributes.put("errorCode", this.errorCode);
        customAttributes.put("errorMessage", this.getMessage());

        return customAttributes;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }
}
