package com.devsuperior.bds02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO eventDTO) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Resource no found");
		}
		Event event = repository.getReferenceById(id);
		event.setName(eventDTO.getName());
		event.setDate(eventDTO.getDate());
		event.setCity(new City(eventDTO.getCityId(), null));
		event.setUrl(eventDTO.getUrl());
		return new EventDTO(event);
	}
}
