import java.util.*;
public class DecryptionKey{
    static int mod(int x) {
        return (x % 26 + 26) % 26;
    }
    static int inv(int x) {
        for (int i = 1; i < 26; i++)
            if (mod(x * i) == 1) return i;
        return -1;
    }
    static int det(int[][] a) {
        return a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1])
             - a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0])
             + a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);
    }
    static int minor(int[][] a, int r, int c) {
        int[] x = new int[4];
        int k = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (i != r && j != c)
                    x[k++] = a[i][j];

        return x[0] * x[3] - x[1] * x[2];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] a = new int[3][3];
        System.out.println("Enter 3x3 key matrix:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = sc.nextInt();

        int di = inv(mod(det(a)));
        if (di == -1) {
            System.out.println("Decryption key does not exist");
            return;
        }
        int[][] adj = new int[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                int sign = (i + j) % 2 == 0 ? 1 : -1;
                adj[j][i] = mod(sign * minor(a, i, j));
            }

        System.out.println("Decryption Key:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(mod(adj[i][j] * di) + " ");
            System.out.println();
        }
    }
}
//(6 24 1/13 16 10/20 17 15)