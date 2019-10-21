package com.treepal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.treepal.domain.GameRecord;
import com.treepal.domain.RestResult;
import com.treepal.service.GameRecordService;
import com.treepal.utils.Const;
import com.treepal.utils.ResultGenerater;

/**
*  Controll the game page
*
* @author  Maida Ge(Mark)
* @version 3.0
* @date   2019-10-07
*/
@Controller
@RequestMapping("/game")
public class GameController extends BaseController {

	private final GameRecordService gameRecordService;
	
	private final ResultGenerater resultGenerater;
	
	
	@Autowired
	public GameController(GameRecordService gameRecordService, ResultGenerater resultGenerater) {
		this.gameRecordService = gameRecordService;
		this.resultGenerater = resultGenerater;
	}
/*
	@ResponseBody
	@RequestMapping(value="/addNew", method = RequestMethod.POST)
	public RestResult addNew(String name, int score) {
		try {
			GameRecord record = new GameRecord(name,score);
			return resultGenerater.getSuccessResult(gameRecordService.addNew(record));
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
	*/
	
	/**
	 * Add new game record to database
	 * @param record the game record receive from frontend
	 * @return json result to frontend
	 */
	@RequestMapping(value="/addNew", method = RequestMethod.POST)
	@ResponseBody
	public RestResult addNew(@RequestBody GameRecord record) {
		try {
			//GameRecord record = new GameRecord(name,score);
			gameRecordService.addNew(record);
			return resultGenerater.getSuccessResult();
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
	
	/**
	 * Retrieve the top10 game record from database
	 * @return view modal
	 */
	@RequestMapping(value= {"#board","/"}, method = RequestMethod.GET)
	public ModelAndView getTop() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(Const.GAME_RECORD,gameRecordService.getTop10());
		mav.setViewName("game#board");
		return mav;
	}
	
}
