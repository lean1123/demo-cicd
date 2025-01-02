package iuh.fit.dhktpm117ctt.group06.controller;

import iuh.fit.dhktpm117ctt.group06.dto.request.AddressRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.AddressResponse;
import iuh.fit.dhktpm117ctt.group06.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Lấy tất cả địa chỉ của người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResponse>> getAllAddressesByUser(@PathVariable String userId) {
        List<AddressResponse> addresses = addressService.findAllByUser(userId);
        if (addresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // Lấy địa chỉ theo id
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable String id) {
        Optional<AddressResponse> addressResponse = addressService.findById(id);
        return addressResponse.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Lưu địa chỉ mới cho người dùng
    @PostMapping("/user/{userId}")
    public ResponseEntity<AddressResponse> createAddress(@PathVariable String userId,
                                                         @RequestBody AddressRequest addressRequest) {
        Optional<AddressResponse> addressResponse = addressService.save(userId, addressRequest);
        return addressResponse.map(response -> new ResponseEntity<>(response, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    // Cập nhật địa chỉ
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable String id,
                                                         @RequestBody AddressRequest addressRequest) {
        Optional<AddressResponse> updatedAddress = addressService.update(id, addressRequest);
        return updatedAddress.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Xóa địa chỉ
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable String id) {
        addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
