package p2m;

public abstract class Parser extends LexAnalyzer
{
    public static boolean errorFound = false;

    public static void main(String[] argv) throws NullPointerException
    {
        // argv[0]: input file containing a fun def list
        // argv[1]: output file displaying the parse tree

        setIO("/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/inputs/test.txt",
                "/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/outputs/out1.txt");

        setLex();

        getToken();

        // build a parse tree
        FunDefList funDefList = getFunDefList();

        funDefList.printParseTree("");

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

    public static FunDefList getFunDefList()
    {
        FunDef funDef = getFunDef();

        getToken();

        return new FunDefList(funDef, null);
    }

    public static FunDef getFunDef()
    {
        Header header = getHeader();

        getToken();

        Exp exp = getExp();

        return new FunDef(header, exp);
    }

    public static Exp getExp()
    {
        if(state == State.Id)
        {
            return new ExpId(t);
        }
        else if(state == State.Int)
        {
            return new ExpInt(Integer.parseInt(t));
        }
        else if(state == State.Float || state == State.FloatE || state == State.FloatF)
        {
            return new ExpFloat(Float.parseFloat(t));
        }
        else if(state == State.Keyword_nil)
        {
            return new ExpNil();
        }
        else if(state == State.LParen)
        {
            getToken();
            System.out.println(t + ' ' + state);

            FunExp funExp = getFunExp();


            // check for close brace
            return null;
        }
        else if(state == State.Keyword_if)
        {
            // check for ExpIfThenElse
            return null;
        }
        else
        {
            errorMsg(7);
            return null;
        }
    }

    public static FunExp getFunExp()
    {
        // get fun op

        // get exp list

        return new FunExp(null, null);
    }

    public static ExpList getExpList()
    {
        return new ExpList(null, null);
    }

    public static FunOp getFunOp()
    {
        if(state == State.Id)
        {
            return null;
        }
        else if(state == State.Keyword_pair)
        {
            return null;
        }
        else if(state == State.Keyword_first)
        {
            return null;
        }
        else if(state == State.Keyword_second)
        {
            return null;
        }
        else if(state.isArithOp())
        {
            // all arithOps
            return null;
        }
        else if(state.isBoolOp())
        {
            // all boolOps
            return null;
        }
        else if(state.isCompOp())
        {
            // all compOps
            return null;
        }
        else
        {
            return null;
        }
    }

    public static Header getHeader()
    {
        FunName funName = getFunName();

        if(funName != null)
        {
            getToken();

            ParameterList parameterList = getParameterList();

            if(parameterList == null)
            {
                return new Header(funName);
            }
            else
            {
                return new Header(funName, parameterList);
            }
        }
        return null;
    }

    public static ParameterList getParameterList()
    {
        if(state == State.Id)
        {
            ParameterList parameterListId = new ParameterList(new ParameterId(t));
            getToken();
            ParameterList parameterList = getParameterList();

            if(parameterList != null || !errorFound)
            {
                parameterListId.parameterList = parameterList;
                return parameterListId;
            }
            else
            {
                return null;
            }
        }

//        errorMsg(5);

        return null;
    }

    public static FunName getFunName()
    {
        if(state == State.Id)
        {
            return new FunName(t);
        }
        else
        {
            errorMsg(5);
            return null;
        }
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
            case 6:	displayln(" { expected"); return;
        }
    }
}
