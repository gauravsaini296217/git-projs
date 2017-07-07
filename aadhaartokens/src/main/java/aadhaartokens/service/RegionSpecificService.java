package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.RegionSpecific;

public interface RegionSpecificService {

	public List<RegionSpecific>  getAll();
	
	public RegionSpecific  getOne(String st);
	
}
