package aadhaartokens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TokenService {

	@RequestMapping(value="/", method={RequestMethod.GET})
	public ModelAndView getToken()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("gettoken");
		return modelAndView;
		
	}
	
}
