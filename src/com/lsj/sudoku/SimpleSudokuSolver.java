package com.lsj.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SimpleSudokuSolver extends AbstractSudokuSolver {
	boolean finish = false;
	private List<Integer> egIndex = new ArrayList<>();
	
	@Override
	public void SetLayout(int[][] layout) {
		super.SetLayout(layout);
		egIndex.clear();
		for(int index=0; index<81; index++){
			if(layout[index/9][index%9] == 0){
				egIndex.add(index);	
			}
		}
	}
	
	@Override
	public void Solve() {
		rec(0);
	}
	
	private void rec(int order){
		if(order>=egIndex.size()){
			finish = true;
			return;
		}
		int row = egIndex.get(order)/9;
		int col = egIndex.get(order)%9;
		for(int number = 1; number <= 9; number++){
			if(CheckPoint(egIndex.get(order), number)){
				layout[row][col] = number;
				rec(order+1);
				if(finish){return;}
				layout[row][col] = 0;
			}
		}
	}
}
