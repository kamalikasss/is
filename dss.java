import java.util.*;
import java.security.*;
public class dss {
    static long modPow(long b, long e, long m) {long r = 1;
        b %= m;
        while (e > 0) {
            if ((e & 1) == 1) r = r * b % m;
            b = b * b % m;
            e >>= 1;}return r;}
    static long[] egcd(long a, long b) {
        if (b == 0) return new long[]{a, 1, 0};
        long[] r = egcd(b, a % b);
        return new long[]{r[0], r[2], r[1] - (a / b) * r[2]};}
    static long modInverse(long a, long m) {
        long[] r = egcd(a, m);
        return (r[1] % m + m) % m;}
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long p = 283, q = 47, h = 60, x = 24;
        long g = modPow(h, (p - 1) / q, p);
        long y = modPow(g, x, p);
        System.out.print("Enter message: ");
        String msg = sc.nextLine();
        long hm = Math.abs(Arrays.hashCode(
            MessageDigest.getInstance("SHA-256").digest(msg.getBytes()))) % q;
        long k = 5;
        long r = modPow(g, k, p) % q;
        long s = (modInverse(k, q) * (hm + x * r)) % q;
        System.out.println("Public key y = " + y);
        System.out.println("Private key x = " + x);
        System.out.println("Message hash h(m) = " + hm);
        System.out.println("Signature (r, s) = (" + r + ", " + s + ")");
    }
}
//hello