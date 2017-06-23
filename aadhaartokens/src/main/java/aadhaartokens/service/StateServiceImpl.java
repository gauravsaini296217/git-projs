package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.State;
import aadhaartokens.repository.StateRepository;

@Service("stateService")
public class StateServiceImpl implements StateService{

	@Autowired
	StateRepository stateRepository;
	
	public List<State> getAll() {
		
		return stateRepository.findAll();
		
	}

	@Override
	public State getState(int stateid) {
		return stateRepository.findOne(stateid);
	}

	
	
	
}
