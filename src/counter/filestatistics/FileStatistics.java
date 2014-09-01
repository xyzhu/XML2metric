package counter.filestatistics;

import java.util.LinkedList;
import java.util.List;

public class FileStatistics {

	/*These are the fields that belong to all
	 * the three languages--c, c++,java
	 */
	public String fileName;
	public int numTotalLine;
	public int numCommentLine;
	public int numBlankLine;
	public int numFunction;
	public int numFunctionDecl;
	public int numBlock;
	public int numDeclstmt;
	public int numDecl;
	public int numExprstmt;
	public int numExpr;
	public int numIf;
	public int numElse;
	public int numWhile;
	public int numFor;
	public int numContinue;
	public int numBreak;
	public int numDo;
	public int numSwitch;
	public int numCase;
	public int numReturn;
	public int numCall;
	public int numParamList;
	public int numParam;
	public int numArguList;
	public int numArgu;
	public int numAssignment;
	
	/*numOperatorList is used to save the number 
	 * of operators in each expression
	 */
	
	public List<Integer> numOperatorList;
	
	/* number of assignment with right part 
	 * having at least one function call
	 */
	public int numCallAssignment;
	/*number of assignment with zero operator and
	 * the right part being a function call
	 */
	public int numZeroOpAssign;
	public int numZeroOpCallAssign;
	public int numConstAssign;
	
	public int numParamDecl;
	public int numDeclInFor;
	public int numDeclInStmt;
	public int numDeclStmtWithInit;
	public int numAssignInDeclStmt;
	
	public FileStatistics(){
		numOperatorList = new LinkedList<Integer>();
	}

	public void setFileName(String name) {
		fileName = name;
	}
	
	public String getFileStatisticsInfo(){
		return getSameFileStatisticsInfo() + getDiffFileStatisticsInfo();
		
	}
	
	public String getSameFileStatisticsInfo() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("*****  File Statistics  *****");
		sBuilder.append("\n");
		sBuilder.append("\n");
		sBuilder.append("File name: [[[ " + fileName + " ]]]");
		sBuilder.append("\n");
		sBuilder.append("\n");
		sBuilder.append("Total line: " + numTotalLine);
		sBuilder.append("\n");
		sBuilder.append("Comment line: " + numCommentLine);
		sBuilder.append("\n");
		sBuilder.append("Blank line: " + numBlankLine);
		sBuilder.append("\n");
		sBuilder.append("Function declaration: " + numFunctionDecl);
		sBuilder.append("\n");
		sBuilder.append("Function: " + numFunction);
		sBuilder.append("\n");
		sBuilder.append("Declaration statement: " + numDeclstmt);
		sBuilder.append("\n");
		sBuilder.append("Declaration:" + numDecl);
		sBuilder.append("\n");
		sBuilder.append("Blocks: " + numBlock);
		sBuilder.append("\n");
		sBuilder.append("Expressions: " + numExpr);
		sBuilder.append("\n");
		sBuilder.append("Expression statements: " + numExprstmt);
		sBuilder.append("\n");
		sBuilder.append("Call: " + numCall);
		sBuilder.append("\n");
		sBuilder.append("Continue: " + numContinue);
		sBuilder.append("\n");
		sBuilder.append("Break: " + numBreak);
		sBuilder.append("\n");
		sBuilder.append("Return: " +numReturn);
		sBuilder.append("\n");
		sBuilder.append("For: " + numFor);
		sBuilder.append("\n");
		sBuilder.append("If: " + numIf);
		sBuilder.append("\n");
		sBuilder.append("Else: " + numElse);
		sBuilder.append("\n");
		sBuilder.append("While: " + numWhile);
		sBuilder.append("\n");
		sBuilder.append("Do: " + numDo);
		sBuilder.append("\n");
		sBuilder.append("Switch: " + numSwitch);
		sBuilder.append("\n");
		sBuilder.append("Case: " + numCase);
		sBuilder.append("\n");
		sBuilder.append("Parameter list: " + numParamList);
		sBuilder.append("\n");
		sBuilder.append("Param: " + numParam);
		sBuilder.append("\n");
		sBuilder.append("Argument list: " + numArguList);
		sBuilder.append("\n");
		sBuilder.append("Argument: " + numArgu);
		sBuilder.append("\n");
		sBuilder.append("Assignment: " + numAssignment);
		sBuilder.append("\n");
		sBuilder.append("Zero operator assignment: " + numZeroOpAssign);
		sBuilder.append("\n");
		sBuilder.append("Zero operator call assignment: " + numZeroOpCallAssign);
		sBuilder.append("\n");
		sBuilder.append("Const assignment: " + numConstAssign);
		sBuilder.append("\n");
		sBuilder.append("Parameter declaration: " + numParamDecl);
		sBuilder.append("\n");
		sBuilder.append("Declaration in for: " + numDeclInFor);
		sBuilder.append("\n");
		sBuilder.append("Declaration in declaration statement: " + numDeclInStmt);
		sBuilder.append("\n");
		sBuilder.append("Declaration statement with initialization: " + numDeclStmtWithInit);
		sBuilder.append("\n");
		sBuilder.append("Assignment in declaration statement" + numAssignInDeclStmt);
		sBuilder.append("\n");
		return sBuilder.toString();

	}

	
	public void increaseNumStruct(){};
	public void increaseNumLabel(){};
	public void increaseNumGoto(){};
	public void increaseNumClass(){};
	public void increaseNumConstructordecl(){};
	public void increaseNumDestructordecl(){};
	public void increaseNumConstructor(){};
	public void increaseNumDestructor(){};
	public void increaseNumTry(){};
	public void increaseNumCatch(){};
	public void increaseNumThrow(){};
	public void increaseNumMacro(){};
	public void increaseNumUnion(){};
	public void increaseNumDirective(){};
	public void increaseNumSynchronized(){};
	public void increaseNumOpOverloadCall(){};//temp
	public int getNumLabel(){return 0;}
	public int getNumClass(){return 0;};
	public int getNumConstructor(){return 0;}
	public int getNumTry(){return 0;}
	public int getNumCatch(){return 0;}
	public int getNumThrow(){return 0;}
	public int getNumSynchronized(){return 0;}
	public int getNumStruct(){return 0;}
	public int getNumGoto(){return 0;}
	public int getNumClassdecl(){return 0;}
	public int getNumConstructordecl(){return 0;}
	public int getNumDestructordecl(){return 0;}
	public int getNumDestructor(){return 0;}
	public int getUnion(){return 0;}
	public int getNumOpOverloadCall(){return 0;}
	public void decreaseNumOpOverloadCall(){};
	public String getDiffFileStatisticsInfo() {return null;}


}