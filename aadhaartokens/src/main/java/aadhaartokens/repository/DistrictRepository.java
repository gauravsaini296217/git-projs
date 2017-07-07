package aadhaartokens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.District;

@Repository("districtRepository")
public interface DistrictRepository extends JpaRepository<District, Integer> {

	@Query("SELECT d FROM District d WHERE stateid = :stateid")
    public List<District> find(@Param("stateid") int stateid);
	
}
