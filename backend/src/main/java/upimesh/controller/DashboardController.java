package upimesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import upimesh.mesh.MeshSimulatorService;
import upimesh.repository.AccountRepository;
import upimesh.repository.TransactionRepository;
import upimesh.service.MeshAnalyticsService;
import upimesh.service.PacketRouteRegistry;

@Controller
public class DashboardController {

    private final AccountRepository accountRepository;

    private final TransactionRepository
            transactionRepository;

    private final MeshAnalyticsService
            analyticsService;

    private final PacketRouteRegistry
            routeRegistry;

    private final MeshSimulatorService
            meshSimulatorService;

    public DashboardController(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            MeshAnalyticsService analyticsService,
            PacketRouteRegistry routeRegistry,
            MeshSimulatorService meshSimulatorService) {

        this.accountRepository =
                accountRepository;

        this.transactionRepository =
                transactionRepository;

        this.analyticsService =
                analyticsService;

        this.routeRegistry =
                routeRegistry;

        this.meshSimulatorService =
                meshSimulatorService;
    }

    @GetMapping("/dashboard")
    public String dashboard(
            Model model) {

        long activeDevices =
                meshSimulatorService
                        .getDevices()
                        .stream()
                        .filter(
                                device -> device.isActive()
                        )
                        .count();

        long offlineDevices =
                meshSimulatorService
                        .getDevices()
                        .stream()
                        .filter(
                                device -> !device.isActive()
                        )
                        .count();

        long bridgeDevices =
                meshSimulatorService
                        .getDevices()
                        .stream()
                        .filter(
                                device -> device.isInternetAvailable()
                        )
                        .count();

        model.addAttribute(
                "accounts",
                accountRepository.findAll()
        );

        model.addAttribute(
                "transactions",
                transactionRepository.findAll()
        );

        model.addAttribute(
                "totalInjected",
                analyticsService
                        .getTotalInjected()
        );

        model.addAttribute(
                "delivered",
                analyticsService
                        .getDelivered()
        );

        model.addAttribute(
                "dropped",
                analyticsService
                        .getDropped()
        );

        model.addAttribute(
                "successRate",
                analyticsService
                        .getSuccessRate()
        );

        model.addAttribute(
                "averageHopCount",
                analyticsService
                        .getAverageHopCount()
        );

        model.addAttribute(
                "routes",
                routeRegistry
                        .getAllRoutes()
                        .entrySet()
        );

        model.addAttribute(
                "devices",
                meshSimulatorService
                        .getDevices()
        );

        model.addAttribute(
                "activeDevices",
                activeDevices
        );

        model.addAttribute(
                "offlineDevices",
                offlineDevices
        );

        model.addAttribute(
                "bridgeDevices",
                bridgeDevices
        );

        model.addAttribute(
                "totalAccounts",
                accountRepository.count()
        );

        model.addAttribute(
                "totalTransactions",
                transactionRepository.count()
        );

        return "dashboard";
    }
}