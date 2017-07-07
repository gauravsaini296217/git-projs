package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.Dob;
import aadhaartokens.repository.DobRepository;

@Service("dobService")
public class DobServiceImpl implements DobService {

	@Autowired
	DobRepository dobRepository;
	
	@Override
	public List<Dob> getAll() {
		
		return dobRepository.findAll();
		
	}

}
