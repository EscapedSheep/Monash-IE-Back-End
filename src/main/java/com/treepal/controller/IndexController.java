package com.treepal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.treepal.domain.Tree;
import com.treepal.utils.Const;

/**
*  control Index page of the web
*
* @author  Maida Ge
* @version 2.0
* @date   2019-09-09
*/
@Controller
@RequestMapping("/")
public class IndexController extends BaseController{
	
	
	@RequestMapping(value="/index")
	public String index(Model model){
//		IndexCollectorView indexCollectorView = collectorService.getCollectors();
		Tree tree = super.getTree();
		if(null != tree){
			model.addAttribute(Const.LOGIN_SESSION_KEY,tree);
		}
		return "index";
	}
	
	@RequestMapping(value="/")
	public String home(Model model) {
		Tree tree = super.getTree();
		if(null != tree){
			model.addAttribute(Const.LOGIN_SESSION_KEY,tree);
			return "activity";
		}
		return "index";
	}
	
	@RequestMapping(value="/activity")
	public String activity(Model model) {
		Tree tree = super.getTree();
		if(null != tree){
			model.addAttribute(Const.LOGIN_SESSION_KEY,tree);
			return "activity";
		}
		return "activity";
	}
	
	@RequestMapping(value="/tree")
	public String tree() {
		return "tree";
	}

	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletResponse response,Model model) {
		getSession().removeAttribute(Const.LOGIN_SESSION_KEY);
		Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		model.addAttribute(Const.LOGIN_SESSION_KEY,null);
		response.addCookie(cookie);
		return "index";
	}
	
	@RequestMapping(value="/addTree")
	public String addTree() {
		return "addTree";
	}
	
	@RequestMapping(value="/test")
	public String test() {
		return "test";
	}
}
