package Block;

import java.security.*;
import java.util.ArrayList;

public class Transaction {
    public String transactionId; // hash of the transaction
    public PublicKey sender; // sender's address/public key
    public PublicKey recipient; // recipient's address/public key
    public float value;
    public byte[] signature; // prevent anyone else from spending funds

    private static int sequence = 0; // a rough count of how many transactions have been generated

    public Transaction(PublicKey from, PublicKey to, float value) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.transactionId = calculateHash();
    }

    private String calculateHash() {
        sequence++; // increase sequence to avoid 2 identical transactions having the same hash
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                StringUtil.getStringFromKey(recipient) +
                Float.toString(value) + sequence
        );
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        signature = StringUtil.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature);
    }
}

