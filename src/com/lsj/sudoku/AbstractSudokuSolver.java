package com.lsj.sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONArray;

public abstract class AbstractSudokuSolver implements SudokuSolver {
	int[][] layout = new int[9][9];
	
	@Override 
	public int[][] GetLayout(){
		return layout;
	}
	
	@Override 
	public String GetFormatLayout(){
		StringBuilder strbld = new StringBuilder();
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				strbld.append(layout[i][j]+" ");
				if((j+1)%3 == 0){
					strbld.append("| ");
				}
			}
			strbld.append("\n");
			if((i+1)%3 == 0){
				strbld.append("-----------------------\n");
			}
		}
		return strbld.toString();
	}

	@Override
	public void SetLayout(int[][] layout) {
		this.layout = layout;
	}

	@Override
	public void Solve(int[][] layout) {
		this.layout = layout;
		Solve();
	}

	@Override
	public void SetLayout(InputStream is) throws Exception {
		InputStreamReader isr = new InputStreamReader(is);
		StringBuilder strbldLayout = new StringBuilder();
		char[] buffer = new char[1024];
		int length = 0;
		
		while((length = isr.read(buffer)) != -1){
			strbldLayout.append(new String(buffer, 0, length));
		}		
		SetLayout(strbldLayout.toString());
	}

	@Override
	public void SetLayout(File file) throws Exception {
		SetLayout(new FileInputStream(file));
	}

	@Override
	public void SetLayout(String strLayout) {
		JSONArray array = JSONArray.fromObject(strLayout);
		for(int i=0; i<9; i++){
			JSONArray row = array.getJSONArray(i);
			for(int j=0; j<9; j++){
				int value = row.getInt(j);
				layout[i][j] = value;
			}
		}
	}
	
	protected boolean CheckPoint(int index, int number){
		int row = index/9;
		int col = index%9;
		
		if(number <= 0 || number >= 10 || index < 0 || index > 81){
			return false;
		}
		
		//1).检查行列是否冲突
		for(int i=0; i<9; i++){
			if(layout[row][i] == number || layout[i][col] == number){		//冲突
				return false;
			}
		}
		
		//2).检查块是否冲突
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
	
	protected int NextPointIndex(int currentIndex){
		for(int index = currentIndex+1; index<81; index++){
			if(layout[index/9][index%9] == 0){
				return index;
			}
		}
		return 81;
	}
}
