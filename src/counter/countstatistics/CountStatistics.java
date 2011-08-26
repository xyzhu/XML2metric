package counter.countstatistics;

import java.util.LinkedList;
import java.util.List;

import counter.QualifiedName;
import counter.filestatistics.FileStatistics;


public class CountStatistics {

	FileStatistics currentFile;
	/*seekingFunctionname is used to tell if
	 * a function name is going to be collected,
	 * while seekingFunctioncallname is used to tell
	 * if a function call name is going to be collected
	 */
	public boolean seekingFunctionname;
	public boolean seekingFunctioncallname;
	public boolean intype;
	public boolean collectChars;
	public String charbucket;
	public int unitlevel;
	public boolean saveOperator;
	/*
	 * incomment is used to tell if somethind is inside
	 * comment element.
	 * new line is uese to tell if a new starts
	 * comment line is used to decide is a line is comment
	 */
	public boolean incomment;
	public boolean newline;
	public boolean commentline;
	public boolean indecl;
	public boolean inexpr;
	public boolean incall;
	/*inargulist is used to tell if an operator is inside
	 * a call argument list. If so, it can not be an operator
	 * for an assignment.
	 */
	public boolean inargulist;
	public boolean isassign;
	public int numOperator;
	public int numassign;
	/*isCallAssign is used to tell if the right part of 
	 * a assignment contains a function call
	 */
	public boolean isCallAssign;
	/*
	 * "->"is a pointer and '-' should not be taken
	 * as a minus operator in an assignment
	 */
	public boolean ispointer = false;
	/*
	 * isConstAssign is used to tell if the 
	 * right part of an assignment is const.
	 * if the right part contains a macro, 
	 * isConstAssgn is not true. we use other 
	 * variable to count this
	 */
	public boolean isConstAssign = true;
	/*
	 * if there is only white space after "=" and befor
	 * "*", this "*" can not be a multiply operator.
	 * Neither is "-" a munis operator.
	 */
	public boolean containOnlyWhiteSpace = true;

	/*
	 * prestr is used to save the string parsed previously
	 * so to decide is there is a ">"  or "<" before "="
	 */
	public String prestr;
	public List<FileStatistics> fileList;
	/*
	 * functionList is used to save all the
	 * name of the functions defined in the project.
	 * functionCallList is used to save all
	 * the name of the function calls that to 
	 * both local functions and library functions.
	 */
	public List<String> functionList;
	public List<String> functionCallList;

	public CountStatistics() {
		QualifiedName.createCollection();
		unitlevel = 0;
		incomment = false;
		newline = false;
		commentline = true;
		intype = false;
		collectChars = false;
		isassign = false;
		isCallAssign = false;
		ispointer = false;
		fileList = new LinkedList<FileStatistics>();
		seekingFunctionname = false;
		seekingFunctioncallname = false;
		functionList = new LinkedList<String>();
		functionCallList = new LinkedList<String>();
	}

	public void startBlock() {
		currentFile.numBlock++;
	}

	public void startFunctiondecl() {
		currentFile.numFunctionDecl++;
	}

	public void startDeclstmt() {
		currentFile.numDeclstmt++;
	}

	public void startDecl() {
		indecl = true;
		currentFile.numDecl++;

	}

	public void startFunction() {
		currentFile.numFunction++;
		seekingFunctionname = true;
	}

	public void endUnit() {
		unitlevel--;
		if(unitlevel >= 0){
			fileList.add(currentFile);
			clearMacroList();
		}
	}

	public List<FileStatistics> getFileList() {
		return fileList;
	}


	public void increaseTotalLine() {
		currentFile.numTotalLine++;
	}

	public void increaseCommentLine() {
		currentFile.numCommentLine++;
	}

	public void increaseBlankLine() {
		currentFile.numBlankLine++;
	}

	public void increaseAssignment() {
		currentFile.numAssignment++;
	}

	public void startParamList() {
		currentFile.numParamList++;
		if(seekingFunctionname){
			functionList.add(charbucket.trim());
			seekingFunctionname = false;
			collectChars = false;
			charbucket = null;
		}
	}

