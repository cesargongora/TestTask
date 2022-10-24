package com.example.demo.controllers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitys.Gateway;
import com.example.demo.entitys.Peripheral;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.UnprocesableEntity;
import com.example.demo.repository.GatewayRepository;
import com.example.demo.repository.PeripheralRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class GatewayController {
    private final GatewayRepository repository;
    private final PeripheralRepository peripheralRepository;

    private static final String IPV4_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static boolean isValidInet4Address(String ip) {
        if (ip == null) {
            return false;
        }

        Matcher matcher = IPv4_PATTERN.matcher(ip);

        return matcher.matches();
    }

    GatewayController(GatewayRepository repository, PeripheralRepository peripheralRepository) {
        this.repository = repository;
        this.peripheralRepository = peripheralRepository;
    }
    @GetMapping("/gateway")
    List<Gateway> all() {
        return repository.findAll();
    }

    @GetMapping("/gateway/{id}")
    Gateway gatewayById(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Noy Found gateway whit id = " + id));
    }

    @PostMapping("/gateway")
    Gateway newGateway(@RequestBody Gateway newGateway) {
        if (!isValidInet4Address(newGateway.getIpaddr())) {
            throw new UnprocesableEntity("Invalid IP Address ");
        }
        if (newGateway.getPeripheral().size() > 10) {
            throw new UnprocesableEntity("Max 10 peripheral devices are allowed for a gateway");
        }
        Gateway gateway = repository.save(newGateway);

        for (Peripheral peripheral : newGateway.getPeripheral()) {
            peripheral.setGateway(newGateway);
            peripheralRepository.save(peripheral);
        }

        return gateway;
    }

    @PutMapping("/gateway/{id}")
    Gateway replaceGateway(@RequestBody Gateway newGateway, @PathVariable Long id) {

        return repository.findById(id)
                .map(gateway -> {
                    gateway.setName(newGateway.getName());
                    gateway.setSnumber(newGateway.getSnumber());
                    gateway.setIpaddr(newGateway.getIpaddr());
                    gateway.setPeripheral(newGateway.getPeripheral());
                    return repository.save(gateway);
                })
                .orElseGet(() -> {
                    newGateway.setId(id);
                    return repository.save(newGateway);
                });
    }

    @DeleteMapping("/gateway/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Noy Found gateway whit id = "+id));

        repository.deleteById(id);
    }
}
