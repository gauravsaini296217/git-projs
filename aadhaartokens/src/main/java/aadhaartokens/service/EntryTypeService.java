package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.EntryType;

public interface EntryTypeService {

	public List<EntryType> getAll();
	
	public EntryType getEntryType(int typeid);
}
	