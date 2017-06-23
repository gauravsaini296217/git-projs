package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.State;

@Repository("stateRepository")
public interface StateRepository extends JpaRepository<State, Integer> {

	
	
}
