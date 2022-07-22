package pl.fis.restapisummary.controller.number;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fis.restapisummary.service.message.MessageService;

@RestController
@RequestMapping(path = "/api/number")
@RequiredArgsConstructor
public class NumberController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<Values> getCalculatedValues() {
        MessageService messageServiceRecalculated = messageService.getValues();
        Values values = new Values(messageServiceRecalculated.getValueOne(), messageServiceRecalculated.getValueTwo());
        return ResponseEntity.ok().body(values);
    }

    @Data
    @AllArgsConstructor
    public class Values {
        private Double valueOne;
        private Double valueTwo;
    }
}
