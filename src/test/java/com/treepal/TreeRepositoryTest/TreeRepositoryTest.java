package com.treepal.TreeRepositoryTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.treepal.Application;
import com.treepal.dao.TreeRepository;
import com.treepal.domain.Tree;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)

@WebAppConfiguration
public class TreeRepositoryTest {

	@Autowired
	private TreeRepository treeRepository;
	
	@Test
	public void test() throws Exception {
		
		String n1 = "tree55";
		Tree t = treeRepository.saveAndFlush(new Tree(n1));
		Assert.assertEquals(4L,t.getId());
		treeRepository.save(new Tree("hahhah"))
		
	}
}
