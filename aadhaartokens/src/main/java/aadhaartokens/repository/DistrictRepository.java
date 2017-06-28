package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.District;

@Repository("districtRepository")
public interface DistrictRepository extends JpaRepository<District, Integer> {

}
