import java.util.ArrayList;
import java.util.Date;

class Block {

    String hash;
    String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    private long timeStamp;
    private int nonce;

    Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        merkleRoot
        );
    }

    public void mineBlock(int difficulty){
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDifficultyString(difficulty);
        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined! : " + hash);
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if ((previousHash != "0")) {
            if ((transaction.processTransaction() != true)) {
                System.out.println("Transaction failed to process. Discarded");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction successfully added to block");
        return true;
    }
}

