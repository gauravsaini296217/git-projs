package kmobrevamp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kmobrevamp.model.Complaint;
import kmobrevamp.model.User;
import kmobrevamp.service.ComplaintService;
import kmobrevamp.service.UserService;

@Controller
public class SupportController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ComplaintService complaintService;
	
	@RequestMapping(value="/support/home", method = RequestMethod.GET)
	public ModelAndView factoryhome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Complaint complaint=new Complaint();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("complaint", complaint);
		modelAndView.setViewName("support/home");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/support/home", method = RequestMethod.POST)
	public ModelAndView registerComplaint(@Valid Complaint complaint,BindingResult result){
		ModelAndView modelAndView = new ModelAndView();
		
		if(result.hasErrors())
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("complaint", complaint);
			modelAndView.setViewName("support/home");
			return modelAndView;	
			
		}
		
		System.out.println(complaint.toString());
		complaintService.saveComplaint(complaint);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("complaint", new Complaint());
		modelAndView.setViewName("support/home");
		return modelAndView;
	}
}
