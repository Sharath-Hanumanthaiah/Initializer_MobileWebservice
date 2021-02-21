package com.initializers.api.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.exception.AddressNotFoundException;
import com.initializers.api.exception.CartItemNotFoundException;
import com.initializers.api.exception.ItemAvailabilityException;
import com.initializers.api.exception.ItemNotFoundException;
import com.initializers.api.exception.UserNotFoundException;
import com.initializers.api.extras.ApplicationProperties;
import com.initializers.api.model.Address;
import com.initializers.api.model.OrderStatus;
import com.initializers.api.model.UserCart;
import com.initializers.api.model.UserCartList;
import com.initializers.api.model.UserOrder;
import com.initializers.api.model.UserOrderSet;
import com.initializers.api.model.output.CreateOrderOutput;
import com.initializers.api.repo.UserCartRepo;
import com.initializers.api.repo.UserOrderSetRepo;
import com.initializers.api.service.AddressService;
import com.initializers.api.service.ItemAvailabilityService;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.OfferConfigurationDBService;
import com.initializers.api.service.UserCartService;
import com.initializers.api.service.UserDetailsService;

@Service
public class UserCartServiceImpl implements UserCartService {

	@Autowired
	private UserCartRepo userCartRepo;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ItemDetailsService itemDetailsService;
	@Autowired
	private ItemAvailabilityService itemAvailabilityService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserOrderSetRepo userOrderSetRepo;
	@Autowired
	private OfferConfigurationDBService offerConfigurationDBService;
	
	@Override
	public UserCart addUserCart(UserCart userCart) {
		if (userCart.getQuantity() <= 0) {
			userCartRepo.deleteById(userCart.getPreviousApiId());
			return userCart;
		}
		if (userDetailsService.getUser(userCart.getPreviousApiId().getUserId()) == null) {
			throw new UserNotFoundException("user not found");
		}
		if (!itemDetailsService.checkItemDetails(userCart.getPreviousApiId().getItemId())) {
			throw new ItemNotFoundException("item not found");
		}
		if (itemAvailabilityService.getAvailabilityById(userCart.getPreviousApiId().getAvailabilityId(),
				userCart.getPreviousApiId().getItemId()) == null) {
			throw new ItemAvailabilityException("");
		}
		return userCartRepo.save(userCart);
	}

	@Override
	public Page<UserCartList> getUserCartByuserId(Long userId, Pageable pageable) {
		return userCartRepo.getUserCart(userId, pageable);
	}

	@Override
	public CreateOrderOutput createOrder(Long userId, Long addressId, String coupenCode) {
		Float totalAmount = 0F;
		CreateOrderOutput createOrderOutput = new CreateOrderOutput();
		List<UserOrder> userOrderList = new ArrayList<>();
		UserOrderSet userOrderSet = new UserOrderSet();
		List<UserCart.CompositeKeyCart> cartIdforDeletion = new ArrayList<>();
		Address address = addressService.getAddressById(addressId);
		if(address == null || address.getUserId() != userId) {
			throw new AddressNotFoundException();
		}
		
		Iterator<UserCart> userCartItr = userCartRepo.findByPreviousApiIdUserId(userId).stream()
				.filter(userCart -> itemAvailabilityService.getAvailabilityById(userCart.getPreviousApiId().getAvailabilityId(),
						userCart.getPreviousApiId().getItemId()) != null).
				iterator();
		
		
		while(userCartItr.hasNext()) {
			Float amount = 0F;
			UserOrder userOrder = new UserOrder();
			UserCart userCart = userCartItr.next();
			Float discountPrice = itemAvailabilityService.getPriceByAvailabilityId(userCart.getPreviousApiId().
					getAvailabilityId());
			if(discountPrice == null) continue;
			userOrder.setUserId(userCart.getPreviousApiId().getUserId());
			userOrder.setItemId(userCart.getPreviousApiId().getItemId());
			userOrder.setAvailabilityId(userCart.getPreviousApiId().getAvailabilityId());
			userOrder.setQuantity(userCart.getQuantity());
			amount += (userCart.getQuantity() * discountPrice);
			userOrder.setAmount(userCart.getQuantity() * discountPrice);
			totalAmount += amount;
			userOrderList.add(userOrder);
			cartIdforDeletion.add(userCart.getPreviousApiId());
		}
		if(userOrderList.size() > 0) {
			userOrderSet.setTotalAmount(totalAmount);
			userOrderSet.setOrderList(userOrderList);
			userOrderSet.setAddressId(addressId);
			userOrderSet.setOrderAt(LocalDate.now());
			userOrderSet.setStatus(new OrderStatus());
			userOrderSet.setCoupenCode(coupenCode);
			for(String type : ApplicationProperties.orderConfigType) {
				Object value = offerConfigurationDBService.configureOrderBeforeSend(userOrderSet,type);
				if(type.equals("DLRCRG") && value != null) {
					userOrderSet.setDeliveryCharge(Float.parseFloat(value.toString()));
				}
				if(type.equals("COUPEN") && value != null) {
					userOrderSet.setCoupenDiscount(Float.parseFloat(value.toString()));
				}
			}
			//create order
			userOrderSetRepo.save(userOrderSet);
			//delete from cart
			for(UserCart.CompositeKeyCart key : cartIdforDeletion) {
				userCartRepo.deleteById(key);
			}
		}else {
			throw new CartItemNotFoundException("Cart items not available");
		}
		createOrderOutput.setOrderId(userOrderSet.getPreviousApiId());
		createOrderOutput.setDeliveredBy(userOrderSet.getDeliveredBy());
		createOrderOutput.setTotalAmount(userOrderSet.getTotalAmount());
		return createOrderOutput;
	}
}
