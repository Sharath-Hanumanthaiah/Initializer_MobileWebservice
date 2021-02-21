package com.initializers.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.initializers.api.model.Address;
import com.initializers.api.model.input.AddressInput;

@Service
public interface AddressService {
	
	Address addAddress(AddressInput address);
	
	Address getAddressById(Long addressId);
	
//	Address getAddressByIdByUserId(Long addressId, Long userID);
	
	Page<Address> getAddressByUserId(Integer first, Integer after, Long userId);
	
	Address updateAddress(AddressInput addressInput);
	
	Boolean deleteAddressById(Long addressId);
}
