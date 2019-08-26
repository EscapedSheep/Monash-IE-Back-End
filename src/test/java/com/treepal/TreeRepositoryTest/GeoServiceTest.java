package com.treepal.TreeRepositoryTest;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.treepal.dao.TreeGeoRepository;
import com.treepal.domain.GeoTree;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeoServiceTest {
	
	@Autowired
	private TreeGeoRepository tGR;
	
	@Test
	public void find() {
		List<GeoTree> c = tGR.findBySuburb("Parkville");
		System.out.println(c + "迈达");
	}

}
