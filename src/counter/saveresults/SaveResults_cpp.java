package counter.saveresults;
import counter.filestatistics.*;

public class SaveResults_cpp extends SaveResults{
	public int numClass;
    public int numStruct = 0;
    public int numConstructordecl = 0;
    public int numDestructordecl = 0;
    public int numConstructor = 0;
    public int numDestructor = 0;
    public int numUnion = 0;
    public int numTry = 0;
    public int numCatch = 0;
    public int numThrow = 0;

    @Override
	public String getDiffTotalStatistics() {
        StringBuilder total = new StringBuilder();
        total.append("Class: " + numClass);
        total.append("\n");
        total.append("Struct: " + numStruct);
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
    public String getDiffFileStatistics(
            FileStatistics fileStatistics) {

        FileStatistics_cpp fs = (FileStatistics_cpp) fileStatistics;
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Class: " + fs.numClass);
        numClass += fs.numClass;
        sBuilder.append("\n");
        sBuilder.append("Struct: " + fs.numStruct);
        numStruct += fs.numStruct;
        sBuilder.append("\n");
        sBuilder.append("Constructor declaration: " + fs.numConstructordecl);
        numConstructordecl += fs.numConstructordecl;
        sBuilder.append("\n");
        sBuilder.append("Destructor declaration: " + fs.numDestructordecl);
        numDestructordecl += fs.numDestructordecl;
        sBuilder.append("\n");
        sBuilder.append("Constructor: " + fs.numConstructor);
        numConstructor += fs.numConstructor;
        sBuilder.append("\n");
        sBuilder.append("Destructor: " + fs.numDestructor);
        numDestructor += fs.numDestructor;
        sBuilder.append("\n");
        sBuilder.append("Union: " + fs.numUnion);
        numUnion += fs.numUnion;
        sBuilder.append("\n");
        sBuilder.append("Try: " + fs.numTry);
        numTry += fs.numTry;
        sBuilder.append("\n");
        sBuilder.append("Catch: " + fs.numCatch);
        numCatch += fs.numCatch;
        sBuilder.append("\n");
        sBuilder.append("Throw: " + fs.numThrow);
        numThrow += fs.numThrow;
        sBuilder.append("\n");
        sBuilder.append("\n");
        return sBuilder.toString();
    }

}
