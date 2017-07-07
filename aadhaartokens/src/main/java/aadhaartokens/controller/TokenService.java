package aadhaartokens.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import aadhaartokens.model.AadhaarTokenStatus;
import aadhaartokens.model.AddressDetail;
import aadhaartokens.model.BranchDateToken;
import aadhaartokens.model.CheckOTP;
import aadhaartokens.model.DateSpecific;
import aadhaartokens.model.District;
import aadhaartokens.model.Dists;
import aadhaartokens.model.Dob;
import aadhaartokens.model.EnrolmentDetails;
import aadhaartokens.model.EnrolmentSpecific;
import aadhaartokens.model.EnrolmentType;
import aadhaartokens.model.EntryType;
import aadhaartokens.model.IssuedTokenDetails;
import aadhaartokens.model.OTP;
import aadhaartokens.model.Pec;
import aadhaartokens.model.Poa;
import aadhaartokens.model.Poi;
import aadhaartokens.model.Por;
import aadhaartokens.model.Posts;
import aadhaartokens.model.RegionSpecific;
import aadhaartokens.model.ReportRequest;
import aadhaartokens.model.RowDates;
import aadhaartokens.model.SearchRequest;
import aadhaartokens.model.State;
import aadhaartokens.model.States;
import aadhaartokens.model.StatusSpecific;
import aadhaartokens.model.TokenDetail;
import aadhaartokens.model.TokenRequest;
import aadhaartokens.model.User;
import aadhaartokens.model.Vills;
import aadhaartokens.service.AadhaarTokenStatusService;
import aadhaartokens.service.AddressDetailService;
import aadhaartokens.service.DateSpecificService;
import aadhaartokens.service.DistrictService;
import aadhaartokens.service.DobService;
import aadhaartokens.service.EnrolmentSpecificService;
import aadhaartokens.service.EnrolmentTypeService;
import aadhaartokens.service.EntryTypeService;
import aadhaartokens.service.PecService;
import aadhaartokens.service.PoaService;
import aadhaartokens.service.PoiService;
import aadhaartokens.service.PorService;
import aadhaartokens.service.RegionSpecificService;
import aadhaartokens.service.StateService;
import aadhaartokens.service.StatusSpecificService;
import aadhaartokens.service.UserService;
import aadhaartokens.utility.AadhaarUtility;

@Controller
public class TokenService {
	
	@Autowired
	StateService stateService;
	
	@Autowired
	DistrictService districtService;

	@Autowired
	PecService pecService;
	
	@Autowired
	EnrolmentTypeService enrolmentTypeService;
	
	@Autowired
	EntryTypeService entryTypeService;
	
	@Autowired
	RegionSpecificService regionSpecificService;
	
	@Autowired
	EnrolmentSpecificService enrolmentSpecificService;
	
	@Autowired
	StatusSpecificService statusSpecificService;
	
	@Autowired
	DateSpecificService dateSpecificService;
	
	@Autowired
	AadhaarTokenStatusService aadhaarTokenStatusService;
	
	@Autowired
	PoiService poiService;
	
	@Autowired
	PoaService poaService;
	
	@Autowired
	PorService porService;
	
	@Autowired
	DobService dobService;
	
	@Autowired
	AddressDetailService addressDetailService;
	
	AadhaarUtility aadhaarUtility=new AadhaarUtility();
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> list=(List<GrantedAuthority>) auth.getAuthorities();
        if (list.get(0).getAuthority().equalsIgnoreCase("Admin")) {
            return "redirect:/admin/home";
        }
        else if (list.get(0).getAuthority().equalsIgnoreCase("State")) {
            return "redirect:/state/home";
        }
        else if (list.get(0).getAuthority().equalsIgnoreCase("Branch")) {
            return "redirect:/branch/home";
        }
        
