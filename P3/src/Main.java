import java.util.Vector;

public class Main
{
    static int TRIALS = 31;
    static double VECTOR_SIZE = 67108864;


    static long start = 0;
    static long stop = 0;

    public static void main(String[] args)
    {
        for(int i = 0 ; i < TRIALS; i++)
        {
           Vector<Double> vector = new Vector<>();

           start = System.nanoTime() / 1000000;
           for(double j = 0; j < VECTOR_SIZE; j++)
                vector.add(j);
           stop += System.nanoTime() / 1000000 - start;
        }

        System.out.println("Time: " + stop / TRIALS);
    }
}
