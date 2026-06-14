package upimesh.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class IdempotencyService {

    private final Set<String> processedPackets =
            ConcurrentHashMap.newKeySet();

    public boolean claim(String packetId) {

        return processedPackets.add(packetId);
    }
}