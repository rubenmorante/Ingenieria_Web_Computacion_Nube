package com.iwcn.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {

	@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("list").addObject("a","a");
	}
	
	/*@RequestMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("list").addObject("a","a");
	}*/
}
