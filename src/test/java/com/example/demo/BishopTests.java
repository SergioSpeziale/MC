package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import chessSet.Pawn;
import chessSet.Piece;
import chessSet.Bishop;
import chessSet.Spot;

@SpringBootTest
public class BishopTests {
	@Test
	void testBishopCannotMoveOutsideBoard() {
		Bishop bishop = this.givenAWhiteBishop();
		Spot start = new Spot(5, 0, bishop);
		Spot end = new Spot(4, -1, null);
		boolean isValid = bishop.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testBishopCannotTakeAlly() {
		Bishop bishop = this.givenAWhiteBishop();
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(5, 13, bishop);
		Spot end = new Spot(6, 12, pawn);
		boolean isValid = bishop.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testIsValidDirection() {
		Bishop bishop = this.givenAWhiteBishop();
		Spot start = new Spot(5, 10, bishop);
		Spot endForward = new Spot(5, 1, null);
		Spot endBackwards = new Spot(5, 12, null);
		Spot endSuperiorDiagonal = new Spot(9, 6, null);
		Spot endInferiorDiagonal = new Spot(2, 13, null);
		Spot endFirstError = new Spot(9, 5, null);
		Spot endSecondError = new Spot(1, 15, null);
		
		boolean isValidEndSuperiorDiagonal = bishop.canMove(start.getX(), start.getY(), endSuperiorDiagonal);
		boolean isValidEndInferiorDiagonal = bishop.canMove(start.getX(), start.getY(), endInferiorDiagonal);
		boolean isValidEndForward = bishop.canMove(start.getX(), start.getY(), endForward);
		boolean isValidEndBackwards = bishop.canMove(start.getX(), start.getY(), endBackwards);		
		boolean isValidEndRandomOne = bishop.canMove(start.getX(), start.getY(), endFirstError);
		boolean isValidEndRandomTwo = bishop.canMove(start.getX(), start.getY(), endSecondError);
		assertTrue(isValidEndSuperiorDiagonal);
		assertTrue(isValidEndInferiorDiagonal);
		assertTrue(!isValidEndForward);
		assertTrue(!isValidEndBackwards);
		assertTrue(!isValidEndRandomOne);
		assertTrue(!isValidEndRandomTwo);
	}
	
	
	private Bishop givenAWhiteBishop() {
		Bishop bishop = new Bishop(Piece.Color.WHITE);
		return bishop;
	}
	
	private Pawn givenAWhitePawn() {
		Pawn pawn = new Pawn(Piece.Color.WHITE);
		return pawn;
	}
}
