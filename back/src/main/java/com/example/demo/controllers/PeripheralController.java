package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitys.Peripheral;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.UnprocesableEntity;
import com.example.demo.repository.GatewayRepository;
import com.example.demo.repository.PeripheralRepository;
import com.example.demo.utils.Status;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PeripheralController {
    private final GatewayRepository gatewayRepository;
    private final PeripheralRepository peripheralRepository;

    PeripheralController(GatewayRepository gatewayRepository, PeripheralRepository peripheralRepository) {
        this.gatewayRepository = gatewayRepository;
        this.peripheralRepository = peripheralRepository;
    }

    @GetMapping("/peripheral")
    List<Peripheral> all() {
        return peripheralRepository.findAll();
    }

    @GetMapping("/peripheral/{id}")
    Peripheral peripheralById(@PathVariable Long id) {
      
      return peripheralRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Noy Found peripheral whit id = "+id));
    }
    
    @PostMapping("/peripheral")
    Peripheral newPeripheral(@RequestBody Peripheral newPeripheral) {
        if (newPeripheral.getStatus()!=Status.OFFLINE && newPeripheral.getStatus()!=Status.ONLINE) {
            throw new UnprocesableEntity("Status can only set to OFFLINE or ONLINE");
        }
        gatewayRepository.findById(newPeripheral.getGateway().getId())
        .orElseThrow(()->new ResourceNotFoundException("Noy Found gateway whit id = "+newPeripheral.getGateway().getId()));

        if(newPeripheral.getGateway().getPeripheral().size()>=10){
            throw new UnprocesableEntity("Max 10 peripheral devices are allowed for a gateway");
        }
        Peripheral peripheral=peripheralRepository.save(newPeripheral);
        
        return peripheral;
    }
    @PutMapping("/peripheral/{id}")
    Peripheral replaceGateway(@RequestBody Peripheral newPeripheral, @PathVariable Long id) {

        return peripheralRepository.findById(id)
                .map(peripheral -> {
                    peripheral.setStatus(newPeripheral.getStatus());
                    peripheral.setUid(newPeripheral.getUid());
                    peripheral.setVendor(newPeripheral.getVendor());
                    peripheral.setGateway(newPeripheral.getGateway());
                    return peripheralRepository.save(peripheral);
                })
                .orElseGet(() -> {
                    newPeripheral.setId(id);
                    return peripheralRepository.save(newPeripheral);
                });
    }

    @DeleteMapping("/peripheral/{id}")
    void deleteEmployee(@PathVariable Long id) {
        peripheralRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Noy Found peripheral whit id = "+id));

        peripheralRepository.deleteById(id);
    }
}
