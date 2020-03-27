package p2m;

public abstract class Parser extends LexAnalyzer
{
    public static boolean errorFound = false;

    public static void main(String[] argv)
    {
        setIO("/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/inputs/test.txt",
                "/Users/hamidurrahman/Downloads/GitHub/Project--TopDownParser/src/outputs/out.txt");

        setLex();

        getToken();

        FunDefList funDefList = getFunDefList();

        if(!t.isEmpty())
        {
            errorMsg(0);
        }
        else if(!errorFound)
        {
            funDefList.printParseTree("");
        }

        closeIO();
    }

    public static FunDefList getFunDefList()
    {
        FunDef funDef = getFunDef();

        if(funDef == null || errorFound)
        {
            return null;
        }

        getToken();

        if(state == State.Start)
        {
            return new FunDefList(funDef);
        }

        FunDefList funDefList = getFunDefList();

        if(errorFound || funDefList == null)
        {
            return null;
        }

        return new FunDefList(funDef, funDefList);
    }

    public static FunDef getFunDef()
    {
        Header header = getHeader();

        if(errorFound || state != State.LBrace)
        {
            errorMsg(6);
            return null;
        }

        getToken();

        Exp exp = getExp();

        if(state != State.RBrace)
        {
            errorMsg(12);
            return null;
        }

        if(exp == null || errorFound)
        {
            return null;
        }

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
            ExpInt expInt = new ExpInt(Integer.parseInt(t));
            getToken();
            return expInt;
        }
        else if(state == State.Float || state == State.FloatE || state == State.FloatF)
        {
            ExpFloat expFloat = new ExpFloat(Float.parseFloat(t));
            getToken();
            return expFloat;
        }
        else if(state == State.Keyword_nil)
        {
            getToken();
            return new ExpNil();
        }
        else if(state == State.LParen)
        {
            getToken();

            FunExp funExp = getFunExp();

            if(errorFound)
            {
                return null;
            }

            if(state == State.RParen)
            {
                getToken();
                return funExp;
            }
            else
            {
                errorMsg(13);
                return null;
            }
        }
        else if(state == State.Keyword_if)
        {
            getToken();

            Exp exp = getExp();

            if(state == State.Keyword_then)
            {
                getToken();

                Exp exp1 = getExp();

                if(errorFound || exp1 == null)
                {
                    errorMsg(2);
                    return null;
                }

                if(state == State.Keyword_else)
                {
                    getToken();

                    Exp exp2 = getExp();

                    if(errorFound || exp2 == null)
                    {
                        return null;
                    }
                    return new ExpIfThenElse(exp, exp1, exp2);
                }
                else
                {
                    errorMsg(9);
                    return null;
                }
            }
            else
            {
                errorMsg(8);
                return null;
            }
        }
        else
        {
            errorMsg(10);
            return null;
        }
    }

    public static FunExp getFunExp()
    {
        FunOp funOp = getFunOp();

        if(funOp == null || errorFound)
        {
            return null;
        }

        ExpList expList = getExpList();

        if(errorFound)
        {
            return null;
        }

        return new FunExp(funOp, expList);
    }

    public static ExpList getExpList()
    {
        if(state == State.RParen)
        {
            return new EmptyExpList();
        }

        if(state == State.Id || state == State.Int
                || state == State.Float || state == State.FloatE
                || state == State.FloatF || state == State.Keyword_nil
                || state == State.LParen || state == State.Keyword_if)
        {
            Exp exp = getExp();

            if(exp == null || errorFound)
            {
                return null;
            }

            ExpList expList = getExpList();

            if(expList instanceof EmptyExpList)
            {
                return new NonEmptyExpList(exp, null);
            }
            if(errorFound)
            {
                return null;
            }
            return new NonEmptyExpList(exp, expList);
        }

//        errorMsg(1);
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

            if(errorFound || parameterList == null)
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
            errorMsg(0);
            return null;
        }
    }

    public static void errorMsg(int messageKey)
    {
        errorFound = true;

        display(t + " : Syntax Error, unexpected symbol where");

        switch( messageKey )
        {
            case 0: displayln(" fun name expected"); return;
            case 1:	displayln(" arith op or ) expected"); return;
            case 2: displayln(" id, int, float, or ( expected"); return;
            case 3:	displayln(" = expected"); return;
            case 4:	displayln(" ; expected"); return;
            case 5:	displayln(" id expected"); return;
            case 6:	displayln(" { expected"); return;
            case 7: displayln(" id, pair, first, second, arith op, bool op or comp op expected"); return;
            case 8: displayln(" then expected"); return;
            case 9: displayln(" else expected"); return;
            case 10: displayln(" id, int, float, nil, ( or if expected"); return;
            case 11: displayln(" id or { "); return;
            case 12: displayln(" } expected"); return;
            case 13: displayln(" ) expected"); return;
        }
    }
}
