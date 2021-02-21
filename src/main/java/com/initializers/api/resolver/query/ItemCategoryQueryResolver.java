package com.initializers.api.resolver.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.initializers.api.model.ItemCategory;
import com.initializers.api.service.ItemCategoryService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class ItemCategoryQueryResolver  implements GraphQLQueryResolver {

	@Autowired
	ItemCategoryService itemCategoryService;
	
	public Connection<ItemCategory> getItemCategory(Integer first, String after) {
		DefaultConnectionCursor cursor = new DefaultConnectionCursor(after);
		System.out.println(cursor.toString());
		Integer afterInt = Integer.parseInt(after);
//		ConnectionCursor connectionCur = new C
		Page<ItemCategory> itemCategoryPage= itemCategoryService.getItemCategory(first, afterInt);
	Connection<ItemCategory> con = new Connection<ItemCategory>() {
			
			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {
					
					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return itemCategoryPage.hasPrevious();
					}
					
					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return itemCategoryPage.hasNext();
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
								String.valueOf(itemCategoryPage.getNumber() + 1)
								);
					} 
				};
				return page;
			}
			
			@Override
			public List<Edge<ItemCategory>> getEdges() {
				List<Edge<ItemCategory>> itemCategoryEdgeList = new ArrayList<Edge<ItemCategory>>();
				for(ItemCategory itemCategory : itemCategoryPage.getContent()) {
					Edge<ItemCategory> itemCategoryEdge = new Edge<ItemCategory>() {
						
						@Override
						public ItemCategory getNode() {
							String idValue = itemCategory.getPreviousApiId() +":ItemCategory";
							itemCategory.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							return itemCategory;
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
	
	public Connection<ItemCategory> getItemCategoryContentById(Integer first, String after, Long categoryId) {
		DefaultConnectionCursor cursor = new DefaultConnectionCursor(after);
		System.out.println(cursor.toString());
		Integer afterInt = Integer.parseInt(after);
//		ConnectionCursor connectionCur = new C
		if(first == 0) {
			return null;
		}
		Page<ItemCategory> itemCategoryPage= itemCategoryService.getItemCategory(first, afterInt);
	Connection<ItemCategory> con = new Connection<ItemCategory>() {
			
			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {
					
					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return itemCategoryPage.hasPrevious();
					}
					
					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return itemCategoryPage.hasNext();
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
								String.valueOf(itemCategoryPage.getNumber() + 1)
								);
					} 
				};
				return page;
			}
			
			@Override
			public List<Edge<ItemCategory>> getEdges() {
				List<Edge<ItemCategory>> itemCategoryEdgeList = new ArrayList<Edge<ItemCategory>>();
				for(ItemCategory itemCategory : itemCategoryPage.getContent()) {
					Edge<ItemCategory> itemCategoryEdge = new Edge<ItemCategory>() {
						
						@Override
						public ItemCategory getNode() {
							String idValue = itemCategory.getPreviousApiId() +":Address";
							itemCategory.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							return itemCategory;
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
	
	public List<ItemCategory> getItemCategoryContentByIdTemp(Long categoryId) {
		List<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>();
		System.out.println("categoryID = " + categoryId);
		if(categoryId == 0) {
//			itemCategoryList.add(new ItemCategory());
			return itemCategoryList;
		}
		itemCategoryList = itemCategoryService.getItemCategoryContentById(categoryId);
		if(categoryId == 2) {
			itemCategoryList.remove(0);
		}
		itemCategoryList.stream().forEach(itemCategory -> itemCategory.setId(Base64.getUrlEncoder().encodeToString(("ItemCategory_" + itemCategory.getPreviousApiId()).getBytes())));			
		return itemCategoryList;
	}
}
