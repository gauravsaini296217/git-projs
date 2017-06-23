package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.State;

public interface StateService {

	public List<State> getAll();
	
	public State getState(int stateid);
	
}
