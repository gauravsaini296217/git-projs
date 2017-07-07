package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.AadhaarTokenStatus;


public interface AadhaarTokenStatusService {

	public List<AadhaarTokenStatus> getAll();
	
	public AadhaarTokenStatus geAadhaarTokenStatus(String status); 
}
