package kmobrevamp.controller;

import java.security.Provider.Service;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kmobrevamp.comparator.PartComparator;
import kmobrevamp.model.Complaint;
import kmobrevamp.model.Factory;
import kmobrevamp.model.Part;
import kmobrevamp.model.SearchComplaint;
import kmobrevamp.model.ServiceCenter;
import kmobrevamp.model.User;
import kmobrevamp.service.ComplaintService;
import kmobrevamp.service.PartService;
import kmobrevamp.service.UserService;

@Controller
public class FactoryController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PartService partService;
	
	@Autowired
	private ComplaintService complaintService;
	
	@RequestMapping(value="/factory/home", method = RequestMethod.GET)
	public ModelAndView factoryhome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Part> parts=partService.getAll();
		PartComparator comparator=new PartComparator();
		Collections.sort(parts, comparator);
		Complaint complaint=new Complaint();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("complaint", complaint);
		modelAndView.addObject("parts",parts);
		modelAndView.addObject("st","AMC");
		modelAndView.addObject("saved", false);
		modelAndView.setViewName("factory/newcomplaint");
		return modelAndView;
	}
	
	@RequestMapping(value="/factory/home", method = RequestMethod.POST)
	public ModelAndView registerComplaint(@Valid Complaint complaint,BindingResult result){
		System.out.println(complaint.toString());
		ModelAndView modelAndView = new ModelAndView();
		Complaint complaintExists=complaintService.findComplaintBySno(complaint.getSno());
		if(complaintExists!=null)
		{
			result.rejectValue("sno", "error.complaint","There is already a complaint registered against same sno");
		}
		if(result.hasErrors())
		{
			for(ObjectError r:result.getAllErrors())
			{
				
				System.out.println(r.getDefaultMessage());
			}
			
			List<Part> parts=partService.getAll();
			PartComparator comparator=new PartComparator();
			Collections.sort(parts, comparator);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("complaint", complaint);
			modelAndView.addObject("parts",parts);
			modelAndView.addObject("st","AMC");
			modelAndView.addObject("saved", false);
			modelAndView.setViewName("factory/newcomplaint");
			return modelAndView;	
			
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		complaint.setRegisteruser(user.getEmail());
		complaint.setCreateddate(new Date());	
		System.out.println(complaint.toString());
		complaintService.saveComplaint(complaint);
		List<Part> parts=partService.getAll();
		PartComparator comparator=new PartComparator();
		Collections.sort(parts, comparator);
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("complaint", new Complaint());
		modelAndView.addObject("parts",parts);
		modelAndView.addObject("st","AMC");
		modelAndView.addObject("saved", true);
		modelAndView.setViewName("factory/newcomplaint");
		return modelAndView;
	}
	
	@RequestMapping(value="/factory/searchcomplaint", method = RequestMethod.GET)
	public ModelAndView supportUpdateComplaint(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchComplaint",new SearchComplaint());
		modelAndView.addObject("saved",false);
		modelAndView.setViewName("factory/searchcomplaint");
		return modelAndView;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/factory/searchcomplaint", method = RequestMethod.POST)
	public ModelAndView supportSearchComplaint(@Valid SearchComplaint searchComplaint, BindingResult result){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Part> parts=partService.getAll();
		PartComparator comparator=new PartComparator();
		Collections.sort(parts, comparator);
		Complaint complaint=complaintService.findComplaintBySno(searchComplaint.getSno());
		if(complaint!=null)
		{
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("complaint", complaint);
			modelAndView.addObject("parts",parts);
			modelAndView.addObject("st","AMC");
			modelAndView.addObject("saved", false);
			modelAndView.setViewName("factory/updatecomplaint");
			return modelAndView;
			
		}
		else{
		
		result.rejectValue("sno", "error.user","There is no complaint with this Serialno");
		if(result.hasErrors())
		{
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchComplaint",searchComplaint);
			modelAndView.addObject("saved",false);
			modelAndView.setViewName("factory/searchcomplaint");
			
		}
		
		return modelAndView;
		
		}
	}
	
	@RequestMapping(value="/factory/updatecomplaint", method = RequestMethod.POST)
	public ModelAndView updateComplaint(@Valid Complaint complaint,BindingResult result){
		ModelAndView modelAndView = new ModelAndView();
		if(result.hasErrors())
		{
			List<ObjectError> errors=result.getAllErrors();
			for(ObjectError error:errors)
			{
				
				System.out.println(error.getDefaultMessage());
				
			}
			
			List<Part> parts=partService.getAll();
			PartComparator comparator=new PartComparator();
			Collections.sort(parts, comparator);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("complaint", complaint);
			modelAndView.addObject("parts",parts);
			modelAndView.addObject("st","AMC");
			modelAndView.addObject("saved", false);
			modelAndView.setViewName("factory/updatecomplaint");
			return modelAndView;	
			
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		complaint.setUpdateuser(user.getEmail());
		complaint.setUpdatedate(new Date());	
		System.out.println(complaint.toString());
		complaintService.saveComplaint(complaint);
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchComplaint",new SearchComplaint());
		modelAndView.addObject("saved",true);
		modelAndView.setViewName("factory/searchcomplaint");
		return modelAndView;
	}
	
	@RequestMapping(value="/factory/registration", method=RequestMethod.GET)
	public ModelAndView registration()
	{
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		Set<ServiceCenter> serviceList=userService.getFactoryById(user.getLocation()).getServiceCenters();
		modelAndView.addObject("services",serviceList);
		User newUser=new User();
		modelAndView.addObject("user", newUser);
		modelAndView.setViewName("factory/registration");
		return modelAndView;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/factory/registration", method=RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user,BindingResult result)
	{
		ModelAndView modelAndView=new ModelAndView();
		User userExists=userService.findUserByEmail(user.getEmail());
		if(userExists!=null)
		{
			
			result.rejectValue("email", "error.user","There is already a user registered with email provided");
		}
		
		if(result.hasErrors())
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user1 = userService.findUserByEmail(auth.getName());
			Set<ServiceCenter> serviceList=userService.getFactoryById(user1.getLocation()).getServiceCenters();
			modelAndView.addObject("services",serviceList);
			modelAndView.addObject("userName", "Welcome " + user1.getName() + " (" + user1.getEmail() + ")");
			modelAndView.setViewName("factory/registration");
		}
		else{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user1 = userService.findUserByEmail(auth.getName());
			List<GrantedAuthority> list=(List<GrantedAuthority>) auth.getAuthorities();
			userService.saveServiceCenterUser(user);
			Set<ServiceCenter> serviceList=userService.getFactoryById(user1.getLocation()).getServiceCenters();
			modelAndView.addObject("services",serviceList);
			modelAndView.addObject("userName", "Welcome " + user1.getName() + " (" + user1.getEmail() + ")");
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("factory/registration");
		}
		
		return modelAndView;
	}
	
	
}
