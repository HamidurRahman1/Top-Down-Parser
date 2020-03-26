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
            ExpId expId = new ExpId(t);
            getToken();
            return expId;
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

            FunExp funExp = getFunExp();


            // check for close brace
            return funExp;
        }
        else if(state == State.Keyword_if)
        {
            // check for ExpIfThenElse
            getToken();



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
        FunOp funOp = getFunOp();

        if(funOp == null || errorFound)
        {
            errorMsg(7);
            return null;
        }

        ExpList expList = getExpList();

        return new FunExp(funOp, expList);
    }

    public static ExpList getExpList()
    {
        System.out.println(t+state);

        if(state == State.RParen)
        {
            return new EmptyExpList();
        }

        if(state == State.Id)
        {
            Exp exp = getExp();

            if(exp == null || errorFound)
            {
                errorMsg(2);
                return null;
            }

            ExpList expList = getExpList();

            if(expList instanceof EmptyExpList)
            {
                return new NonEmptyExpList(exp, expList);
            }
            if(errorFound)
            {
                return null;
            }
            return new NonEmptyExpList(exp, expList);
        }

        errorMsg(5);
        return null;
    }

    public static FunOp getFunOp()
    {
        if(state == State.Id)
        {
            FunOpId funOpId = new FunOpId(t);
            getToken();
            return funOpId;
        }
        else if(state == State.Keyword_pair)
        {
            getToken();
            return new FunOpPair();
        }
        else if(state == State.Keyword_first)
        {
            getToken();
            return new FunOpFirst();
        }
        else if(state == State.Keyword_second)
        {
            getToken();
            return new FunOpSecond();
        }
        else if(state.isArithOp())
        {
            return getArithOp();
        }
        else if(state.isBoolOp())
        {
            return getBoolOp();
        }
        else if(state.isCompOp())
        {
            return getCompOp();
        }
        else
        {
            errorMsg(7);
            return null;
        }
    }

    public static CompOp getCompOp()
    {
        if(state == State.Lt)
        {
            getToken();
            return new CompOpLt();
        }
        else if(state == State.Le)
        {
            getToken();
            return new CompOpLe();
        }
        else if(state == State.Gt)
        {
            getToken();
            return new CompOpGt();
        }
        else if(state == State.Ge)
        {
            getToken();
            return new CompOpGe();
        }
        else
        {
            getToken();
            return new CompOpEq();
        }
    }

    public static BoolOp getBoolOp()
    {
        if(state == State.Keyword_or)
        {
            getToken();
            return new BoolOpOr();
        }
        else if(state == State.Keyword_and)
        {
            getToken();
            return new BoolOpAnd();
        }
        else
        {
            getToken();
            return new BoolOpNot();
        }
    }

    public static ArithOp getArithOp()
    {
        if(state == State.Add)
        {
            getToken();
            return new ArithOpAdd();
        }
        else if(state == State.Sub)
        {
            getToken();
            return new ArithOpSub();
        }
        else if(state == State.Mul)
        {
            getToken();
            return new ArithOpMul();
        }
        else
        {
            getToken();
            return new ArithOpDiv();
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
        if(state == State.LBrace)
            return new EmptyParameterList();

        if(state == State.Id)
        {
            String param = t;

            getToken();

            ParameterList parameterList = getParameterList();

            if(parameterList instanceof EmptyParameterList)
            {
                return new NonEmptyParameterList(param, parameterList);
            }
            if(errorFound)
            {
                return null;
            }
            return new NonEmptyParameterList(param, parameterList);
        }

        errorMsg(5);
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
            case 7: displayln(" id, pair, first, second, arith op, bool op, comp op expected"); return;
        }
    }
}
