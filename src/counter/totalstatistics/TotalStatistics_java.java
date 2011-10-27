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
		total.append("\n");
		total.append("-------------------------");
		total.append("\n");
		total.append("\n");
		total.append("\n");
		return total.toString();
	}
	
	public int getLocalFunctionCallNumber(List<String> functionList,
			List<String> functionCallList, List<String> classList, 
			List<String> callerList) {
		int numCall = 0;
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
		while(it_caller.hasNext()){
			callername = it_caller.next();
			if (classSet.contains(callername)){
				numCall++;
			}
		}
		return numCall;
	}
	
	public void getDiffTotalStatistics(FileStatistics fileStatistics){
		FileStatistics_java fs = (FileStatistics_java) fileStatistics;
		numClass += fs.numClass;
		numConstructor += fs.numConstructor;
		numTry += fs.numTry;
		numCatch += fs.numCatch;
		numThrow += fs.numThrow;
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
}
