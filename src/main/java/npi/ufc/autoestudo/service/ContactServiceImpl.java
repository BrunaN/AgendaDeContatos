package npi.ufc.autoestudo.service;

import npi.ufc.autoestudo.dao.ContactRepository;
import npi.ufc.autoestudo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void updateContact(Long id, Contact contact) {
        Contact contato = contactRepository.findOne(id);

        contato.setEmail(contact.getEmail());
        contato.setName(contact.getName());
        contato.setNumber(contact.getNumber());

        contactRepository.save(contato);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.delete(id);
    }

    @Override
    public Collection<Contact> getContacts() {
       return contactRepository.findAll();
    }
}
