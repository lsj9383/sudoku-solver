package demo;

import java.io.File;

import com.lsj.sudoku.SimpleSudokuSolver;
import com.lsj.sudoku.SudokuSolver;

public class Main {

	public static void main(String[] args) throws Exception {
		SudokuSolver solver = new SimpleSudokuSolver();		//这是一个最简单的速度求解器，请原谅，也是效率最低的
		solver.SetLayout(new File("layout.json"));			//加载json文件，json文件中是数独布局描述信息
		solver.Solve();										//求解
		System.out.println(solver.GetFormatLayout());		//获得输出的格式化信息
	}

}
