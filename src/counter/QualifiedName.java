package counter;
import java.util.ArrayList;
import java.util.Collection;
enum elementName{unitEle, functionEle, function_declEle, 
	nameEle, typeEle, blockEle, decl_stmtEle, declEle, 
	expr_stmtEle, exprEle, ifEle, elseEle, whileEle, 
	forEle, continueEle, breakEle, doEle, switchEle, 
	caseEle, returnEle, callEle, parameter_listEle, 
	paramEle, argument_listEle, argumentEle, commentEle, 
	structEle, labelEle, classEle,  constructorEle, 
	tryEle, catchEle,  throwEle, class_declEle, 
	constructor_declEle, destructor_declEle, destructorEle, 
	unionEle, packageEle, synchronizedEle, gotoEle, 
	cppdefineEle, directiveEle, defaultEle};

    public class QualifiedName {

        static Collection<String> collection = new ArrayList<String>();

        public static Collection<String> createCollection(){

            collection.add("unit");
            collection.add("function_decl");
            collection.add("function");
            collection.add("name");
            collection.add("type");
            collection.add("block");
            collection.add("decl_stmt");
            collection.add("decl");
            collection.add("expr_stmt");
            collection.add("expr");
            collection.add("if");
            collection.add("else");
            collection.add("while");
            collection.add("for");
            collection.add("continue");
            collection.add("break");
            collection.add("do");
            collection.add("switch");
            collection.add("case");
            collection.add("return");
            collection.add("call");
            collection.add("parameter_list");
            collection.add("param");
            collection.add("argument_list");
            collection.add("argument");
            collection.add("comment");
            collection.add("struct");
            collection.add("label");
            collection.add("class");
            collection.add("constructor");
            collection.add("try");
            collection.add("catch");
            collection.add("throw");
            collection.add("class_decl");
            collection.add("constructor_decl");
            collection.add("destructor_decl");
            collection.add("destructor");
            collection.add("union");
            collection.add("package");
            collection.add("sysnchronized");
            collection.add("goto");
            collection.add("cppdefine");
            collection.add("directive");
            collection.add("default");
            
            return collection;
        }

        public static elementName getElementName(String qualifiedName) {
            elementName eleName;
            if(collection.contains(qualifiedName)){
                String name = qualifiedName + "Ele";
                eleName = elementName.valueOf(name); 
            }
            else
                eleName = elementName.defaultEle;
            return eleName;
        }

    }
