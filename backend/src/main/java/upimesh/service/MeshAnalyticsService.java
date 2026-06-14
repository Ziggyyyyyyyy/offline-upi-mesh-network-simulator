package upimesh.service;

import org.springframework.stereotype.Service;

@Service
public class MeshAnalyticsService {

    private long totalInjected;
    private long delivered;
    private long dropped;
    private long totalHopCount;

    public void packetInjected() {
        totalInjected++;
    }

    public void packetDelivered(
            int hopCount) {

        delivered++;
        totalHopCount += hopCount;
    }

    public void packetDropped() {
        dropped++;
    }

    public long getTotalInjected() {
        return totalInjected;
    }

    public long getDelivered() {
        return delivered;
    }

    public long getDropped() {
        return dropped;
    }

    public double getSuccessRate() {

        if (totalInjected == 0) {
            return 0;
        }

        return (delivered * 100.0)
                / totalInjected;
    }

    public double getAverageHopCount() {

        if (delivered == 0) {
            return 0;
        }

        return (double) totalHopCount
                / delivered;
    }
}