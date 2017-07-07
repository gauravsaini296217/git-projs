package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.StatusSpecific;

@Repository("statusSpecificRepository")
public interface StatusSpecificRepository extends JpaRepository<StatusSpecific, String>{

	
}
