package upimesh.mesh;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkTopology {

    private final Map<String, List<String>>
            neighbors =
            new HashMap<>();

    public NetworkTopology() {

        neighbors.put(
                "Device-A",
                List.of(
                        "Device-B",
                        "Device-C"
                )
        );

        neighbors.put(
                "Device-B",
                List.of(
                        "Device-A",
                        "Device-D"
                )
        );

        neighbors.put(
                "Device-C",
                List.of(
                        "Device-A",
                        "Device-D"
                )
        );

        neighbors.put(
                "Device-D",
                List.of(
                        "Device-B",
                        "Device-C"
                )
        );
    }

    public List<String> getNeighbors(
            String deviceId) {

        return neighbors.getOrDefault(
                deviceId,
                Collections.emptyList()
        );
    }
}