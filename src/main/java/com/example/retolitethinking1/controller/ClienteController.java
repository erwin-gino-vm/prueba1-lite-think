package com.example.retolitethinking1.controller;

import com.example.retolitethinking1.model.ClienteModel;
import com.example.retolitethinking1.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<ClienteModel>> getAll(){
        return ResponseEntity.ok(clienteService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getById(@PathVariable Long id){
        Optional<ClienteModel> clienteData = clienteService.getClienteId(id);
        return clienteData.map(
                clienteModel -> new ResponseEntity<>(clienteModel, HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<ClienteModel> getByCedula(@PathVariable String cedula){
        Optional<ClienteModel> clienteData = clienteService.getClienteByCedulaCiudadania(cedula);
        return clienteData.map(
                clienteModel -> new ResponseEntity<>(clienteModel, HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/store")
    public ResponseEntity<ClienteModel> save(@RequestBody ClienteModel clienteModel) throws Exception{
        return new ResponseEntity<>(clienteService.save(clienteModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ClienteModel> update(@PathVariable("id") Long id, @RequestBody ClienteModel clienteModel) throws Exception{
        Optional<ClienteModel> clienteData = clienteService.getClienteId(id);
        if (clienteData.isPresent()) {
            clienteModel.setId(id);
            return new ResponseEntity<>(clienteService.save(clienteModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable("id") Long id) {
        Optional<ClienteModel> clienteData = clienteService.getClienteId(id);
        if (clienteData.isPresent()) {
            clienteService.delete(clienteData.get());
            return new ResponseEntity<>("Cliente eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
