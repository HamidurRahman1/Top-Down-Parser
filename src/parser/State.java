package parser;

public enum State
{
    // final states  ordinal number  token accepted

    Add,             // 0          +
    Sub,             // 1          -
    Mul,             // 2          *
    Div,             // 3          /
    Lt,              // 4          <
    Le,              // 5          <=
    Gt,              // 6          >
    Ge,              // 7          >=
    Eq,              // 8          =
    Id,              // 9          identifiers
    Int,             // 10         integers
    Float,           // 11         floats without exponentiation part
    FloatE,          // 12         floats with exponentiation part
    FloatF,          // 13         floats with suffix "f" or "F"
    LParen,          // 14         (
    RParen,          // 15         )
    LBrace,          // 16         {
    RBrace,          // 17         }

    // non-final states                 string recognized

    Start,           // 18         the empty string
    Dot,             // 19         ".", "+.", "-."
    E,               // 20         float parts ending with E or e
    EPlusMinus,      // 21         float parts ending with + or - in exponentiation part

    // keyword states

    Keyword_if,
    Keyword_then,
    Keyword_else,
    Keyword_or,
    Keyword_and,
    Keyword_not,
    Keyword_pair,
    Keyword_first,
    Keyword_second,
    Keyword_nil,

    UNDEF;

    public boolean isFinal()
    {
        return this.compareTo(State.RBrace) <= 0;
    }

    public boolean isArithOp()
    {
        return this.compareTo(Div) <= 0;
    }

    public boolean isCompOp()
    {
        return this.compareTo(Lt) >= 0 && this.compareTo(Eq) <= 0;
    }

    public boolean isBoolOp()
    {
        return this.compareTo(Keyword_or) >= 0 && this.compareTo(Keyword_not) <= 0;
    }
}