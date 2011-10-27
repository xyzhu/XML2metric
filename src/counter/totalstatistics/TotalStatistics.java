package counter.totalstatistics;

import java.util.List;

import counter.filestatistics.*;

public class TotalStatistics {
	public int numFile;
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
	public int numArgument;
	public int numAssignment;
	public int numLocalFunctionCall = 0;
	public int numLibFunctionCall = 0;
	public int numZeroOpAssign = 0;
	public int numZeroOpCallAssign = 0;
	public int numConstAssign = 0;
	public int numParamDecl = 0;
	public int numDeclInFor = 0;
	public int numDeclInStmt = 0;
	public int numDeclStmtWithInit = 0;
	public int numAssignInDeclStmt = 0;
	//for C and C++, numLocalFunctionCall2 is used to get the number of local
	//function calls that have a caller. For Java, numLocalFunctionCall2 is used
	//to get the number of local method calls.
	public int numLocalFunctionCall2 = 0;
	public int numLocalOpOverloadCall = 0;
	public int numLibOpOverloadCall = 0;
	
	public void getTotalStatistics(FileStatistics fs, int numLocalCall1, List<String> functionList, 
			List<String> functionCallList, List<String> classList, List<String> operandTypeList,
			List<String> callerList){
		getSameTotalStatistics(fs);
		getDiffTotalStatistics(fs);
		getOtherTotalStatisticsInfo(numLocalCall1, functionList, 
				functionCallList, classList, operandTypeList, callerList);
	}
	
	public String getTotalStatisticsInfo(){
		String samepart = getSameTotalStatisticsInfo();
		String diffpart = getDiffTotalStatisticsInfo();
		return samepart+diffpart;
	}

	private String getOtherTotalStatisticsInfo(int numLocalCall1, List<String> functionList, 
			List<String> functionCallList, List<String> classList, List<String> operandTypeList,
			List<String> callerList) {
		numLocalFunctionCall2 = getLocalFunctionCallNumber(functionList, functionCallList, classList, callerList);
		numLocalOpOverloadCall = getLocalOpOverloadCall(classList, operandTypeList);
		numLocalFunctionCall = numLocalCall1 + numLocalFunctionCall2;
		numLibOpOverloadCall = getLibOpOverloadCall();;
		numLibFunctionCall = functionCallList.size() - numLocalFunctionCall;
		
		return null;
	}

	public void getSameTotalStatistics(FileStatistics fs){
		numTotalLine += fs.numTotalLine;
		numCommentLine += fs.numCommentLine;
		numBlankLine += fs.numBlankLine;
		numFunctionDecl += fs.numFunctionDecl;
		numFunction += fs.numFunction;
		numDeclstmt += fs.numDeclstmt;
		numDecl += fs.numDecl;
		numBlock += fs.numBlock;
		numExpr += fs.numExpr;
		numExprstmt += fs.numExprstmt;
		numCall += fs.numCall;
		numContinue += fs.numContinue;
		numBreak += fs.numBreak;
		numReturn += fs.numReturn;
		numFor += fs.numFor;
		numIf += fs.numIf;
		numElse += fs.numElse;
		numWhile += fs.numWhile;
		numDo += fs.numDo;
		numSwitch += fs.numSwitch;
		numCase += fs.numCase;
		numParamList += fs.numParamList;
		numParam += fs.numParam;
		numArguList += fs.numArguList;
		numArgument += fs.numArgu;
		numAssignment += fs.numAssignment;
		numZeroOpAssign += fs.numZeroOpAssign;
		numZeroOpCallAssign += fs.numZeroOpCallAssign;
		numConstAssign += fs.numConstAssign;
		numParamDecl += fs.numParamDecl;
		numDeclInFor += fs.numDeclInFor;
		numDeclInStmt += fs.numDeclInStmt;
		numDeclStmtWithInit += fs.numDeclStmtWithInit;
		numAssignInDeclStmt += fs.numAssignInDeclStmt;
	}

	private String getSameTotalStatisticsInfo() {
		StringBuilder total = new StringBuilder();
		total.append("\n");
		total.append("*** Total Statistics Information ***");
		total.append("\n");
		total.append("\n");
		total.append("File: " + numFile);
		total.append("\n");
		total.append("Total line: " + numTotalLine);
		total.append("\n");
		total.append("Comment line: " + numCommentLine);
		total.append("\n");
		total.append("Blank line: " + numBlankLine);
		total.append("\n");
		total.append("Function declaration: " + numFunctionDecl);
		total.append("\n");
		total.append("Function: " + numFunction);
		total.append("\n");
		total.append("Call: " + numCall);
		total.append("\n");
		total.append("Expression statement: " + numExprstmt);
		total.append("\n");
		total.append("Expression: " + numExpr);
		total.append("\n");
		total.append("Block: " + numBlock);
		total.append("\n");
		total.append("Declaration: " + numDecl);
		total.append("\n");
		total.append("Declaration statement: " + numDeclstmt);
		total.append("\n");
		total.append("Continue: " + numContinue);
		total.append("\n");
		total.append("Break: " + numBreak);
		total.append("\n");
		total.append("Return: " + numReturn);
		total.append("\n");
		total.append("For: " + numFor);
		total.append("\n");
		total.append("If: " + numIf);
		total.append("\n");
		total.append("Else: " + numElse);
		total.append("\n");
		total.append("While: " + numWhile);
		total.append("\n");
		total.append("Switch: " + numSwitch);
		total.append("\n");
		total.append("Case: " + numCase);
		total.append("\n");
		total.append("Parameter list: " + numParamList);
		total.append("\n");
		total.append("Argument list: " + numArguList);
		total.append("\n");
		total.append("Parameter: " + numParam);
		total.append("\n");
		total.append("Argument: " + numArgument);
		total.append("\n");
		total.append("Assignment: " + numAssignment);
		total.append("\n");
		total.append("Do: " + numDo);
		total.append("\n");
		total.append("Local function call: " + numLocalFunctionCall);
		total.append("\n");
		total.append("Library function call: " + numLibFunctionCall);
		total.append("\n");
		total.append("Zero operator assignment: " + numZeroOpAssign);
		total.append("\n");
		total.append("Zero operator call assignment: " + numZeroOpCallAssign);
		total.append("\n");
		total.append("Const assignment: " + numConstAssign);
		total.append("\n");
		total.append("Parameter declaration: " + numParamDecl);
		total.append("\n");
		total.append("Declaration in for: " + numDeclInFor);
		total.append("\n");
		total.append("Declaration in declaration statement: " + numDeclInStmt);
		total.append("\n");
		total.append("Declaration statement with initialization: " + numDeclStmtWithInit);
		total.append("\n");
		total.append("Assignment in declaration statement: " + numAssignInDeclStmt);
		total.append("\n");
		return total.toString();
	}

	public String getDiffTotalStatisticsInfo(){return null;};
	public void getDiffTotalStatistics(FileStatistics fs){};
	public int getLocalFunctionCallNumber(List<String> functionList,
			List<String> functionCallList, List<String> classList, 
			List<String> callerList){return 0;}
	public int getLibOpOverloadCall() {return 0;}
	public int getLocalOpOverloadCall(List<String> classList,
			List<String> operandTypeList) {	return 0;}
				
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
}
