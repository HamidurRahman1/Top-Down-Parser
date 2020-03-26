package p2m;

public class NonEmptyParameterList extends ParameterList
{
    public String parameterId;
    public ParameterList bpl;

    public NonEmptyParameterList(String parameterId) {
        this.parameterId = parameterId;
    }

    public NonEmptyParameterList(String parameterId, ParameterList bpl) {
        this.parameterId = parameterId;
        this.bpl = bpl;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <parameter list>");

        indent += " ";

        IO.displayln(indent + indent.length() + " " + parameterId);

        if(bpl != null)
        {
            bpl.printParseTree(indent);
        }
    }
}
