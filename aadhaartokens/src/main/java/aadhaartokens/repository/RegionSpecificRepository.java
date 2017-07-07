package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.RegionSpecific;

@Repository("regionSpecificRepository")
public interface RegionSpecificRepository extends JpaRepository<RegionSpecific, String> {

	
}
