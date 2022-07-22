package pl.fis.restapisummary.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
@Data
public class MessageService {
    private Double valueOne;
    private Double valueTwo;
    private Integer decimalPlaces;
    private Integer multiplier;

    public MessageService() {
        this.decimalPlaces = 2;
        this.multiplier = 1;
    }

    private void calculateValues() {
        valueOne = BigDecimal.valueOf(Math.random() * multiplier).setScale(decimalPlaces, RoundingMode.HALF_UP).doubleValue();
        valueTwo = BigDecimal.valueOf(Math.random() * multiplier).setScale(decimalPlaces, RoundingMode.HALF_UP).doubleValue();
        log.info("valueOne: {}", valueOne);
        log.info("valueTwo: {}", valueTwo);
    }

    public MessageService getValues() {
        MessageService messageService = new MessageService();
        messageService.calculateValues();
        log.info("Returned values: {} and {} ", messageService.valueOne, messageService.valueTwo);
        return messageService;
    }
}
