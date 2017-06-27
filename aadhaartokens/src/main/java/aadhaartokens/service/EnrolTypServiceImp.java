package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.EnrolmentType;
import aadhaartokens.repository.EnrolTypRepository;

@Service("EnrolmentTypeService")
public class EnrolTypServiceImp implements EnrolmentTypeService {

	@Autowired
	EnrolTypRepository enrolTypRepository;
	
	public List<EnrolmentType> getAll(){
		return enrolTypRepository.findAll();
	}
	
	
	@Override
	public EnrolmentType getEnrolmentType(int sno) {
		
		return enrolTypRepository.findOne(sno);
	}

}
