package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.Poa;

@Repository("poaRepository")
public interface PoaRepository extends JpaRepository<Poa, Integer>{

}
