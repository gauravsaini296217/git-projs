package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.EnrolmentType;

@Repository("EntroTypRepository")
public interface EnrolTypRepository extends JpaRepository<EnrolmentType, Integer>{

}
