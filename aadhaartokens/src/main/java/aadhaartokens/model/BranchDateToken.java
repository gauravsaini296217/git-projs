package aadhaartokens.model;

public class BranchDateToken {

	private int id;
	private String date;
	private int availabletokenno;
	private int maxtokens;
	private String timeslot;
	private boolean selected=false;
	private String peccode;
	
	
	
	
	public String getPeccode() {
		return peccode;
	}
	public void setPeccode(String peccode) {
		this.peccode = peccode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAvailabletokenno() {
		return availabletokenno;
	}
	public void setAvailabletokenno(int availabletokenno) {
		this.availabletokenno = availabletokenno;
	}
	public int getMaxtokens() {
		return maxtokens;
	}
	public void setMaxtokens(int maxtokens) {
		this.maxtokens = maxtokens;
	}
	public String getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	
	public BranchDateToken(String date, int availabletokenno, int maxtokens, String timeslot) {
		super();
		this.date = date;
		this.availabletokenno = availabletokenno;
		this.maxtokens = maxtokens;
		this.timeslot = timeslot;
	}
	public BranchDateToken() {
		super();
	}
	
	
}
