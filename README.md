# TopDownParser

This project is a continuation of Project 1 - LexicalAnalyzer. Implement a top-down, recursive-descent parser for the following BNF for our project language.

<pre>
⟨fun def list⟩ → ⟨fun def⟩ | ⟨fun def⟩ ⟨fun def list⟩
⟨fun def⟩ → ⟨header⟩ "{" ⟨exp⟩ "}"
⟨header⟩ → ⟨fun name⟩ ⟨parameter list⟩
⟨fun name⟩ → ⟨id⟩
⟨parameter list⟩ → ε | ⟨id⟩ ⟨parameter list⟩
⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | ⟨floatF⟩ | "nil" | "(" ⟨fun exp⟩ ")" | "if" ⟨exp⟩ "then" ⟨exp⟩ "else" ⟨exp⟩
⟨fun exp⟩ → ⟨fun op⟩ ⟨exp list⟩
⟨fun op⟩ → ⟨id⟩ | "pair" | "first" | "second" | ⟨arith op⟩ | ⟨bool op⟩ | ⟨comp op⟩
⟨arith op⟩ → + | − | * | /
⟨bool op⟩ → "or" | "and" | "not"
⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="
⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩

NOTE: ε stands for the empty string.

</pre>

A lexical analyzer for this language's tokens has been implemented in Project 1.

Your program will read any text file that contains (what is intended to be) a string in the category ⟨fun def list⟩. It will then construct an explicit parse tree and display it in linearly indented form: each syntactic category name labeling a node is displayed on a separate line, prefixed with the integer i representing the node's depth and indented by i blanks. This is a basic form of syntax profiler.

Explicit parse trees are to be constructed by class objects using the method described in lecture. The categories ⟨fun def list⟩, ⟨parameter list⟩, ⟨exp list⟩ have a linear list structure defined by the right-recursive patterns:
<pre>
⟨X list⟩ → ⟨Y⟩ | ⟨Y⟩ ⟨X list⟩
⟨X list⟩ → ε | ⟨Y⟩ ⟨X list⟩
For production rules with alternatives:
⟨X⟩ → α1 | ··· | αn,    n ≥ 2
</pre>
the general strategy is to make the class for ⟨X⟩ abstract and the classes for αi subclasses of it. Class fields represent sub parse trees. For example, the second recursive pattern of ⟨X list⟩ with ε may be implemented by the following class schemas:
<pre>
abstract class XList
{
}

class EmptyXList extends XList // represents ε
{
}

class NonEmptyXList extends XList // represents ⟨Y⟩⟨X list⟩
{
	Y   y;
	Xlist   xlist;
}
</pre>
Parse trees should be displayed by printParseTree functions in suitable syntactic category classes.

An appropriate error message should be issued when the first syntax error is found; in this project there is no need to recover from it and continue parsing. (Real-world compilers do some type of syntax-error recovery and attempt to find more syntax errors.)
