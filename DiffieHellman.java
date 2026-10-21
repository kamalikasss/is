import java.math.BigInteger;
public class DiffieHellman {
    static int primitiveRoot(int p) {
        for (int g = 2; g < p; g++) {
            int count = 0;
            for (int i = 1; i < p; i++)
                if (BigInteger.valueOf(g).modPow(
                    BigInteger.valueOf(i),
                    BigInteger.valueOf(p)).intValue() == 1)
                    count++;
            if (count == 1)
                return g;
        }
        return -1;
    }
    static BigInteger publicKey(int g, int x, int p) {
        return BigInteger.valueOf(g).modPow(
            BigInteger.valueOf(x), BigInteger.valueOf(p));
    }
    static BigInteger sharedSecret(BigInteger key, int x, int p) {
        return key.modPow(BigInteger.valueOf(x), BigInteger.valueOf(p));
    }
    public static void main(String[] args) {
        int p = 23;
        int g = primitiveRoot(p);
        int a = 6, b = 15;
        BigInteger A = publicKey(g, a, p);
        BigInteger B = publicKey(g, b, p);
        BigInteger K1 = sharedSecret(B, a, p);
        BigInteger K2 = sharedSecret(A, b, p);
        System.out.println("Prime: " + p);
        System.out.println("Primitive Root: " + g);
        System.out.println("Client Public Key: " + A);
        System.out.println("Server Public Key: " + B);
        System.out.println("Client Shared Secret: " + K1);
        System.out.println("Server Shared Secret: " + K2);
    }
}
