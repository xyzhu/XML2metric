package counter.countstatistics;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import counter.filestatistics.FileStatistics_cpp;

public class CountStatistics_cpp extends CountStatistics{


	//	String functionName;
	public Boolean seekingClassname = false;
	public boolean seekingTypename = false;
	public boolean seekingObjname = false;
	public List<String> classList;
	public List<String> macroList;
	public Set<String> cplusplusVarType;
	public List<String> objList;
	public String typename;
	public boolean inname;
	public boolean seekingOperandname;
	public int numOperandname;
	public boolean maybeOpOverloadCall;
	public boolean isOpOverloadCall;

	public boolean seekingMacroname = false;
	public boolean seekingAssignname = false;
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
		objList = new LinkedList<String>();
		cplusplusVarType = new HashSet<String>();
		cplusplusVarType.add("bool");
		cplusplusVarType.add("unsigned short int");
		cplusplusVarType.add("short int");
		cplusplusVarType.add("unsigned long int");
		cplusplusVarType.add("long int");
		cplusplusVarType.add("int");
		cplusplusVarType.add("unsigned int");
		cplusplusVarType.add("char");
		cplusplusVarType.add("wchar_t");
		cplusplusVarType.add("float");
		cplusplusVarType.add("double");
		cplusplusVarType.add("long double");
	}

	@Override
	public void startUnit(String fileName){
		currentFile = new FileStatistics_cpp();
		currentFile.setFileName(fileName);
		unitlevel++;
		//		filename = fileName;
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
		if(isassign&&containMacroDefinition){
			seekingAssignname = true;
			containMacroAssign = false;
			collectChars = true;
			charbucket = null;
		}
		if(isassign){
			isConstAssign = false;
		}
		if(inexpr){
			numOperandname++;
			seekingOperandname = true;
		}
	}

	public void startType() {
		intype = true;
		if(indeclstmt){
			seekingTypename = true;
		}
	}

	public void endName(){
		addClassname();
		addMacroname();
		addObjname();
		checkMacroAssign();
		if(inexpr&&seekingOperandname&&numOperandname==1){
			if(!objList.contains(charbucket.trim())){
				maybeOpOverloadCall = true;
				collectChars = false;
				charbucket = null;
			}
			else{
				maybeOpOverloadCall = false;
				charbucket = null;
			}
			seekingOperandname = false;
		}
		if(intype){
			if(seekingTypename&&charbucket!=null){
				typename = charbucket.trim();
//				System.out.println(typename);
				if(!cplusplusVarType.contains(typename)){
					seekingObjname = true;
					collectChars = false;
					charbucket = null;
				}
				seekingTypename = false;
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

	public void clearMacroList(){
		macroList.clear();
		containMacroDefinition = false;
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
			objList.add(charbucket.trim());
			System.out.println(charbucket.trim());
			seekingObjname = false;
			collectChars = false;
		}
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

	public boolean checkOperatorOverloadCall(String str) {
//		System.out.println(str+"&&&");
		if(maybeOpOverloadCall){
			if(str.contains("=")||str.contains("+")||str.contains("-")||str.contains("*")||str.contains("/")){
				isOpOverloadCall = true;
				currentFile.numCall++;
			}
//			System.out.println(isOpOverloadCall+"$$$$$$$$");
		}
		maybeOpOverloadCall = false;
		return isOpOverloadCall;
	}
}
