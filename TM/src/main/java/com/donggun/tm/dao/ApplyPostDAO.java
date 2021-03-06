package com.donggun.tm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.donggun.tm.dto.ApplyPost;

@Mapper
@Repository
public interface ApplyPostDAO {
	public List<ApplyPost> searchApplyPostList(int post_no) throws SQLException;
	public int searchIsApplied(Map param) throws SQLException;
	
	public void insertApplyPost(ApplyPost applyPost) throws SQLException;
	public void updateApplyPost(ApplyPost applyPost) throws SQLException;
	public void deleteApplyPost(Map param) throws SQLException;
}
