package aadhaartokens.service;

import java.util.List;

import aadhaartokens.model.AddressDetail;

public interface AddressDetailService {
	
	public List<AddressDetail> getAll();
	public AddressDetail getAddressDetail(int aadpincodemasterid);
	public List<AddressDetail> getAddressByPincode(int pincode);

}
