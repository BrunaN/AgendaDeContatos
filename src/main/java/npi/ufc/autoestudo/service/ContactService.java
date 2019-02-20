package npi.ufc.autoestudo.service;

import npi.ufc.autoestudo.model.Contact;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ContactService {

    public abstract void createContact(Contact contact);

    public abstract void updateContact(Long id, Contact contact);

    public abstract void deleteContact(Long id);

    public abstract Collection<Contact> getContacts();
}
