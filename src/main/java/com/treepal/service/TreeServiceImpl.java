/*
package com.treepal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treepal.dao.TreeRepository;
import com.treepal.domain.Tree;

@Service("TreeService")
public class TreeServiceImpl implements TreeService {
	
	@Autowired
	private TreeRepository treeRepository;

	@Override
	public Tree getNewTree(String name) {
		return treeRepository.save(new Tree(name));
		
	}

	@Override
	public Tree growTree(Long id, int point) {
		Optional<Tree> t = treeRepository.findById(id);
		Tree theTree = new Tree();
		if (t.isPresent())
			theTree = t.get();
		theTree.setAge(theTree.getAge() + point);
		return treeRepository.save(theTree);
	}

	@Override
	public boolean checkExist(String name) {
		return treeRepository.findByName(name) == null ? false:true;
	}

	@Override
	public Tree saveTree(Tree tree) {
		return treeRepository.save(tree);
	}

	@Override
	public Tree checkLogin(Tree tree) {
		return treeRepository.findByIdAndName(tree.getId(), tree.getName());
	}

	@Override
	public void resetTodayGrowth() {

	}
	
	

}
*/
