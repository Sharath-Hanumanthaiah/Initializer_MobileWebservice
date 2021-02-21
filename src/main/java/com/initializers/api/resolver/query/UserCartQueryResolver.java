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
import com.initializers.api.model.UserCartList;
import com.initializers.api.repo.UserCartRepo;
import com.initializers.api.service.UserCartService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class UserCartQueryResolver implements GraphQLQueryResolver {
	
	@Autowired
	UserCartService userCartService;
	@Autowired
	UserCartRepo userCartRepo;
	
	public Connection<UserCartList> getUserCart(Integer first, String after, Long userId) {
		Pageable pageable = PageRequest.of(Integer.parseInt(after), first);
//		ConnectionCursor connectionCur = new C
		Page<UserCartList> userCartPage = userCartService.getUserCartByuserId(userId, pageable);
		Connection<UserCartList> con = new Connection<UserCartList>() {

			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {

					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return userCartPage.hasPrevious();
					}

					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return userCartPage.hasNext();
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
								String.valueOf(userCartPage.getNumber() + 1)
								);
//								getContent().get(addressPage.getSize() - 1).getId().toString());
					}
				};
				return page;
			}

			@Override
			public List<Edge<UserCartList>> getEdges() {
				List<Edge<UserCartList>> userCartEdgeList = new ArrayList<Edge<UserCartList>>();
				for (UserCartList userCart : userCartPage.getContent()) {
					Edge<UserCartList> userCartEdge = new Edge<UserCartList>() {

						@Override
						public UserCartList getNode() {
							String idValue = userCart.getPreviousApiId().getItemId() + IdTypes.Cart + userCart.getPreviousApiId().getAvailabilityId();
							@SuppressWarnings("unchecked")
							ArrayList<String> imageLinks = (ArrayList<String>) userCart.getImageLinks().get(0);
							if(imageLinks.size() > 0 ) userCart.setImageLink(imageLinks.get(0));
							userCart.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							userCart.setDiscountPrice(userCart.getDiscountPrice() * userCart.getQuantity());
							return userCart;
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
					userCartEdgeList.add(userCartEdge);
				}
				return userCartEdgeList;
			}

		};
		return con;
	}
}