	public void startArgumentList() {
		currentFile.numArguList++;
		if(seekingFunctioncallname){
			functionCallList.add(charbucket.trim());
			seekingFunctioncallname = false;
			collectChars = false;
			charbucket = null;
		}
		inargulist = true;
	}
	public void startType() {
		intype = true;
	}

	public void startComment() {
		incomment = true;
	}

	public void startName() {
		if(seekingFunctionname||seekingFunctioncallname) {
			collectChars = true;
			charbucket = null;
		}
		seekMacroname();
		seekAssignname();
		seekClassname();
		if(isassign){
			isConstAssign = false;
		}
	}


	public void startCall() {
		currentFile.numCall++;
		seekingFunctioncallname = true;
		if(isassign==true&&isCallAssign==false){
			increaseCallAssignment();
			isCallAssign=true;
		}
		incall = true;
	}

	public void startExprstmt() {
		currentFile.numExprstmt++;
	}

	public void startExpr() {
		/*
		 * if a expr is inside a decl, the assignment can
		 * not end in the end of expr.
		 */
		if(!indecl){
			inexpr = true;
		}
		currentFile.numExpr++;
	}

	public void startContinue() {
		currentFile.numContinue++;
	}

	public void startBreak() {
		currentFile.numBreak++;
	}

	public void startReturn() {
		currentFile.numReturn++;
	}

	public void startFor() {
		currentFile.numFor++;
	}

	public void startIf() {
		currentFile.numIf++;
	}

	public void startElse() {
		currentFile.numElse++;
	}

	public void startWhile() {
		currentFile.numWhile++;
	}

	public void startDo() {
		currentFile.numDo++;
	}

	public void startSwitch() {
		currentFile.numSwitch++;
	}

	public void startCase() {
		currentFile.numCase++;
	}

	public void startParam() {
		currentFile.numParam++;
	}

	public void startArgument() {
		currentFile.numArgu++;
	}

	public void endComment() {
		incomment = false;
		if(commentline){
			increaseCommentLine();
		}
	}

	public void endDecl() {
		if(indecl==true&&isassign==true){
			//				indecl = false;
			if(saveOperator){
				for(int i=0; i<numassign;i++){
					addNumOperator(numOperator);
				}
			}
			if(numOperator==0){
				currentFile.numZeroOpAssign += numassign;
			}
			if(isCallAssign==true&&numOperator==0){
				currentFile.numZeroOpCallAssign += numassign;
			}
			if(numOperator==0){
				if(isConstAssign){
					currentFile.numConstAssign += numassign;
				}
				else{
					setMacroConstAssign();
				}
			}
			isassign = false;
			isCallAssign = false;
			numOperator = 0;
			numassign = 0;
		}
		indecl = false;
	}


	public void endType() {
		intype = false;
	}

	public void endName(){
		addClassname();
		addMacroname();
		checkMacroAssign();
	}
	public void endExpr() {
		if(inexpr==true&&isassign==true){
			//				inexpr = false;

			for(int i=0; i<numassign;i++){
				addNumOperator(numOperator);
			}
			if(numOperator==0){
				currentFile.numZeroOpAssign += numassign;
			}
			if(isCallAssign==true&&numOperator==0){
				currentFile.numZeroOpCallAssign += numassign;
			}
			if(numOperator==0){
				if(isConstAssign){
					currentFile.numConstAssign += numassign;
				}
				else{
					//check if this is a macro assignment
					setMacroConstAssign();
				}
			}
			isassign = false;
			isCallAssign = false;
			numOperator = 0;
			numassign = 0;
		}
		inexpr = false;
	}

	public void endArguList(){
		inargulist = false;
	}

	public void endCall(){
		incall = false;
	}

	public List<String> getFunctionList() {
		return functionList;
	}

	public List<String> getFunctionCallList() {
		return functionCallList;
	}


	public void addNumOperator(int numOperator) {
		currentFile.numOperatorList.add(numOperator);
	}

	public void increaseCallAssignment() {
		currentFile.numCallAssignment++;
	}


