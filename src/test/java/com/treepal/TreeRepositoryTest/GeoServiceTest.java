package com.treepal.TreeRepositoryTest;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.treepal.domain.GameRecord;
import com.treepal.service.GameRecordService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeoServiceTest {
	
	@Autowired
	private GameRecordService grs;
	
	@Test
	public void find() {
		GameRecord gm = new GameRecord("ff",10);
		List<GameRecord> re = grs.addNew(gm);
		for (GameRecord r : re) {
			System.out.print(r.getName() + "这里");
		}
	}

}
