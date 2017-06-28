package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.EnrolmentType;

@Repository("enrolmentTypeRepository")
public interface EnrolmentTypeRepository extends JpaRepository<EnrolmentType, Integer>{

}
