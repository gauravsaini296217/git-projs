package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.EnrolmentType;
import aadhaartokens.repository.EnrolmentTypeRepository;

@Service("enrolmentTypeService")
public class EnrolmentTypeServiceImpl implements EnrolmentTypeService {

	@Autowired
	EnrolmentTypeRepository enrolTypRepository;
	
	public List<EnrolmentType> getAll(){
		return enrolTypRepository.findAll();
	}
	
	
	@Override
	public EnrolmentType getEnrolmentType(int sno) {
		
		return enrolTypRepository.findOne(sno);
	}

}
