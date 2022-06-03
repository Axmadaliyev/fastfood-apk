package com.example.clientservice.service;

import com.example.clientservice.dto.ApiResponse;
import com.example.clientservice.dto.CustomerDTO;
import com.example.clientservice.entity.Customers;
import com.example.clientservice.entity.enums.ClientStatus;
import com.example.clientservice.entity.enums.Language;
import com.example.clientservice.entity.enums.Region;
import com.example.clientservice.entity.enums.UserType;
import com.example.clientservice.repository.AttachmetPhoteRepository;
import com.example.clientservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AttachmetPhoteRepository attachmetRepository;
    private final CustomerRepository customerRepository;

    public ApiResponse save(CustomerDTO customerDTO){
        Customers customers=new Customers();
        customers.setName(customerDTO.getName());
        customers.setNumber(customerDTO.getPhonenumber());
        customers.setStatus(ClientStatus.ACTIVE);
        customers.setBirthdate(customerDTO.getBirthdate());
        customers.setRegion(Region.valueOf(customerDTO.getRegion()));
        customers.setLang(Language.valueOf(customerDTO.getLanguage()));
        customers.setUserType(UserType.valueOf(customerDTO.getUserType()));
        customers.setPhoto(attachmetRepository.getById(customerDTO.getPhotoId()));
        customerRepository.save(customers);
        return new ApiResponse("save",true);
    }

    public ApiResponse updete(Long id, CustomerDTO customerDTO){

        Optional<Customers> byId = customerRepository.findById(id);
        Customers customers1 = byId.get();
        customers1.setName(customerDTO.getName());
        customers1.setNumber(customerDTO.getPhonenumber());
        customers1.setStatus(ClientStatus.ACTIVE);
        customers1.setBirthdate(customerDTO.getBirthdate());
        customers1.setRegion(Region.valueOf(customerDTO.getRegion()));
        customers1.setLang(Language.valueOf(customerDTO.getLanguage()));
        customers1.setUserType(UserType.valueOf(customerDTO.getUserType()));
        customers1.setPhoto(attachmetRepository.getById(customerDTO.getPhotoId()));
        customerRepository.save(customers1);
        return new ApiResponse("updete",true);

    }
    public ApiResponse dalete(Long id){
        Optional<Customers> byId = customerRepository.findById(id);
        Customers customers = byId.get();
        customers.setStatus(ClientStatus.DELETED);
        customerRepository.save(customers);
        return new ApiResponse("dalete",true);
    }
    public ApiResponse blockedClient(Long id){

        Optional<Customers> byId = customerRepository.findById(id);
        Customers customers = byId.get();
        customers.setStatus(ClientStatus.BLOCK);
        customerRepository.save(customers);
        return new ApiResponse("blocked",true);

    }

}
