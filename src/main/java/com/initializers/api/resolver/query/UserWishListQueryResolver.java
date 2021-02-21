package com.initializers.api.resolver.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.initializers.api.constant.IdTypes;
import com.initializers.api.model.ItemDetails;
import com.initializers.api.service.UserWishlistService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class UserWishListQueryResolver implements GraphQLQueryResolver{
	
	@Autowired
	UserWishlistService userWishlistService;
	
	public Connection<ItemDetails> getUserWishList(Integer first, String after, Long userId) {
		Integer afterInt = Integer.parseInt(after);
		Pageable page = PageRequest.of(afterInt, first);
		Map<String, Object> itemDetailsMap = userWishlistService.getUserWishlist(userId, page);
		@SuppressWarnings("unchecked")
		Page<ItemDetails> itemDetailsPage = (Page<ItemDetails>) itemDetailsMap.get("page");
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
						return (boolean) itemDetailsMap.get("hasNextPage");
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
						return new DefaultConnectionCursor(itemDetailsMap.get("endCursor").toString());
//								getContent().get(userOrderPage.getSize() - 1).getId().toString());
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
							String idValue = itemDetails.getPreviousApiId() + IdTypes.ItemDetailsType;
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
