package com.initializers.api.service.impl;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.UserOrderSet;
import com.initializers.api.repo.UserOrderSetRepo;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.UserOrderService;

@Service
public class UserOrderServiceImpl implements UserOrderService {

	@Autowired
	private UserOrderSetRepo userOrderSetRepo;
	@Autowired
	private ItemDetailsService itemDetailsService;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public Page<UserOrderSet> getUserOrderList(Long userId, Pageable pageable) {
		Page<UserOrderSet> userOrderSetList = userOrderSetRepo.findByOrderListUserId(userId, pageable);
		return userOrderSetList;
	}

	@Override
	public UserOrderSet getUserOrder(Long id) {
//		Map<String, Object> returnVal = new HashMap<>();
//		List<Object> userOrderList = new ArrayList<>();
		UserOrderSet userOrderSet = userOrderSetRepo.findFirstByPreviousApiId(id);
		return userOrderSet;
//		if (userOrderSet != null) {
//			returnVal.put("id", userOrderSet.getId());
//			for (UserOrder userOrder : userOrderSet.getOrderList()) {
//				Map<String, Object> userOrderMap = new HashMap<>();
//				userOrderMap.put("amount", userOrder.getAmount());
//				userOrderMap.put("quantity", userOrder.getQuantity());
//				// add new value to availablity ID to get data irrespective of availability
//				Long[] availablityId = { userOrder.getAvailabilityId(), 1L };
//
//				ItemListTemp itemListTemp = itemDetailsService.getItemListById(userOrder.getItemId(), availablityId);
//				if (itemListTemp != null) {
//					userOrderMap.put("itemDetails", itemListTemp);
//				}
//				userOrderList.add(userOrderMap);
//			}
//			returnVal.put("item", userOrderList);
//			returnVal.put("totalAmount", userOrderSet.getTotalAmount());
//			returnVal.put("status", userOrderSet.getStatus());
//			returnVal.put("orderAt", userOrderSet.getOrderAt());
//			Float deliveryCharge = userOrderSet.getDeliveryCharge();
//			if (deliveryCharge != null) {
//				returnVal.put("deliveryCharge", "+ " + deliveryCharge);
//			}
//			returnVal.put("deliveredBy", userOrderSet.getDeliveredBy());
//			returnVal.put("coupenCode", userOrderSet.getCoupenCode());
//			Float coupenDiscount = userOrderSet.getCoupenDiscount();
//			if (coupenDiscount != null) {
//				returnVal.put("coupenDiscount", "- " + coupenDiscount);
//			}
//		}
//		return returnVal;
	}

}