	public void characterHandle(char[] text, int start, int length) {
//								System.out.println(new String(text, start, length));
		if (collectChars) {
			String str = new String(text, start, length);
			if (charbucket == null) {
				charbucket = str;
			}
			else {
				charbucket += str;
			}
		}
		if(unitlevel > 0){
			getLine(text, start, length);
			getNumAssignment(text, start, length);
		}
	}
	/**
	 * @param text
	 * @param start
	 * @param length
	 */
	public void getNumAssignment(char[] text, int start, int length) {
		String str = new String(text, start, length);
//						System.out.println(str);
		if(str.contains("=")&&!str.contains("!=")
				&&!str.contains("==")){
			if(prestr==null){
				setAssignment(str);
			}
			else{
				int len = prestr.length();
				if(prestr.charAt(len-1)!='>'&&prestr.charAt(len-1)!='<'){
					setAssignment(str);
				}
			}
		}
		if(isassign==true&&!inargulist){
			numOperator += getNumOperator(str, prestr);
		}
		prestr = str;
	}

	/**
	 * @param str
	 */
	private void setAssignment(String str) {
		if(!includeInString(str)){System.out.println(str);
			if(indecl==true||inexpr==true){
				increaseAssignment();
				isassign = true;
				numassign++;
				isConstAssign = true;
				containOnlyWhiteSpace = true;
			}
		}
	}

	/**
	 * @param text
	 * @param start
	 * @param length
	 */
	public void getLine(char[] text, int start, int length) {
		for (int i = 0; i < length; i++) {
			if (text[start + i] == '\n') {
				increaseTotalLine();
				if(incomment&&commentline){
					increaseCommentLine();
				}
				if(newline){
					increaseBlankLine();
				}
				newline = true;
				commentline = true;
			}
			else if(text[start + i] != ' '&&text[start+i] != '\t'){
				newline = false;
				if(!incomment){
					commentline = false;
				}
			}
		}
	}

	private boolean includeInString(String str) {
		int index = str.indexOf('=');
		int l = str.length();
		int n = 0;
		for(int i=0;i<index;i++){
			if(str.charAt(i)=='\"'){
				n++;
				break;
			}
		}
		for(int j=index+1;j<l;j++)
		{
			if(str.charAt(j)=='\"')
			{
				n++;
				break;
			}
		}
		if(n==2)
			return true;
		else
			return false;
	}
	private int getNumOperator(String str, String prestr) {
		int l = str.length();
		int numOp = 0;
		char c;
		for(int i=0; i<l;i++){
			c = str.charAt(i);
			if(c=='+'||c=='-'||c=='*'||c=='\\'){
				numOp++;
			}
			if(c=='>'&&ispointer ==true){
				numOp--;
			}
			if(c=='-'){
				if(containOnlyWhiteSpace){
					numOp--;
				}
				ispointer = true;
			}
			else
				ispointer = false;
			if(c=='*'&&containOnlyWhiteSpace){
				numOp--;
			}
			if(c!=' '&&c!='='){
				containOnlyWhiteSpace = false;
			}
			if(str.contains("-=")||str.contains("+=")
					||str.contains("*=")||str.contains("/=")){
				numOp++;
			}
		}
		return numOp;
	}


	public void startStruct(){};
	public void startLabel(){};
	public void startGoto(){};
	public void startClass(){};
	public void startConstructordecl(){};
	public void startDestructordecl(){};
	public void startConstructor(){};
	public void startDestructor(){};
	public void startTry(){};
	public void startCatch(){};
	public void startThrow(){};
	public void startMacro(){};
	public void startUnion(){};
	public void startSynchronized(){};
	public void startUnit(String f){};
	public void startCppdefine(){};
	public void seekClassname(){};
	public void addClassname(){};
	public List<String> getClassList(){
		return null;
	}
	public void clearMacroList(){};
	public void seekMacroname(){};
	public void setMacroConstAssign(){};
	public void seekAssignname(){};
	public void addMacroname(){};
	public void checkMacroAssign(){};
	public void addMacroAssign(){};

}
