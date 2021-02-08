package com.koreait.sboard.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.sboard.model.BoardCmtDomain;
import com.koreait.sboard.model.BoardCmtEntity;
import com.koreait.sboard.model.BoardDTO;
import com.koreait.sboard.model.BoardDomain;
import com.koreait.sboard.model.BoardEntity;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity p);
	int selMaxPageNum(BoardDTO p);
	List<BoardDomain> selBoardList(BoardDTO p);
	BoardDomain selBoard(BoardDTO p);
	int updBoard(BoardEntity p);
	int updBoardHits(BoardDTO p);
	int delBoard(BoardDTO p);
	
	
	//------------------------- CMT --------------------//
	int insCmt(BoardCmtEntity p);
	List<BoardCmtDomain> selCmtList(BoardCmtEntity p);
	int updCmt(BoardCmtEntity p);
	int delCmt(BoardCmtEntity p);
	
}

