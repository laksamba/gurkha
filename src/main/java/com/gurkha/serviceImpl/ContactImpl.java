package com.gurkha.serviceImpl;

import com.gurkha.dtos.ContactDto;
import com.gurkha.entities.Contact;
import com.gurkha.exception.ResourceNotFoundException;
import com.gurkha.mapper.ContactMapper;
import com.gurkha.repository.ContactRepo;
import com.gurkha.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactImpl  implements ContactService {

    private  final ContactRepo contactRepo;

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        Contact contact= ContactMapper.toContact(contactDto);
         return ContactMapper.toDto(contactRepo.save(contact));
    }

    @Override
    public ContactDto getContactById(Long id) {
         Contact contact = contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("resource not found with id:"+id));
         return  ContactMapper.toDto(contact);
    }

    @Override
    public List<ContactDto> getAllContacts() {
      return   contactRepo.findAll().stream().map(ContactMapper::toDto).toList();
    }

    @Override
    public void deleteContact(Long id) {
   Contact contact   =   contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("not match  of id to delete"+1));
   contactRepo.delete(contact);

    }
}
