package com.gurkha.controller;
import com.gurkha.dtos.ContactDto;
import com.gurkha.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {


    private  final ContactService contactService;


    @PostMapping
    public ResponseEntity<ContactDto> postContact (@RequestBody ContactDto contactDto){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(contactService.createContact(contactDto));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ContactDto> getContact(@PathVariable long id){
        return  ResponseEntity.status(HttpStatus.FOUND).body(contactService.getContactById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAll() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok("Deleted successfully");
    }

}
