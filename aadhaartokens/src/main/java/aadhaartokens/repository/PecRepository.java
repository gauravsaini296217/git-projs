package aadhaartokens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.Pec;

@Repository("pecRepository")
public interface PecRepository extends JpaRepository<Pec, Integer>{

	@Query("SELECT p FROM Pec p WHERE p.peccode is not null and p.peccode!=''")
    public List<Pec> findAll();

	@Query("SELECT p FROM Pec p WHERE p.peccode is not null and p.peccode!='' and p.stateid=:stateid")
    public List<Pec> find(@Param("stateid") int stateid);
	
	
}
