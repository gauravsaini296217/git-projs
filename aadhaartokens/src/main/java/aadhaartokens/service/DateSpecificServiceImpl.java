package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.DateSpecific;
import aadhaartokens.repository.DateSpecificRepository;

@Service("dateSpecificService")
public class DateSpecificServiceImpl implements DateSpecificService {

	@Autowired
	DateSpecificRepository dateSpecificRepository;
	
	@Override
	public List<DateSpecific> getAll() {
		
		return dateSpecificRepository.findAll();
		
	}

	
	
}
