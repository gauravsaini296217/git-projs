package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.District;
import aadhaartokens.repository.DistrictRepository;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	DistrictRepository districtRepository;
	
	@Override
	public List<District> getAll() {
		
		return districtRepository.findAll();
		
	}

}
