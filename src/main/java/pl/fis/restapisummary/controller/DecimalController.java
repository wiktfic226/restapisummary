package pl.fis.restapisummary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fis.restapisummary.service.MessageService;


@RestController
@RequestMapping(path = "/api/decimal")
@RequiredArgsConstructor
@Slf4j
public class DecimalController {

    private final MessageService messageService;

    @PutMapping
    public ResponseEntity<Void> setDecimal(@RequestBody Integer decimal) {
        messageService.setDecimalPlaces(decimal);
        log.info("Set decimal places to {}", messageService.getDecimalPlaces());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Integer> getDecimal() {
        return ResponseEntity.ok().body(messageService.getDecimalPlaces());
    }
}
