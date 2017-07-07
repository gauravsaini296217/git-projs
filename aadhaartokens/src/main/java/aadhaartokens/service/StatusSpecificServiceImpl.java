package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.StatusSpecific;
import aadhaartokens.repository.StatusSpecificRepository;

@Service("statusSpecificService")
public class StatusSpecificServiceImpl implements StatusSpecificService{

	@Autowired
	StatusSpecificRepository statusSpecificRepository;
	
	@Override
	public List<StatusSpecific> getAll() {
		
		return statusSpecificRepository.findAll();
		
	}

	
	
}
