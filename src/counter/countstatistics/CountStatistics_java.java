package counter.countstatistics;

import java.util.LinkedList;
import java.util.List;

import counter.filestatistics.FileStatistics_java;

public class CountStatistics_java extends CountStatistics{

	public Boolean seekingClassname = false;
	public List<String> classList;
	
	public CountStatistics_java(Boolean saveop){
		saveOperator = saveop;
		classList = new LinkedList<String>();
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
}