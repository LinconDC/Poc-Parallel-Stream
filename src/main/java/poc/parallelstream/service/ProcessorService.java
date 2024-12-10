package poc.parallelstream.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProcessorService {

    public double processTransactions(String filePath) throws Exception {
        Path path = Paths.get(new ClassPathResource(filePath).getURI());

        return Files.lines(path)
                .parallel()
                .map(this::parseTransactionAmount)
                .filter(amount -> amount > 10000)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private Double parseTransactionAmount(String line) {
        try {
            String[] fields = line.split(",");
            return Double.parseDouble(fields[1]);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
