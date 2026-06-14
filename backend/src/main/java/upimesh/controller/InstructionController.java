package upimesh.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.model.PaymentInstruction;
import upimesh.model.TransferRequest;
import upimesh.util.InstructionFactory;

@RestController
@RequestMapping("/instruction")
public class InstructionController {

    @PostMapping
    public PaymentInstruction createInstruction(
            @RequestBody TransferRequest request) {

        return InstructionFactory.createInstruction(
                request.getSenderVpa(),
                request.getReceiverVpa(),
                request.getAmount(),
                "dummy-pin-hash"
        );
    }
}