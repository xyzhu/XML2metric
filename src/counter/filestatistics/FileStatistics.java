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
	
	public FileStatistics(){
		numOperatorList = new LinkedList<Integer>();
	}

	public void setFileName(String name) {
		fileName = name;
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
	public void increaseNumSynchronized(){};
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

}