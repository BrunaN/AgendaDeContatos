package npi.ufc.autoestudo.controller;

import npi.ufc.autoestudo.model.Contact;
import npi.ufc.autoestudo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ContactServiceController {

    @Autowired
    ContactService contactService;

    @RequestMapping(value="/contacts")
    public ResponseEntity<Object> getContact(){
        return new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
    }

    @RequestMapping(value="/contacts", method = RequestMethod.POST)
    public ResponseEntity<Object> createContact(@RequestBody Contact contact){
        contactService.createContact(contact);

        return new ResponseEntity<>("contato criado com sucesso", HttpStatus.CREATED);
    }

    @RequestMapping(value="contact/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateContact(@PathVariable("id") Long id, @RequestBody Contact contact){
        contactService.updateContact(id, contact);

        return new ResponseEntity<>("contato atualizado com sucesso", HttpStatus.OK);
    }

    @RequestMapping(value="contact/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        System.out.println(id);
        contactService.deleteContact(id);

        return new ResponseEntity<>("contato foi deletado com sucesso", HttpStatus.OK);
    }
}
