package pl.fis.restapisummary.controller.multiplier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.fis.restapisummary.service.message.MessageService;

@RestController
@RequestMapping(path = "/api/multiplier")
@RequiredArgsConstructor
@Slf4j
public class MultiplierController {

    private final MessageService messageService;
    @PutMapping
    public ResponseEntity<Void> setMultiplier(@RequestBody Integer multiplier) {
        messageService.setMultiplier(multiplier);
        log.info("Set multiplier value to {}", messageService.getMultiplier());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('MULTIPLIER_READ', 'ACCESS_ALL')")
    public ResponseEntity<Integer> getMultiplier() {
        return ResponseEntity.ok().body(messageService.getMultiplier());
    }
}
