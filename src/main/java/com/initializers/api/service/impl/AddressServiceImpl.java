package com.initializers.api.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.constant.Status;
import com.initializers.api.exception.AddressNotFoundException;
import com.initializers.api.exception.RequiredValueMissingException;
import com.initializers.api.exception.UserNotFoundException;
import com.initializers.api.model.Address;
import com.initializers.api.model.input.AddressInput;
import com.initializers.api.repo.AddressRepo;
import com.initializers.api.service.AddressService;
import com.initializers.api.service.UserDetailsService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AddressRepo addressRepo;
	
	@Override
	public Address addAddress(AddressInput addressInput) {
		if(addressInput == null) {
			throw new RequiredValueMissingException();
		}else if(addressInput.getUserId() == null) {
			throw new UserNotFoundException("User Not found");
		}else if(userDetailsService.getUser(addressInput.getUserId()) == null) {
			throw new UserNotFoundException("User Not found");
		}else {
			Address address = new Address();
			BeanUtils.copyProperties(addressInput, address);
			address.setStatus(Status.Active);
			return addressRepo.save(address);
		}
	}

	@Override
	public Address getAddressById(Long addressId) {
		if(addressId == null) {
			throw new RequiredValueMissingException();
		}else {
			return addressRepo.findFirstByPreviousApiId(addressId);
		}
	}

	@Override
	public Page<Address> getAddressByUserId(Integer first, Integer after, Long userId) {
		Pageable page = PageRequest.of(after, first);
		if(userId == null) {
			throw new RequiredValueMissingException();
		}else {
			return addressRepo.findByUserId(userId, page);
		}
	}

	@Override
	public Address updateAddress(AddressInput addressInput) {
		if(addressInput.getPreviousApiId() != null) {
			Address address = new Address();
			BeanUtils.copyProperties(addressInput, address);
			Address dbAddress = getAddressById(addressInput.getPreviousApiId());
			if(dbAddress == null) {
				throw new AddressNotFoundException();
			}else {
				if(address.getFirstLine() != null) {
					dbAddress.setFirstLine(address.getFirstLine());
				}
				if(address.getSecondLine() != null) {
					dbAddress.setSecondLine(address.getSecondLine());
				}
				if(address.getName() != null) {
					dbAddress.setName(address.getName());
				}
				if(address.getPhoneNumber() != null) {
					dbAddress.setPhoneNumber(address.getPhoneNumber());
				}
				if(address.getLandMark() != null) {
					dbAddress.setLandMark(address.getLandMark());
				}
				if(address.getPinCode() != null) {
					dbAddress.setPinCode(address.getPinCode());
				}
				return addressRepo.save(dbAddress);
			}
		}else {
			throw new RequiredValueMissingException();
		}
	}

	@Override
	public Boolean deleteAddressById(Long addressId) {
		if(addressId != null) {
			Address dbAddress = getAddressById(addressId);
			if(dbAddress == null) {
				throw new AddressNotFoundException();
			}else {
				dbAddress.setStatus(Status.InActive);
				addressRepo.save(dbAddress);
				return true;
			}
		}
		return false;
	}

//	@Override
//	//dummy method
//	public Address getAddressByIdByUserId(Long addressId, Long userID) {
//		if(addressId == null || userID == null) {
//			throw new RequiredValueMissingException();
//		}else {
//			return null;
////			return addressRepo.findByIdByUserID(addressId, userID);
//		}
//	}

}
