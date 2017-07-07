package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.Pec;
import aadhaartokens.repository.PecRepository;

@Service("pecService")
public class PecServiceImpl implements PecService {

	@Autowired
	PecRepository pecRepository;
	
	@Override
	public List<Pec> getAll() {
		
		return pecRepository.findAll();
		
	}

	@Override
	public Pec getone(int pecid) {
		
		return pecRepository.findOne(pecid);
	}

	@Override
	public List<Pec> getStateWisePecs(int stateid) {
		
		return pecRepository.find(stateid);
	}

	
	
}
