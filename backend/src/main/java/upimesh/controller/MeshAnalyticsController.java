package upimesh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.service.MeshAnalyticsService;

@RestController
public class MeshAnalyticsController {

    private final MeshAnalyticsService analyticsService;

    public MeshAnalyticsController(
            MeshAnalyticsService analyticsService) {

        this.analyticsService = analyticsService;
    }

    @GetMapping("/api/analytics/mesh")
    public Map<String, Object> stats() {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "totalInjected",
                analyticsService.getTotalInjected()
        );

        response.put(
                "delivered",
                analyticsService.getDelivered()
        );

        response.put(
                "dropped",
                analyticsService.getDropped()
        );

        response.put(
                "successRate",
                analyticsService.getSuccessRate()
        );

        response.put(
                "averageHopCount",
                analyticsService.getAverageHopCount()
        );

        return response;
    }
}