#数独求解器
提供java的数独求解器接口，[这里](https://github.com/lsj9383/sudoku-solver/blob/master/src/demo/Main.java)是一个使用例子。后期将会开发在线版api。<br>

##一、*快速开始*

###1.*jar包*依赖
`lib`目录下是该项目依赖的jar包，主要用于提供json解析的功能。

###2.*最小示例*
```java
public static void main(String[] args) throws Exception {
	String sudoLayout = "["
		+"[0, 0, 0,  0, 4, 5,  8, 0, 7],"
		+"[0, 0, 0,  0, 7, 0,  0, 0, 0],"
		+"[0, 0, 0,  8, 0, 0,  4, 1, 0],"
		
		+"[0, 0, 0,  0, 2, 0,  0, 0, 0],"
		+"[0, 0, 0,  0, 3, 0,  1, 9, 5],"
		+"[8, 0, 0,  0, 1, 0,  0, 0, 3],"
		
		+"[0, 2, 0,  3, 0, 0,  0, 7, 0],"
		+"[0, 0, 3,  7, 0, 0,  5, 0, 8],"
		+"[0, 5, 4,  2, 0, 0,  0, 0, 0]]";
		
	SudokuSolver solver = new SimpleSudokuSolver();		//这是一个最简单的速度求解器，请原谅，也是效率最低的。
	solver.SetLayout(sudoLayout);						//设置数独布局
	solver.Solve();										//求解
	System.out.println(solver.GetFormatLayout());		//显示数独布局的格式化信息
}

```