        return "redirect:/login";
    }
	
	@RequestMapping(value={"/login"}, method=RequestMethod.GET)
	public ModelAndView login()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/error", method={RequestMethod.GET})
	public ModelAndView getError()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("error");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/access-denied", method = RequestMethod.GET)
	public ModelAndView accessdenied(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView adminhome(){
		
		List<State> stateList=stateService.getAll();
		Collections.sort(stateList);
		State othersState=stateService.getState(23);
		stateList.remove(othersState);
		List<District> districtList=districtService.getAll();
		Collections.sort(districtList);
		List<Pec> pecList=pecService.getAll();
		Collections.sort(pecList);
		List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
		Collections.sort(enrolmentTypeList);
		List<RegionSpecific> regionSpecificList=regionSpecificService.getAll();
		List<EnrolmentSpecific> enrolmentSpecificList=enrolmentSpecificService.getAll();
		List<StatusSpecific> statusSpecificList=statusSpecificService.getAll();
		List<DateSpecific> dateSpecificList=dateSpecificService.getAll();
		List<AadhaarTokenStatus> aadhaarTokenStatusList=aadhaarTokenStatusService.getAll();
		ReportRequest reportRequest=new ReportRequest();
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("regionSpecificList", regionSpecificList);
		modelAndView.addObject("states", stateList);
		modelAndView.addObject("districts", districtList);
		modelAndView.addObject("pecs", pecList);
		modelAndView.addObject("enrolmentSpecificList", enrolmentSpecificList);
		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
		modelAndView.addObject("statusSpecificList", statusSpecificList);
		modelAndView.addObject("aadhaarTokenStatusList", aadhaarTokenStatusList);
		modelAndView.addObject("dateSpecificList", dateSpecificList);
		modelAndView.addObject("reportRequest", reportRequest);
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin/home", method=RequestMethod.POST)
	public ModelAndView getReport(@Valid ReportRequest reportRequest,BindingResult result)
	{
		if(reportRequest.getDatespecific().equalsIgnoreCase("Date Range") && reportRequest.getFdate().compareTo(reportRequest.getTdate())>0)
		{
			result.rejectValue("fdate", "error.fdate","From Date can't be After To Date");
		}
		
		if(result.hasErrors())
		{
			List<State> stateList=stateService.getAll();
			Collections.sort(stateList);
			State othersState=stateService.getState(23);
			stateList.remove(othersState);
			List<District> districtList=districtService.getAll();
			Collections.sort(districtList);
			List<Pec> pecList=pecService.getAll();
			Collections.sort(pecList);
			List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
			Collections.sort(enrolmentTypeList);
			List<RegionSpecific> regionSpecificList=regionSpecificService.getAll();
			List<EnrolmentSpecific> enrolmentSpecificList=enrolmentSpecificService.getAll();
			List<StatusSpecific> statusSpecificList=statusSpecificService.getAll();
			List<DateSpecific> dateSpecificList=dateSpecificService.getAll();
			List<AadhaarTokenStatus> aadhaarTokenStatusList=aadhaarTokenStatusService.getAll();
			ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("regionSpecificList", regionSpecificList);
			modelAndView.addObject("states", stateList);
			modelAndView.addObject("districts", districtList);
			modelAndView.addObject("pecs", pecList);
			modelAndView.addObject("enrolmentSpecificList", enrolmentSpecificList);
			modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
			modelAndView.addObject("statusSpecificList", statusSpecificList);
			modelAndView.addObject("aadhaarTokenStatusList", aadhaarTokenStatusList);
			modelAndView.addObject("dateSpecificList", dateSpecificList);
			modelAndView.addObject("reportRequest", reportRequest);
			modelAndView.setViewName("admin/home");
			return modelAndView;
		}
		
		List<TokenDetail> tokenDetails=aadhaarUtility.getReportResult(reportRequest);
		
		return new ModelAndView("excelView","tokenDetails", tokenDetails);
	}
	
	
	@RequestMapping(value="/state/home", method = RequestMethod.GET)
	public ModelAndView statehome(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		State state=stateService.getState(Integer.parseInt(user.getPeccode()));
		
		List<District> districtList=districtService.getStateWiseDistricts(state.stateid);
		Collections.sort(districtList);
		List<Pec> pecList=pecService.getAll();
		Collections.sort(pecList);
		List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
		Collections.sort(enrolmentTypeList);
		List<RegionSpecific> regionSpecificList=regionSpecificService.getAll();
		RegionSpecific rSpecific=regionSpecificService.getOne("State");
		regionSpecificList.remove(rSpecific);
		List<EnrolmentSpecific> enrolmentSpecificList=enrolmentSpecificService.getAll();
		List<StatusSpecific> statusSpecificList=statusSpecificService.getAll();
		List<DateSpecific> dateSpecificList=dateSpecificService.getAll();
		List<AadhaarTokenStatus> aadhaarTokenStatusList=aadhaarTokenStatusService.getAll();
		ReportRequest reportRequest=new ReportRequest();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("regionSpecificList", regionSpecificList);
		modelAndView.addObject("districts", districtList);
		modelAndView.addObject("pecs", pecList);
		modelAndView.addObject("enrolmentSpecificList", enrolmentSpecificList);
		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
		modelAndView.addObject("statusSpecificList", statusSpecificList);
		modelAndView.addObject("aadhaarTokenStatusList", aadhaarTokenStatusList);
		modelAndView.addObject("dateSpecificList", dateSpecificList);
		modelAndView.addObject("reportRequest", reportRequest);
		modelAndView.setViewName("state/home");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/state/home", method=RequestMethod.POST)
	public ModelAndView getStateReport(@Valid ReportRequest reportRequest,BindingResult result)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		State state=stateService.getState(Integer.parseInt(user.getPeccode()));
		
		
		if(reportRequest.getDatespecific().equalsIgnoreCase("Date Range") && reportRequest.getFdate().compareTo(reportRequest.getTdate())>0)
		{
			result.rejectValue("fdate", "error.fdate","From Date can't be After To Date");
		}
		
		if(result.hasErrors())
		{
			List<District> districtList=districtService.getStateWiseDistricts(state.stateid);
			Collections.sort(districtList);
			List<Pec> pecList=pecService.getAll();
			Collections.sort(pecList);
			List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
			Collections.sort(enrolmentTypeList);
			List<RegionSpecific> regionSpecificList=regionSpecificService.getAll();
			RegionSpecific rSpecific=regionSpecificService.getOne("State");
			regionSpecificList.remove(rSpecific);
			List<EnrolmentSpecific> enrolmentSpecificList=enrolmentSpecificService.getAll();
			List<StatusSpecific> statusSpecificList=statusSpecificService.getAll();
			List<DateSpecific> dateSpecificList=dateSpecificService.getAll();
			List<AadhaarTokenStatus> aadhaarTokenStatusList=aadhaarTokenStatusService.getAll();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("regionSpecificList", regionSpecificList);
			modelAndView.addObject("districts", districtList);
			modelAndView.addObject("pecs", pecList);
			modelAndView.addObject("enrolmentSpecificList", enrolmentSpecificList);
			modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
			modelAndView.addObject("statusSpecificList", statusSpecificList);
			modelAndView.addObject("aadhaarTokenStatusList", aadhaarTokenStatusList);
			modelAndView.addObject("dateSpecificList", dateSpecificList);
			modelAndView.addObject("reportRequest", reportRequest);
			modelAndView.setViewName("state/home");
			return modelAndView;
		}
		
		List<TokenDetail> tokenDetails=aadhaarUtility.getStateReportResult(reportRequest, user.getPeccode());
		
		return new ModelAndView("excelView","tokenDetails", tokenDetails);
	}
	
	
	@RequestMapping(value="/branch/home", method = RequestMethod.GET)
	public ModelAndView branchhome(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
		Collections.sort(enrolmentTypeList);
		List<EnrolmentSpecific> enrolmentSpecificList=enrolmentSpecificService.getAll();
		List<StatusSpecific> statusSpecificList=statusSpecificService.getAll();
		List<DateSpecific> dateSpecificList=dateSpecificService.getAll();
		List<AadhaarTokenStatus> aadhaarTokenStatusList=aadhaarTokenStatusService.getAll();
		ReportRequest reportRequest=new ReportRequest();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("enrolmentSpecificList", enrolmentSpecificList);
		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
		modelAndView.addObject("statusSpecificList", statusSpecificList);
		modelAndView.addObject("aadhaarTokenStatusList", aadhaarTokenStatusList);
		modelAndView.addObject("dateSpecificList", dateSpecificList);
		modelAndView.addObject("reportRequest", reportRequest);
		modelAndView.setViewName("branch/home");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/branch/home", method=RequestMethod.POST)
	public ModelAndView getBranchReport(@Valid ReportRequest reportRequest,BindingResult result)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		State state=stateService.getState(Integer.parseInt(user.getPeccode()));
		
		
		if(reportRequest.getDatespecific().equalsIgnoreCase("Date Range") && reportRequest.getFdate().compareTo(reportRequest.getTdate())>0)
		{
			result.rejectValue("fdate", "error.fdate","From Date can't be After To Date");
		}
		
		if(result.hasErrors())
		{
			
			List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
			Collections.sort(enrolmentTypeList);
			List<EnrolmentSpecific> enrolmentSpecificList=enrolmentSpecificService.getAll();
			List<StatusSpecific> statusSpecificList=statusSpecificService.getAll();
			List<DateSpecific> dateSpecificList=dateSpecificService.getAll();
			List<AadhaarTokenStatus> aadhaarTokenStatusList=aadhaarTokenStatusService.getAll();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("enrolmentSpecificList", enrolmentSpecificList);
			modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
			modelAndView.addObject("statusSpecificList", statusSpecificList);
			modelAndView.addObject("aadhaarTokenStatusList", aadhaarTokenStatusList);
			modelAndView.addObject("dateSpecificList", dateSpecificList);
			modelAndView.addObject("reportRequest", reportRequest);
			modelAndView.setViewName("branch/home");
			return modelAndView;
		}
		
		List<TokenDetail> tokenDetails=aadhaarUtility.getBranchReportResult(reportRequest, user.getPeccode());
		
		return new ModelAndView("excelView","tokenDetails", tokenDetails);
	}
	
	@RequestMapping(value="/admin/registration", method=RequestMethod.GET)
	public ModelAndView registration()
	{
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<State> stateList=stateService.getAll();
		Collections.sort(stateList);
		State othersState=stateService.getState(23);
		stateList.remove(othersState);
		modelAndView.addObject("pecs",stateList);
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		User newUser=new User();
		modelAndView.addObject("user", newUser);
		modelAndView.setViewName("admin/registration");
		return modelAndView;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin/registration", method=RequestMethod.POST)
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
			List<State> stateList=stateService.getAll();
			Collections.sort(stateList);
			State othersState=stateService.getState(23);
			stateList.remove(othersState);
			modelAndView.addObject("pecs",stateList);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user1 = userService.findUserByEmail(auth.getName());
			modelAndView.addObject("userName", "Welcome " + user1.getName() + " (" + user1.getEmail() + ")");
			modelAndView.setViewName("admin/registration");
		}
		else{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user1 = userService.findUserByEmail(auth.getName());
			List<GrantedAuthority> list=(List<GrantedAuthority>) auth.getAuthorities();
			userService.saveStateUser(user);
			List<State> stateList=stateService.getAll();
			Collections.sort(stateList);
			State othersState=stateService.getState(23);
			stateList.remove(othersState);
			modelAndView.addObject("pecs",stateList);
			modelAndView.addObject("userName", "Welcome " + user1.getName() + " (" + user1.getEmail() + ")");
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("admin/registration");
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/state/registration", method=RequestMethod.GET)
	public ModelAndView stateregistration()
	{
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Pec> pecList=pecService.getStateWisePecs(Integer.parseInt(user.getPeccode()));
		Collections.sort(pecList);
		modelAndView.addObject("pecs",pecList);
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		User newUser=new User();
		modelAndView.addObject("user", newUser);
		modelAndView.setViewName("state/registration");
		return modelAndView;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/state/registration", method=RequestMethod.POST)
	public ModelAndView statecreateNewUser(@Valid User user,BindingResult result)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user1 = userService.findUserByEmail(auth.getName());
		
		
		ModelAndView modelAndView=new ModelAndView();
		User userExists=userService.findUserByEmail(user.getEmail());
		if(userExists!=null)
		{
			
			result.rejectValue("email", "error.user","There is already a user registered with email provided");
		}
		
		if(result.hasErrors())
		{
			List<Pec> pecList=pecService.getStateWisePecs(Integer.parseInt(user1.getPeccode()));
			Collections.sort(pecList);
			modelAndView.addObject("pecs",pecList);
			modelAndView.addObject("userName", "Welcome " + user1.getName() + " (" + user1.getEmail() + ")");
			modelAndView.setViewName("state/registration");
		}
		else{
			List<GrantedAuthority> list=(List<GrantedAuthority>) auth.getAuthorities();
			userService.saveBranchUser(user);
			List<Pec> pecList=pecService.getStateWisePecs(Integer.parseInt(user1.getPeccode()));
			Collections.sort(pecList);
			modelAndView.addObject("pecs",pecList);
			modelAndView.addObject("userName", "Welcome " + user1.getName() + " (" + user1.getEmail() + ")");
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("state/registration");
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/branch/update", method=RequestMethod.GET)
	public ModelAndView branchupdate()
	{
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		SearchRequest searchRequest=new SearchRequest();
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", false);
		modelAndView.setViewName("branch/update");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/branch/update", method=RequestMethod.POST)
	public ModelAndView branchupdatePost(@Valid SearchRequest searchRequest,BindingResult result)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		try{
		
		
		if(result.hasErrors())
		{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		if(!searchRequest.getEnrolmentid().equalsIgnoreCase(""))
		{
			modelAndView.addObject("result", true);
		}
		else{
			modelAndView.addObject("result", false);	
		}
		
		modelAndView.setViewName("branch/update");
		return modelAndView;
		}
		
		System.out.println("EnrolmentId:"+searchRequest.getEnrolmentid());
		
		if(searchRequest.getEnrolmentid()==null || searchRequest.getEnrolmentid().equalsIgnoreCase(""))
		{
		TokenDetail tokenDetail=aadhaarUtility.getSearchResult(searchRequest);
		
		if(tokenDetail==null)
		{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchRequest", searchRequest);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "No Result Found");
			modelAndView.addObject("result", false);
			modelAndView.setViewName("branch/update");
			return modelAndView;
			
		}
		
		searchRequest.setTokenid(String.valueOf(tokenDetail.getTokenid()));
		searchRequest.setTname(tokenDetail.getName());
		searchRequest.setTmobile(tokenDetail.getContactno());
		searchRequest.setTtoken(tokenDetail.getFulltokenno());
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", true);
		modelAndView.setViewName("branch/update");
		return modelAndView;
		}
		else{
			
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		
		String enroldatetime=searchRequest.getEnrolmentid().substring(14, 28);
		
		System.out.println("Enroldatetime:"+enroldatetime);
		
		Date dd=format.parse(enroldatetime);
		
		
		System.out.println("SearchRequestGaurav:"+searchRequest);
		System.out.println("Date:"+dd);
		if(dd==null)
		{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchRequest", searchRequest);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "Invalid Enrolment Id");
			modelAndView.addObject("result", true);
			modelAndView.setViewName("branch/update");
			return modelAndView;
			
			
		}
		else{
		String result1=aadhaarUtility.updateResult(searchRequest, user.getEmail());	
			if(result1.equalsIgnoreCase("Updated"))
			{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			SearchRequest searchRequest1=new SearchRequest();
			modelAndView.addObject("searchRequest", searchRequest1);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "Updated Successfully");
			modelAndView.addObject("result", false);
			modelAndView.setViewName("branch/update");
			return modelAndView;
			}
			else{
				
				ModelAndView modelAndView=new ModelAndView();
				modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
				modelAndView.addObject("searchRequest", searchRequest);
				modelAndView.addObject("message", true);
				modelAndView.addObject("msg", "Error While Updating");
				modelAndView.addObject("result", true);
				modelAndView.setViewName("branch/update");
				return modelAndView;
				
			}
		}
			
		}
		
		}catch(Exception e)
		{
			
			System.out.println(e);
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchRequest", searchRequest);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "Invalid Enrolment Id");
			modelAndView.addObject("result", true);
			modelAndView.setViewName("branch/update");
			return modelAndView;
			
		}
	}
	
	@RequestMapping(value="/branch/search", method=RequestMethod.GET)
	public ModelAndView branchsearch()
	{
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		SearchRequest searchRequest=new SearchRequest();
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", false);
		modelAndView.setViewName("branch/search");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/branch/search", method=RequestMethod.POST)
	public ModelAndView branchsearchPost(@Valid SearchRequest searchRequest,BindingResult result)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		try{
		
		
		if(result.hasErrors())
		{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
	    modelAndView.addObject("result", false);	
		modelAndView.setViewName("branch/search");
		return modelAndView;
		}
		
		System.out.println("EnrolmentId:"+searchRequest.getEnrolmentid());
		
		
		List<TokenDetail> tokendetails=aadhaarUtility.getSearchResults(searchRequest);
		
		if(tokendetails.size()==0)
		{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchRequest", searchRequest);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "No Result Found");
			modelAndView.addObject("result", false);
			modelAndView.setViewName("branch/search");
			return modelAndView;
			
		}
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", true);
		modelAndView.addObject("tokendetails", tokendetails);
		modelAndView.setViewName("branch/search");
		return modelAndView;
		
		
		
		}catch(Exception e)
		{
			
			System.out.println(e);
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchRequest", searchRequest);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "Invalid Enrolment Id");
			modelAndView.addObject("result", true);
			modelAndView.setViewName("branch/search");
			return modelAndView;
			
		}
	}
	
	
	@RequestMapping(value="/admin/update", method=RequestMethod.GET)
	public ModelAndView adminupdate()
	{
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		SearchRequest searchRequest=new SearchRequest();
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", false);
		modelAndView.setViewName("admin/update");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/admin/update", method=RequestMethod.POST)
	public ModelAndView adminupdatePost(@Valid SearchRequest searchRequest,BindingResult result)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		if(result.hasErrors())
		{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		if(!searchRequest.getEnrolmentid().equalsIgnoreCase(""))
		{
			modelAndView.addObject("result", true);
		}
		else{
			modelAndView.addObject("result", false);	
		}
		
		modelAndView.setViewName("admin/update");
		return modelAndView;
		}
		
		System.out.println("EnrolmentId:"+searchRequest.getEnrolmentid());
		
		if(searchRequest.getEnrolmentid()==null || searchRequest.getEnrolmentid().equalsIgnoreCase(""))
		{
		TokenDetail tokenDetail=aadhaarUtility.getSearchResult(searchRequest);
		
		if(tokenDetail==null)
		{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchRequest", searchRequest);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "No Result Found");
			modelAndView.addObject("result", false);
			modelAndView.setViewName("admin/update");
			return modelAndView;
			
		}
		
		searchRequest.setTokenid(String.valueOf(tokenDetail.getTokenid()));
		searchRequest.setTname(tokenDetail.getName());
		searchRequest.setTmobile(tokenDetail.getContactno());
		searchRequest.setTtoken(tokenDetail.getFulltokenno());
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", true);
		modelAndView.setViewName("admin/update");
		return modelAndView;
		}
		else{
		
		System.out.println("SearchRequestGaurav:"+searchRequest);	
			
		String result1=aadhaarUtility.updateResult(searchRequest, user.getEmail());	
			if(result1.equalsIgnoreCase("Updated"))
			{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			SearchRequest searchRequest1=new SearchRequest();
			modelAndView.addObject("searchRequest", searchRequest1);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "Updated Successfully");
			modelAndView.addObject("result", false);
			modelAndView.setViewName("admin/update");
			return modelAndView;
			}
			else{
				
				ModelAndView modelAndView=new ModelAndView();
				modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
				modelAndView.addObject("searchRequest", searchRequest);
				modelAndView.addObject("message", true);
				modelAndView.addObject("msg", "Error While Updating");
				modelAndView.addObject("result", true);
				modelAndView.setViewName("admin/update");
				return modelAndView;
				
			}
			
		}
	}
	
	
	@RequestMapping(value="/state/update", method=RequestMethod.GET)
	public ModelAndView stateupdate()
	{
		ModelAndView modelAndView=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		SearchRequest searchRequest=new SearchRequest();
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", false);
		modelAndView.setViewName("admin/update");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/state/update", method=RequestMethod.POST)
	public ModelAndView stateupdatePost(@Valid SearchRequest searchRequest,BindingResult result)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		if(result.hasErrors())
		{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		if(!searchRequest.getEnrolmentid().equalsIgnoreCase(""))
		{
			modelAndView.addObject("result", true);
		}
		else{
			modelAndView.addObject("result", false);	
		}
		
		modelAndView.setViewName("admin/update");
		return modelAndView;
		}
		
		System.out.println("EnrolmentId:"+searchRequest.getEnrolmentid());
		
		if(searchRequest.getEnrolmentid()==null || searchRequest.getEnrolmentid().equalsIgnoreCase(""))
		{
		TokenDetail tokenDetail=aadhaarUtility.getSearchResult(searchRequest);
		
		if(tokenDetail==null)
		{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("searchRequest", searchRequest);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "No Result Found");
			modelAndView.addObject("result", false);
			modelAndView.setViewName("admin/update");
			return modelAndView;
			
		}
		
		searchRequest.setTokenid(String.valueOf(tokenDetail.getTokenid()));
		searchRequest.setTname(tokenDetail.getName());
		searchRequest.setTmobile(tokenDetail.getContactno());
		searchRequest.setTtoken(tokenDetail.getFulltokenno());
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("searchRequest", searchRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("result", true);
		modelAndView.setViewName("admin/update");
		return modelAndView;
		}
		else{
		
		System.out.println("SearchRequestGaurav:"+searchRequest);	
			
		String result1=aadhaarUtility.updateResult(searchRequest, user.getEmail());	
			if(result1.equalsIgnoreCase("Updated"))
			{
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
			SearchRequest searchRequest1=new SearchRequest();
			modelAndView.addObject("searchRequest", searchRequest1);
			modelAndView.addObject("message", true);
			modelAndView.addObject("msg", "Updated Successfully");
			modelAndView.addObject("result", false);
			modelAndView.setViewName("admin/update");
			return modelAndView;
			}
			else{
				
				ModelAndView modelAndView=new ModelAndView();
				modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
				modelAndView.addObject("searchRequest", searchRequest);
				modelAndView.addObject("message", true);
				modelAndView.addObject("msg", "Error While Updating");
				modelAndView.addObject("result", true);
				modelAndView.setViewName("admin/update");
				return modelAndView;
				
			}
			
		}
	}
	
	@RequestMapping(value="/", method={RequestMethod.GET})
	public ModelAndView getToken()
	{
		TokenRequest tokenRequest=new TokenRequest();
		tokenRequest.setStatus("N");
		tokenRequest.setTime("00:00");
		List<State> stateList=stateService.getAll();
		Collections.sort(stateList);
		State othersState=stateService.getState(23);
		stateList.remove(othersState);
		List<District> districtList=districtService.getAll();
		Collections.sort(districtList);
		List<Pec> pecList=pecService.getAll();
		Collections.sort(pecList);
		List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
		Collections.sort(enrolmentTypeList);
		List<EntryType> entryTypeList=entryTypeService.getAll();
		List<RowDates> rowDates=new ArrayList<>();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("states", stateList);
		modelAndView.addObject("districts", districtList);
		modelAndView.addObject("pecs", pecList);
		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
		modelAndView.addObject("entrytypes", entryTypeList);
		modelAndView.addObject("rowdates", rowDates);
		modelAndView.addObject("tokenRequest", tokenRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.addObject("disable",false);
		modelAndView.addObject("confrm", "");
		modelAndView.addObject("loadregion", "1");
		modelAndView.setViewName("gettoken");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView IssueToken(@Valid TokenRequest tokenRequest,BindingResult result){
		
		
		
		List<State> stateList=stateService.getAll();
		Collections.sort(stateList);
		State othersState=stateService.getState(23);
		stateList.remove(othersState);
		List<District> districtList=districtService.getAll();
		Collections.sort(districtList);
		List<Pec> pecList=pecService.getAll();
		Collections.sort(pecList);
		List<EnrolmentType> enrolmentTypeList=enrolmentTypeService.getAll();
		Collections.sort(enrolmentTypeList);
		List<EntryType> entryTypeList=entryTypeService.getAll();
		System.out.println("District:"+tokenRequest.getDistrict());
		System.out.println("Pec:"+tokenRequest.getPec());
		Pec pec=pecService.getone(Integer.parseInt(tokenRequest.getPec()));
		List<RowDates> rowDates=aadhaarUtility.getNextFifteenDays(pec.getPeccode());
		
		System.out.println(tokenRequest.getTime());
		if(result.hasErrors())
    	{
			ModelAndView modelAndView=new ModelAndView();
    		modelAndView.addObject("states", stateList);
    		modelAndView.addObject("districts", districtList);
    		modelAndView.addObject("pecs", pecList);
    		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
    		modelAndView.addObject("entrytypes", entryTypeList);
    		modelAndView.addObject("rowdates", rowDates);
    		modelAndView.addObject("tokenRequest", tokenRequest);
    		modelAndView.addObject("message", false);
    		modelAndView.addObject("msg", "");
    		modelAndView.addObject("disable",false);
    		modelAndView.addObject("confrm", "");
    		modelAndView.addObject("loadregion", "0");
    		modelAndView.setViewName("gettoken");
    		return modelAndView;
    		
    	}
		
		if(tokenRequest.getTime().equalsIgnoreCase("00:00"))
		{
		OTP otp=aadhaarUtility.sendOTP(tokenRequest.getMobile(), "00:00");
		tokenRequest.setTime(otp.getTime());
		tokenRequest.setStatus("G");
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("states", stateList);
		modelAndView.addObject("districts", districtList);
		modelAndView.addObject("pecs", pecList);
		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
		modelAndView.addObject("entrytypes", entryTypeList);
		modelAndView.addObject("rowdates", rowDates);
		modelAndView.addObject("tokenRequest", tokenRequest);
		modelAndView.addObject("message", true);
		modelAndView.addObject("msg", "OTP Sent to Mobile");
		modelAndView.addObject("disable",true);
		modelAndView.addObject("confrm", "");
		modelAndView.addObject("loadregion", "0");
		modelAndView.setViewName("gettoken");
		return modelAndView;
		
		}
		else if(tokenRequest.getStatus().equalsIgnoreCase("G"))
		{
		CheckOTP checkOTP=aadhaarUtility.checkOTP(tokenRequest.getMobile(), tokenRequest.getTime(), tokenRequest.getOtp());
		if(checkOTP.getStatus().equalsIgnoreCase("Correct"))
        {
        	tokenRequest.setStatus("V");
        	
        	ModelAndView modelAndView=new ModelAndView();
    		modelAndView.addObject("states", stateList);
    		modelAndView.addObject("districts", districtList);
    		modelAndView.addObject("pecs", pecList);
    		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
    		modelAndView.addObject("entrytypes", entryTypeList);
    		modelAndView.addObject("rowdates", rowDates);
    		modelAndView.addObject("tokenRequest", tokenRequest);
    		modelAndView.addObject("message", true);
    		modelAndView.addObject("msg", "OTP Successfully Validated");
    		modelAndView.addObject("disable",true);
    		if(aadhaarUtility.appointmentExists(tokenRequest.getName(), tokenRequest.getMobile()))
    		{
    			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
    		}
    		else{
    			modelAndView.addObject("confrm", "");
    		}
    		modelAndView.addObject("loadregion", "0");
    		modelAndView.setViewName("gettoken");
    		return modelAndView;
    		
        }
        else{
        	
        	
        		ModelAndView modelAndView=new ModelAndView();
        		modelAndView.addObject("states", stateList);
        		modelAndView.addObject("districts", districtList);
        		modelAndView.addObject("pecs", pecList);
        		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
        		modelAndView.addObject("entrytypes", entryTypeList);
        		modelAndView.addObject("rowdates", rowDates);
        		modelAndView.addObject("tokenRequest", tokenRequest);
        		modelAndView.addObject("message", true);
        		modelAndView.addObject("msg", "Wrong OTP entered");
        		modelAndView.addObject("disable",true);
        		if(aadhaarUtility.appointmentExists(tokenRequest.getName(), tokenRequest.getMobile()))
	    		{
	    			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
	    		}
	    		else{
	    			modelAndView.addObject("confrm", "");
	    		}
        		modelAndView.addObject("loadregion", "0");
        		modelAndView.setViewName("gettoken");
        		return modelAndView;
        		
        	
        }
			
		}

		/*if(tokenRequest.getEmail()==null || tokenRequest.getEmail().equalsIgnoreCase(""))
		{	
		result.rejectValue("email", "error.tokenservice","Email is mandatory");
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("states", stateList);
		modelAndView.addObject("districts", districtList);
		modelAndView.addObject("pecs", pecList);
		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
		modelAndView.addObject("entrytypes", entryTypeList);
		modelAndView.addObject("rowdates", rowDates);
		modelAndView.addObject("tokenRequest", tokenRequest);
		modelAndView.addObject("message", false);
		modelAndView.addObject("msg", "");
		modelAndView.setViewName("gettoken");
		return modelAndView;
		
		}*/
		
		
		System.out.println("EntryType:"+tokenRequest.getEntrytype());
		System.out.println("EnrolType:"+tokenRequest.getEnroltype());
		if(tokenRequest.getEntrytype().equalsIgnoreCase("1"))
		{
			System.out.println(tokenRequest.getRid()+" "+tokenRequest.getAadhaar());
			if(!tokenRequest.getEnroltype().equalsIgnoreCase("1"))
			{
				
				if(tokenRequest.getAadhaar()==null || tokenRequest.getAadhaar().equalsIgnoreCase(""))
				{
					
					ModelAndView modelAndView=new ModelAndView();
					modelAndView.addObject("states", stateList);
					modelAndView.addObject("districts", districtList);
					modelAndView.addObject("pecs", pecList);
					modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
					modelAndView.addObject("entrytypes", entryTypeList);
					modelAndView.addObject("rowdates", rowDates);
					modelAndView.addObject("tokenRequest", tokenRequest);
					modelAndView.addObject("message", true);
					modelAndView.addObject("msg", "Aadhaar is Mandatory If it is Updation Case");
					modelAndView.addObject("disable",true);
					if(aadhaarUtility.appointmentExists(tokenRequest.getName(), tokenRequest.getMobile()))
		    		{
		    			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
		    		}
		    		else{
		    			modelAndView.addObject("confrm", "");
		    		}
					modelAndView.addObject("loadregion", "0");
					modelAndView.setViewName("gettoken");
					return modelAndView;
					
				}
				else{
					
					IssuedTokenDetails issuedTokenDetails=new IssuedTokenDetails();
					try
			        {
						BranchDateToken branchDateToken=aadhaarUtility.getDateToken(Integer.parseInt(tokenRequest.getRid()), tokenRequest.getRpeccode(), tokenRequest.getRdate());
						
						tokenRequest.setRid(String.valueOf(branchDateToken.getId()));
						tokenRequest.setRdate(branchDateToken.getDate());
						tokenRequest.setRavtoken(String.valueOf(branchDateToken.getAvailabletokenno()));
						tokenRequest.setRmxtoken(String.valueOf(branchDateToken.getMaxtokens()));
						tokenRequest.setRtimeslot(branchDateToken.getTimeslot());
						tokenRequest.setRpeccode(branchDateToken.getPeccode());
						
						
						System.out.println("tokenRequest:"+tokenRequest.toString());
			    
						issuedTokenDetails=aadhaarUtility.issueToken(tokenRequest);
					
			        }catch(Exception e)
			        {
			        
			        	e.printStackTrace();
			        	
			        }
					
					
					return new ModelAndView("pdfView","issuedTokenDetails", issuedTokenDetails);
					
					
				}
				
				
			}
			else if(tokenRequest.getRid()==null || tokenRequest.getRid().equalsIgnoreCase(""))
			{
				
				System.out.println("Enter");
				ModelAndView modelAndView=new ModelAndView();
				modelAndView.addObject("states", stateList);
				modelAndView.addObject("districts", districtList);
				modelAndView.addObject("pecs", pecList);
				modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
				modelAndView.addObject("entrytypes", entryTypeList);
				modelAndView.addObject("tokenRequest", tokenRequest);
				modelAndView.addObject("rowdates", rowDates);
				modelAndView.addObject("message", true);
				modelAndView.addObject("msg", "Choose Appointment Date");
				modelAndView.addObject("disable",true);
				if(aadhaarUtility.appointmentExists(tokenRequest.getName(), tokenRequest.getMobile()))
	    		{
	    			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
	    		}
	    		else{
	    			modelAndView.addObject("confrm", "");
	    		}
				modelAndView.addObject("loadregion", "0");
				modelAndView.setViewName("gettoken");
				return modelAndView;
				
			}
			else{
				
				IssuedTokenDetails issuedTokenDetails=new IssuedTokenDetails();
				try
		        {
					BranchDateToken branchDateToken=aadhaarUtility.getDateToken(Integer.parseInt(tokenRequest.getRid()), tokenRequest.getRpeccode(), tokenRequest.getRdate());
					
					tokenRequest.setRid(String.valueOf(branchDateToken.getId()));
					tokenRequest.setRdate(branchDateToken.getDate());
					tokenRequest.setRavtoken(String.valueOf(branchDateToken.getAvailabletokenno()));
					tokenRequest.setRmxtoken(String.valueOf(branchDateToken.getMaxtokens()));
					tokenRequest.setRtimeslot(branchDateToken.getTimeslot());
					tokenRequest.setRpeccode(branchDateToken.getPeccode());
					
					
					System.out.println("tokenRequest:"+tokenRequest.toString());
		    
					issuedTokenDetails=aadhaarUtility.issueToken(tokenRequest);
					
		        }catch(Exception e)
		        {
		        
		        	e.printStackTrace();
		        	
		        }
				
				
				return new ModelAndView("pdfView","issuedTokenDetails", issuedTokenDetails);
				
			}
			
			
		}
		else{
			
			System.out.println(tokenRequest.getRid()+" "+tokenRequest.getAadhaar());
			if(!tokenRequest.getEnroltype().equalsIgnoreCase("1"))
			{
				
				if(tokenRequest.getAadhaar()==null || tokenRequest.getAadhaar().equalsIgnoreCase(""))
				{
					
					ModelAndView modelAndView=new ModelAndView();
					modelAndView.addObject("states", stateList);
					modelAndView.addObject("districts", districtList);
					modelAndView.addObject("pecs", pecList);
					modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
					modelAndView.addObject("entrytypes", entryTypeList);
					modelAndView.addObject("rowdates", rowDates);
					modelAndView.addObject("tokenRequest", tokenRequest);
					modelAndView.addObject("message", true);
					modelAndView.addObject("msg", "Aadhaar is Mandatory If it is Updation Case");
					modelAndView.addObject("disable",true);
					if(aadhaarUtility.appointmentExists(tokenRequest.getName(), tokenRequest.getMobile()))
		    		{
		    			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
		    		}
		    		else{
		    			modelAndView.addObject("confrm", "");
		    		}
					modelAndView.addObject("loadregion", "0");
					modelAndView.setViewName("gettoken");
					return modelAndView;
					
				}
				else{
				
					System.out.println("Enrolment Form U");
					
					EnrolmentDetails enrolmentDetails=new EnrolmentDetails();
					
					enrolmentDetails.setAadhaarno(tokenRequest.getAadhaar());
					if(tokenRequest.getEnroltype().equalsIgnoreCase("1"))
					{
					enrolmentDetails.setEnroltype("New Enrolment");	
					}
					else if(tokenRequest.getEnroltype().equalsIgnoreCase("2"))
					{
					enrolmentDetails.setEnroltype("Demographic Updation");	
					}
					else if(tokenRequest.getEnroltype().equalsIgnoreCase("3"))
					{
					enrolmentDetails.setEnroltype("Biometric Updation");	
					}
					enrolmentDetails.setMobile(tokenRequest.getMobile());
					enrolmentDetails.setEmail(tokenRequest.getEmail());
					enrolmentDetails.setOtp(tokenRequest.getOtp());
					enrolmentDetails.setOtptime(tokenRequest.getTime());
					enrolmentDetails.setSstate(tokenRequest.getState());
					enrolmentDetails.setSdistrict(tokenRequest.getDistrict());
					
					
					enrolmentDetails.setName(tokenRequest.getName());
					Pec pec1=pecService.getone(Integer.parseInt(tokenRequest.getPec()));
					List<RowDates> rowdates=aadhaarUtility.getNextFifteenDays(pec1.getPeccode());
					enrolmentDetails.setPeccode(pec1.getPeccode());
					
					List<Poi> pois=poiService.getAll();
					List<Poa> poas=poaService.getAll();
					List<Por> pors=porService.getAll();
					List<Dob> dobs=dobService.getAll();
					
					ModelAndView modelAndView=new ModelAndView();
					modelAndView.addObject("enrolmentDetails", enrolmentDetails);
					modelAndView.addObject("pois", pois);
					modelAndView.addObject("poas", poas);
					modelAndView.addObject("pors", pors);
					modelAndView.addObject("dobs", dobs);
					modelAndView.addObject("enrolmtype", tokenRequest.getEnroltype());
					modelAndView.addObject("rowdates", rowdates);
					if(aadhaarUtility.appointmentExists(enrolmentDetails.getName(), enrolmentDetails.getMobile()))
		    		{
		    			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
		    		}
		    		else{
		    			modelAndView.addObject("confrm", "");
		    		}
					modelAndView.setViewName("enrolmentform");
					return modelAndView;
					 
					
					
				}
				
				
			}
			
			else{
				
				System.out.println("Enrolment Form");
				EnrolmentDetails enrolmentDetails=new EnrolmentDetails();
				
				enrolmentDetails.setAadhaarno(tokenRequest.getAadhaar());
				if(tokenRequest.getEnroltype().equalsIgnoreCase("1"))
				{
				enrolmentDetails.setEnroltype("New Enrolment");	
				}
				else if(tokenRequest.getEnroltype().equalsIgnoreCase("2"))
				{
				enrolmentDetails.setEnroltype("Demographic Updation");	
				}
				else if(tokenRequest.getEnroltype().equalsIgnoreCase("3"))
				{
				enrolmentDetails.setEnroltype("Biometric Updation");	
				}
				enrolmentDetails.setMobile(tokenRequest.getMobile());
				enrolmentDetails.setEmail(tokenRequest.getEmail());
				enrolmentDetails.setOtp(tokenRequest.getOtp());
				enrolmentDetails.setOtptime(tokenRequest.getTime());
				enrolmentDetails.setSstate(tokenRequest.getState());
				enrolmentDetails.setSdistrict(tokenRequest.getDistrict());
				
				
				enrolmentDetails.setName(tokenRequest.getName());
				Pec pec1=pecService.getone(Integer.parseInt(tokenRequest.getPec()));
				List<RowDates> rowdates=aadhaarUtility.getNextFifteenDays(pec1.getPeccode());
				enrolmentDetails.setPeccode(pec1.getPeccode());
				
				List<Poi> pois=poiService.getAll();
				List<Poa> poas=poaService.getAll();
				List<Por> pors=porService.getAll();
				List<Dob> dobs=dobService.getAll();
				
				ModelAndView modelAndView=new ModelAndView();
				modelAndView.addObject("enrolmentDetails", enrolmentDetails);
				modelAndView.addObject("pois", pois);
				modelAndView.addObject("poas", poas);
				modelAndView.addObject("pors", pors);
				modelAndView.addObject("dobs", dobs);
				modelAndView.addObject("enrolmtype", tokenRequest.getEnroltype());
				modelAndView.addObject("rowdates", rowdates);
				if(aadhaarUtility.appointmentExists(enrolmentDetails.getName(), enrolmentDetails.getMobile()))
	    		{
	    			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
	    		}
	    		else{
	    			modelAndView.addObject("confrm", "");
	    		}
				modelAndView.setViewName("enrolmentform");
				return modelAndView;
				
			}
			
		}
		
		
		
		
	}
	
	@RequestMapping(value="/enrolmentform", method = RequestMethod.POST)
	public ModelAndView issueEnrolmentToken(@Valid EnrolmentDetails enrolmentDetails,BindingResult result){
		
		
		
		System.out.println("Peccode:"+enrolmentDetails.getPeccode());
		
		List<RowDates> rowdates=aadhaarUtility.getNextFifteenDays(enrolmentDetails.getPeccode());
		
		List<Poi> pois=poiService.getAll();
		List<Poa> poas=poaService.getAll();
		List<Por> pors=porService.getAll();
		List<Dob> dobs=dobService.getAll();
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("enrolmentDetails", enrolmentDetails);
		modelAndView.addObject("pois", pois);
		modelAndView.addObject("poas", poas);
		modelAndView.addObject("pors", pors);
		modelAndView.addObject("dobs", dobs);
		if(enrolmentDetails.getEnroltype().equalsIgnoreCase("New Enrolment"))
		{
			modelAndView.addObject("enrolmtype", "1");
		}
		else if(enrolmentDetails.getEnroltype().equalsIgnoreCase("Demographic Updation"))
		{
			modelAndView.addObject("enrolmtype", "2");
		}
		else if(enrolmentDetails.getEnroltype().equalsIgnoreCase("Biometric Updation"))
		{
			modelAndView.addObject("enrolmtype", "3");
		}
		
		modelAndView.addObject("rowdates", rowdates);
		if(aadhaarUtility.appointmentExists(enrolmentDetails.getName(), enrolmentDetails.getMobile()))
		{
			modelAndView.addObject("confrm", "You already has an Appointment, that will be cancelled automatically.");
		}
		else{
			modelAndView.addObject("confrm", "");
		}
		
		if(result.hasErrors())
		{
			modelAndView.setViewName("enrolmentform");
			return modelAndView;
			
		}
		
		System.out.println("EnrolmentDetails.vill:"+enrolmentDetails.getVill());
		
		if(enrolmentDetails.getVill()==null || enrolmentDetails.getVill().equalsIgnoreCase(""))
		{
			
			List<AddressDetail> addressDetails=addressDetailService.getAddressByPincode(Integer.parseInt(enrolmentDetails.getPin()));
			Set<States> states=new HashSet<States>();
			Set<Dists> dists=new HashSet<Dists>();
			Set<Posts> posts=new HashSet<Posts>();
			Set<Vills> vills=new HashSet<Vills>();
			States st=new States();
			Dists dt=new Dists();
			Posts pt=new Posts();
			Vills vt=new Vills();
			for(AddressDetail ad:addressDetails)
			{
				st=new States();
				dt=new Dists();
				pt=new Posts();
				vt=new Vills();
				
				st.setId(String.valueOf(ad.getStateCode()));
				st.setName(ad.getStateName());
				
				dt.setId(String.valueOf(ad.getDistrictCode()));
				dt.setName(ad.getDistrictName());
				
				pt.setId(String.valueOf(ad.getSubDistrictCode()));
				pt.setName(ad.getSubDistrictName());
				
				vt.setId(ad.getVtcCode());
				vt.setName(ad.getVtcName());
				
				states.add(st);
				dists.add(dt);
				posts.add(pt);
				vills.add(vt);
			}
			
			modelAndView.addObject("vills", vills);
			modelAndView.addObject("posts", posts);
			modelAndView.addObject("dists", dists);
			modelAndView.addObject("states", states);
			
			modelAndView.addObject("rowdates", rowdates);
			modelAndView.setViewName("enrolmentform");
			return modelAndView;
			
		}
		else{
			
			System.out.println("EnrolmentDetails:"+enrolmentDetails.getAadhaarno());
			System.out.println("EnrolmentDetails:"+enrolmentDetails.getEnroltype()+" "+enrolmentDetails.getName());
			
			if(enrolmentDetails.getAadhaarno()==null)
			{
				enrolmentDetails.setAadhaarno("");
				
			}
			
			if(enrolmentDetails.getAge()>5)
			{	
			
				enrolmentDetails.setDguardian("NA");
			
				enrolmentDetails.setDgname("NA");
			
				enrolmentDetails.setDgaadhaar("NA");
			}
			
			
			if(enrolmentDetails.getVertype().equalsIgnoreCase("DocumentBased"))
			{
				
				enrolmentDetails.setHof("NA");
				enrolmentDetails.setHofname("NA");
				enrolmentDetails.setHofaadhaar("NA");
				
			}
			else if(enrolmentDetails.getVertype().equalsIgnoreCase("HeadofFamily"))
			{
				
				enrolmentDetails.setPoi("NA");
				enrolmentDetails.setPoa("NA");
				enrolmentDetails.setDobp("NA");
				enrolmentDetails.setPor("NA");
				
			}
			
			try
	        {
		
				if(enrolmentDetails.getEnroltype().equalsIgnoreCase("New Enrolment"))
				{
				enrolmentDetails.setEnroltype("1");	
				}
				else if(enrolmentDetails.getEnroltype().equalsIgnoreCase("Demographic Updation"))
				{
				enrolmentDetails.setEnroltype("2");	
				}
				else if(enrolmentDetails.getEnroltype().equalsIgnoreCase("Biometric Updation"))
				{
				enrolmentDetails.setEnroltype("3");	
				}
				
				enrolmentDetails=aadhaarUtility.getEDateToken(enrolmentDetails);
			
	        }catch(Exception e)
	        {
	        
	        	e.printStackTrace();
	        	
	        }
			
			return new ModelAndView("enrolmentpdfView","enrolmentDetails", enrolmentDetails);
			
		}
		
	}
	
	
	
}
