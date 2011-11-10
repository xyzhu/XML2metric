package counter.totalstatistics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import counter.filestatistics.*;

public class TotalStatistics_c extends TotalStatistics{
	public int numStruct = 0;
	public int numGoto = 0;
	public int numLabel = 0;

	@Override
	public String getDiffTotalStatisticsInfo() {
		StringBuilder total = new StringBuilder();
		total.append("Struct: " + numStruct);
		total.append("\n");
		total.append("Goto: " + numGoto);
		total.append("\n");
		total.append("Label: " + numLabel);
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
		numLocalFunctionCall = numLocalFunctionCall1 + numLocalFunctionCall2;
		numLibFunctionCall = functionCallList.size() - numLocalFunctionCall;
	}
	
	public void addDiffFileStatistics(FileStatistics fileStatistics){
		FileStatistics_c fs = (FileStatistics_c) fileStatistics;
		numStruct += fs.numStruct;
		numGoto += fs.numGoto;
		numLabel += fs.numLabel;
	}
	
	public int getLocalFunctionCallNumber() {
		int numLocalCall = 0;
		boolean isLocal = false;
		Set<String> methodSet = new HashSet<String>();
		Iterator<String> it_method = functionList.iterator();
		while(it_method.hasNext()){
			methodSet.add(it_method.next());
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

	public int getNumStruct(){
		return numStruct;
	}
	
	public int getNumGoto(){
		return numGoto;
	}
	
	public int getNumLabel(){
		return numLabel;
	}
}
