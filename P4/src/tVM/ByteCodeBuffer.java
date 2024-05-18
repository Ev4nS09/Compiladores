package tVM;

import ErrorHandler.ErrorLog;

import java.io.*;

public class ByteCodeBuffer
{
    private final DataInputStream byteBuffer;

    public ByteCodeBuffer(String byteCodeFile) throws IOException
    {
        this.byteBuffer = new DataInputStream(new FileInputStream(byteCodeFile));
    }

    public byte getByte() throws IOException
    {
        if(!isAvailable())
            ErrorLog.fatalError("Out of file.");

        return this.byteBuffer.readByte();
    }

    public int getInt() throws IOException
    {
        if(!isAvailable())
            ErrorLog.fatalError("Out of file.");
        return this.byteBuffer.readInt();
    }

    public char getChar() throws IOException
    {
        return byteBuffer.readChar();
    }

    public String getString() throws IOException
    {
        StringBuilder result = new StringBuilder();
        int length = getInt();

        for(int i = 0; i < length; i++)
        {
            result.append(getChar());
        }

        return result.toString();
    }

    public Double getDouble() throws IOException
    {
        return this.byteBuffer.readDouble();
    }

    public boolean getBoolean() throws IOException
    {
        return getByte() == 1;
    }


    public boolean isAvailable() throws IOException
    {
        return this.byteBuffer.available() > 0;
    }

    public void close() throws IOException
    {
        this.byteBuffer.close();
    }
}
