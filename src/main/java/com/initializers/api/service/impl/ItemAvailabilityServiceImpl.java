package com.initializers.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.initializers.api.model.ItemAvailability;
import com.initializers.api.repo.ItemAvailabilityRepo;
import com.initializers.api.service.ItemAvailabilityService;

@Service
public class ItemAvailabilityServiceImpl implements ItemAvailabilityService {

	@Autowired
	private ItemAvailabilityRepo itemAvailabilityRepo;
//	@Autowired
//	private ItemDetailsService itemDetailsService;

	@Override
	public List<ItemAvailability> getAvailabilityByItemId(Long itemId, char... availability) {
		if (availability.length > 0) {
			return itemAvailabilityRepo.findByItemId(itemId);
		}
		return itemAvailabilityRepo.findByItem(itemId);
	}


//	@Override
//	public ItemAvailability getMaxAvailabilityByItemId(Long itemId) {
//		return itemAvailabilityRepo.findTopByItemIdOrderByDiscountDesc(itemId);
//	}

	@Override
	public ItemAvailability getAvailabilityById(Long id, Long itemId) {
		ItemAvailability itemAvailability = itemAvailabilityRepo.findFisrtById(id);
		if (itemAvailability != null && !itemAvailability.getItemId().equals(itemId)) {
			return null;
		} else {
			return itemAvailability;
		}
	}

	@Override
	public Float getPriceByAvailabilityId(Long id) {
		ItemAvailability itemAvailability = itemAvailabilityRepo.findFirstDiscountPriceById(id);
		if (itemAvailability != null) {
			return itemAvailability.getDiscountPrice();
		}
		return null;
	}

	@Override
	public StringBuilder getValueUnitByAvailabilityId(Long id) {
		ItemAvailability itemAvailability = itemAvailabilityRepo.findFirstValueUnitId(id);
		StringBuilder valueUnit = new StringBuilder();
		if (itemAvailability != null) {
			valueUnit.append(itemAvailability.getValue());
			valueUnit.append(" ");
			valueUnit.append(itemAvailability.getUnit());
		}
		return valueUnit;
	}

}
