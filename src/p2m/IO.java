package p2m;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class IO
{
    public static BufferedReader inStream;
    public static PrintWriter outStream;

    public static int a;
    public static char c;

    public static int getNextChar()
    {
        try
        {
            return inStream.read();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getChar()
    {
        int i = getNextChar();
        while ( Character.isWhitespace((char) i) )
            i = getNextChar();
        return i;
    }

    public static void display(String s)
    {
        outStream.print(s);
    }

    public static void displayln(String s)
    {
        outStream.println(s);
    }

    public static void setIO(String inFile, String outFile)
    {
        try
        {
            inStream = new BufferedReader( new FileReader(inFile) );
            outStream = new PrintWriter( new FileOutputStream(outFile) );
            a = inStream.read();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeIO()
    {
        try
        {
            inStream.close();
            outStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}