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
	public boolean seekingSynchronized = false;
	public List<String> classList;
	public boolean isobjdecl;
	public Set<String> javaVarType;
	public String typename;
	public Map<String, String> classobj;
	public List<String> callerList;
	//callerFunctionCallList is used to save the 
	//function call's name that has a caller
	public List<String> callerFunctionCallList;
	public boolean inname;
	public boolean seekingCallername;
	//hasCaller is used to tell if a method call in Java has a caller.
	//if not, this method call must be a call to local defined mehtod.
	public boolean hasCaller;
	//the following three variable is used to save the number of local calls
	//that have no caller.
	public int numLocalCall;
	public int numLocalGetterSetterCall;

	public CountStatistics_java(Boolean saveop){
		saveOperator = saveop;
		classList = new LinkedList<String>();
		classobj = new HashMap<String, String>();
		callerList = new LinkedList<String>();
		callerFunctionCallList = new LinkedList<String>();
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
		seekingSynchronized = true;
	}

	public void startFunction() {
		currentFile.numFunction++;
		seekingFunctionname = true;
		seekingSynchronized = true;
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
				||seekingClassname||seekingTypename||seekingObjname||seekingSynchronized) {
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
			seekingCallername = false;
		}
		if(seekingSynchronized){
			if(charbucket!=null&&charbucket.trim().equals("synchronized")){
				currentFile.increaseNumSynchronized();
				seekingSynchronized = false;
			}
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

	public int getNumLocalGetterSetterCall(){
		return numLocalGetterSetterCall;
	}

	public void checkFunctionCall(String callName){
		if(!hasCaller){
			if(isGetterSetterCall(callName))
				numLocalGetterSetterCall++;
		}
		else{
			callerFunctionCallList.add(callName);
		}
		hasCaller = false;
	}

	public boolean isGetterSetterCall(String callname){
		if(callname.startsWith("get")||callname.startsWith("_get")
				||callname.startsWith("_get")||callname.startsWith("_set")
				||callname.startsWith("Get")||callname.startsWith("_Get")
				||callname.startsWith("_Get")||callname.startsWith("_Get")){
			return true;
		}
		else
			return false;
	}

	public List<String> getCallerFunctionCallList(){
		return callerFunctionCallList;
	}
}