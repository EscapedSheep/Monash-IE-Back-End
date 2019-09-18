package com.treepal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.treepal.service.GameRecordService;
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
	
	@Autowired
	private GameRecordService gameRecordService;
	
	@RequestMapping(value="/index")
	public String index(Model model){
//		IndexCollectorView indexCollectorView = collectorService.getCollectors();
		//Tree tree = super.getTree();
		//if(null != tree){
			//model.addAttribute(Const.LOGIN_SESSION_KEY,tree);
		//}
		return "index";
	}
	
	@RequestMapping(value="/")
	public String home(Model model) {
		//Tree tree = super.getTree();
		//if(null != tree){
			//model.addAttribute(Const.LOGIN_SESSION_KEY,tree);
			//return "activity";
		//}
		return "index";
	}
	/*
	@RequestMapping(value="/activity")
	public String activity(Model model) {
		Tree tree = super.getTree();
		if(null != tree){
			model.addAttribute(Const.LOGIN_SESSION_KEY,tree);
			return "activity";
		}
		return "activity";
	}
	*/
	
	@RequestMapping(value="/game")
	public String tree(Model model) {
		//ModelAndView model = new ModelAndView();
		model.addAttribute(Const.GAME_RECORD,gameRecordService.getTop10());
		return "game";
	}

	@RequestMapping(value="/explore")
	public String explore() {
		return "explore";
	}
	
	@RequestMapping(value="/educate")
	public String educate() {
		return "educate";
	}
}
