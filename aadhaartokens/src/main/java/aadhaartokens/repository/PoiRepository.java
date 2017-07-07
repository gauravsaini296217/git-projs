package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.Poi;

@Repository("poiRepository")
public interface PoiRepository extends JpaRepository<Poi, Integer>{

}
