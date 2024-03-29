package counter.totalstatistics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import counter.filestatistics.*;

public class TotalStatistics_cpp extends TotalStatistics{
	public int numClass;
	public int numStruct = 0;
	public int numConstructordecl = 0;
	public int numDestructordecl = 0;
	public int numConstructor = 0;
	public int numDestructor = 0;
	public int numUnion = 0;
	public int numDirective = 0;
	public int numTry = 0;
	public int numCatch = 0;
	public int numThrow = 0;
	public int numOpOverloadCall = 0;

	public String getDiffTotalStatisticsInfo() {
		StringBuilder total = new StringBuilder();
		total.append("Class: " + numClass);
		total.append("\n");
		total.append("Struct: " + numStruct);
		total.append("\n");
		total.append("Operator overload call: " + numOpOverloadCall);
		total.append("\n");
		total.append("Local operator overload call: " + numLocalOpOverloadCall);
		total.append("\n");
		total.append("Library operator overload call: " + numLibOpOverloadCall);
		total.append("\n");
		total.append("Constructor declaration: " + numConstructordecl);
		total.append("\n");
		total.append("Destructor declaration: " + numDestructordecl);
		total.append("\n");
		total.append("Constructor: " + numConstructor);
		total.append("\n");
		total.append("Destructor: " + numDestructor);
		total.append("\n");
		total.append("Union: " + numUnion);
		total.append("\n");
		total.append("Directive: " + numDirective);
		total.append("\n");
		total.append("Try: " + numTry);
		total.append("\n");
		total.append("Catch: " + numCatch);
		total.append("\n");
		total.append("Throw: " + numThrow);
		total.append("\n");
		total.append("\n");
		total.append("-------------------------");
		total.append("\n");
		total.append("\n");
		total.append("\n");
		return total.toString();
	}


	public void getDiffTotalStatisticsPart1(List<FileStatistics> fsList){
		FileStatistics fs;
		Iterator<FileStatistics> it = fsList.iterator();
		while(it.hasNext()){
			fs = it.next();
			addDiffFileStatistics(fs);
		}
	}
	
	public void getTotalStatisticsPart2() {
		numLocalFunctionCall2 = getLocalFunctionCallNumber();
		numLocalOpOverloadCall = getLocalOpOverloadCall();
		numLocalFunctionCall = numLocalFunctionCall1 + numLocalFunctionCall2;
		numLibOpOverloadCall = getLibOpOverloadCall();
		numLibFunctionCall = functionCallList.size() - numLocalFunctionCall;
	}
	
	public void addDiffFileStatistics(FileStatistics fileStatistics){
		FileStatistics_cpp fs = (FileStatistics_cpp) fileStatistics;
		numClass += fs.numClass;
		numStruct += fs.numStruct;
		numConstructordecl += fs.numConstructordecl;
		numDestructordecl += fs.numDestructordecl;
		numConstructor += fs.numConstructor;
		numDestructor += fs.numDestructor;
		numUnion += fs.numUnion;
		numDirective += fs.numDirective;
		numTry += fs.numTry;
		numCatch += fs.numCatch;
		numThrow += fs.numThrow;
		numOpOverloadCall += fs.numOpOverloadCall;
	}
	
	public int getLocalFunctionCallNumber() {
		int numLocalCall = 0;
		boolean isLocal = false;
		Set<String> methodSet = new HashSet<String>();
		Iterator<String> it_method = functionList.iterator();
		while(it_method.hasNext()){
			methodSet.add(it_method.next());
		}
		int clsize = 0;
		Set<String> classSet = null;
		if(classList!=null){
			clsize = classList.size();
			classSet = new HashSet<String>();
		}
		if(clsize!=0){
			Iterator<String> it_class = classList.iterator();
			while(it_class.hasNext()){
				classSet.add(it_class.next());
			}
		}
		Iterator<String> it_call = functionCallList.iterator();
		String callname;
		while(it_call.hasNext()){
			callname = it_call.next();
			isLocal = false;
			if (methodSet.contains(callname)){
				numLocalCall++;
				isLocal = true;
			}
			if(clsize!=0){
				if(classSet.contains(callname)){
					numLocalCall++;
				}
				else if(callname.startsWith("~")){
					if(classSet.contains(callname.substring(1, callname.length()))){
						numLocalCall++;
					}
				}
			}
			if(isGetterSetterCall(callname)){
				if(isLocal){
					numLocalGetterSetterCall++;
				}
				else{
					numLibGetterSetterCall++;
				}
			}
		}
		return numLocalCall;
	}


	public int getLibOpOverloadCall() {
		return numOpOverloadCall - numLocalOpOverloadCall;
	}

	public int getLocalOpOverloadCall() {	
		int numcall = 0;
		Iterator<String> it = operandTypeList.iterator();
		String operandType;
		while(it.hasNext()){
			operandType = it.next();
			if(classList.contains(operandType)){
				numcall++;
			}
		}
		return numcall;
	}
	
	public int getNumClass(){
		return numClass;
	}
	public int getNumStruct(){
		return numStruct;
	}
	public int getNumConstructordecl(){
		return numConstructordecl;
	}
	public int getNumDestructordecl(){
		return numDestructordecl;
	}
	public int getNumConstructor(){
		return numConstructor;
	}
	public int getNumDestructor(){
		return numDestructor;
	}
	public int getNumUnion(){
		return numUnion;
	}
	public int getNumDirective(){
		return numDirective;
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
	public int getNumOpOverloadCall(){
		return numOpOverloadCall;
	}
}
