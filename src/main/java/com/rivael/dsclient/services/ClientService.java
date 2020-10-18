package com.rivael.dsclient.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivael.dsclient.dto.ClientDTO;
import com.rivael.dsclient.entities.Client;
import com.rivael.dsclient.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll(); 
		return list.stream().map(entity -> new ClientDTO(entity)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> opEntity = repository.findById(id); 
		return new ClientDTO(opEntity.get());
	}
	
}
