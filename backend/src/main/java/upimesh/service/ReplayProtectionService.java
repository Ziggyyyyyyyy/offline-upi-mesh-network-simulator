package upimesh.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class ReplayProtectionService {

    public boolean isFresh(
            Instant signedAt) {

        Duration age =
                Duration.between(
                        signedAt,
                        Instant.now()
                );

        return age.toMinutes() < 10;
    }
}