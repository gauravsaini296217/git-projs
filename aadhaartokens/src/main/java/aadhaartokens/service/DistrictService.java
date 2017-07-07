package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.District;

public interface DistrictService {

	public List<District> getAll();
	
	public List<District> getStateWiseDistricts(int stateid);
	
	public District getDistrict(int districtid);
	
	
}
