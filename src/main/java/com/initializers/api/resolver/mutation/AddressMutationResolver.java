package com.initializers.api.resolver.mutation;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.initializers.api.constant.IdTypes;
import com.initializers.api.model.Address;
import com.initializers.api.model.input.AddressInput;
import com.initializers.api.model.output.AddressOutput;
import com.initializers.api.service.AddressService;

@Component
public class AddressMutationResolver implements GraphQLMutationResolver{

	@Autowired
	AddressService addressService;
	
	public AddressOutput upsertAddress(AddressInput addressInput) {
		Address address = new Address();
		if(addressInput.getPreviousApiId() != null) {
			address = addressService.updateAddress(addressInput);
		} else {
			address = addressService.addAddress(addressInput);
		}
		AddressOutput addressOutput = new AddressOutput();
		addressOutput.setAddress(address);
		String idValue = address.getPreviousApiId() + IdTypes.AddressType;
		address.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
		return addressOutput;
	}
	
	public Boolean deleteAddress(Long id) {
		return addressService.deleteAddressById(id);
	}
}
