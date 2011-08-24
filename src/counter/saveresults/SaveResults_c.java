package counter.saveresults;
import counter.filestatistics.*;

public class SaveResults_c extends SaveResults{
    public int numStruct = 0;
    public int numGoto = 0;
    public int numLabel = 0;

    @Override
	public String getDiffTotalStatistics() {
        StringBuilder total = new StringBuilder();
        total.append("Struct: " + numStruct);
        total.append("\n");
        total.append("Do: " + numDo);
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

	@Override
    public String getDiffFileStatistics(
            FileStatistics fileStatistics) {

		FileStatistics_c fs = (FileStatistics_c) fileStatistics;
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Struct: " + fs.numStruct);
        numStruct += fs.numStruct;
        sBuilder.append("\n");
        sBuilder.append("Goto: " + fs.numGoto);
        numGoto += fs.numGoto;
        sBuilder.append("\n");
        sBuilder.append("Label: " + fs.numLabel);
        numLabel += fs.numLabel;
        sBuilder.append("\n");
        sBuilder.append("\n");
        return sBuilder.toString();
    }

}
