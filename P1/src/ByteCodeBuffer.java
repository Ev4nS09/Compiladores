import java.io.*;

public class ByteCodeBuffer
{
    private final byte[] byteBuffer;
    private int byteBufferPointer;

    public ByteCodeBuffer(byte[] bytes)
    {
        this.byteBuffer = bytes;
        this.byteBufferPointer = 0;
    }

    public ByteCodeBuffer(String byteCodeFile) throws IOException
    {
        DataInputStream inputStream = new DataInputStream(new FileInputStream(byteCodeFile));
        this.byteBuffer = new byte[inputStream.available()];
        inputStream.read(this.byteBuffer);
        inputStream.close();

        this.byteBufferPointer = 0;
    }

    public byte[] getByteBuffer()
    {
        return this.byteBuffer;
    }

    public int getByteBufferPointer()
    {
        return this.byteBufferPointer;

    }
    public byte getByte()
    {
        if (!isAvailable())
            throw new IndexOutOfBoundsException("Buffer pointer out of bounds");

        return this.byteBuffer[this.byteBufferPointer++];
    }

    public int getInt()
    {
        return (getByte() << 24) + (getByte() << 16) + (getByte() << 8) + (getByte());
    }

    public char getChar()
    {
        return (char) ((getByte() << 8) + (getByte()));
    }

    public String getString()
    {
        StringBuilder result = new StringBuilder();
        int length = getInt();
        for(int i = 0; i < length; i++)
        {
            result.append(getChar());
        }
        return result.toString();
    }

    public Double getDouble()
    {
        long current = 0;
        for(int i = 0; i < 8; i++) //8 is how many bytes a double has.
        {
            current = (current << 8) + (getByte() & 0xFF);
        }
        return Double.longBitsToDouble(current);
    }

    public boolean getBoolean()
    {
        return getByte() == 1;
    }



    public boolean isAvailable()
    {
        return this.byteBufferPointer < this.byteBuffer.length;
    }

    public void resetPointer()
    {
        this.byteBufferPointer = 0;
    }

    public void movePointer(int index)
    {
        if(index < 0 || index >= this.byteBuffer.length)
            throw new IndexOutOfBoundsException("Invalid value for pointer. It should be in range between, 0 and the length of the byte array");

        this.byteBufferPointer = index;
    }

    public void finishPointer()
    {
        this.byteBufferPointer = this.byteBuffer.length;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder(this.byteBuffer.length > 0 ? String.valueOf(this.byteBuffer[0]) : "");

        for(int i = 1; i < this.byteBuffer.length; i++)
            result.append(" ").append(String.format("%02X", this.byteBuffer[i]));

        return result.toString();
    }
}
