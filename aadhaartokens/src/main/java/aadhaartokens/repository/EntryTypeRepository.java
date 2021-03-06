package aadhaartokens.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.EntryType;

@Repository("entryTypeRepository")
public interface EntryTypeRepository extends JpaRepository<EntryType, Integer> {
	
}
