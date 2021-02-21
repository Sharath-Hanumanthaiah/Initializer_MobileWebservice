package com.initializers.api.resolver.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.initializers.api.model.HomePage;
import com.initializers.api.model.HomePageContent;
import com.initializers.api.model.ItemCategory;
import com.initializers.api.model.ItemDetails;
import com.initializers.api.model.ItemSubCategory;
import com.initializers.api.service.HomePageService;
import com.initializers.api.service.ItemCategoryService;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.ItemSubCategoryService;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import graphql.relay.PageInfo;

@Component
public class HomePageQueryResolver  implements GraphQLQueryResolver {
	
	@Autowired
	HomePageService homePageService;
	@Autowired
	ItemCategoryService itemCategoryService;
	@Autowired
	ItemSubCategoryService itemSubCategoryService;
	@Autowired
	ItemDetailsService itemDetailsService;
	
	public Connection<HomePage> getHomePage(Integer first, String after) {
		Integer afterInt = Integer.parseInt(after);
//		ConnectionCursor connectionCur = new C
		Page<HomePage> homePage = homePageService.getHomePage(first, afterInt);
		Connection<HomePage> con = new Connection<HomePage>() {

			@Override
			public PageInfo getPageInfo() {
				// TODO Auto-generated method stub
				PageInfo page = new PageInfo() {

					@Override
					public boolean isHasPreviousPage() {
						// TODO Auto-generated method stub
						return homePage.hasPrevious();
					}

					@Override
					public boolean isHasNextPage() {
						// TODO Auto-generated method stub
						return homePage.hasNext();
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
								String.valueOf(homePage.getNumber() + 1)
								);
//								getContent().get(homePage.getSize() - 1).getId().toString());
					}
				};
				return page;
			}

			@Override
			public List<Edge<HomePage>> getEdges() {
				List<Edge<HomePage>> homePageEdgeList = new ArrayList<Edge<HomePage>>();
				for (HomePage homepage : homePage.getContent()) {
					Edge<HomePage> homePageEdge = new Edge<HomePage>() {

						@Override
						public HomePage getNode() {
							List<HomePageContent> homePageContentList = new ArrayList<>();
							if (homepage.getItemType().equals("ItemSubCategory")) {
								List<ItemSubCategory> itemSubCategory = itemSubCategoryService.
										getItemSubCategoryByCategoryId(homepage.getTypeId());
								itemSubCategory.stream().forEach(subCategory -> {
									HomePageContent homePageContent = new HomePageContent();
									homePageContent.setId(Base64.getUrlEncoder().encodeToString(("HomePageItemSubCategory_" + subCategory.getPreviousApiId()).getBytes()));
									homePageContent.setName(subCategory.getName());
									homePageContent.setPreviousApiId(subCategory.getPreviousApiId());
									homePageContent.setDescription(subCategory.getDescription());
									homePageContent.setOffer(subCategory.getOffer());
									homePageContent.setImageLink(subCategory.getImageLink());
									homePageContentList.add(homePageContent);
								});
							}
							else if(homepage.getItemType().equals("ItemDetails")) {
								List<ItemDetails> itemDetails = itemDetailsService.getItemDetailsBySubCategory(homepage.getTypeId());
								itemDetails.stream().forEach(details -> {
									HomePageContent homePageContent = new HomePageContent();
									homePageContent.setId(Base64.getUrlEncoder().encodeToString(("HomePageItemDetails_" + details.getPreviousApiId()).getBytes()));
									homePageContent.setName(details.getName());
									homePageContent.setPreviousApiId(details.getPreviousApiId());
									if(details.getImageLinks().size() > 0) {
										homePageContent.setImageLink(details.getImageLinks().get(0));										
									}
									homePageContentList.add(homePageContent);
								});
							} else if(homepage.getItemType().equals("ItemCategory")) {
								List<ItemCategory> itemCategory = itemCategoryService.getItemCategory();
								itemCategory.stream().forEach(category -> {
									HomePageContent homePageContent = new HomePageContent();
									homePageContent.setId(Base64.getUrlEncoder().encodeToString(("HomePageItemCategory_" + category.getPreviousApiId()).getBytes()));
									homePageContent.setName(category.getName());
									homePageContent.setPreviousApiId(category.getPreviousApiId());
									homePageContent.setDescription(category.getDescription());
									homePageContent.setOffer(category.getOffer());
									homePageContent.setImageLink(category.getImageLink());
									homePageContentList.add(homePageContent);
								});
							}
							String idValue = homepage.getPreviousApiId() +":HomePage";
							homepage.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
							homepage.setContent(homePageContentList);
							return homepage;
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
					homePageEdgeList.add(homePageEdge);
				}
				return homePageEdgeList;
			}

		};
		return con;
	}
	
}
