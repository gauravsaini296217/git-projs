package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.EnrolmentSpecific;
import aadhaartokens.repository.EnrolmentSpecificRepository;

@Service("enrolmentSpecificService")
public class EnrolmentSpecificServiceImpl implements EnrolmentSpecificService {

	@Autowired
	EnrolmentSpecificRepository enrolmentSpecificRepository;
	
	@Override
	public List<EnrolmentSpecific> getAll() {
		
		return enrolmentSpecificRepository.findAll();
		
	}

}
