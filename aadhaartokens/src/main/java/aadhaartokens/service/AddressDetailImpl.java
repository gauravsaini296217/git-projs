package aadhaartokens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aadhaartokens.model.AddressDetail;
import aadhaartokens.repository.AddressDetailRepository;

@Service("addressDetailService")
public class AddressDetailImpl implements AddressDetailService{

	@Autowired
	AddressDetailRepository addressDetailRepository;
	
	@Override
	public List<AddressDetail> getAll() {
		
		return addressDetailRepository.findAll();
	}

	@Override
	public AddressDetail getAddressDetail(int aadpincodemasterid) {
		return addressDetailRepository.findOne(aadpincodemasterid);
	}

	@Override
	public List<AddressDetail> getAddressByPincode(int pincode) {
		return addressDetailRepository.find(pincode);
	}

	
	
}

