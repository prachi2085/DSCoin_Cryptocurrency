package Block;
	import java.util.ArrayList;

	public class Blockchain {
	    public static ArrayList<Block> blockchain = new ArrayList<>();
	    public static int difficulty = 5;

	    public static void main(String[] args) {
	        blockchain.add(new Block("First Block", "0"));
	        System.out.println("Trying to Mine block 1... ");
	        blockchain.get(0).mineBlock(difficulty);

	        blockchain.add(new Block("Second Block", blockchain.get(blockchain.size() - 1).hash));
	        System.out.println("Trying to Mine block 2... ");
	        blockchain.get(1).mineBlock(difficulty);

	        blockchain.add(new Block("Third Block", blockchain.get(blockchain.size() - 1).hash));
	        System.out.println("Trying to Mine block 3... ");
	        blockchain.get(2).mineBlock(difficulty);

	        System.out.println("\nBlockchain is Valid: " + isChainValid());
	    }

	    public static Boolean isChainValid() {
	        Block currentBlock;
	        Block previousBlock;
	        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

	        for (int i = 1; i < blockchain.size(); i++) {
	            currentBlock = blockchain.get(i);
	            previousBlock = blockchain.get(i - 1);
	            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
	                System.out.println("Current Hashes not equal");
	                return false;
	            }
	            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
	                System.out.println("Previous Hashes not equal");
	                return false;
	            }
	            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
	                System.out.println("This block hasn't been mined");
	                return false;
	            }
	        }
	        return true;
	    }
	}



