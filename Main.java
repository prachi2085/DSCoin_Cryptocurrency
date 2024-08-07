package Block;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Main {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }


    public static void main(String[] args) {
        System.out.println("Bouncy Castle provider added successfully!");
        // Your code here
    }
}

