import java.util.Date;

class Block {

    String hash;
    String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
    }

    public void mineBlock(int difficulty){
        String target = new String(new char [difficulty]).replace('\0','0');
        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined! : " + hash);
    }
}

