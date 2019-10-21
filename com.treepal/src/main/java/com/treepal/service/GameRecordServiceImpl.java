package com.treepal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treepal.dao.GameRecordRepository;
import com.treepal.domain.GameRecord;

/**
*  Game page service implementation
*
* @author  Maida Ge(Mark)
* @version 3.0
* @date   2019-10-07
*/
@Service("GameRecordService")
public class GameRecordServiceImpl implements GameRecordService {

	@Autowired
	private GameRecordRepository gameRecordRepository;
	
	/**
	 * Get the Top10 record from database
	 */
	@Override
	public List<GameRecord> getTop10() {
		List<GameRecord> list = gameRecordRepository.findTop();
		if (list.size() <= 10)
			return list;
		List<GameRecord> result = new ArrayList<GameRecord>();
		for (int i = 0; i < 10; i++) {
			result.add(list.get(i));
		}
		return result;
	}

	/**
	 * Add new record to database
	 */
	@Override
	public List<GameRecord> addNew(GameRecord record) {
		gameRecordRepository.save(record);
		return getTop10();
	}

}
