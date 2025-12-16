package com.gurkha.service;

import com.gurkha.dtos.ContactDto;

import java.util.List;

public interface ContactService {


    ContactDto createContact(ContactDto contactDto);

    ContactDto getContactById(Long id);

    List<ContactDto> getAllContacts();

    void deleteContact(Long id);
}
