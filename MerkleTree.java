package Block;

import java.util.ArrayList;

public class MerkleTree {
    public static String getMerkleRoot(ArrayList<Transaction> transactions) {
        int count = transactions.size();
        ArrayList<String> previousTreeLayer = new ArrayList<>();
        for (Transaction transaction : transactions) {
            previousTreeLayer.add(transaction.transactionId);
        }
        ArrayList<String> treeLayer = previousTreeLayer;

        while (count > 1) {
            treeLayer = new ArrayList<>();
            for (int i = 1; i < previousTreeLayer.size(); i += 2) {
                treeLayer.add(StringUtil.applySha256(previousTreeLayer.get(i - 1) + previousTreeLayer.get(i)));
            }
            count = treeLayer.size();
            previousTreeLayer = treeLayer;
        }

        return (treeLayer.size() == 1) ? treeLayer.get(0) : "";
    }
}

