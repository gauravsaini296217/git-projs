package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.Pec;

public interface PecService {

	public List<Pec> getAll();
	
	public Pec getone(int pecid);
}
