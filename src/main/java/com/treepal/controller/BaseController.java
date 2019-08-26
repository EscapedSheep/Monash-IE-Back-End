package com.treepal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.treepal.domain.Tree;
import com.treepal.utils.Const;
import com.treepal.utils.Des3EncryptionUtil;


@Controller
public class BaseController {
	
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    protected HttpSession getSession() {
        return getRequest().getSession();
    }
    
    protected Tree getTree() {
    	return (Tree)getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }
    
    protected String cookieSign(String value){
        value = value + Const.PASSWORD_KEY;
        String sign = Des3EncryptionUtil.encode(Const.DES3_KEY,value);
        return sign;
    }
}
