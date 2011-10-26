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
	public List<String> callerList;
	public boolean inname;
	public boolean seekingCallername;
	public boolean hasCaller;
	public int numLocalCall;

	public CountStatistics_java(Boolean saveop){
		saveOperator = saveop;
		classList = new LinkedList<String>();
		classobj = new HashMap<String, String>();
		callerList = new LinkedList<String>();
		javaVarType = new HashSet<String>();
		javaVarType.add("byte");
		javaVarType.add("short");
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
		classobj.clear();
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
		if(seekingFunctionname||seekingFunctioncallname
				||seekingClassname||seekingTypename||seekingObjname) {
			collectChars = true;
			charbucket = null;
		}
		if(isassign){
			isConstAssign = false;
		}
		if(incall){
			if(inname){
				hasCaller = true;
			}
			inname = true;
		}
	}

	public void seekCallername(){
		seekingCallername = true;
	}

	public void endName(){
		String caller;
		addClassname();
		addObjname();
		if(incall&&seekingCallername){
			if(!hasCaller){
				numLocalCall++;
			}
			else{
				caller = charbucket.trim();
				if(classobj.get(caller)!=null){
					callerList.add(classobj.get(caller));
				}
				else{
					callerList.add(caller);
				}
			}
			hasCaller = false;
			seekingCallername = false;
		}
		inname = false;
	}

	public void addClassname(){
		if(seekingClassname){
			classList.add(charbucket.trim());
			seekingClassname = false;
			collectChars = false;
		}
	}
	public void addObjname(){
		if(isobjdecl&&seekingObjname&&charbucket!=null){
			classobj.put(charbucket.trim(), typename);
		}
		collectChars = false;
		seekingObjname = false;
	}

	public List<String> getClassList(){
		return classList;
	}

	public List<String> getCallerList(){
		return callerList;
	}
	public void endType(){
		intype = false;
		if(seekingTypename&&charbucket!=null){
			typename = charbucket.trim();
			if(!javaVarType.contains(typename)){
				isobjdecl = true;
				collectChars = false;
				seekingObjname = true;
			}
			seekingTypename = false;
		}
	}
	public int getNumLocalCall(){
		return numLocalCall;
	}
}