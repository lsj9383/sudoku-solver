#数独求解器
提供java的数独求解器接口，[这里](https://github.com/lsj9383/sudoku-solver/blob/master/src/demo/Main.java)是一个使用例子。后期将会开发在线版api。<br>

##一、*快速开始*

###1.*jar包*依赖
`lib`目录下是该项目依赖的jar包，主要用于提供json解析的功能。

###2.*数独布局*
为了方便的描述数独的初始布局信息(初始布局信息即数独题目)，这里采用json格式。如初始布局信息:
```
[
	[0, 8, 9,  0, 0, 0,  5, 7, 0],
	[2, 0, 0,  9, 0, 6,  0, 0, 3],
	[3, 0, 7,  0, 8, 0,  4, 0, 6],
	
	[0, 3, 0,  2, 0, 7,  0, 5, 0],
	[0, 0, 8,  0, 0, 0,  2, 0, 0],
	[0, 6, 0,  8, 0, 5,  0, 4, 0],
	
	[7, 0, 4,  0, 1, 0,  3, 0, 5],
	[8, 0, 0,  3, 0, 4,  0, 0, 7],
	[0, 5, 3,  0, 0, 0,  8, 6, 0]
]
```
代表着如下的数独题目:
<br>![](https://github.com/lsj9383/sudoku-solver/blob/master/icon/layout.png)<br>
很明显，在json布局描述中，用0表示该位置为空。


###3.*最小示例*
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

##二、*求解器*
本项目提供了不同的求解器，不同的求解器有各自的特点。所有的求解器都实现了SudokuSolver接口:
```java
public interface SudokuSolver {
	void SetLayout(int[][] layout);
	void SetLayout(File file) throws Exception;
	void SetLayout(String strJson) throws Exception;
	
	int[][] GetLayout();
	String GetFormatLayout();
	
	void Solve();
	boolean CheckLayout();
	
}
```

###1.*SimpleSudokuSolver*
这是最简单的求解器，不具备遍历数独多种可能答案的能力，仅仅具备将数独初始布局求出其一个结果布局的能力。

###2.*DFSSudokuSolver*
由于数独的结果在某些情况下并不唯一，该求解器可以遍历数独的所有结果布局。一次Solve()方法的调用，就是求解下一个结果布局。

###3.*BFSSudokuSolver*
该求解器也可以遍历数独的所有结果布局。和上一个求解器不同的是，这个求解器的一次Solve()就已经将所有的布局结果都计算出来了，后面的Solve()方法的调用都不会有实际的求解，而是单纯的将结果进行切换。对于结果非常多的数独布局，不建议采用该方案，因为该求解器的首次求解较慢，并且较耗内存。