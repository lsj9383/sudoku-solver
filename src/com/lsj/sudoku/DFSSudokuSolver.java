package com.lsj.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSSudokuSolver extends AbstractSudokuSolver {
	private Stack<Integer[]> stack = new Stack<>();
	private List<Integer> indexMap = new ArrayList<>();
	
	@Override
	public void SetLayout(int[][] layout) {
		super.SetLayout(layout);
		stack.clear();
		indexMap.clear();
		for(int index=-1; index!=81; ){
			index=NextPointIndex(index);
			indexMap.add(index);
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
			while(indexMap.get(info[0]) == 81){
				info = stack.pop();
			}
			stack.push(info);
		}
	
		while(!stack.isEmpty()){
			Integer[] info = stack.pop();
			int currentIndex = indexMap.get(info[0]);
			if(currentIndex == 81){
				break;
			}
			
			int row = currentIndex/9;
			int col = currentIndex%9;
			CleanFrom(indexMap, info[0]);
			
			if(CheckPoint(currentIndex, info[1])){
				layout[row][col] = info[1];
				for(int number=1; number<=9; number++){
					stack.push(new Integer[]{info[0]+1, number});	
				}
			}
		}
	}
	
	private void CleanFrom(List<Integer> indexMap, int cnt){
		for(int index = indexMap.get(cnt); index!=81; index=indexMap.get(++cnt)){
			if(layout[index/9][index%9] != 0){
				layout[index/9][index%9] = 0;
			}
		}
	}
}
