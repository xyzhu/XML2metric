package counter.filestatistics;

public class FileStatistics_c extends FileStatistics{
	
	/*These are the fileds that belong to
	 * only c 
	 */
	public int numStruct;
	public int numLabel;
	public int numGoto;
	
	public FileStatistics_c(){
		numTotalLine = 0;
		numCommentLine = 0;
		numBlankLine = 0;
		numFunction = 0;
		numFunctionDecl = 0;
		numBlock = 0;
		numDeclstmt = 0;
		numDecl = 0;
		numExprstmt = 0;
		numExpr = 0;
		numIf = 0;
		numElse = 0;
		numWhile = 0;
		numFor = 0;
		numContinue = 0;
		numBreak = 0;
		numDo = 0;
		numSwitch = 0;
		numCase = 0;
		numReturn = 0;
		numCall = 0;
		numParamList = 0;
		numParam = 0;
		numArguList = 0;
		numArgu = 0;
		numAssignment = 0;
		numCallAssignment = 0;
		numStruct = 0;
		numLabel = 0;
		numGoto = 0;
	}
	
	public void increaseNumStruct(){
		numStruct++;
	}
	public void increaseNumGoto(){
		numGoto++;
	}
	public void increaseNumLabel(){
		numLabel++;
	}
	public int getNumStruct(){
		return numStruct;
	}
	public int getNumGoto(){
		return numGoto;
	}
	public int getNumLabel(){
		return numLabel;
	}

}