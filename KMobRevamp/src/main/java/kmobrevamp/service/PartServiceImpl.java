package kmobrevamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kmobrevamp.model.Part;
import kmobrevamp.repository.PartRepository;

@Service("partService")
public class PartServiceImpl implements PartService {

	@Autowired
	PartRepository partRepository;
	
	@Override
	public List<Part> getAll() {
		
		return partRepository.findAll();
		
	}

	
}
