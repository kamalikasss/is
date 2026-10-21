import java.util.*;
public class SHA {
    static int leftRotate(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String msg = sc.nextLine();
        int A = 0x11223344, B = 0x55678899;
        int C = 0x00AABBCC, D = 0xDDEEFF01;
        int E = 0x23456789, K = 0x5A827999;
        int f = B ^ C ^ D;
        int W = 0;
        for (int i = 0; i < Math.min(4, msg.length()); i++)
            W = (W << 8) | (msg.charAt(i) & 255);
        int T = leftRotate(A, 5) + f + E + W + K;
        System.out.printf("f = %08X%n", f);
        System.out.printf("W = %08X%n", W);
        System.out.printf("K = %08X%n", K);
        System.out.printf("ROTL(A,5) = %08X%n", leftRotate(A, 5));
        System.out.printf("T = %08X%n", T);
        System.out.printf("Updated A = %08X%n", T);
        System.out.printf("Updated B = %08X%n", A);
        System.out.printf("Updated C = %08X%n", leftRotate(B, 30));
        System.out.printf("Updated D = %08X%n", C);
        System.out.printf("Updated E = %08X%n", D);
        sc.close();
    }
}