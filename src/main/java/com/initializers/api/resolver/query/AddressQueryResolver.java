package com.initializers.api.resolver.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.initializers.api.constant.IdTypes;
import com.initializers.api.model.Address;
import com.initializers.api.service.AddressService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class AddressQueryResolver implements GraphQLQueryResolver {

	@Autowired
	AddressService addressService;

	public Connection<Address> getAddress(Integer first, String after, Long userId) {
		Integer afterInt = Integer.parseInt(after);
//		ConnectionCursor connectionCur = new C
		Page<Address> addressPage = addressService.getAddressByUserId(first, afterInt, userId);
		Connection<Address> con = new Connection<Address>() {

			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {

					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return addressPage.hasPrevious();
					}

					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return addressPage.hasNext();
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
								String.valueOf(addressPage.getNumber() + 1)
								);
//								getContent().get(addressPage.getSize() - 1).getId().toString());
					}
				};
				return page;
			}

			@Override
			public List<Edge<Address>> getEdges() {
				List<Edge<Address>> itemCategoryEdgeList = new ArrayList<Edge<Address>>();
				for (Address address : addressPage.getContent()) {
					Edge<Address> itemCategoryEdge = new Edge<Address>() {

						@Override
						public Address getNode() {
							String idValue = address.getPreviousApiId() + IdTypes.AddressType;
							address.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							return address;
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
					itemCategoryEdgeList.add(itemCategoryEdge);
				}
				return itemCategoryEdgeList;
			}

		};
		return con;
	}

}
