package com.treepal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.treepal.domain.GameRecord;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {

	@Query("select g from GameRecord g order by g.score desc")
	List<GameRecord> findTop();
}
