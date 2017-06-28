package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.EnrolmentType;;

public interface EnrolmentTypeService {
	
	public List<EnrolmentType> getAll();
	
	public EnrolmentType getEnrolmentType(int sno);
	
}
