package upimesh.service;

import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class PacketRouteRegistry {

    private final Map<String, List<String>>
            routes =
            new ConcurrentHashMap<>();

    public void saveRoute(
            String packetId,
            List<String> route) {

        routes.put(
                packetId,
                route
        );
    }

    public List<String> getRoute(
            String packetId) {

        return routes.get(
                packetId
        );
    }

    public Map<String, List<String>>
    getAllRoutes() {

        return routes;
    }
}