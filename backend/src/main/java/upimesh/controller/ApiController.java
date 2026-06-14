package upimesh.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.model.TransferRequest;
import upimesh.service.SettlementService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final SettlementService settlementService;

    public ApiController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping("/send")
    public String sendMoney(
            @RequestBody TransferRequest request) {

        settlementService.transfer(
                request.getSenderVpa(),
                request.getReceiverVpa(),
                request.getAmount()
        );

        return "Transfer Successful";
    }
}