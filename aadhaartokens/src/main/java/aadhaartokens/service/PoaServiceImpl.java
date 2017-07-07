package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.Poa;
import aadhaartokens.repository.PoaRepository;

@Service("poaService")
public class PoaServiceImpl implements PoaService {

	@Autowired
	PoaRepository poaRepository;
	
	@Override
	public List<Poa> getAll() {
		
		return poaRepository.findAll();
		
	}

}
