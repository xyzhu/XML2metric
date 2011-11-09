package counter.countstatistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import counter.filestatistics.FileStatistics_cpp;

public class CountStatistics_cpp extends CountStatistics{


	public String filename;
	public Boolean seekingClassname = false;
	public boolean seekingTypename = false;
	public boolean seekingObjname = false;
	public List<String> classList;
	public List<String> macroList;
	public Set<String> cplusplusVarType;
	public Map<String, String> classobj;
	public List<String> operandTypeList;
	public String typename;
	public boolean inname;
	public boolean seekingOperandname;
	public int numOperandname;
	public boolean maybeOpOverloadCall;
	public String operandname;
	public boolean isMinusOverload;

	public boolean seekingMacroname = false;
	public boolean seekingAssignname = false;
	public boolean iscout = false;
	public boolean iscoutop = false;
	public boolean ispointer = false;
	/*
	 * containMacroAssign is used to tell if the right part
	 *  of an assignment contains an macro
	 * containsOnlyMacroAssign = is used to tell if the 
	 * right part of an assignment contains only macro and
	 * no other variable.
	 * At first, containOnlyMacroAssign is set to true;
	 * as long as a variable name that is not a macro exist
	 * in an assignment, it is set to false.
	 * containMacroAssign is set to false at first, if a macro
	 * in an assignment exist, it is set to true.
	 * So if an assignment does not contain only macro assign,
	 * it is not a const assignment. But if it contains a macro
	 * and contains only the macro, it is an const assignment.
	 */
	public boolean containMacroAssign = false;
	public boolean containMacroDefinition = false;

	public CountStatistics_cpp(Boolean saveop){
		saveOperator = saveop;
		classList = new LinkedList<String>();
		macroList = new LinkedList<String>();
		classobj = new HashMap<String, String>();
		cplusplusVarType = new HashSet<String>();
		operandTypeList = new LinkedList<String>();
		cplusplusVarType.add("bool");
		cplusplusVarType.add("long int");
		cplusplusVarType.add("int");
		cplusplusVarType.add("char");
		cplusplusVarType.add("wchar_t");
		cplusplusVarType.add("float");
		cplusplusVarType.add("double");
		cplusplusVarType.add("long");
		cplusplusVarType.add("void");
		cplusplusVarType.add("size_t");
		cplusplusVarType.add("short");
	}

	@Override
	public void startUnit(String fileName){
		currentFile = new FileStatistics_cpp();
		currentFile.setFileName(fileName);
		unitlevel++;
		filename = fileName;
		//		System.out.println(filename);
	}

	public void startStruct(){
		currentFile.increaseNumStruct();
	}

	public void startLabel(){
		currentFile.increaseNumLabel();
	}

	public void startClass(){
		currentFile.increaseNumClass();
		seekingClassname = true;
	}

	public void startConstructor(){
		currentFile.increaseNumConstructor();
	}

	public void startDestructor(){
		currentFile.increaseNumDestructor();
	}

	public void startTry(){
		currentFile.increaseNumTry();
	}

	public void startCatch(){
		currentFile.increaseNumCatch();
	}

	public void startThrow(){
		currentFile.increaseNumThrow();
	}

	public void startConstructordecl(){
		currentFile.increaseNumConstructordecl();
	}

	public void startDestructordecl(){
		currentFile.increaseNumDestructordecl();
	}

	public void startUnion(){
		currentFile.increaseNumUnion();
	}
	/*as long as there is a macro definition, we will
	 * start seeking the name of the macro, and set
	 * the containMacroDefinition to true.
	 */

	public void startCppdefine(){
		seekingMacroname = true;
		containMacroDefinition = true;
	}

	public void startName() {
		if(seekingFunctionname||seekingFunctioncallname||
				seekingClassname||seekingMacroname||seekingTypename||seekingObjname||(seekingOperandname&&numOperandname==1)) {
			collectChars = true;
			charbucket = null;
		}
		if(seekingOperandname&&numOperandname==0){
			collectChars = true;
			charbucket = null;
			numOperandname++;
		}
		if(numOperandname>0){
			maybeOpOverloadCall = false;
		}
		if(isassign&&containMacroDefinition){
			seekingAssignname = true;
			containMacroAssign = false;
			collectChars = true;
			charbucket = null;
		}
		if(isassign){
			isConstAssign = false;
		}
	}

	public void startType() {
		intype = true;
		if(indecl){
			seekingTypename = true;
			typename = null;
		}
	}

	public void startExpr() {
		/*
		 * if a expr is inside a decl, the assignment can
		 * not end in the end of expr.
		 */
		if(!indecl){
			innodeclexpr = true;
		}
		currentFile.numExpr++;
		seekingOperandname = true;
		numOperandname = 0;
		operandname = null;
	}

	public void endName(){
		addClassname();
		addMacroname();
		addObjname();
		checkMacroAssign();
		if(seekingOperandname&&numOperandname==1&&charbucket!=null){
			operandname = charbucket.trim();
			if(operandname.equals("cout")||operandname.equals("cerr")
					||operandname.equals("cin")){
				iscout = true;
				iscoutop = false;
			}
			else if(classobj.keySet().contains(operandname)){
				maybeOpOverloadCall = true;
				collectChars = false;
				charbucket = null;
			}
			else{
				maybeOpOverloadCall = false;
			}
			seekingOperandname = false;
		}
		if(intype){
			if(seekingTypename&&charbucket!=null){
				typename = charbucket.trim();
			}
		}
	}

