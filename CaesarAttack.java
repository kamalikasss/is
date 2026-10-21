//ceasar cipher
import java.io.*;
import java.util.*;
import java.nio.file.*;
public class CaesarAttack 
{
    static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static int charToNum(char c) 
    {
        return chars.indexOf(Character.toUpperCase(c));
    }
    static char numToChar(int n) 
    {
        return chars.charAt((n % 36 + 36) % 36);
    }
    static String encrypt(String text, int k) 
    {
        String s = "";
        for (char c : text.toCharArray()) 
        {
            int x = charToNum(c);
            if (x >= 0)
                s += numToChar((x + k) % 36);
            else
                s += c;
        }
        return s;
    }
    static String decrypt(String text, int k) 
    {
        return encrypt(text, 36 - k);
    }
    public static void main(String[] args) throws Exception 
    {
        String text = Files.readString(Path.of("plain.txt"));
        int key = 5;
        long start = System.nanoTime();
        String cipher = encrypt(text, key);
        long time = System.nanoTime() - start;
        Files.writeString(Path.of("cipher.txt"), cipher);
        System.out.println("Encrypted: " + cipher);
        System.out.println("Encryption time: " + time + " ns");
        System.out.println("\nBrute Force Attack:");
        for (int k = 0; k < 36; k++)
            System.out.println("Key " + k + ": " + decrypt(cipher, k));
        String plain = decrypt(cipher, key);
        int[] freq = new int[36];
        for (char c : plain.toCharArray()) {
            int x = charToNum(c);
            if (x >= 0)
                freq[x]++;
        }
        System.out.println("\nPlaintext Letter Frequencies:");
        for (int i = 0; i < 36; i++)
            System.out.println(numToChar(i) + " = " + freq[i]);
        System.out.println("\nEncryption Time vs File Size:");
        for (int size = 100; size <= text.length(); size += 100) 
        {
            String part = text.substring(0, size);
            start = System.nanoTime();
            encrypt(part, key);
            time = System.nanoTime() - start;
            System.out.println(size + " bytes, " + time + " ns"); 
        } 
    } 
}