package com.lee.leetcode.pro0026_0050;

/*
 * 
Determine if a Sudoku is valid.

Sudoku Puzzles - The Rules:
	1. Each row must have the numbers 1-9 occuring just once.
	2. Each column must have the numbers 1-9 occuring just once.
	3. And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

	=========================================
	|| 5 | 3 |   ||   | 7 |   ||   |   |   ||
	-----------------------------------------
	|| 6 |   |   || 1 | 9 | 5 ||   |   |   ||
	-----------------------------------------
	||   | 9 | 8 ||   |   |   ||   | 6 |   ||
	=========================================
	|| 8 |   |   ||   | 6 |   ||   |   | 3 ||
	-----------------------------------------
	|| 4 |   |   || 8 |   | 3 ||   |   | 1 ||
	-----------------------------------------
	|| 7 |   |   ||   | 2 |   ||   |   | 6 ||
	=========================================
	||   | 6 |   ||   |   |   || 2 | 8 |   ||
	-----------------------------------------
	||   |   |   || 4 | 1 | 9 ||   |   | 5 ||
	-----------------------------------------
	||   |   |   ||   | 8 |   ||   | 7 | 9 ||
	=========================================

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 * 
 */
public class Pro_0036_validSudoku {

	public static void main(String[] args) {
		char[][] board = {
			{ '5','3','.',  '.','7','.',  '.','.','.' },
			{ '6','.','.',  '1','9','5',  '.','.','.' },
			{ '.','9','8',  '.','.','.',  '.','6','.' },
			
			{ '8','.','.',  '.','6','.',  '.','.','3' },
			{ '4','.','.',  '8','.','3',  '.','.','1' },
			{ '7','.','.',  '.','2','.',  '.','.','6' },
			
			{ '.','6','.',  '.','.','.',  '2','8','.' },
			{ '.','.','.',  '4','1','9',  '.','.','5' },
			{ '.','.','.',  '.','8','.',  '.','7','9' }
		};
		System.out.println(isValidSudoku1(board));
		System.out.println(isValidSudoku(board));
	}
	
	public static boolean isValidSudoku1(char[][] board) {
		Bits[] rowBits = new Bits[9];
		Bits[] columnBits = new Bits[9];
		Bits[] gridBits = new Bits[9];
		init(rowBits, columnBits, gridBits);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				char ch = board[i][j];
				if(ch == '.') { continue; }
				if(isRowDuplicate(i, ch, rowBits)
				|| isColumnDuplicate(j, ch, columnBits)
				|| isGridDuplicate(i, j, ch, gridBits)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void init(Bits[] rowBits, Bits[] columnBits, Bits[] gridBits) {
		for(int i=0; i<9; i++) {
			rowBits[i] = new Bits();
			columnBits[i] = new Bits();
			gridBits[i] = new Bits();
		}
	}
	
	private static boolean isRowDuplicate(int row, char ch, Bits[] rowBits) {
		return checkDuplicateOrFlip(ch, rowBits[row]);
	}
	
	private static boolean isColumnDuplicate(int column, char ch, Bits[] columnBits) {
		return checkDuplicateOrFlip(ch, columnBits[column]);
	}

	private static boolean isGridDuplicate(int row, int column, char ch, Bits[] gridBits) {
		int index = row - (row % 3) + (column / 3);
		return checkDuplicateOrFlip(ch, gridBits[index]);
	}

	public static boolean isValidSudoku(char[][] board) {
		Bits bits = new Bits();
		
        // scan row
		for(int i=0; i<9; i++) {
			bits.reset();
			for(int j=0; j<9; j++) {
				if(checkDuplicateOrFlip(board[i][j], bits)) { return false; }
			}
		}
		
		// scan column
		for(int j=0; j<9; j++) {
			bits.reset();
			for(int i=0; i<9; i++) {
				if(checkDuplicateOrFlip(board[i][j], bits)) { return false; }
			}
		}
		
		// scan grid
		for(int i=0; i<3; i++) {
			int iMin = i*3, iMax = iMin + 3;
			for(int j=0; j<3; j++) {
				int jMin = j*3, jMax = jMin + 3;
				bits.reset();
				for(int k=iMin; k<iMax; k++) {
					for(int l=jMin; l<jMax; l++) {
						if(checkDuplicateOrFlip(board[k][l], bits)) { return false; }
					}
				}
			}
		}
		
		return true;
    }
	
	private static boolean checkDuplicateOrFlip(char ch, Bits bits) {
		switch(ch) {
		case '1': case '2': case '3': case '4':
		case '5': case '6': case '7': case '8': case '9':
			return bits.checkTrueOrFlip(ch - '0');
		case '.':
		default:
			return false;
		}
	}
	
	private static class Bits {
		int bits;
		Bits() { reset(); }
		void reset() { bits = 0; }
		boolean checkTrueOrFlip(int index) {
			int prev = bits;
			bits |= (1 << index);
			return bits == prev;
		}
	}
}
