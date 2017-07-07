package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.EnrolmentSpecific;

@Repository("enrolmentSpecificRepository")
public interface EnrolmentSpecificRepository extends JpaRepository<EnrolmentSpecific, String> {

	
}
