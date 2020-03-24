package p2m;

public abstract class Parser extends LexAnalyzer
{
    public static boolean errorFound = false;

    public static void main(String[] argv) throws NullPointerException
    {
        // argv[0]: input file containing a fun def list
        // argv[1]: output file displaying the parse tree

        setIO("/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/inputs/in1.txt",
                "/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/outputs/out1.txt");

        setLex();

        getToken();

        // build a parse tree
        FunDefList funDefList = funDefList();

        if (!t.isEmpty())
        {
            errorMsg(5);
        }
        else if(!errorFound)
        {
            // print the parse tree in linearly indented form in argv[1] file
            funDefList.printParseTree("");
        }

        closeIO();
    }

    public static FunDefList funDefList()
    {
        FunDef funDef = funDef();
        return new FunDefList(null, null);
    }

    public static FunDef funDef()
    {


        return new FunDef(null, null);
    }

    public static void errorMsg(int messageKey)
    {
        errorFound = true;

        display(t + " : Syntax Error, unexpected symbol where");

        switch( messageKey )
        {
            case 1:	displayln(" arith op or ) expected"); return;
            case 2: displayln(" id, int, float, or ( expected"); return;
            case 3:	displayln(" = expected"); return;
            case 4:	displayln(" ; expected"); return;
            case 5:	displayln(" id expected"); return;
        }
    }
}
