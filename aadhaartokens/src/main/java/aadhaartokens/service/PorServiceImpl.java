package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.Por;
import aadhaartokens.repository.PorRepository;

@Service("porService")
public class PorServiceImpl implements PorService {

	@Autowired
	PorRepository porRepository;
	
	@Override
	public List<Por> getAll() {
		
		return porRepository.findAll();
		
	}

}
