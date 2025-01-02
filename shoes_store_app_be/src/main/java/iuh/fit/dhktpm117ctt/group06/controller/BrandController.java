package iuh.fit.dhktpm117ctt.group06.controller;

import iuh.fit.dhktpm117ctt.group06.dto.request.BrandRequest;
import iuh.fit.dhktpm117ctt.group06.service.BrandService;
import iuh.fit.dhktpm117ctt.group06.service.LoggerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private LoggerService loggerService;

    @GetMapping
    public ResponseEntity<?> getAllBrands() {
        loggerService.logInfo("Get all brands");
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable String id) {
        return ResponseEntity.ok(brandService.findById(id).get());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveBrand(@Valid @ModelAttribute BrandRequest brandRequest) {
        return ResponseEntity.ok(brandService.save(brandRequest).get());
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBrand(@PathVariable String id, @Valid @ModelAttribute BrandRequest brandRequest) {
        return ResponseEntity.ok(brandService.update(id, brandRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable String id) {
        brandService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBrand(@RequestParam String keyword) {
        return ResponseEntity.ok(brandService.search(keyword));
    }
}
