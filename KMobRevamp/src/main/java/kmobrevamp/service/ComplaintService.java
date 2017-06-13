package kmobrevamp.service;

import kmobrevamp.model.Complaint;

public interface ComplaintService {

	public void saveComplaint(Complaint complaint);
	
	public Complaint findComplaintBySno(String sno);
}
