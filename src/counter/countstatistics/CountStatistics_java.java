package counter.countstatistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import counter.filestatistics.FileStatistics_java;

public class CountStatistics_java extends CountStatistics{

	public boolean seekingClassname = false;
	public boolean seekingTypename = false;
	public boolean seekingObjname = false;
	public List<String> classList;
	public boolean isobjdecl;
	public Set<String> javaVarType;
	public String typename;
	public Map<String, String> classobj;
	public List<String> callername;
	public boolean inname;
	public boolean seekingCallname;

	public CountStatistics_java(Boolean saveop){
		saveOperator = saveop;
		classList = new LinkedList<String>();
		classobj = new HashMap<String, String>();
		callername = new LinkedList<String>();
		javaVarType = new HashSet<String>();
		javaVarType.add("boolean");
		javaVarType.add("char");
		javaVarType.add("int");
		javaVarType.add("long");
		javaVarType.add("float");
		javaVarType.add("double");
		javaVarType.add("Boolean");
		javaVarType.add("Character");
		javaVarType.add("Integer");
		javaVarType.add("Long");
		javaVarType.add("Float");
		javaVarType.add("Double");
		javaVarType.add("String");
		javaVarType.add("Date");
	}

	@Override
	public void startUnit(String fileName){
		currentFile = new FileStatistics_java();
		currentFile.setFileName(fileName);
		unitlevel++;
	}

	public void startClass(){
		currentFile.increaseNumClass();
		seekingClassname = true;
	}

	public void startConstructor(){
		currentFile.increaseNumConstructor();
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

	public void startType() {
		intype = true;
		if(indecl){
			seekingTypename = true;
		}
	}

	public void startName() {
		if(seekingFunctionname||seekingFunctioncallname||seekingClassname||seekingTypename||seekingObjname) {
			collectChars = true;
			charbucket = null;
		}
		if(isassign){
			isConstAssign = false;
		}
		if(incall){
			if(inname){
				seekingCallname = true;
			}
			inname = true;
		}
	}

	public void endName(){
		addClassname();
		addObjname();
		if(incall){
			if(!seekingCallname){
				currentFile.increaseNumLocalCall();
			}
			else{
				callername.add(charbucket.trim());
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
	public void addObjname(){
		if(isobjdecl){
			//we suppose there are not objects having the same name in a project
			classobj.put(charbucket.trim(), typename);
		}
		collectChars = false;
	}

	public List<String> getClassList(){
		return classList;
	}

	public void endType(){
		intype = false;
		if(javaVarType.contains(typename)){
			isobjdecl = true;
			collectChars = false;
		}
	}
}