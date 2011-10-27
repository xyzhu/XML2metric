package counter.filestatistics;

public class FileStatistics_java extends FileStatistics{

	/*These are the fileds that belong to 
	 * only c++
	 */
	public int numClass;
	public int numConstructor;
	public int numTry;
	public int numCatch;
	public int numThrow;
	
	public FileStatistics_java(){
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
		numClass = 0;
		numConstructor = 0;
		numTry = 0;
		numCatch = 0;
		numThrow = 0;
	}
	
	public void increaseNumClass(){
		numClass++;
	}
	public void increaseNumConstructor(){
		numConstructor++;
	}
	public void increaseNumTry(){
		numTry++;
	}
	public void increaseNumCatch(){
		numCatch++;
	}
	public void increaseNumThrow(){
		numThrow++;
	}
	public int getNumClass(){
		return numClass;
	}
	public int getNumConstructor(){
		return numConstructor;
	}
	public int getNumTry(){
		return numTry;
	}
	public int getNumCatch(){
		return numCatch;
	}
	public int getNumThrow(){
		return numThrow;
	}
	
	@Override
	public String getDiffFileStatisticsInfo() {

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Class: " + numClass);
		sBuilder.append("\n");
		sBuilder.append("Constructor: " + numConstructor);
		sBuilder.append("\n");
		sBuilder.append("Try: " + numTry);
		sBuilder.append("\n");
		sBuilder.append("Catch: " + numCatch);
		sBuilder.append("\n");
		sBuilder.append("Throw: " + numThrow);
		sBuilder.append("\n");
		sBuilder.append("\n");
		return sBuilder.toString();
	}

}