package demo;

import java.io.File;

import com.lsj.sudoku.DFSSudokuSolver;
import com.lsj.sudoku.SimpleSudokuSolver;
import com.lsj.sudoku.SudokuSolver;

public class Main {
	static void TestSimpleSudokuSolver() throws Exception{
		SudokuSolver solver = new SimpleSudokuSolver();		
		solver.SetLayout(new File("layout.json"));			
		solver.Solve();										
		System.out.println(solver.GetFormatLayout());
	}
	
	static void TestDFSSudokuSolver() throws Exception{
		SudokuSolver solver = new DFSSudokuSolver();
		solver.SetLayout(new File("layout.json"));
		
		solver.Solve();										
		System.out.println(solver.GetFormatLayout());
	}
	
	static void TestDFSItera() throws Exception{		
		SudokuSolver solver = new DFSSudokuSolver();
		solver.SetLayout(new File("layout.json"));
		
		solver.Solve();										
		System.out.println(solver.GetFormatLayout());	
		System.out.println("*******************************************************");
		
		solver.Solve();
		System.out.println(solver.GetFormatLayout());
		System.out.println("*******************************************************");
		
		solver.Solve();
		System.out.println(solver.GetFormatLayout());
		System.out.println("*******************************************************");
	}
	
	public static void main(String[] args) throws Exception {
//		TestSimpleSudokuSolver();	//递归式dfs求解
//		TestDFSSudokuSolver();		//迭代式dfs求解
		TestDFSItera();				//迭代器求解
	}
}