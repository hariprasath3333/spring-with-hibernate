package com.hari.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdditionController {

	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView("Addition");
		return modelAndView;
	}
	
}
