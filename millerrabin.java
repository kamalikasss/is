import java.util.*;
public class millerrabin{
    static long modPow(long a, long b, long m) {long r = 1;a %= m; while (b > 0) {
            if ((b & 1) == 1) r = r * a % m;
            a = a * a % m;b >>= 1;}return r;}
    static boolean prime(long n) {
        if (n < 2) return false;
        for (long a = 2; a < n; a++) {
            if (a * a > n) break;
            if (n % a == 0) return false;}
   return true;
    }static boolean millerRabin(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0) return false;
        long d = n - 1;
        int s = 0;
        while (d % 2 == 0) {d /= 2;s++;}
        long[] bases = {2, 3, 5, 7, 11};
        for (long a : bases) {
            if (a >= n) continue;
            long x = modPow(a, d, n);
            if (x == 1 || x == n - 1) continue;
            int i;for (i = 1; i < s; i++) {
                x = x * x % n;if (x == n - 1) break;}
            if (i == s) return false;}
        return true;}
    static long factor(long n) {
        for (long i = 2; i <= n / i; i++)
            if (n % i == 0) return i;
        return n;}
    static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;b = a % b;a = t;
        }return a;}
    static boolean primitiveRoot(int g, int p) {
        for (int i = 1; i < p - 1; i++)
            if (modPow(g, i, p) == 1) return false;
        return modPow(g, p - 1, p) == 1;}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a prime number: ");
        int p = sc.nextInt();
        if (prime(p)) {
            System.out.println("Primitive roots:");for (int g = 1; g < p; g++)
                if (primitiveRoot(g, p))
                    System.out.print(g + " ");
            System.out.println();
        } else {System.out.println("Not a prime number");}System.out.print("Enter integer to factorize: ");
        long n = sc.nextLong();
        System.out.print("Factors: ");
        while (n > 1) {
            long f = factor(n);
            System.out.print(f + " ");
            n /= f;
        }System.out.println();
        System.out.print("Enter number for Miller-Rabin test: ");
        long x = sc.nextLong();
        System.out.println(millerRabin(x) ? "Probably Prime" : "Composite");} }
//(17 5 7)