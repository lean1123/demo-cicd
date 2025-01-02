package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.AddressRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.AddressResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Address;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import iuh.fit.dhktpm117ctt.group06.repository.AddressRepository;
import iuh.fit.dhktpm117ctt.group06.repository.UserRepository;
import iuh.fit.dhktpm117ctt.group06.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public Address mapToAddress(AddressRequest addressRequest) {
        return modelMapper.map(addressRequest, Address.class);
    }

    public AddressResponse mapToAddResponse(Address address) {
        return modelMapper.map(address, AddressResponse.class);
    }
    @Override
    public List<AddressResponse> findAllByUser(String userId) {
        List<Address> addresses = addressRepository.findAllByUser(userId);
        return addresses.stream().map(this::mapToAddResponse).toList();
    }

    @Override
    public Optional<AddressResponse> findById(String id) {
        Address address = addressRepository.findById(id).orElse(null);
        return Optional.of(mapToAddResponse(address));
    }

    @Override
    public void deleteById(String id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Optional<AddressResponse> save(String userId, AddressRequest addressRequest) {
        Address newAddress = mapToAddress(addressRequest);
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            newAddress.setUser(user);
            Address savedAddress = addressRepository.save(newAddress);
            return Optional.of(mapToAddResponse(savedAddress));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AddressResponse> update(String id, AddressRequest addressRequest) {
        Address address = addressRepository.findById(id).orElse(null);
        if(address != null){
            address.setCity(addressRequest.getCity());
            address.setDistrict(addressRequest.getDistrict());
            address.setWard(addressRequest.getWard());
            address.setStreet(addressRequest.getStreet());
            Address updatedAddress = addressRepository.save(address);
            return Optional.of(mapToAddResponse(updatedAddress));
        }
        return Optional.empty();
    }
}

