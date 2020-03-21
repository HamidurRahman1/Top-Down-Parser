
class Floatp extends Primary
{
    float val;

    Floatp(float f)
    {
        val = f;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <primary> " + val);
    }

//    Val Eval(HashMap<String,Val> state)
//    {
//        return new FloatVal(val);
//    }
//
//    void emitInstructions()
//    {
//        IO.displayln("push " + val);
//    }
}
