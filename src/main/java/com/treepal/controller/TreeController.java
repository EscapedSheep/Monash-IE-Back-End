package com.treepal.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.treepal.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treepal.domain.RestResult;
import com.treepal.domain.Tree;
import com.treepal.service.TreeService;
import com.treepal.utils.Const;
import com.treepal.utils.ResultGenerater;

@Controller
@RequestMapping("/tree")
public class TreeController extends BaseController{

	private final TreeService treeService;
	
	private final ResultGenerater resultGenerater;
	
	@Autowired
	public TreeController(TreeService treeService, ResultGenerater resultGenerater) {
		this.treeService = treeService;
		this.resultGenerater = resultGenerater;
	}
	
	@RequestMapping(value="/getNewTree", method = RequestMethod.GET)
	@ResponseBody
	public RestResult getNewTree(Tree tree, Model model) {
		
		/*
		 * @Validated Tree tree
		 * BindingResult bindingResult
		 *  method = RequestMethod.POST
		 *  */
		if (tree.getName().trim() == "") {
			return resultGenerater.getFailResult("Tree name should be provided");
		}
		/*
		if (treeService.checkExist(tree.getName())) {
			return resultGenerater.getFailResult("Tree name existed!");
		}
		*/
		Tree t = treeService.saveTree(tree);
		getSession().setAttribute(Const.LOGIN_SESSION_KEY, t);
		model.addAttribute(Const.LOGIN_SESSION_KEY, t);
		return resultGenerater.getSuccessResult(t);

	}
	
	/*
	 * Tree name and id should be provided.
	 */
	@RequestMapping(value="/viewMyTree", method = RequestMethod.POST)
	@ResponseBody
	public RestResult loginTree(Tree tree, HttpServletResponse response) {
		try {
			if (!StringUtil.isInteger(tree.getFakeId()) )
				return resultGenerater.getFailResult("Cannot find tree name: " + tree.getName() + " with ID:" + tree.getId());
			tree.setId(Long.parseLong(tree.getFakeId()));
			Tree t = treeService.checkLogin(tree);
			if (t != null) {
				Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(t.getId().toString()));
				cookie.setMaxAge(Const.COOKIE_TIMEOUT);
				cookie.setPath("/");
				response.addCookie(cookie);
				getSession().setAttribute(Const.LOGIN_SESSION_KEY, t);
				return resultGenerater.getSuccessResult("/");
			}
			return resultGenerater.getFailResult("Cannot find tree name: " + tree.getName() + " with ID:" + tree.getId());
		}
		catch (Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
	
	
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RestResult handleConstraintViolationException(ConstraintViolationException cve) {
        String errorMessage = cve.getConstraintViolations().iterator().next().getMessage();
        return resultGenerater.getFailResult(errorMessage);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public RestResult handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return resultGenerater.getFailResult("Tree name existed!");
    }
}
