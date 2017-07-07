package aadhaartokens.model;

import javax.validation.constraints.Pattern;

public class SearchRequest {

	private String searchtype;
	
	@Pattern(regexp="^|[A-Za-z ]+$")
	private String name;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobile;
	
	@Pattern(regexp="^|[A-Za-z0-9]+$")
	private String tokenno;

	@Pattern(regexp="(^$|[0-9]{28})")
	private String enrolmentid;
	
	private String tokenid;
	
	private String tname;

	private String tmobile;
	
	private String ttoken;
	
	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}
	
	public String getEnrolmentid() {
		return enrolmentid;
	}

	public void setEnrolmentid(String enrolmentid) {
		this.enrolmentid = enrolmentid;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	
	
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTmobile() {
		return tmobile;
	}

	public void setTmobile(String tmobile) {
		this.tmobile = tmobile;
	}

	public String getTtoken() {
		return ttoken;
	}

	public void setTtoken(String ttoken) {
		this.ttoken = ttoken;
	}

	@Override
	public String toString() {
		return "SearchRequest [searchtype=" + searchtype + ", name=" + name + ", mobile=" + mobile + ", tokenno="
				+ tokenno + "]";
	}
	
	
	
}
