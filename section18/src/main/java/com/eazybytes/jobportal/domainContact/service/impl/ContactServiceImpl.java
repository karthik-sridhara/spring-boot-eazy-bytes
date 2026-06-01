package com.eazybytes.jobportal.domainContact.service.impl;

import com.eazybytes.jobportal.audit.AuditorAwareImpl;
import com.eazybytes.jobportal.constants.ApplicationConstants;
import com.eazybytes.jobportal.domainContact.service.IContactService;
import com.eazybytes.jobportal.dto.ContactRequestDto;
import com.eazybytes.jobportal.dto.ContactResponseDto;
import com.eazybytes.jobportal.entity.Contact;
import com.eazybytes.jobportal.exception.BusinessException;
import com.eazybytes.jobportal.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;
    private final AuditorAwareImpl  auditorAware;

    @Override
    @Transactional
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        boolean result = false;
        Contact contact = contactRepository.save(transformToEntity(contactRequestDto));
        if(contact != null && contact.getId() != null) {
            result = true;
        }
        return result;
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        contact.setStatus(ApplicationConstants.NEW_CONTACT_MESSAGE_STATUS);
        return contact;
    }

    @Override
    public List<ContactResponseDto> getContactMessages() {
        List<Contact> contacts = contactRepository.findAllByStatusOrderByCreatedAtDesc(ApplicationConstants.NEW_CONTACT_MESSAGE_STATUS);
        return contacts.stream().map((contact)->transformToDto(contact)).toList();
    }

    @Override
    public List<ContactResponseDto> getContactMessagesWithSort(
        String sortBy,
        String sortOrder
    ) {
        Sort sort;
        if(sortOrder.equalsIgnoreCase("asc")) {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        } else {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }
        List<Contact> contacts = contactRepository.findAllByStatus(ApplicationConstants.NEW_CONTACT_MESSAGE_STATUS,sort);
        return contacts.stream().map((contact)->transformToDto(contact)).toList();
    }

    @Override
    public Page<ContactResponseDto> getContactMessagesWithPage(
        Integer page,
        Integer size,
        String sortBy,
        String sortOrder
    ) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        Page<Contact> contactsPage = contactRepository.findAllByStatus(ApplicationConstants.NEW_CONTACT_MESSAGE_STATUS, PageRequest.of(page, size, sort));
        return contactsPage.map(contact -> transformToDto(contact));
    }

    private ContactResponseDto transformToDto(Contact contact) {
        return new ContactResponseDto(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getUserType(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getCreatedAt()
        );
    }

    @Override
    @Transactional
    public void closeContactMsg(Long contactId) {
//        Contact contact = contactRepository.findById(contactId)
//            .orElseThrow(
//                    () -> new BusinessException("INVALID_CONTACT_ID","Contact message not found with id: " + contactId, HttpStatus.BAD_REQUEST)
//            );
//        contact.setStatus(ApplicationConstants.CLOSE_CONTACT_MESSAGE_STATUS);
//        contactRepository.save(contact);
        int noOfRowsUpdated = contactRepository.updateStatusById(
                ApplicationConstants.CLOSE_CONTACT_MESSAGE_STATUS,
                contactId,
                auditorAware.getCurrentAuditor().orElse("Anonymous User")
        );
        if(noOfRowsUpdated != 1) {
            throw new BusinessException(
                    "INVALID_CONTACT_ID",
                    "Contact message not found with id: " + contactId,
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
