package com.treepal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treepal.dao.GameRecordRepository;
import com.treepal.domain.GameRecord;

@Service("GameRecordService")
public class GameRecordServiceImpl implements GameRecordService {

	@Autowired
	private GameRecordRepository gameRecordRepository;
	
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

	@Override
	public List<GameRecord> addNew(GameRecord record) {
		gameRecordRepository.save(record);
		return getTop10();
	}

}
