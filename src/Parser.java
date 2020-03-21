
public abstract class Parser extends LexAnalyzer
{
    static boolean errorFound = false;

    // <assignment list> --> <assignment> | <assignment> <assignment list>
    public static AssignmentList assignmentList()
    {
        Assignment assignment = assignment();
        if ( state == State.Id )
        {
            AssignmentList assignmentList = assignmentList();
            return new MultipleAssignment(assignment, assignmentList);
        }
        else return assignment;
    }


    // <assignment> --> <id> = <E> ";"
    public static Assignment assignment()
    {
        if ( state == State.Id )
        {
            String id = t;
            getToken();
            if ( state == State.Eq )    // State.Assign
            {
                getToken();
                E e = E();
                if ( state == State.Semicolon )
                {
                    getToken();
                    return new Assignment(id, e);
                }
                else
                    errorMsg(4);
            }
            else
                errorMsg(3);
        }
        else
            errorMsg(5);
        return null;
    }


    // <E> --> <term> | <term> + <E> | <term> - <E>
    public static E E()
    {
        Term term = term();
        if ( state == State.Add ) // State.Add
        {
            getToken();
            E e = E();
            return new AddE(term, e);
        }
        else if ( state == State.Sub )    // State.Minus
        {
            getToken();
            E e = E();
            return new SubE(term, e);
        }
        else return new SingleTerm(term);
    }


    // <term> --> <primary> | <primary> * <term> | <primary> / <term>
    public static Term term()
    {
        Primary primary = primary();
        if ( state == State.Mul )     // State.Times
        {
            getToken();
            Term term = term();
            return new MulTerm(primary, term);
        }
        else if ( state == State.Div )
        {
            getToken();
            Term term = term();
            return new DivTerm(primary, term);
        }
        else return new SinglePrimary(primary);
    }


    // <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"
    public static Primary primary()
    {
        switch ( state )
        {
            case Id:

                Id id = new Id(t);
                getToken();
                return id;

            case Int:

                Int intElem = new Int(Integer.parseInt(t));
                getToken();
                return intElem;

            case Float: case FloatE:

            Floatp floatElem = new Floatp(Float.parseFloat(t));
            getToken();
            return floatElem;

            case LParen:

                getToken();
                E e = E();
                if ( state == State.RParen )
                {
                    getToken();
                    Parenthesized paren = new Parenthesized(e);
                    return paren;
                }
                else
                {
                    errorMsg(1);
                    return null;
                }

            default:

                errorMsg(2);
                return null;
        }
    }

    public static void errorMsg(int i)
    {
        errorFound = true;

        display(t + " : Syntax Error, unexpected symbol where");

        switch( i )
        {
            case 1:	displayln(" arith op or ) expected"); return;
            case 2: displayln(" id, int, float, or ( expected"); return;
            case 3:	displayln(" = expected"); return;
            case 4:	displayln(" ; expected"); return;
            case 5:	displayln(" id expected"); return;
        }
    }

    public static void main(String argv[])
    {
        // argv[0]: input file containing an assignment list
        // argv[1]: output file displaying the parse tree

//        setIO( argv[0], argv[1] );

        setIO("/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/inputs/in1.txt",
                "/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/outputs/out1.txt");
        setLex();

        getToken();

        AssignmentList assignmentList = assignmentList(); // build a parse tree
        if ( ! t.isEmpty() )
            errorMsg(5);
        else if ( ! errorFound )
            assignmentList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

        closeIO();
    }
}
