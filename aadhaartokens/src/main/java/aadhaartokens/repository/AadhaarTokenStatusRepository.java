package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.AadhaarTokenStatus;

@Repository("aadhaarTokenStatusRepository")
public interface AadhaarTokenStatusRepository extends JpaRepository<AadhaarTokenStatus, String> {

}
