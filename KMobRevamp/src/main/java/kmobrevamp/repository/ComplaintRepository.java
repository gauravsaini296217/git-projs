package kmobrevamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kmobrevamp.model.Complaint;

@Repository("complaintRepository")
public interface ComplaintRepository extends JpaRepository<Complaint,String> {

	
		Complaint findBySno(String sno);
}
