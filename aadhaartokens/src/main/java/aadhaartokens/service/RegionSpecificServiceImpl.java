package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.RegionSpecific;
import aadhaartokens.repository.RegionSpecificRepository;

@Service("regionSpecificService")
public class RegionSpecificServiceImpl implements RegionSpecificService {

	@Autowired
	RegionSpecificRepository regionSpecificRepository;
	
	@Override
	public List<RegionSpecific> getAll() {
		
		return regionSpecificRepository.findAll();
		
	}

	@Override
	public RegionSpecific getOne(String st) {
		
		return regionSpecificRepository.findOne(st);
	}
	
	

}
