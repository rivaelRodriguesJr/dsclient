package com.rivael.dsclient.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivael.dsclient.dto.ClientDTO;
import com.rivael.dsclient.entities.Client;
import com.rivael.dsclient.repositories.ClientRepository;
import com.rivael.dsclient.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> page = repository.findAll(pageRequest); 
		return page.map(entity -> new ClientDTO(entity));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
		return new ClientDTO(entity);
	}
	
}
