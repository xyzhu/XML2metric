package counter.saveresults;

import counter.filestatistics.*;


public class SaveResults_java extends SaveResults{
	
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

	@Override
    public String getDiffFileStatisticsInfo(
            FileStatistics fileStatistics) {

		FileStatistics_java fs = (FileStatistics_java) fileStatistics;
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Class: " + fs.numClass);
        sBuilder.append("\n");
        sBuilder.append("Constructor: " + fs.numConstructor);
        sBuilder.append("\n");
        sBuilder.append("Try: " + fs.numTry);
        sBuilder.append("\n");
        sBuilder.append("Catch: " + fs.numCatch);
        sBuilder.append("\n");
        sBuilder.append("Throw: " + fs.numThrow);
        sBuilder.append("\n");
        sBuilder.append("\n");
        return sBuilder.toString();
    }
	
	public void getDiffTotalStatistics(FileStatistics fileStatistics){
		FileStatistics_java fs = (FileStatistics_java) fileStatistics;
        numClass += fs.numClass;
        numConstructor += fs.numConstructor;
        numTry += fs.numTry;
        numCatch += fs.numCatch;
        numThrow += fs.numThrow;
	}

}
