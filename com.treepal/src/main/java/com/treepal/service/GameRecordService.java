package com.treepal.service;

import java.util.List;

import com.treepal.domain.GameRecord;

/**
*  Game page service
*
* @author  Maida Ge(Mark)
* @version 3.0
* @date   2019-10-07
*/
public interface GameRecordService {

	public List<GameRecord> getTop10();
	
	public List<GameRecord> addNew(GameRecord record);
}
