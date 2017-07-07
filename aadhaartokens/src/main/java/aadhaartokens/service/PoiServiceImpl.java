package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.Poi;
import aadhaartokens.repository.PoiRepository;

@Service("poiService")
public class PoiServiceImpl implements PoiService {

	@Autowired
	PoiRepository poiRepository;
	
	@Override
	public List<Poi> getAll() {
		
		return poiRepository.findAll();
		
	}

}
