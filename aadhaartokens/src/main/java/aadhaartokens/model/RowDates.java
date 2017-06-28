package aadhaartokens.model;

public class RowDates {

	 BranchDateToken[] branchDateTokens;
	 
	 public RowDates()
	 {
		 branchDateTokens=new BranchDateToken[3];
		 
	 }

	public BranchDateToken[] getBranchDateTokens() {
		return branchDateTokens;
	}

	public void setBranchDateTokens(BranchDateToken[] branchDateTokens) {
		this.branchDateTokens = branchDateTokens;
	}
	 
	 
	 
	 
}
