package counter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import counter.filestatistics.*;

import counter.totalstatistics.*;



public class SaveResults {
	public int numFile;
	public int numTotalLine;
	public int numCommentLine;
	public int numBlankLine;
	public int numFunction;
	public int numFunctionDecl;
	public int numBlock;
	public int numDeclstmt;
	public int numDecl;
	public int numExprstmt;
	public int numExpr;
	public int numIf;
	public int numElse;
	public int numWhile;
	public int numFor;
	public int numContinue;
	public int numBreak;
	public int numDo;
	public int numSwitch;
	public int numCase;
	public int numReturn;
	public int numCall;
	public int numParamList;
	public int numParam;
	public int numArguList;
	public int numArgument;
	public int numAssignment;
	public int numLocalFunctionCall = 0;
	public int numLibFunctionCall = 0;
	public int numZeroOpAssign = 0;
	public int numZeroOpCallAssign = 0;
	public int numConstAssign = 0;
	public int numParamDecl = 0;
	public int numDeclInFor = 0;
	public int numDeclInStmt = 0;
	public int numDeclStmtWithInit = 0;
	public int numAssignInDeclStmt = 0;
	//for C and C++, numLocalFunctionCall2 is used to get the number of local
	//function calls that have a caller. For Java, numLocalFunctionCall2 is used
	//to get the number of local method calls.
	public int numLocalFunctionCall2 = 0;
	public int numLocalOpOverloadCall = 0;
	public int numLibOpOverloadCall = 0;


	public void writeResult(String fileName, String language,
			List<FileStatistics> fileList, List<String> functionList,
			List<String> functionCallList, List<String> classList,
			List<String> callerList, int numLocalCall1,List<String> operandTypeList,
			Boolean saveFunction, Boolean saveOperator, Boolean savefilestat) {
		FileStatistics fileStatistics;
		TotalStatistics totalStatistics = null;
		if(language.equals("C")){
			totalStatistics = new TotalStatistics_c();
		}
		else if(language.equals("C++")){
			totalStatistics = new TotalStatistics_cpp();
		}
		else if(language.equals("Java")){
			totalStatistics = new TotalStatistics_java();
		}
		else{
			System.err.println("Can not parse projects in this language");
			System.exit(0);
		}
		String fileInfo;
		try{
			int index = fileName.indexOf(".xml");
			String outFileName = fileName.substring(0, index);
			//create file
			FileWriter fw = new FileWriter(outFileName + ".txt");
			BufferedWriter writer = new BufferedWriter(fw);
			StringBuilder fileBuilder = new StringBuilder();
			Iterator<FileStatistics> it = fileList.iterator();
			if(savefilestat){
				while(it.hasNext()){
					totalStatistics.numFile++;
					fileStatistics = it.next();
					totalStatistics.getTotalStatistics(fileStatistics, numLocalCall1, functionList, 
							functionCallList, classList, operandTypeList, callerList);
					fileInfo = fileStatistics.getFileStatisticsInfo();
					fileBuilder.append(fileInfo);
					fileBuilder.append("\n");
				}
			}
			else{
				while(it.hasNext()){
					totalStatistics.numFile++;
					fileStatistics = it.next();
					totalStatistics.getTotalStatistics(fileStatistics, numLocalCall1, functionList, 
					functionCallList, classList, operandTypeList, callerList);
				}
			}
			String totalInfo = totalStatistics.getTotalStatisticsInfo();
			writer.write(totalInfo);
			writer.write(fileBuilder.toString());
			writer.close();
			if(saveFunction){
				writeFunction(outFileName, functionList, functionCallList);
			}
			if(saveOperator){
				writeOperator(outFileName, fileList);
			}
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}		
	}

	public void writeFunction(String fileName, List<String> functionList,
			List<String> functionCallList) {
		try{
			//create file
			FileWriter fw_function = new FileWriter(fileName +"_functions.txt");
			FileWriter fw_functioncall = new FileWriter(fileName +"_functioncalls.txt");
			BufferedWriter writer_function = new BufferedWriter(fw_function);
			BufferedWriter writer_functioncall = new BufferedWriter(fw_functioncall);
			Iterator<String> it_function = functionList.iterator();
			StringBuilder functionBuilder = new StringBuilder();
			while(it_function.hasNext()){
				functionBuilder.append(it_function.next());
				functionBuilder.append("\n");
			}
			writer_function.write(functionBuilder.toString());
			writer_function.close();
			Iterator<String> it_functioncall = functionCallList.iterator();
			StringBuilder functioncallBuilder = new StringBuilder();
			while(it_functioncall.hasNext()){
				functioncallBuilder.append(it_functioncall.next());
				functioncallBuilder.append("\n");
			}
			writer_functioncall.write(functioncallBuilder.toString());
			writer_functioncall.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}		
	}

	public void writeOperator(String fileName, 
			List<FileStatistics> fileList) {
		FileStatistics fileStatistics;
		try{
			//create file
			FileWriter fw_operator = new FileWriter(fileName +"_operator.txt");
			BufferedWriter writer_operator = new BufferedWriter(fw_operator);
			String operatorInfo = "";
			Iterator<FileStatistics> it = fileList.iterator();
			while(it.hasNext()){
//				numFile++;
				fileStatistics = it.next();
				operatorInfo = getOperatorInfo(fileStatistics.numOperatorList);
				writer_operator.write(fileStatistics.fileName);
				writer_operator.write("\n");
				writer_operator.write(operatorInfo);
			}
			writer_operator.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}		
	}


	private String getOperatorInfo(List<Integer> numOperatorList) {
		StringBuilder opBuilder = new StringBuilder();
		Iterator <Integer> opIt = numOperatorList.iterator();
		int n = 0;
		while(opIt.hasNext()){
			n = opIt.next();
			opBuilder.append(n);
			opBuilder.append("\n");
		}
		return opBuilder.toString();
	}

}
