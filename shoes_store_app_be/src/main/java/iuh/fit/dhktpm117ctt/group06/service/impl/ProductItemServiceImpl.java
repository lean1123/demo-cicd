package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.cloudinary.CloudinaryProvider;
import iuh.fit.dhktpm117ctt.group06.dto.request.ProductItemRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductItemResponse;
import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;
import iuh.fit.dhktpm117ctt.group06.exception.AppException;
import iuh.fit.dhktpm117ctt.group06.exception.ErrorCode;
import iuh.fit.dhktpm117ctt.group06.repository.ProductItemRepository;
import iuh.fit.dhktpm117ctt.group06.service.ProductItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductItemServiceImpl implements ProductItemService {

	@Autowired
	private ProductItemRepository productItemRepository;
	@Autowired
	private CloudinaryProvider cloudinaryProvider;
	private ModelMapper modelMapper = new ModelMapper();

	private ProductItemResponse mapToProductItemResponse(ProductItem productItem) {
		return modelMapper.map(productItem, ProductItemResponse.class);
	}

	private ProductItem mapToProductItem(ProductItemRequest productItemRequest) {
		return modelMapper.map(productItemRequest, ProductItem.class);
	}

	@Transactional
	@Override
	public Optional<ProductItemResponse> save(ProductItemRequest productItemRequest) {
		ProductItem productItem = mapToProductItem(productItemRequest);
		if (productItemRequest.getListDetailImages() != null) {
			try {
				List<Map> uploadResult = cloudinaryProvider.uploadFiles(productItemRequest.getListDetailImages(),
						"Product-Item", productItem.getProduct().getId());
				List<String> listDetailImages = new ArrayList<>();
				for (Map map : uploadResult) {
					listDetailImages.add(map.get("url").toString());
				}
				productItem.setListDetailImages(listDetailImages);
				// return
				// Optional.of(mapToProductItemResponse(productItemRepository.save(productItem)));
			} catch (Exception e) {
				throw new AppException(ErrorCode.AVATAR_INVALID);
			}
		}
		return Optional.of(mapToProductItemResponse(productItemRepository.save(productItem)));
	}

	@Transactional
	@Override
	public Optional<ProductItemResponse> update(String id, ProductItemRequest productItemRequest) {
		Optional<ProductItem> optionalProductItem = productItemRepository.findById(id);
		if (optionalProductItem.isPresent()) {
			ProductItem productItem = optionalProductItem.get();
			productItem.setPrice(productItemRequest.getPrice());
			productItem.setQuantity(productItemRequest.getQuantity());
			productItem.setColor(ProductColor.valueOf(productItemRequest.getColor()));
			productItem.setSize(productItemRequest.getSize());

			var files = productItemRequest.getListDetailImages();

			if (files == null || files.length <= 0) {
				productItem.setListDetailImages(productItem.getListDetailImages());
			} else {
				List<MultipartFile> nonEmptyFiles = new ArrayList<>();
				for (MultipartFile file : files) {
					if (!file.isEmpty()) {
						nonEmptyFiles.add(file);
					}
				}

				if (nonEmptyFiles.size() == 0) {
					productItem.setListDetailImages(productItem.getListDetailImages());
					return Optional.of(mapToProductItemResponse(productItemRepository.save(productItem)));
				}

				try {
					List<Map> uploadResult = cloudinaryProvider.uploadFiles(nonEmptyFiles.toArray(new MultipartFile[0]),
							"Product-Item", productItem.getId());
					List<String> listDetailImages = new ArrayList<>();
					for (Map map : uploadResult) {
						listDetailImages.add(map.get("url").toString());
					}
					productItem.setListDetailImages(listDetailImages);
					return Optional.of(mapToProductItemResponse(productItemRepository.save(productItem)));
				} catch (Exception e) {
					throw new AppException(ErrorCode.AVATAR_INVALID);
				}
			}

			return Optional.of(mapToProductItemResponse(productItemRepository.save(productItem)));
		}
		return Optional.empty();

	}

	@Override
	@Transactional
	public Optional<ProductItem> updateQuantity(String id, int quantity) {
		Optional<ProductItem> optionalProductItem = productItemRepository.findById(id);
		if (optionalProductItem.isPresent()) {
			ProductItem productItem = optionalProductItem.get();
			if (quantity != 0) {
				System.out.println("Quantity: " + quantity);
				productItem.setQuantity(quantity);
				return Optional.of(productItemRepository.save(productItem));
			} else {
				throw new AppException(ErrorCode.QTY_INVALID);
			}

		}
		return Optional.empty();
	}

	@Transactional
	@Override
	public void deleteById(String productItemId) {

		if (productItemRepository.exexistedByOrders(productItemId).size() > 0) {
			throw new AppException(ErrorCode.PRODUCT_ITEM_EXISTED_IN_ORDER_DETAILS);
		}

		productItemRepository.deleteById(productItemId);
	}

	@Override
	public Optional<ProductItemResponse> updateDetailImage(String productItemId, MultipartFile[] newDetailImages) {
		return Optional.empty();
	}

	@Override
	public List<ProductItemResponse> findByProduct(String productId) {
		List<ProductItem> productItems = productItemRepository.findByProduct(productId);
		return productItems.stream().map(this::mapToProductItemResponse).toList();
	}

	@Override
	public Optional<ProductItemResponse> findByColorAndSize(String color, String size, String productId) {
		return productItemRepository.findByColorAndSizeAndProductId(color, size, productId)
				.map(this::mapToProductItemResponse);
	}

	@Override
	public List<ProductColor> findDistinctColorsByProductId(String productId) {
		return productItemRepository.findDistinctColorsByProductId(productId);
	}

	@Override
	public List<String> findDistinctSizesByProductId(String productId) {
		return productItemRepository.findDistinctSizesByProductId(productId);
	}

	@Override
	public List<ProductItem> findAll() {
		return productItemRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<ProductItem> findById(String id) {
		return productItemRepository.findById(id);
	}
	
	@Override
	public Page<ProductItem> listTopSaleProductItems(Pageable pageable){
		return productItemRepository.listTopSaleProductItems(pageable);
	}

	@Override
	public Optional<ProductItem> findByProductAndSizeAndColor(String productId, String size, ProductColor color) {
		return productItemRepository.findByProductAndSizeAndColor(productId, color, size);
	}


	@Override
	public Page<ProductItem> listNewProductItems(Pageable pageable) {
		return productItemRepository.listNewProductItems(pageable);
	}



	@Override
	public List<ProductItem> searchProductItemsByColorOrAndSizeOrPriceBetweenOrProductName(ProductColor color, String size, double minPrice, double maxPrice, String productName) {
		return productItemRepository.searchProductItemsByFilters(color , size, minPrice, maxPrice, productName);
	}
}
