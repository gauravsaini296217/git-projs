package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.Dob;

@Repository("dobRepository")
public interface DobRepository extends JpaRepository<Dob, Integer>{

}
