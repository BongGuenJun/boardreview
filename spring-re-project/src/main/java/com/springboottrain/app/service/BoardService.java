package com.springboottrain.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboottrain.app.domain.Board;
import com.springboottrain.app.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	@Autowired
	private BoardMapper boardMapper;
	

	public Map<String, Object> boardList(int pageNum, String type, String keyword){
		log.info("boardList(int pageNum, String type, String keyword)");
		
		int currentPage = pageNum;
		
		int startRow = (currentPage-1)*PAGE_SIZE;
		log.info("startRow : " + startRow);
		
		boolean searchOption = (type.equals("null")
				|| keyword.equals("null")) ? false : true;
		
		int listCount = boardMapper.getBoardCount(type, keyword);
		
		List<Board> boardList = boardMapper.boardList(startRow, PAGE_SIZE, type, keyword);
		
		int pageCount =
				listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				-(currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP -1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("bList", boardList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("listCount", listCount);
		modelMap.put("pageGroup", PAGE_GROUP);
		modelMap.put("searchOption", searchOption);
		
		if(searchOption) {
			modelMap.put("type",  type);
			modelMap.put("keyword",  keyword);
		}
		return modelMap;
	}

	public Board getBoard(int no) {
		log.info("BoardService: getBoard(int no)");
		return boardMapper.getBoard(no);
	}
	
	public void addBoard(Board board) {
		log.info("BoardService: addBoard(Board board)");
		boardMapper.insertBoard(board);
	}
	
	public boolean isPassCheck(int no, String pass) {
		log.info("BoardService: isPassCheck(int no, String pass)");
		boolean result = false;
		
		String dbPass = boardMapper.isPassCheck(no);
		if(dbPass.equals(pass)) {
			result = true;
		}
		return result;
	}
	
	public void updateBoard(Board board) {
		log.info("BoardService : updateBoard(Board board)");
		boardMapper.updateBoard(board);
	}
	
	public void deleteBoard(int no) {
		log.info("BoardService: deleteBoard(int no)");
		boardMapper.deleteBoard(no);
	}
	
	public Board getBoard(int no, boolean isCount) {
		log.info("BoardService: getBoard(int no, boolean isCount)");
		if(isCount) {
			boardMapper.incrementReadCount(no);
		}
		return boardMapper.getBoard(no);
	}

}
