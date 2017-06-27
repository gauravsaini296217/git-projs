package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import aadhaartokens.model.EntryType;
import aadhaartokens.repository.EntryTypeRepository;


public class EntryTypeImpl implements EntryTypeService{
	
	@Autowired
	EntryTypeRepository entryTypeRepository;

	@Override
	public List<EntryType> getAll() {
		return entryTypeRepository.findAll();
	}

	@Override
	public EntryType getEntryType(int typeid) {
		return entryTypeRepository.findOne(typeid);
	}
	
}
