package com.lsj.sudoku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSudokuSolver extends AbstractSudokuSolver{
	private Queue<QueueItem> queue = new LinkedList<>();
	private List<Integer> egIndex = new ArrayList<>();
	private List<int[]> results = new ArrayList<>();
	
	@Override
	public void SetLayout(int[][] layout) {
		super.SetLayout(layout);
		results.clear();
		queue.clear();
		egIndex.clear();
		for(int index=0; index<81; index++){
			if(layout[index/9][index%9] == 0){
				egIndex.add(index);	
			}
		}
	}
	
	@Override
	public void Solve() {
		if(queue.isEmpty()){
			for(int number=1; number<=9; number++){
				queue.add(new QueueItem(0, number, egIndex.size()));
			}
		}
		
		while(!queue.isEmpty()){
			QueueItem item = queue.poll();
			
			if(item.order >= egIndex.size()){
				results.add(item.result);
				continue;
			}
			
			if(CheckPoint(egIndex.get(item.order), item.number, item.result)){
				int[] result = new int[item.result.length];
				for(int i=0; i<result.length; i++){
					result[i] = item.result[i];
				}
				result[item.order] = item.number;
				for(int number=1; number<=9; number++){
					queue.add(new QueueItem(item.order+1, number, result));	
				}
			}
		}
		
		for(int order = 0; order < egIndex.size(); order++){
			int ind = egIndex.get(order);
			layout[ind/9][ind%9] = results.get(0)[order];
		}
	}

	private boolean CheckPoint(int index, int number, int[] result) {
		int[][] layoutBuffer = new int[9][9];
		for(int row=0; row<9; row++){
			for(int col=0; col<9; col++){
				layoutBuffer[row][col] = layout[row][col];
			}
		}
		for(int order = 0; order < egIndex.size(); order++){
			int ind = egIndex.get(order);
			layoutBuffer[ind/9][ind%9] = result[order];
		}
		
		//1).行列冲突检查
		for(int i=0; i<9; i++){
			if(layoutBuffer[index/9][i] == number || layoutBuffer[i][index%9] == number){
				return false;
			}
		}
		
		//2).块冲突检查
		int row = index/9;
		int col = index%9;
		int blk_base_row = (row/3) * 3;
		int blk_base_col = (col/3) * 3;
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(layout[blk_base_row+i][blk_base_col+j] == number){		//冲突
					return false;
				}
			}
		}
		return true;
	}
	
	class QueueItem{
		int order;
		int number;
		int[] result;
		
		public QueueItem(int order, int number, int egIndexSize){
			this.order = order;
			this.number = number;
			this.result = new int[egIndexSize];
			for(int i=0; i<result.length; i++){
				result[i] = 0;
			}
		}
		
		public QueueItem(int order, int number, int[] result){
			this.order = order;
			this.number = number;
			this.result = result;
		}
		
		@Override
		public String toString() {
			String res = new String();
			res += "order : "+order+"\n";
			res += "number: "+number+"\n";
			res += "result: ";
			for(int i=0; i<result.length; i++){
				res+=result[i]+",";
			}
			return res+"\n";
		}
	}
}
