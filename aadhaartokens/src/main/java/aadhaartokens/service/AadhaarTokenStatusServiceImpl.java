package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.AadhaarTokenStatus;
import aadhaartokens.repository.AadhaarTokenStatusRepository;

@Service("aadhaarTokenStatusService")
public class AadhaarTokenStatusServiceImpl implements AadhaarTokenStatusService{

	@Autowired
	AadhaarTokenStatusRepository aadhaarTokenStatusRepository;
	
	@Override
	public List<AadhaarTokenStatus> getAll() {
		
		return aadhaarTokenStatusRepository.findAll();
	}

	@Override
	public AadhaarTokenStatus geAadhaarTokenStatus(String status) {
		return aadhaarTokenStatusRepository.findOne(status);
	}

	
	
}
