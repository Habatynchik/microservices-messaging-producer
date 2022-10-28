package ua.epam.microservicesmessagingproducer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.epam.microservicesmessagingproducer.model.entity.User;
import ua.epam.microservicesmessagingproducer.service.ProducerService;

@RestController
@RequestMapping("/api/")
@Slf4j
public class ProducerController {
    private ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Value("${app.message}")
    private String response;

    @PostMapping("/produce")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        Long response2 = producerService.sendMessageAndReceive(user);
        log.info("user sent: " + user);
        log.info("responce " + response2);
        return ResponseEntity.ok(response);
    }
}
