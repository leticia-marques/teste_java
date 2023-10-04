package com.safeway.teste.domain.service;

import com.safeway.teste.domain.dto.client.ClientInputDto;
import com.safeway.teste.domain.dto.client.ClientListDto;
import com.safeway.teste.domain.dto.client.ClientResponseDto;
import com.safeway.teste.domain.exception.ClientNotFoundException;
import com.safeway.teste.domain.model.Client;
import com.safeway.teste.domain.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientResponseDto save(ClientInputDto clientDto) {
        Client client = new Client(clientDto);
        client = this.clientRepository.save(client);
        return new ClientResponseDto(client);
    }

    public Page<ClientListDto> search(Pageable pageable) {
        Page<Client> page = this.clientRepository.findAll(pageable);
        return page.map(client -> new ClientListDto(client));
    }

    public ClientResponseDto getById(Long clientId) {
        Client client = this.getById(new Client(clientId));
        return new ClientResponseDto(client);
    }

    public Client getById(Client client) {
        return this.clientRepository.findById(client.getId()).orElseThrow(() ->
            new ClientNotFoundException(client.getId()));
    }


    @Transactional
    public void delete(Long clientId) {
        Client client = this.getById(new Client(clientId));
       this.clientRepository.delete(client);
    }
}
