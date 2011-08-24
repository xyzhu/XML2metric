package counter.filestatistics;

public class FileStatistics_cpp extends FileStatistics{

	/*These are the fileds that belong to 
	 * only c++
	 */
	public int numStruct;
	public int numLabel;
	public int numClass;
	public int numConstructor;
	public int numTry;
	public int numCatch;
	public int numThrow;
	public int numConstructordecl;
	public int numDestructordecl;
	public int numDestructor;
	public int numUnion;
	
	public FileStatistics_cpp(){
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
		numClass = 0;
		numConstructor = 0;
		numTry = 0;
		numCatch = 0;
		numThrow = 0;
		numConstructordecl = 0;
		numDestructordecl = 0;
		numDestructor = 0;
		numUnion = 0;
	}
	
	public void increaseNumStruct(){
		numStruct++;
	}
	public void increaseNumClass(){
		numClass++;
	}
	public void increaseNumConstructordecl(){
		numConstructordecl++;
	}
	public void increaseNumDestructordecl(){
		numDestructordecl++;
	}
	public void increaseNumConstructor(){
		numConstructor++;
	}
	public void increaseNumDestructor(){
		numDestructor++;
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
	public void increaseNumUnion(){
		numUnion++;
	}
	public int getNumStruct(){
		return numStruct;
	}
	public int getNumLabel(){
		return numLabel;
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
	public int getNumConstructordecl(){
		return numConstructordecl;
	}
	public int getNumDestructordecl(){
		return numDestructordecl;
	}
	public int getNumDestructor(){
		return numDestructor;
	}
	public int getUnion(){
		return numUnion;
	}
}