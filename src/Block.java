import java.util.Date;

class Block {

    String hash;
    private String previousHash;
    private String data;
    private long timeStamp;

    Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    private String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
    }
}

