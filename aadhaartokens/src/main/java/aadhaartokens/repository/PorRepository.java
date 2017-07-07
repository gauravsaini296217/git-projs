package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.Por;

@Repository("porRepository")
public interface PorRepository extends JpaRepository<Por, Integer>{

}
