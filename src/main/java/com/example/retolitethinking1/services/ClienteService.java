package com.example.retolitethinking1.services;

import com.example.retolitethinking1.model.ClienteModel;
import com.example.retolitethinking1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<ClienteModel> getAllClients(){
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> getClienteId(Long id){
        return clienteRepository.findById(id);
    }

    public Optional<ClienteModel> getClienteByCedulaCiudadania(String cedula){
        return clienteRepository.findByCedulaCiudadania(cedula);
    }

    public ClienteModel save(ClienteModel clienteModel) throws Exception{
        if(
                !clienteModel.getTipoDocumento().equals('P')  &&
                !clienteModel.getTipoDocumento().equals('C')
        ) {
            throw new Exception("El documento no es valido");
        }
        return clienteRepository.save(clienteModel);
    }

    public void delete(ClienteModel clienteModel) {
        clienteRepository.delete(clienteModel);
    }
}
