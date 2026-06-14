package upimesh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import upimesh.service.PacketRouteRegistry;

@RestController
@RequestMapping("/api/routes")
public class RouteTrackingController {

    private final PacketRouteRegistry
            routeRegistry;

    public RouteTrackingController(
            PacketRouteRegistry routeRegistry) {

        this.routeRegistry =
                routeRegistry;
    }

    @GetMapping
    public Map<String, List<String>>
    allRoutes() {

        return routeRegistry
                .getAllRoutes();
    }

    @GetMapping("/{packetId}")
    public List<String> route(
            @PathVariable String packetId) {

        return routeRegistry
                .getRoute(packetId);
    }
}