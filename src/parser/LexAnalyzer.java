package parser;

import java.util.HashMap;

public abstract class LexAnalyzer extends IO
{
    public static String t;
    public static State state;

    private static State nextState[][] = new State[22][128];

    private static HashMap<String, State> keywordMap = new HashMap<>();

    private static void setKeywordMap()
    {
        keywordMap.put("if",     State.Keyword_if);
        keywordMap.put("then",   State.Keyword_then);
        keywordMap.put("else",   State.Keyword_else);
        keywordMap.put("or",     State.Keyword_or);
        keywordMap.put("and",    State.Keyword_and);
        keywordMap.put("not",    State.Keyword_not);
        keywordMap.put("pair",   State.Keyword_pair);
        keywordMap.put("first",  State.Keyword_first);
        keywordMap.put("second", State.Keyword_second);
        keywordMap.put("nil",    State.Keyword_nil);
    }

    private static int driver()
    {
        State nextSt;

        t = "";
        state = State.Start;

        if ( Character.isWhitespace((char) a) )
            a = getChar();
        if ( a == -1 )
            return -1;

        while ( a != -1 )
        {
            c = (char) a;
            nextSt = nextState[state.ordinal()][a];
            if ( nextSt == State.UNDEF )
            {
                if ( state.isFinal() )
                {
                     return 1;
                }
                else
                {
                    t = t+c;
                    a = getNextChar();
                    return 0;
                }
            }
            else
            {
                state = nextSt;
                t = t+c;
                a = getNextChar();
            }
        }

        if ( state.isFinal() )
            return 1;
        else
            return 0;
    }

    private static void setNextState()
    {
        for (int s = 0; s <= 21; s++ )
            for (int c = 0; c <= 127; c++ )
                nextState[s][c] = State.UNDEF;

        for (char c = 'A'; c <= 'Z'; c++)
        {
            nextState[State.Start     .ordinal()][c] = State.Id;
            nextState[State.Id        .ordinal()][c] = State.Id;
        }

        for (char c = 'a'; c <= 'z'; c++)
        {
            nextState[State.Start     .ordinal()][c] = State.Id;
            nextState[State.Id        .ordinal()][c] = State.Id;
        }

        for (char d = '0'; d <= '9'; d++)
        {
            nextState[State.Start      .ordinal()][d] = State.Int;
            nextState[State.Id         .ordinal()][d] = State.Id;
            nextState[State.Int        .ordinal()][d] = State.Int;
            nextState[State.Add        .ordinal()][d] = State.Int;
            nextState[State.Sub        .ordinal()][d] = State.Int;
            nextState[State.Dot        .ordinal()][d] = State.Float;
            nextState[State.Float      .ordinal()][d] = State.Float;
            nextState[State.E          .ordinal()][d] = State.FloatE;
            nextState[State.EPlusMinus .ordinal()][d] = State.FloatE;
            nextState[State.FloatE     .ordinal()][d] = State.FloatE;
        }

        nextState[State.Start.ordinal()]['+'] = State.Add;
        nextState[State.Start.ordinal()]['-'] = State.Sub;
        nextState[State.Start.ordinal()]['*'] = State.Mul;
        nextState[State.Start.ordinal()]['/'] = State.Div;
        nextState[State.Start.ordinal()]['<'] = State.Lt;
        nextState[State.Start.ordinal()]['>'] = State.Gt;
        nextState[State.Start.ordinal()]['='] = State.Eq;
        nextState[State.Start.ordinal()]['('] = State.LParen;
        nextState[State.Start.ordinal()][')'] = State.RParen;
        nextState[State.Start.ordinal()]['{'] = State.LBrace;
        nextState[State.Start.ordinal()]['}'] = State.RBrace;
        nextState[State.Start.ordinal()]['.'] = State.Dot;

        nextState[State.Lt .ordinal()]['='] = State.Le;
        nextState[State.Gt .ordinal()]['='] = State.Ge;

        nextState[State.Add.ordinal()]['.'] = State.Dot;
        nextState[State.Sub.ordinal()]['.'] = State.Dot;
        nextState[State.Int.ordinal()]['.'] = State.Float;

        nextState[State.Float.ordinal()]['e'] = State.E;
        nextState[State.Float.ordinal()]['E'] = State.E;
        nextState[State.Int  .ordinal()]['e'] = State.E;
        nextState[State.Int  .ordinal()]['E'] = State.E;

        nextState[State.E.ordinal()]['+'] = State.EPlusMinus;
        nextState[State.E.ordinal()]['-'] = State.EPlusMinus;

        nextState[State.Int   .ordinal()]['f'] = State.FloatF;
        nextState[State.Int   .ordinal()]['F'] = State.FloatF;
        nextState[State.Float .ordinal()]['f'] = State.FloatF;
        nextState[State.Float .ordinal()]['F'] = State.FloatF;
        nextState[State.FloatE.ordinal()]['f'] = State.FloatF;
        nextState[State.FloatE.ordinal()]['F'] = State.FloatF;
    }

    private static void keywordCheck()
    {
        State keywordState = keywordMap.get(t);
        if ( keywordState != null )
            state = keywordState;
    }

    public static void getToken()
    {
        int i = driver();
        if ( state == State.Id )
            keywordCheck();
        else if ( i == 0 )
            displayln(t + " : Lexical Error, invalid token");
    }

    public static void setLex()
    {
        setNextState();
        setKeywordMap();
    }
}