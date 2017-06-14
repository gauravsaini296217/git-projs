package kmobrevamp.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kmobrevamp.comparator.PartComparator;
import kmobrevamp.model.Complaint;
import kmobrevamp.model.Part;
import kmobrevamp.model.SearchComplaint;
import kmobrevamp.model.User;
import kmobrevamp.service.ComplaintService;
import kmobrevamp.service.PartService;
import kmobrevamp.service.UserService;

@Controller
public class SupportController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private PartService partService;
	
	@RequestMapping(value="/support/home", method = RequestMethod.GET)
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
		modelAndView.setViewName("support/newcomplaint");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/support/home", method = RequestMethod.POST)
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
			modelAndView.setViewName("support/newcomplaint");
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
		modelAndView.setViewName("support/newcomplaint");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/support/searchcomplaint", method = RequestMethod.GET)
	public ModelAndView supportUpdateComplaint(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchComplaint",new SearchComplaint());
		modelAndView.addObject("saved",false);
		modelAndView.setViewName("support/searchcomplaint");
		return modelAndView;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/support/searchcomplaint", method = RequestMethod.POST)
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
			modelAndView.setViewName("support/updatecomplaint");
			return modelAndView;
			
		}
		else{
		
		result.rejectValue("sno", "error.user","There is no complaint with this Serialno");
		if(result.hasErrors())
		{
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchComplaint",searchComplaint);
			modelAndView.addObject("saved",false);
			modelAndView.setViewName("support/searchcomplaint");
			
		}
		
		return modelAndView;
		
		}
	}
	
	
	@RequestMapping(value="/support/updatecomplaint", method = RequestMethod.POST)
	public ModelAndView updateComplaint(@Valid Complaint complaint,BindingResult result){
		System.out.println(complaint.toString());
		ModelAndView modelAndView = new ModelAndView();
		if(result.hasErrors())
		{
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
			modelAndView.setViewName("support/updatecomplaint");
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
		modelAndView.setViewName("support/searchcomplaint");
		return modelAndView;
	}
	
}
