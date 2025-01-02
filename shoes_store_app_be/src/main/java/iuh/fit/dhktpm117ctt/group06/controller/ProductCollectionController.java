package iuh.fit.dhktpm117ctt.group06.controller;

import iuh.fit.dhktpm117ctt.group06.dto.request.ProductCollectionRequest;
import iuh.fit.dhktpm117ctt.group06.service.ProductCollectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collections")
public class ProductCollectionController {
    @Autowired
    private ProductCollectionService productCollectionService;

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<?> findByBrand(@PathVariable String brandId) {
        return ResponseEntity.ok(productCollectionService.findByBrand(brandId));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody ProductCollectionRequest request) {
        return ResponseEntity.ok(productCollectionService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody ProductCollectionRequest request) {
        return ResponseEntity.ok(productCollectionService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(productCollectionService.findById(id));
    }

    @GetMapping("/{brandId}/search")
    public ResponseEntity<?> search(@RequestParam String keyword, @PathVariable String brandId) {
        return ResponseEntity.ok(productCollectionService.search(keyword, brandId));
    }


}
