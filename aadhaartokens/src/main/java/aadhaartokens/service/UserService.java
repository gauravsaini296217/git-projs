package aadhaartokens.service;

import aadhaartokens.model.User;

public interface UserService {

	public User findUserByEmail(String email);
	public void saveStateUser(User user);
	public void saveBranchUser(User user);
/*	public void saveServiceCenterUser(User user);
	public void saveSupportCenterUser(User user);
	public List<Factory> getFactories();
	public Factory getFactoryById(int id);
	public List<ServiceCenter> getServices();
	public ServiceCenter getServiceById(int id);*/
}
