package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.Pec;

@Repository("pecRepository")
public interface PecRepository extends JpaRepository<Pec, Integer>{

}
