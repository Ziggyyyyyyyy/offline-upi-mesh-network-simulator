package upimesh.crypto;

public class EncryptedPayload {

    private String encryptedKey;
    private String encryptedData;

    public EncryptedPayload() {
    }

    public EncryptedPayload(
            String encryptedKey,
            String encryptedData) {

        this.encryptedKey = encryptedKey;
        this.encryptedData = encryptedData;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(
            String encryptedKey) {

        this.encryptedKey = encryptedKey;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(
            String encryptedData) {

        this.encryptedData = encryptedData;
    }
}