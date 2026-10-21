public class eccaffinepts {
static int p = 17, a = 2, b = 2;
static int mod(int x) {
    return (x % p + p) % p;
}
static int inverse(int x) {
    for (int i = 1; i < p; i++)
        if (mod(x * i) == 1) return i;
    return 0;
}
static void generatePoints() {
    for (int x = 0; x < p; x++)
        for (int y = 0; y < p; y++)
            if (mod(y * y) == mod(x * x * x + a * x + b))
                System.out.println("(" + x + "," + y + ")");
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
    System.out.println("Affine Points:");
    generatePoints();
    int[] G = {5, 1};
    int d = 7, k = 3;
    int[] Pm = {6, 3};
    int[] Q = scalarMultiply(d, G[0], G[1]);
    int[] C1 = scalarMultiply(k, G[0], G[1]);
    int[] S = scalarMultiply(k, Q[0], Q[1]);
    int[] C2 = pointAdd(Pm[0], Pm[1], S[0], S[1]);
    System.out.println("C1 = (" + C1[0] + "," + C1[1] + ")");
    System.out.println("C2 = (" + C2[0] + "," + C2[1] + ")");
    int[] T = scalarMultiply(d, C1[0], C1[1]);
    int[] recovered = pointAdd(C2[0], C2[1], T[0], mod(-T[1]));
    System.out.println("Decrypted Point = (" + recovered[0] + "," + recovered[1] + ")");
}
}