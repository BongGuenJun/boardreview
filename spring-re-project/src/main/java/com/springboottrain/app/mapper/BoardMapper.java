package com.springboottrain.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboottrain.app.domain.Board;

@Mapper
public interface BoardMapper {
	public List<Board> boardList(
			@Param("startRow") int startRow, @Param("num") int num,
			@Param("type") String type, @Param("keyword") String keyword);

	public int getBoardCount(
			@Param("type")String type, @Param("keyword") String keyword);
	
	public int getBoardCount();
	
	public Board getBoard(int no);
	
	public void insertBoard(Board board);
	
	public String isPassCheck(int no);
	
	public void updateBoard(Board board);
	
	public void deleteBoard(int no);
	
	public void incrementReadCount(int no);
	

}
