package com.lsj.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSSudokuSolver extends AbstractSudokuSolver {
	private Stack<Integer[]> stack = new Stack<>();
	private List<Integer> egIndex = new ArrayList<>();
	
	@Override
	public void SetLayout(int[][] layout) {
		super.SetLayout(layout);
		stack.clear();
		egIndex.clear();
		for(int index=0; index<81; index++){
			if(layout[index/9][index%9] == 0){
				egIndex.add(index);	
			}
		}
	}
	
	@Override
	public void Solve() {
		if(stack.isEmpty()){
			for(int number=1; number<=9; number++){
				stack.push(new Integer[]{0, number});	
			}
		}else{
			Integer[] info = stack.pop();
			while(info[0] >= egIndex.size()){
				info = stack.pop();
			}
			stack.push(info);
		}
	
		while(!stack.isEmpty()){
			Integer[] info = stack.pop();
			if(info[0] >= egIndex.size()){
				break;
			}
			int currentIndex = egIndex.get(info[0]);
			
			int row = currentIndex/9;
			int col = currentIndex%9;
			CleanFrom(egIndex, info[0]);
			
			if(CheckPoint(currentIndex, info[1])){
				layout[row][col] = info[1];
				for(int number=1; number<=9; number++){
					stack.push(new Integer[]{info[0]+1, number});	
				}
			}
		}
	}
	
	private void CleanFrom(List<Integer> indexMap, int cnt){
		for(int index : indexMap){
			if(index >= indexMap.get(cnt) && layout[index/9][index%9] != 0){
				layout[index/9][index%9] = 0;
			}
		}
	}
}
