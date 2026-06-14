package upimesh.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderVpa;

    private String receiverVpa;

    private BigDecimal amount;

    private String status;

    private Instant createdAt;

    private String packetHash;

private Integer hopCount;

private String bridgeNodeId;

private Instant settledAt;

public String getPacketHash() {
    return packetHash;
}

public void setPacketHash(
        String packetHash) {

    this.packetHash = packetHash;
}

public Integer getHopCount() {
    return hopCount;
}

public void setHopCount(
        Integer hopCount) {

    this.hopCount = hopCount;
}

public String getBridgeNodeId() {
    return bridgeNodeId;
}

public void setBridgeNodeId(
        String bridgeNodeId) {

    this.bridgeNodeId = bridgeNodeId;
}

public Instant getSettledAt() {
    return settledAt;
}

public void setSettledAt(
        Instant settledAt) {

    this.settledAt = settledAt;
}

    public Transaction() {
    }

    public Transaction(String senderVpa,
                       String receiverVpa,
                       BigDecimal amount,
                       String status,
                       Instant createdAt) {

        this.senderVpa = senderVpa;
        this.receiverVpa = receiverVpa;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getSenderVpa() {
        return senderVpa;
    }

    public void setSenderVpa(String senderVpa) {
        this.senderVpa = senderVpa;
    }

    public String getReceiverVpa() {
        return receiverVpa;
    }

    public void setReceiverVpa(String receiverVpa) {
        this.receiverVpa = receiverVpa;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}