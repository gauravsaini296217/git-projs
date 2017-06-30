package aadhaartokens.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import aadhaartokens.model.BranchDateToken;
import aadhaartokens.model.CheckOTP;
import aadhaartokens.model.District;
import aadhaartokens.model.EnrolmentType;
import aadhaartokens.model.EntryType;
import aadhaartokens.model.IssuedTokenDetails;
import aadhaartokens.model.OTP;
import aadhaartokens.model.Pec;
import aadhaartokens.model.RowDates;
import aadhaartokens.model.State;
import aadhaartokens.model.TokenRequest;
import aadhaartokens.service.DistrictService;
import aadhaartokens.service.EnrolmentTypeService;
import aadhaartokens.service.EntryTypeService;
import aadhaartokens.service.PecService;
import aadhaartokens.service.StateService;
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
	
	AadhaarUtility aadhaarUtility=new AadhaarUtility();
	
	@RequestMapping(value="/error", method={RequestMethod.GET})
	public ModelAndView getError()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("error");
		return modelAndView;
		
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
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("states", stateList);
		modelAndView.addObject("districts", districtList);
		modelAndView.addObject("pecs", pecList);
		modelAndView.addObject("enrolmenttypes", enrolmentTypeList);
		modelAndView.addObject("entrytypes", entryTypeList);
		modelAndView.addObject("tokenRequest", tokenRequest);
		modelAndView.addObject("message", true);
		modelAndView.addObject("msg", "Full information Functionality is under development");
		modelAndView.setViewName("gettoken");
		return modelAndView;
		
		
	}
	
	
}
