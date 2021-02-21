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
import com.initializers.api.model.UserReview;
import com.initializers.api.service.UserDetailsService;
import com.initializers.api.service.UserReviewService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class UserReviewQueryResolver  implements GraphQLQueryResolver{
	@Autowired
	private UserReviewService userReviewService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	public Connection<UserReview> getItemReview(Integer first, String after, Long itemId) {
		Pageable page = PageRequest.of(Integer.parseInt(after), first);
		Page<UserReview> userReviewPage = userReviewService.findReviewByItemId(itemId, page);
		 
	Connection<UserReview> con = new Connection<UserReview>() {
			
			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {
					
					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return userReviewPage.hasPrevious();
					}
					
					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return userReviewPage.hasNext();
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
								String.valueOf(userReviewPage.getNumber() + 1)
								);
					} 
				};
				return page;
			}
			
			@Override
			public List<Edge<UserReview>> getEdges() {
				List<Edge<UserReview>> userReviewEdgeList = new ArrayList<Edge<UserReview>>();
				for(UserReview userReview : userReviewPage.getContent()) {
					Edge<UserReview> userReviewEdge = new Edge<UserReview>() {
						
						@Override
						public UserReview getNode() {
							String idValue = userReview.getPreviousApiId() +":UserReview";
							userReview.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							userReview.setUserName(userDetailsService.getNameById(userReview.getPreviousApiId().getUserId()));
							return userReview;
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
					userReviewEdgeList.add(userReviewEdge);
				}
				return userReviewEdgeList;
			}
			
	};
			return con;
		
	}
}
