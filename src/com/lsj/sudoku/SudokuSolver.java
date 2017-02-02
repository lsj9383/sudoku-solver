package com.lsj.sudoku;

import java.io.File;

public interface SudokuSolver {
	void SetLayout(int[][] layout);
	void SetLayout(File file) throws Exception;
	void SetLayout(String strJson) throws Exception;
	
	int[][] GetLayout();
	String GetFormatLayout();
	
	void Solve();
	boolean CheckLayout();
	
}
