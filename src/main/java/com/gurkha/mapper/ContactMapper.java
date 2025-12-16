package com.gurkha.mapper;

import com.gurkha.dtos.ContactDto;
import com.gurkha.entities.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public  static Contact toContact(ContactDto dto){
        Contact obj= new Contact();
        obj.setId(dto.getId());
        obj.setEmail(dto.getEmail());
        obj.setUserName(dto.getUserName());
        obj.setMessage(dto.getMessage());
        obj.setCompanyName(dto.getCompanyName());
        return  obj;
    }

    public  static ContactDto toDto(Contact contact){
        ContactDto obj= new ContactDto();
        obj.setId(contact.getId());
        obj.setUserName(contact.getUserName());
        obj.setEmail(contact.getEmail());
        obj.setMessage(contact.getMessage());
        obj.setCompanyName(contact.getCompanyName());
        return  obj;
    }
}
