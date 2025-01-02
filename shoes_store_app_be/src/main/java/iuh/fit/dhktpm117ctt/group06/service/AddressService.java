package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.AddressRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.AddressResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<AddressResponse> findAllByUser(String userId);
    Optional<AddressResponse> findById(String id);
    void deleteById(String id);
    Optional<AddressResponse> save(String userId, AddressRequest addressRequest);
    Optional<AddressResponse> update(String id, AddressRequest addressRequest);
}
