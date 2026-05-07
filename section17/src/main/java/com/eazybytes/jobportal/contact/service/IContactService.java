package com.eazybytes.jobportal.contact.service;

import com.eazybytes.jobportal.dto.ContactRequestDto;
import com.eazybytes.jobportal.dto.ContactResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IContactService {

    boolean saveContact(ContactRequestDto contactRequestDto);

    List<ContactResponseDto> getContactMessages();

    List<ContactResponseDto> getContactMessagesWithSort(String sortBy, String sortOrder);

    Page<ContactResponseDto> getContactMessagesWithPage(Integer page, Integer size, String sortBy, String sortOrder);

    void closeContactMsg(Long contactId);
}
