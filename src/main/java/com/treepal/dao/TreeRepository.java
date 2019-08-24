package com.treepal.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.treepal.domain.Tree;

public interface TreeRepository extends JpaRepository<Tree, Long> {
	
	Tree findByName(String name);
	
	Tree findByIdAndName(Long id, String name);

}
