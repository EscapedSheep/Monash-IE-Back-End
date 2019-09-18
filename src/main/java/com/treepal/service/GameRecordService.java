package com.treepal.service;

import java.util.List;

import com.treepal.domain.GameRecord;

public interface GameRecordService {

	public List<GameRecord> getTop10();
	
	public List<GameRecord> addNew(GameRecord record);
}
