package com.initializers.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.initializers.api.model.Address;
import com.initializers.api.service.SequenceGeneratorService;

@Component
public class AddressModelListener extends AbstractMongoEventListener<Address>{
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
    public AddressModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }
	
	@Override
    public void onBeforeConvert(BeforeConvertEvent<Address> event) {
		//do not create new data for updating
		if( event.getSource().getPreviousApiId() == null) {
			event.getSource().setPreviousApiId(sequenceGenerator.generateSequence(Address.SEQUENCE_NAME));
		}
		
    }
}
