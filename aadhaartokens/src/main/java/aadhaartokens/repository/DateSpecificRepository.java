package aadhaartokens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.DateSpecific;

@Repository("dateSpecificRepository")
public interface DateSpecificRepository extends JpaRepository<DateSpecific, String> {

}
