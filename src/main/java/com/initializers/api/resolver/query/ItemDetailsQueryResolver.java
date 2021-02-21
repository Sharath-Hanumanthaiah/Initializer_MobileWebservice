package com.initializers.api.resolver.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.initializers.api.constant.IdTypes;
import com.initializers.api.model.ItemDetails;
import com.initializers.api.model.UserWishlist;
import com.initializers.api.service.ItemAvailabilityService;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.UserWishlistService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class ItemDetailsQueryResolver  implements GraphQLQueryResolver{
	
	@Autowired
	ItemDetailsService itemDetailsService;
	@Autowired
	ItemAvailabilityService itemAvailabilityService;
	@Autowired
	UserWishlistService userWishlistService;
	
	public Connection<ItemDetails> getItemDetails(Integer first, String after, String itemType, Long typeId, Long userId) {
		Integer afterInt = Integer.parseInt(after);
		Pageable page = PageRequest.of(afterInt, first);
		
		Page<ItemDetails> itemDetailsPage;
		if(itemType.equals("ItemCategory")) {
		itemDetailsPage  = itemDetailsService.getItemDetailsByCategory(typeId, page);
		}else {
			itemDetailsPage  = itemDetailsService.getItemDetailsBySubCategory(typeId, page);
		}
		return createItemDetailsConnection(itemDetailsPage, userId);
	}
	public ItemDetails getItemDetailsById(Long itemId, Long userId) {
		ItemDetails itemDetails = itemDetailsService.getItemDetails(userId, itemId);
		String idValue = itemDetails.getPreviousApiId() + IdTypes.ItemDetailsType;
		itemDetails.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
	 	return itemDetails;
	}
	
	public Connection<ItemDetails> createItemDetailsConnection(Page<ItemDetails> itemDetailsPage, Long userId) {
		UserWishlist userWishlist = userWishlistService.getUserWishlist(userId);
		
		Connection<ItemDetails> con = new Connection<ItemDetails>() {

			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {

					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return itemDetailsPage.hasNext();
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
						return new DefaultConnectionCursor(
								String.valueOf(itemDetailsPage.getNumber() + 1)
								);
					}
				};
				return page;
			}

			@Override
			public List<Edge<ItemDetails>> getEdges() {
				List<Edge<ItemDetails>> itemDetailsEdgeList = new ArrayList<Edge<ItemDetails>>();
				for (ItemDetails itemDetails : itemDetailsPage.getContent()) {
					Edge<ItemDetails> itemDetailsEdge = new Edge<ItemDetails>() {

						@Override
						public ItemDetails getNode() {
							//set user wishlist
							if(userWishlist != null && userWishlist.getItemsId().contains(itemDetails.getPreviousApiId())) {
								itemDetails.setIsWishlist(true);
							} else {
								itemDetails.setIsWishlist(false);
							}
							//set id hash
							String idValue = itemDetails.getPreviousApiId() + IdTypes.ItemDetailsType;
							itemDetails.setItemAvailability(itemAvailabilityService.
									getAvailabilityByItemId(itemDetails.getPreviousApiId(), 'N'));
							if(itemDetails.getImageLinks()!= null && itemDetails.getImageLinks().size() > 0) {
								itemDetails.setImageLink(itemDetails.getImageLinks().get(0));
							}
							itemDetails.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							return itemDetails;
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
					itemDetailsEdgeList.add(itemDetailsEdge);
				}
				return itemDetailsEdgeList;
			}

		};
		return con;
	}

}
