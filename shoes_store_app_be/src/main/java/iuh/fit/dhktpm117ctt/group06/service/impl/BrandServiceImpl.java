package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.cloudinary.CloudinaryProvider;
import iuh.fit.dhktpm117ctt.group06.dto.request.BrandRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.BrandResponse;
import iuh.fit.dhktpm117ctt.group06.dto.response.UserResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Brand;
import iuh.fit.dhktpm117ctt.group06.exception.AppException;
import iuh.fit.dhktpm117ctt.group06.exception.ErrorCode;
import iuh.fit.dhktpm117ctt.group06.repository.BrandRepository;
import iuh.fit.dhktpm117ctt.group06.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    private CloudinaryProvider cloudinaryProvider;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    private final ModelMapper modelMapper = new ModelMapper();

    public Brand mapToBrand(BrandRequest brandRequest) {
        return modelMapper.map(brandRequest, Brand.class);
    }

    public BrandResponse mapToBrandResponse(Brand brand) {
        return modelMapper.map(brand, BrandResponse.class);
    }

    @Override
    public Optional<BrandResponse> findById(String id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        return Optional.ofNullable(brand).map(this::mapToBrandResponse);
    }

    @Override
    public Optional<BrandResponse> save(BrandRequest brandRequest) {
        if (brandRequest.getAvatar() == null) {
            return Optional.empty();
        } else {
            try {
                Map uploadResult = cloudinaryProvider.upload(brandRequest.getAvatar(),"Brand", brandRequest.getBrandName());
                Brand brand = new Brand();
                brand.setAvatar(uploadResult.get("url").toString());
                brand.setBrandName(brandRequest.getBrandName());
                return Optional.of(mapToBrandResponse(brandRepository.save(brand)));
            } catch (Exception e) {
                throw new AppException(ErrorCode.AVATAR_INVALID);
            }
        }
    }

    @Override
    public Optional<BrandResponse> update(String id,BrandRequest brandRequest) {
        Optional<Brand> brand = brandRepository.findById(id);

        if (brand.isEmpty()) {
            return Optional.empty();
        }
        else {
            String avatar = brand.get().getAvatar();
            if (brandRequest.getAvatar() == null) {
                Brand updatedBrand = new Brand();
                updatedBrand.setId(brand.get().getId());
                updatedBrand.setAvatar(avatar);
                updatedBrand.setBrandName(brandRequest.getBrandName());
                return Optional.of(mapToBrandResponse(brandRepository.save(updatedBrand)));
            } else {
                try {
                    Map uploadResult = cloudinaryProvider.upload(brandRequest.getAvatar(),"Brand", brandRequest.getBrandName());
                    avatar = uploadResult.get("url").toString();
                } catch (Exception e) {
                    throw new AppException(ErrorCode.AVATAR_INVALID);
                }
                Brand updatedBrand = new Brand();
                updatedBrand.setId(brand.get().getId());
                updatedBrand.setAvatar(avatar);
                updatedBrand.setBrandName(brandRequest.getBrandName());
                return Optional.of(mapToBrandResponse(brandRepository.save(updatedBrand)));
            }
        }
    }


    @Override

    public void deleteById(String id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandResponse> findAll() {
        var brands = brandRepository.findAll();
        return brands.stream().map(this::mapToBrandResponse).toList();
    }

    @Override
    public List<BrandResponse> search(String keyword) {
        return brandRepository.search(keyword).stream().map(this::mapToBrandResponse).toList();
    }
}
