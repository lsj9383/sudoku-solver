package com.lsj.sudoku;

import java.io.File;
import java.io.InputStream;

public interface SudokuSolver {
	void SetLayout(int[][] layout);
	void SetLayout(InputStream is) throws Exception;
	void SetLayout(File file) throws Exception;
	void SetLayout(String strJson) throws Exception;
	int[][] GetLayout();
	
	
	void Solve();
	void Solve(int[][] layout);
	
}
