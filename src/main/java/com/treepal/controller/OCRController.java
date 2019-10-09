package com.treepal.controller;

import com.baidu.aip.ocr.AipOcr;
import com.treepal.domain.RestResult;
import com.treepal.utils.JSONChange;
import com.treepal.utils.ResultGenerater;
import com.treepal.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
 
/**
*  Controll words recognition
*
* @author  Maida Ge(Mark)
* @version 3.0
* @date   2019-10-07
*/
@RestController
public class OCRController {
	
	@Autowired
	private ResultGenerater resultGenerater;
 /*
    @RequestMapping(value = "/ocr",method = RequestMethod.POST)
    public Map ocr(MultipartFile file) throws Exception{
        byte[] buf = file.getBytes();
        AipOcr client = new AipOcr("17317048","2296rk3tQLEXiI63BeHuvdoX","eqHcbmA6DgEqYjtSFoXxqQbKwbfxL6Rn");
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        JSONObject res = client.basicGeneral(buf, options);
        Map map = JSONChange.json2map(res.toString());
        return map;
    }
    */
	/*
	@RequestMapping(value = "/ocr",method = RequestMethod.POST)
    public RestResult ocr(MultipartFile file) throws Exception{
        byte[] buf = file.getBytes();
        AipOcr client = new AipOcr("17317048","2296rk3tQLEXiI63BeHuvdoX","eqHcbmA6DgEqYjtSFoXxqQbKwbfxL6Rn");
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        JSONObject res = client.basicGeneral(buf, options);
        Map<String,Object> map = JSONChange.json2map(res.toString());
        return resultGenerater.getSuccessResult(map);
    }
    */
	
	/**
	 * Compare the submitted image's words with species name, find the similarity and return result
	 * @param request request contains the image and species name
	 * @return json resultt
	 */
	@RequestMapping(value = "/ocr", method = RequestMethod.POST)     
    public RestResult handleFileUpload(HttpServletRequest request) {  
		try {
			MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;  
	        List<MultipartFile> files = ((MultipartHttpServletRequest) request)    
	                .getFiles("file");   
	        String s1 = params.getParameter("name");   
	        MultipartFile file = files.get(0);
	        byte[] buf = file.getBytes();
	        AipOcr client = new AipOcr("17317048","2296rk3tQLEXiI63BeHuvdoX","eqHcbmA6DgEqYjtSFoXxqQbKwbfxL6Rn");
	        HashMap<String, String> options = new HashMap<String, String>();
	        options.put("language_type", "CHN_ENG");
	        JSONObject res = client.basicGeneral(buf, options);
	        JSONArray ja = res.getJSONArray("words_result");
	        StringBuffer sb = new StringBuffer();
	        for (Object jo:ja) {
	        	JSONObject tmp = (JSONObject) jo;
	        	sb.append(tmp.getString("words"));
	        }
	        if (sb.length() == 0) {
	        	return resultGenerater.getNoImageResult();
	        }
	        double simi = StringUtil.SimilarDegree(s1, sb.toString());
	        if (simi >= 0.35)
	        	return resultGenerater.getSuccessResult();
	        return resultGenerater.getFailResult("Cannot rec");
		}catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
    
}