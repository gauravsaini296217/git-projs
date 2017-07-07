package aadhaartokens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aadhaartokens.model.AddressDetail;
import aadhaartokens.model.Pec;

@Repository("addressDetailRepository")
public interface AddressDetailRepository extends JpaRepository<AddressDetail, Integer>{

	@Query("SELECT p FROM AddressDetail p WHERE p.pincode=:pincode")
    public List<AddressDetail> find(@Param("pincode") int pincode);
	
}
