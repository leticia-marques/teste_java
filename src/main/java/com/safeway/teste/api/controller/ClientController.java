package com.safeway.teste.api.controller;

import com.safeway.teste.domain.dto.client.ClientInputDto;
import com.safeway.teste.domain.dto.client.ClientListDto;
import com.safeway.teste.domain.dto.client.ClientResponseDto;
import com.safeway.teste.domain.model.Client;
import com.safeway.teste.domain.repository.ClientRepository;
import com.safeway.teste.domain.service.ClientService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    private ClientRepository clientRepository;
    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    //TODO refactorar papara usar service

    @GetMapping("/lista")
    public List<Client> listAll() {
        return this.clientRepository.findAll();
    }

    @PostMapping
    public ClientResponseDto save(@RequestBody @Valid ClientInputDto clientDto) {
        return this.clientService.save(clientDto);
    }

    @GetMapping
    public Page<ClientListDto> search(Pageable page) {
        return this.clientService.search(page);
    }

    @GetMapping("/{clientId}")
    public ClientResponseDto getById(@PathVariable Long clientId) {
        return this.clientService.getById(clientId);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable Long clientId) {
        this.clientService.delete(clientId);
    }
}
