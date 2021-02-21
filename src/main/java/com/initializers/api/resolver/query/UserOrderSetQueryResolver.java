package com.initializers.api.resolver.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.initializers.api.model.Address;
import com.initializers.api.model.ItemDetails;
import com.initializers.api.model.UserOrder;
import com.initializers.api.model.UserOrderSet;
import com.initializers.api.service.AddressService;
import com.initializers.api.service.ItemAvailabilityService;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.UserOrderService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class UserOrderSetQueryResolver implements GraphQLQueryResolver {

	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private ItemDetailsService itemDetailsService;
	@Autowired
	private ItemAvailabilityService itemAvailabalityService;
	@Autowired
	private AddressService addressService;

	public Connection<UserOrderSet> getUserOrderSet(Integer first, String after, Long userId) {
		Integer afterInt = Integer.parseInt(after);
		Pageable page = PageRequest.of(afterInt, first);
		Page<UserOrderSet> userOrderPage = userOrderService.getUserOrderList(userId, page);
		Connection<UserOrderSet> con = new Connection<UserOrderSet>() {

			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {

					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return userOrderPage.hasPrevious();
					}

					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return userOrderPage.hasNext();
					}

					@Override
					public ConnectionCursor getStartCursor() {
						// TODO Auto-generated method stub
//						return Scalars.GraphQLBigDecimal
						return null;
					}

					@Override
					public ConnectionCursor getEndCursor() {
						// TODO Auto-generated method stub
						return new DefaultConnectionCursor(String.valueOf(userOrderPage.getNumber() + 1));
//								getContent().get(userOrderPage.getSize() - 1).getId().toString());
					}
				};
				return page;
			}

			@Override
			public List<Edge<UserOrderSet>> getEdges() {
				List<Edge<UserOrderSet>> userOrderEdgeList = new ArrayList<Edge<UserOrderSet>>();
				for (UserOrderSet userOrder : userOrderPage.getContent()) {
					Edge<UserOrderSet> itemCategoryEdge = new Edge<UserOrderSet>() {

						@Override
						public UserOrderSet getNode() {
							String idValue = userOrder.getPreviousApiId() + ":userOrder";
							String itemName = null;
							userOrder.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							for (UserOrder order : userOrder.getOrderList()) {
								ItemDetails itemDetails = itemDetailsService
										.getItemNameImageLinkById(order.getItemId());
								String getName = StringUtils.capitalize(itemDetails.getName());
								if (itemName == null) {
									itemName = getName;
								} else {
									itemName = itemName + ", " + getName;
								}
							}
							userOrder.setOrderDetails(itemName);
							return userOrder;
						}

						@Override
						public ConnectionCursor getCursor() {
							// TODO Auto-generated method stub
							ConnectionCursor cur = new ConnectionCursor() {

								@Override
								public String getValue() {
									// TODO Auto-generated method stub
									return "kar";
								}
							};
							return cur;
						}
					};
					userOrderEdgeList.add(itemCategoryEdge);
				}
				return userOrderEdgeList;
			}

		};
		return con;
	}

	public UserOrderSet getUserOrderById(Long orderId) {
		Float netAmount = 0F;
		UserOrderSet userOrderSet = userOrderService.getUserOrder(orderId);
		if (userOrderSet != null) {
			userOrderSet.setId(
					Base64.getUrlEncoder().encodeToString((userOrderSet.getPreviousApiId() + ":userOrder").getBytes()));
			Address address = addressService.getAddressById(userOrderSet.getAddressId());
			address.setId(Base64.getUrlEncoder().encodeToString((address.getPreviousApiId() + ":Address").getBytes()));
			userOrderSet.setAddressDetails(address);
			for (UserOrder userOrder : userOrderSet.getOrderList()) {
				netAmount += userOrder.getAmount();
				userOrder.setUnit(
						itemAvailabalityService.getValueUnitByAvailabilityId(userOrder.getAvailabilityId()).toString());
				ItemDetails itemDetails = itemDetailsService.getItemNameImageLinkById(userOrder.getItemId());
				userOrder.setItemName(itemDetails.getName());
				if (itemDetails.getImageLinks() != null && itemDetails.getImageLinks().size() > 0) {
					userOrder.setImageLink(itemDetails.getImageLinks().get(0));
				}
			}
			userOrderSet.setNetAmount(netAmount);
		}
		return userOrderSet;
	}
}
