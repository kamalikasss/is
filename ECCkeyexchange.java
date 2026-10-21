public class ECCkeyexchange {
static int p = 17, a = 2, b = 2;
static int mod(int x) {
    return (x % p + p) % p;
}
static int inverse(int x) {
    for (int i = 1; i < p; i++)
        if (mod(x * i) == 1) return i;
    return 0;
}
static int[] pointAdd(int x1, int y1, int x2, int y2) {
    if (x1 == x2 && mod(y1 + y2) == 0) return null;
    int l;
    if (x1 == x2 && y1 == y2)
        l = mod((3 * x1 * x1 + a) * inverse(2 * y1));
    else
        l = mod((y2 - y1) * inverse(x2 - x1));
    int x3 = mod(l * l - x1 - x2);
    int y3 = mod(l * (x1 - x3) - y1);
    return new int[]{x3, y3};
}
static int[] scalarMultiply(int k, int x, int y) {
    int[] r = null, q = {x, y};
    while (k > 0 && q != null) {
        if (k % 2 == 1)
            r = r == null ? q : pointAdd(r[0], r[1], q[0], q[1]);
        q = pointAdd(q[0], q[1], q[0], q[1]);
        k /= 2;
    }
    return r;
}
public static void main(String[] args) {
    int[] G = {5, 1};
    int alice = 5, bob = 7;
    int[] A = scalarMultiply(alice, G[0], G[1]);
    int[] B = scalarMultiply(bob, G[0], G[1]);
    System.out.println("Alice Public Key: (" + A[0] + "," + A[1] + ")");
    System.out.println("Bob Public Key: (" + B[0] + "," + B[1] + ")");
    int[] S1 = scalarMultiply(alice, B[0], B[1]);
    int[] S2 = scalarMultiply(bob, A[0], A[1]);
    System.out.println("Alice Shared Secret: (" + S1[0] + "," + S1[1] + ")");
    System.out.println("Bob Shared Secret: (" + S2[0] + "," + S2[1] + ")");
}
}
