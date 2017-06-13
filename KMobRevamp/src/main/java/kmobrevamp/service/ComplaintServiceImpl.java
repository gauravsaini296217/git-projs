package kmobrevamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kmobrevamp.model.Complaint;
import kmobrevamp.repository.ComplaintRepository;

@Service("complaintService")
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;

	@Override
	public void saveComplaint(Complaint complaint) {
	 
		 complaintRepository.save(complaint);
		
	}

	@Override
	public Complaint findComplaintBySno(String sno) {
		
		return complaintRepository.findBySno(sno);
	}

	
	
	
}
