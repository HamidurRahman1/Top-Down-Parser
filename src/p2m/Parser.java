package p2m;

public abstract class Parser extends LexAnalyzer
{
    static boolean errorFound = false;

    public static void main(String argv[])
    {
        // argv[0]: input file containing a fun def list
        // argv[1]: output file displaying the parse tree

        setIO("/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/inputs/in1.txt",
                "/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/outputs/out1.txt");
        setLex();

        getToken();

        FunDefList funDefList = funDefList(); // build a parse tree
        if ( ! t.isEmpty() )
            errorMsg(5);
        else if ( ! errorFound )
            funDefList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

        closeIO();
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

    public static FunDefList funDefList()
    {
        return null;
    }
}
