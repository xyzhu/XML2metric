package counter.countstatistics;

import java.util.LinkedList;
import java.util.List;

import counter.filestatistics.FileStatistics_cpp;

public class CountStatistics_cpp extends CountStatistics{


	String functionName;
	public Boolean seekingClassname = false;
	public List<String> classList;
	public List<String> macroList;

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
	public boolean containOnlyMacroAssign = true;
	public boolean containMacroDefinition = false;

	public CountStatistics_cpp(Boolean saveop){
		saveOperator = saveop;
		classList = new LinkedList<String>();
		macroList = new LinkedList<String>();
	}

	@Override
	public void startUnit(String fileName){
		currentFile = new FileStatistics_cpp();
		currentFile.setFileName(fileName);
		unitlevel++;
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
	public void startCppdefine(){
		seekingMacroname = true;
		containMacroDefinition = true;
	}

	public void seekClassname(){
		if(seekingClassname){
			collectChars = true;
			charbucket = null;
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

	public void seekMacroname(){
		if(seekingMacroname||seekingAssignname) {
			collectChars = true;
			charbucket = null;
		}
	}

	public void setMacroConstAssign(){
		if(containMacroDefinition&&containMacroAssign&&containOnlyMacroAssign){
			currentFile.numConstAssign += numassign;
		}
	}
	public void seekAssignname(boolean b){
		seekingAssignname = b;
	}

	public void startSeekAssignname(){
		if(containMacroDefinition){
			seekingAssignname = true;
			containMacroAssign = false;
			containOnlyMacroAssign = true;
		}
	}

	public void addMacroname(){
		if(seekingMacroname){
			macroList.add(charbucket.trim());
			seekingMacroname = false;
			collectChars = false;
		}
	}
	public void checkMacroAssign(){
		if(seekingAssignname){
			if(charbucket!=null&&!macroList.contains(charbucket.trim())){
				containOnlyMacroAssign = false;
				seekingAssignname = false;
			}
			if(!incall&&macroList.contains(charbucket.trim())){
				containMacroAssign = true;
			}
		}
	}

	public void addMacroAssign(){
		if(containMacroDefinition&&!isConstAssign
				&&containMacroAssign&&containOnlyMacroAssign&&numOperator==0){
			currentFile.numConstAssign += numassign;
		}
	}
}
