package aadhaartokens.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import aadhaartokens.model.Role;
import aadhaartokens.model.User;
import aadhaartokens.repository.RoleRepository;
import aadhaartokens.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveStateUser(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role role=roleRepository.findByRole("State");
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
        userRepository.save(user);
	}

    public void saveBranchUser(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role role=roleRepository.findByRole("Branch");
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
        userRepository.save(user);
	}

	
	
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(userName);
		List<GrantedAuthority> authorities=getUserAuthority(user.getRoles());
		
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		 		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		 		for (Role role : userRoles) {
		 			roles.add(new SimpleGrantedAuthority(role.getRole()));
		 		}
		 
		 		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		 		return grantedAuthorities;
		 	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities)
	{
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive() , true, true, true, authorities);
	}


	
	
	
}
