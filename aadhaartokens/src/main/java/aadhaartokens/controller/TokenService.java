package aadhaartokens.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import aadhaartokens.model.State;
import aadhaartokens.model.TokenRequest;
import aadhaartokens.service.StateService;

@Controller
public class TokenService {
	
	@Autowired
	StateService stateService;

	@RequestMapping(value="/", method={RequestMethod.GET})
	public ModelAndView getToken()
	{
		TokenRequest tokenRequest=new TokenRequest();
		List<State> stateList=stateService.getAll();
		Collections.sort(stateList);
		State othersState=stateService.getState(23);
		stateList.remove(othersState);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("states", stateList);
		modelAndView.addObject("tokenRequest", tokenRequest);
		modelAndView.setViewName("gettoken");
		return modelAndView;
		
	}
	
}
