package poc.parallelstream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import poc.parallelstream.service.ProcessorService;

@RestController
public class ProcessorController {

    private final ProcessorService service;

    public ProcessorController(ProcessorService service) {
        this.service = service;
    }

    @GetMapping("process")
    public String processTransaction(@RequestParam String filePath) {
        try {
            double total = service.processTransactions(filePath);
            return "Total sum of transactions greater than 10,000: " + total;
        } catch (Exception e) {
            return "Error processing transactions: " + e.getMessage();
        }
    }
}
