// hill cipher
import java.util.*;
public class Knownplaintext 
{
    static int mod(int x) 
    {
        return (x % 26 + 26) % 26;
    }
    static int inv(int x)
    {
        for (int i = 1; i < 26; i++)
            if (mod(x * i) == 1) return i;
        return -1;
    }
    static int[][] inverse(int[][] a) 
    {
        int d = mod(a[0][0] * a[1][1] - a[0][1] * a[1][0]);
        int di = inv(d);
        if (di == -1) throw new IllegalArgumentException("Inverse does not exist");
        return new int[][] {
            {mod(a[1][1] * di), mod(-a[0][1] * di)},
            {mod(-a[1][0] * di), mod(a[0][0] * di)}
        };
    }
    static int[][] multiply(int[][] a, int[][] b) 
    {
        int[][] r = new int[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    r[i][j] = mod(r[i][j] + a[i][k] * b[k][j]);
        return r;
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int[][] p = new int[2][2];
        int[][] c = new int[2][2];

        System.out.println("Enter plaintext matrix:");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                p[i][j] = sc.nextInt();

        System.out.println("Enter ciphertext matrix:");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                c[i][j] = sc.nextInt();

        try 
        {
            int[][] key = multiply(c, inverse(p));
            System.out.println("Recovered Key Matrix:");
            for (int[] row : key)
                System.out.println(row[0] + " " + row[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Plaintext matrix is not invertible");
        }
    }
}
//(1 2 3 5/25 2 2 5)