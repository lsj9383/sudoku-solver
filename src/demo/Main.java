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
	
	public static void main(String[] args) throws Exception {
//		TestSimpleSudokuSolver();
		TestDFSSudokuSolver();
	}
}