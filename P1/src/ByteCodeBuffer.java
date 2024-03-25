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
    }

    public byte[] getByteBuffer()
    {
        return byteBuffer;
    }

    public int getByteBufferPointer()
    {
        return byteBufferPointer;

    }
    public byte getByte()
    {
        if (!isAvailable())
            throw new IndexOutOfBoundsException("Buffer pointer is at the last position of buffer");

        return this.byteBuffer[this.byteBufferPointer++];
    }

    public int getInt()
    {
        return (getByte() << 24) + (getByte() << 16) + (getByte() << 8) + (getByte());
    }

    public boolean isAvailable()
    {
        return this.byteBufferPointer < this.byteBuffer.length;
    }

    public void resetPointer()
    {
        this.byteBufferPointer = 0;
    }

    public void movePointer(int translate)
    {
        int newPointerPosition = this.byteBufferPointer + translate;
        if(newPointerPosition < 0 || newPointerPosition >= this.byteBuffer.length)
            throw new IndexOutOfBoundsException("Invalid translation for pointer. It should be in range between, 0 and the length of the byte array");

        this.byteBufferPointer = newPointerPosition;
    }

    public void movePointerToIndex(int index)
    {
        if(index < 0 || index >= this.byteBuffer.length)
            throw new IndexOutOfBoundsException("Invalid value for pointer. It should be in range between, 0 and the length of the byte array");

        this.byteBufferPointer = index;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder(this.byteBuffer.length > 0 ? STR."\{this.byteBuffer[0]}" : "");

        for(int i = 1; i < this.byteBuffer.length; i++)
            result.append(" ").append(String.format("%02X", this.byteBuffer[i]));

        return result.toString();
    }
}
