package counter.totalstatistics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import counter.filestatistics.*;


public class TotalStatistics_java extends TotalStatistics{

	public int numClass = 0;
	public int numConstructor = 0;
	public int numTry = 0;
	public int numCatch = 0;
	public int numThrow = 0;
	public int numSynchronized = 0;


	@Override
	public String getDiffTotalStatisticsInfo() {

		StringBuilder total = new StringBuilder();
		total.append("Class: " + numClass);
		total.append("\n");
		total.append("Constructor: " + numConstructor);
		total.append("\n");
		total.append("Try: " + numTry);
		total.append("\n");
		total.append("Catch: " + numCatch);
		total.append("\n");
		total.append("Throw: " + numThrow);
		total.append("\n");
		total.append("Synchronized: " + numSynchronized);
		total.append("\n");
		total.append("\n");
		total.append("-------------------------");
		total.append("\n");
		total.append("\n");
		total.append("\n");
		return total.toString();
	}
	
	public int getLocalFunctionCallNumber() {
		int numLocalCall = 0;
		Set<String> methodSet = new HashSet<String>();
		Iterator<String> it_method = functionList.iterator();
		while(it_method.hasNext()){
			methodSet.add(it_method.next());
		}
		Set<String> classSet = new HashSet<String>();
		Iterator<String> it_class = classList.iterator();
		while(it_class.hasNext()){
			classSet.add(it_class.next());
		}
		Iterator<String> it_caller = callerList.iterator();
		String callername;
		String callname;
		Iterator<String> it_call = callerFunctionCallList.iterator();
		while(it_caller.hasNext()){
			callername = it_caller.next();
			callname = it_call.next();
			if (classSet.contains(callername)){
				numLocalCall++;
				if(isGetterSetterCall(callname))
					numLocalGetterSetterCall++;
			}
			else{
				if(isGetterSetterCall(callname))
					numLibGetterSetterCall++;
			}

		}
		return numLocalCall;
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
		numLocalFunctionCall = numLocalFunctionCall1 + numLocalFunctionCall2;
		numLibFunctionCall = functionCallList.size() - numLocalFunctionCall;
		numLocalGetterSetterCall += numLocalGetterSetterCall1;
	}
	
	public void addDiffFileStatistics(FileStatistics fileStatistics){
		FileStatistics_java fs = (FileStatistics_java) fileStatistics;
		numClass += fs.numClass;
		numConstructor += fs.numConstructor;
		numTry += fs.numTry;
		numCatch += fs.numCatch;
		numThrow += fs.numThrow;
		numSynchronized += fs.numSynchronized;
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
	public int getNumSynchronized(){
			return numSynchronized;
	}
}
