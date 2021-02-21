package com.initializers.api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.initializers.api.exception.UserNotFoundException;
import com.initializers.api.model.UserDetails;
import com.initializers.api.repo.UserDetailsRepo;
import com.initializers.api.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserDetailsRepo userDetailsRepo;
//	@Autowired
//	private TransientUserDetailsService transientUserService;
	
	@Override
	public Long addUser(UserDetails userDetails) {
		userDetails.setStatus("Active");
		UserDetails userSavedData = userDetailsRepo.save(userDetails);
		return userSavedData.getPreviousApiId();
	}

	@Override
	public UserDetails getUser(Long userId) {
		UserDetails userDetails = userDetailsRepo.findFirstByPreviousApiId(userId);
		if(userDetails == null) throw new UserNotFoundException("User Not found");
		userDetails.setPassword(null);
		userDetails.setStatus(null);
		userDetails.setFirstName(StringUtils.capitalize(userDetails.getFirstName()));
		userDetails.setLastName(StringUtils.capitalize(userDetails.getLastName()));
		return userDetails;
	}

//	@Override
//	public Object deleteUser(Long userId) {
//		Map<String,Object> returnVal = new HashMap<>();
//		returnVal.put("Info", "User deleted");
//		if(getUser(userId) != null) {
//			UserDetails userDetails = getUser(userId);
//			userDetails.setStatus("Inactive");
//			userDetailsRepo.save(userDetails);
//		}else if(transientUserService.getUser(userId) != null) {
//			transientUserService.deleteUser(userId);
//		}else {
//			throw new UserNotFoundException();
//		}
//		returnVal.put("Info", "user deleted successfuly");
//		return returnVal;
//	}

	@Override
	public UserDetails updateUserDetail(UserDetails userDetails) {
		if(userDetails == null) {
//			throw new RequiredValueMissingException();
		}else {
			UserDetails dbUserDetails = userDetailsRepo.findFirstByPreviousApiId(userDetails.getPreviousApiId());
			if(dbUserDetails == null) {
//				throw new UserNotFoundException();
			}else {
				final String email = userDetails.getEmail();
				final String firstName = userDetails.getFirstName();
				final String lastName = userDetails.getLastName();
//				final String password = userDetails.getPassword();
				
//				TODO need to validate with otp before update
				if(email != dbUserDetails.getEmail() && email != null) {
					dbUserDetails.setEmail(email);
				}
				if(firstName != dbUserDetails.getFirstName() && firstName != null) {
					dbUserDetails.setFirstName(firstName);
				}
				if(lastName != dbUserDetails.getLastName() && lastName != null) {
					dbUserDetails.setLastName(lastName);
				}
//				if(password != dbUserDetails.getPassword() && password != null) {
//					dbUserDetails.setPassword(password);
//				}
				UserDetails userDetailsAfterSave = userDetailsRepo.save(dbUserDetails);
				userDetailsAfterSave.setPassword(null);
				userDetailsAfterSave.setStatus(null);
				return userDetailsAfterSave;
			}
		}
		return null;
	}

	@Override
	public String getNameById(Long userId) {
		UserDetails userDetails = userDetailsRepo.findNameByPreviousApiId(userId);
		if(userDetails != null) {
			return userDetails.getFirstName() + " " + userDetails.getLastName();
		}
		return null;
	}

	@Override
	public Object deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkUser(Long userId) {
		if(userDetailsRepo.findNameByPreviousApiId(userId) != null) {
			return true;
		}
		return false;
	}

	
}
