package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import aadhaartokens.model.State;
import aadhaartokens.repository.StateRepository;

public class StateServiceImpl implements StateService{

	@Autowired
	StateRepository stateRepository;
	
	public List<State> getAll() {
		
		return stateRepository.findAll();
		
	}

	
	
}
