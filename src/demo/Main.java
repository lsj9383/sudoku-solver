package demo;

import java.io.File;

import com.lsj.sudoku.SimpleSudokuSolver;
import com.lsj.sudoku.SudokuSolver;

public class Main {

	public static void main(String[] args) throws Exception {
		SudokuSolver solver = new SimpleSudokuSolver();
		solver.SetLayout(new File("layout.json"));
		solver.Solve();
		
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				System.out.print(solver.GetLayout()[i][j]+" ");
				if((j+1)%3 == 0){
					System.out.print("| ");
				}
			}
			System.out.println();
			if((i+1)%3 == 0){
				System.out.println("-----------------------");
			}
		}
	}

}
