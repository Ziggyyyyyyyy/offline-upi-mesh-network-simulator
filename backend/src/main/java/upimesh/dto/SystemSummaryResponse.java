package upimesh.dto;

public class SystemSummaryResponse {

    private long totalAccounts;
    private long totalTransactions;

    public SystemSummaryResponse(
            long totalAccounts,
            long totalTransactions) {

        this.totalAccounts = totalAccounts;
        this.totalTransactions = totalTransactions;
    }

    public long getTotalAccounts() {
        return totalAccounts;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }
}