	public void addClassname(){
		if(seekingClassname){
			classList.add(charbucket.trim());
			seekingClassname = false;
			collectChars = false;
		}
	}

	public List<String> getClassList(){
		return classList;
	}

	public List<String> getOperandTypeList(){
		return operandTypeList;
	}

	public void clearMacroList(){
		macroList.clear();
		containMacroDefinition = false;
	}

	public void clearObjList(){
		classobj.clear();
	}

	public void setMacroConstAssign(){
		if(containMacroDefinition&&containMacroAssign){
			currentFile.numConstAssign += numassign;
		}
	}

	/*
	 * If seekingMacroname is true, the name got
	 * is a macor name, we add it to macroList,
	 * and stop seeking macro name.
	 */
	public void addMacroname(){
		if(seekingMacroname){
			macroList.add(charbucket.trim());
			seekingMacroname = false;
			collectChars = false;
			charbucket = null;
		}
	}

	public void addObjname(){
		if(seekingObjname&&charbucket!=null){
			classobj.put(charbucket.trim(), typename);
			seekingObjname = false;
			collectChars = false;
		}
	}

	public void stopSeekingOperator(){
		maybeOpOverloadCall = false;
		iscout = false;
	}
	/*
	 *if seeking assignment name is true, the name got is 
	 *the right part of an assignment, we check if it is
	 *a macro, if it is, this is an assignment with macro.
	 *
	 */
	public void checkMacroAssign(){
		if(seekingAssignname){
			if(!incall&&charbucket!=null&&macroList.contains(charbucket.trim())){
				containMacroAssign = true;
			}
			/*
			 * if this assign name is a macro, then if
			 * this is zero operator, this assignment is 
			 * zero operator const assign. Other wise,
			 * this assignment contains other name, it can 
			 * not be a zero operator const assign
			 * so here, we set seekingAssignname to false because
			 * no matter this name is macro or not, we do not
			 * have to check other name in this assignment
			 */
			seekingAssignname = false;
		}
	}

	public void addMacroAssign(){
		if(containMacroDefinition&&containMacroAssign){
			currentFile.numConstAssign += numassign;
		}
	}

	public void endType(){
		intype = false;
		if(!(typename==null)){
			if(!(typename.equals("const")||typename.equals("unsigned")
					||typename.equals("static"))){
				if(!cplusplusVarType.contains(typename)){
					seekingObjname = true;
					collectChars = false;
					charbucket = null;
				}
			}
		}
		seekingTypename = false;
	}
	public void checkOperatorOverloadCall(String str) {
		int tempNumOpOverloadCall = 0;
		String s = str.trim();
		if(!ispointer&&iscout&&(s.equals("<")||s.equals(">"))){
			if(!iscoutop){
				iscoutop = true;
			}
			else{
				currentFile.increaseNumOpOverloadCall();System.out.println("*****");
				operandTypeList.add("cout");
				iscoutop = false;
			}
		}
		if(s.equals("-")){
			ispointer = true;
		}
		else{
			ispointer = false;
		}
		if(incall&&s.contains("operator")){
			tempNumOpOverloadCall++;
		}
		if(maybeOpOverloadCall){
			if(s.equals("==")||s.equals("!=")||s.equals("+")
					||s.equals("*")||s.equals("/")||s.equals("+=")
					||s.equals("-=")||s.equals("*=")||s.equals("/=")
					||s.equals("++")||s.equals("--")){
				currentFile.increaseNumOpOverloadCall();
				operandTypeList.add(classobj.get(operandname));
				maybeOpOverloadCall = false;
				tempNumOpOverloadCall++;
				numOpOverloadCall++;
				//				System.out.println(operandname+"111111111"+ str);
				if(isassign&&(s.equals("++")||s.equals("--"))){
					tempNumOpOverloadCall--;
					//					System.out.println("2222222222");
				}
				if(s.equals("!=")){
					tempNumOpOverloadCall--;
					numOpOverloadCall--;
				}
				if(s.equals("+=")||s.equals("-=")){
					isassign = false;
					decreaseAssignment();
					numassign = 0;
					numOpOverloadCall = 0;
				}
			}
			else if(isMinusOverload){
				if(s.equals(">")){
					currentFile.decreaseNumOpOverloadCall();
					operandTypeList.remove(classobj.get(operandname));
					tempNumOpOverloadCall--;
					numOpOverloadCall--;
					//					System.out.println("333333333333");
				}
				maybeOpOverloadCall = false;
				isMinusOverload = false;
			}
			else if(s.equals("-")){
				isMinusOverload = true;
				currentFile.increaseNumOpOverloadCall();
				tempNumOpOverloadCall++;
				numOpOverloadCall++;
				//				System.out.println("4444444444");
				operandTypeList.add(classobj.get(operandname));
			}
			else
				maybeOpOverloadCall = false;
		}
		if(isassign==true&&!inargulist&&tempNumOpOverloadCall!=0){
			numOperator -= tempNumOpOverloadCall;
		}
		tempNumOpOverloadCall = 0;
	}

}
