package com.initializers.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.initializers.api.model.UserOrderSet;
import com.initializers.api.service.SequenceGeneratorService;

@Component
public class UserOrderModelListener extends AbstractMongoEventListener<UserOrderSet>{

	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
    public UserOrderModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }
	
	@Override
    public void onBeforeConvert(BeforeConvertEvent<UserOrderSet> event) {
		if(event.getSource().getPreviousApiId() == null) {
			event.getSource().setPreviousApiId(sequenceGenerator.generateSequence(UserOrderSet.SEQUENCE_NAME));
		}
    }
}
