import java.util.*;
public class rsakeypairgeneration {
    static long inverse(long a, long m) {
        for (long d = 1; d < m; d++)
            if (a * d % m == 1) return d;
        return -1;
    }static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;}return a;
    }static boolean prime(long n) {
        if (n < 2) return false;
        for (long i = 2; i <= n / i; i++)
            if (n % i == 0) return false;
        return true;}
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter prime p: ");
        long p = sc.nextLong();
        System.out.print("Enter prime q: ");
        long q = sc.nextLong();
        if (!prime(p) || !prime(q) || p == q) {
            System.out.println("Enter two distinct prime numbers");
            return;
        }long n = p * q;
        long phi = (p - 1) * (q - 1);
        int count = 0;
System.out.println("Public Key (e,n) | Private Key (d,n)");
for (long e = 2; e < phi && count < 10; e++) {
            if (gcd(e, phi) == 1) {
                long d = inverse(e, phi);
                if (d != -1) {
                    System.out.println("(" + e + "," + n + ") | (" + d + "," + n + ")");
                    count++;
                } } }
        if (count < 10)
            System.out.println("Only " + count + " valid pairs available");
    }
}