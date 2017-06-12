package kmobrevamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kmobrevamp.model.Part;

public interface PartRepository extends JpaRepository<Part, String> {

	
}
