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
import com.initializers.api.model.ItemSubCategory;
import com.initializers.api.service.ItemSubCategoryService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class ItemSubCategoryQueryResolver implements GraphQLQueryResolver {

	@Autowired
	private ItemSubCategoryService itemSubCategoryService;

//	public List<ItemSubCategory> getItemSubCategoryByCategoryId(Long categoryId) {
//		List<ItemSubCategory> itemSubCategoryList = itemSubCategoryService.getItemSubCategoryByCategoryId(categoryId);
//		itemSubCategoryList.stream().forEach(subCategory -> subCategory.
//				setId(Base64.getUrlEncoder().encodeToString(("ItemSubCategory_" + subCategory.getPreviousApiId()).getBytes())));
//		return itemSubCategoryList;
//	}
	
	public Connection<ItemSubCategory> getItemSubCategoryByCategoryId(Integer first, String after, Long categoryId) {
		Pageable page = PageRequest.of(Integer.parseInt(after), first);
		Page<ItemSubCategory> itemSubCategoryPage = itemSubCategoryService.getItemSubCategoryByCategoryId(categoryId, page);
//		Page<ItemCategory> itemCategoryPage= itemCategoryService.getItemCategory(first, afterInt);
	Connection<ItemSubCategory> con = new Connection<ItemSubCategory>() {
			
			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {
					
					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return itemSubCategoryPage.hasPrevious();
					}
					
					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return itemSubCategoryPage.hasNext();
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
								String.valueOf(itemSubCategoryPage.getNumber() + 1)
								);
					} 
				};
				return page;
			}
			
			@Override
			public List<Edge<ItemSubCategory>> getEdges() {
				List<Edge<ItemSubCategory>> itemSubCategoryEdgeList = new ArrayList<Edge<ItemSubCategory>>();
				for(ItemSubCategory ItemSubCategory : itemSubCategoryPage.getContent()) {
					Edge<ItemSubCategory> itemSubCategoryEdge = new Edge<ItemSubCategory>() {
						
						@Override
						public ItemSubCategory getNode() {
							String idValue = ItemSubCategory.getPreviousApiId() +":ItemSubCategory";
							ItemSubCategory.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							return ItemSubCategory;
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
					itemSubCategoryEdgeList.add(itemSubCategoryEdge);
				}
				return itemSubCategoryEdgeList;
			}
			
	};
		return con;
	}
}
