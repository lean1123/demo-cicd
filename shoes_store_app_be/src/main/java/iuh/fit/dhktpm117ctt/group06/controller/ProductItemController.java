package iuh.fit.dhktpm117ctt.group06.controller;

import java.util.*;

import iuh.fit.dhktpm117ctt.group06.entities.Product;
import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;
import jakarta.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iuh.fit.dhktpm117ctt.group06.dto.request.ProductItemRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.ListProductItemsPagegination;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductItemResponse;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductResponse;
import iuh.fit.dhktpm117ctt.group06.exception.ErrorCode;
import iuh.fit.dhktpm117ctt.group06.service.ProductItemService;
import iuh.fit.dhktpm117ctt.group06.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product-items")
public class ProductItemController {
    private final static Logger logger = LoggerFactory.getLogger(ProductItemController.class.getName());

    @Autowired
    private ProductItemService productItemService;

	@Autowired
	private ProductService productService;

    @GetMapping("getListProductItems/{productId}")
    public ResponseEntity<?> getAllProductItems(@PathVariable String productId) {
        Map<String, Object> response = new LinkedHashMap();
        response.put("status", HttpStatus.OK.value());
        response.put("data", productItemService.findByProduct(productId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductItemById(@PathVariable String id, HttpSession httpSession) {
        Map<String, Object> response = new LinkedHashMap<>();

        Optional<ProductItem> productItemOptional = productItemService.findById(id);

        if (productItemOptional.isEmpty()) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("data", "Product item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ProductItem productItem = productItemOptional.get();

        saveProductToSession(httpSession, productItem);

        // Build the response DTO

        Product product = productItem.getProduct();

        List<ProductColor> colors = productService.getListColors(product.getId());
        List<String> sizes = productService.getListSizes(product.getId());

        ProductResponse productResponse = productService.getProductById(product.getId());

        productResponse.setColors(colors);
        productResponse.setSizes(sizes);

        ProductItemResponse itemResponse = ProductItemResponse.builder().id(productItem.getId()).price(productItem.getPrice()).quantity(productItem.getQuantity()).listDetailImages(productItem.getListDetailImages()).color(productItem.getColor().toString()).size(productItem.getSize()).product(productResponse).status(productItem.getStatus().name()).build();

        response.put("status", HttpStatus.OK.value());
        response.put("data", itemResponse);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addNewProductItem(@Valid @ModelAttribute ProductItemRequest productItemRequest,
			BindingResult bindingResult) {
		Map<String, Object> response = new LinkedHashMap<>();

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new LinkedHashMap<>();
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("errors", errors);
		}

		Optional<ProductItemResponse> productItemResponse = productItemService.save(productItemRequest);

		if (productItemResponse.isEmpty()) {
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("data", ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		response.put("status", HttpStatus.OK.value());
		response.put("data", productItemResponse.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateProductItem(@PathVariable String id,
			@Valid @ModelAttribute ProductItemRequest productItemRequest, BindingResult bindingResult) {
		Map<String, Object> response = new LinkedHashMap<>();

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new LinkedHashMap<>();
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("errors", errors);
		}

		Optional<ProductItemResponse> productItemResponse = productItemService.update(id, productItemRequest);

		if (productItemResponse.isEmpty()) {
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("data", ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		response.put("status", HttpStatus.OK.value());
		response.put("data", productItemResponse.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductItem(@PathVariable String id) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			productItemService.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("data", ErrorCode.PRODUCT_ITEM_EXISTED_IN_ORDER_DETAILS.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		response.put("status", HttpStatus.OK.value());
		response.put("data", "Product item deleted");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	 @GetMapping("/recent")
    public ResponseEntity<?> getRecentProducts(HttpSession httpSession) {
        Map<String, Object> response = new LinkedHashMap<>();
        List<ProductItem> products = (List<ProductItem>) httpSession.getAttribute("recentProducts");
        if (products == null) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("data", "No recent products");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        List<ProductItemResponse> productItemResponses = new ArrayList<>();

        for (ProductItem productItem : products) {
            Product product = productItem.getProduct();

            List<ProductColor> colors = productService.getListColors(product.getId());
            List<String> sizes = productService.getListSizes(product.getId());

            ProductResponse productResponse = productService.getProductById(product.getId());

            productResponse.setColors(colors);
            productResponse.setSizes(sizes);

            ProductItemResponse itemResponse = ProductItemResponse.builder().id(productItem.getId()).price(productItem.getPrice()).quantity(productItem.getQuantity()).listDetailImages(productItem.getListDetailImages()).color(productItem.getColor().toString()).size(productItem.getSize()).product(productResponse).status(productItem.getStatus().name()).build();

            productItemResponses.add(itemResponse);
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", productItemResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/top-sale")
    public ResponseEntity<?> getTopSaleProductItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = new LinkedHashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductItem> pageObject = productItemService.listTopSaleProductItems(pageable);
        List<ProductItem> productItems = pageObject.getContent();

        List<ProductItemResponse> productItemResponses = new ArrayList<>();

        for (ProductItem productItem : productItems) {
            Product product = productItem.getProduct();

            List<ProductColor> colors = productService.getListColors(product.getId());
            List<String> sizes = productService.getListSizes(product.getId());

            ProductResponse productResponse = productService.getProductById(product.getId());

            productResponse.setColors(colors);
            productResponse.setSizes(sizes);

            ProductItemResponse itemResponse = ProductItemResponse.builder().id(productItem.getId()).price(productItem.getPrice()).quantity(productItem.getQuantity()).listDetailImages(productItem.getListDetailImages()).color(productItem.getColor().toString()).size(productItem.getSize()).product(productResponse).status(productItem.getStatus().name()).build();

            productItemResponses.add(itemResponse);
        }

        ListProductItemsPagegination listProductItemsPagegination = ListProductItemsPagegination.builder().currentPage(pageObject.getPageable().getPageNumber()).totalPage(pageObject.getTotalPages()).totalItems(pageObject.getTotalElements()).pageSize(pageable.getPageSize()).data(productItemResponses).build();

        response.put("status", HttpStatus.OK.value());
        response.put("data", listProductItemsPagegination);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/new")
    public ResponseEntity<?> getNewProductItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = new LinkedHashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductItem> pageObject = productItemService.listNewProductItems(pageable);
        List<ProductItem> productItems = pageObject.getContent();

        List<ProductItemResponse> productItemResponses = new ArrayList<>();

        for (ProductItem productItem : productItems) {
            Product product = productItem.getProduct();

            List<ProductColor> colors = productService.getListColors(product.getId());
            List<String> sizes = productService.getListSizes(product.getId());

            ProductResponse productResponse = productService.getProductById(product.getId());

            productResponse.setColors(colors);
            productResponse.setSizes(sizes);

            ProductItemResponse itemResponse = ProductItemResponse.builder().id(productItem.getId()).price(productItem.getPrice()).quantity(productItem.getQuantity()).listDetailImages(productItem.getListDetailImages()).color(productItem.getColor().toString()).size(productItem.getSize()).product(productResponse).status(productItem.getStatus().name()).build();

            productItemResponses.add(itemResponse);
        }

        ListProductItemsPagegination listProductItemsPagegination = ListProductItemsPagegination.builder().currentPage(pageObject.getPageable().getPageNumber()).totalPage(pageObject.getTotalPages()).totalItems(pageObject.getTotalElements()).pageSize(pageable.getPageSize()).data(productItemResponses).build();

        response.put("status", HttpStatus.OK.value());
        response.put("data", listProductItemsPagegination);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-by-color-and-size")
    public ResponseEntity<?> getProductItemByColorAndSize(@RequestParam String productId, @RequestParam String color, @RequestParam String size) {
        Map<String, Object> response = new LinkedHashMap<>();

        Optional<ProductItem> productItemOptional = productItemService.findByProductAndSizeAndColor(productId, size, ProductColor.valueOf(color));
        if (productItemOptional.isEmpty()) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("data", "Product item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ProductItem productItem = productItemOptional.get();
        Product product = productItem.getProduct();

        List<ProductColor> colors = productService.getListColors(product.getId());
        List<String> sizes = productService.getListSizes(product.getId());

        ProductResponse productResponse = productService.getProductById(product.getId());

        productResponse.setColors(colors);
        productResponse.setSizes(sizes);

        ProductItemResponse itemResponse = ProductItemResponse.builder().id(productItem.getId()).price(productItem.getPrice()).quantity(productItem.getQuantity()).listDetailImages(productItem.getListDetailImages()).color(productItem.getColor().toString()).size(productItem.getSize()).product(productResponse).status(productItem.getStatus().name()).build();

        response.put("status", HttpStatus.OK.value());
        response.put("data", itemResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void saveProductToSession(HttpSession httpSession, ProductItem product) {
        List<ProductItem> products = (List<ProductItem>) httpSession.getAttribute("recentProducts");
        if (products == null) {
            products = new ArrayList<>();
            products.add(product);
        } else {

            for (ProductItem p : products) {
                if (p.getId().equals(product.getId())) {
                    return;
                }
            }
            products.add(product);
        }
        httpSession.setAttribute("recentProducts", products);

    }

    @GetMapping("/colors")
    public ResponseEntity<?> getDistinctColorsByProductId(@RequestParam String productId) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("data", productItemService.findDistinctColorsByProductId(productId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sizes")
    public ResponseEntity<?> getDistinctSizesByProductId(@RequestParam String productId) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("data", productItemService.findDistinctSizesByProductId(productId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(defaultValue = "") String color,
                                    @RequestParam(defaultValue = "") String size,
                                    @RequestParam(defaultValue = "0.0") double minPrice,
                                    @RequestParam(defaultValue = "0.0") double maxPrice,
                                     @RequestParam(defaultValue = "") String productName) {
        Map<String, Object> response = new LinkedHashMap<>();
        double maxPriceDouble = maxPrice == 0.0 ? Double.MAX_VALUE : maxPrice;
        ProductColor colorEnum = color.isEmpty() ? null : ProductColor.valueOf(color.toUpperCase());

        size = size.isEmpty() ? null : size;

        logger.info("color: " + color + " size: " + size + " minPrice: " + minPrice + " maxPrice: " + maxPriceDouble + " productName: " + productName);

        List<ProductItem> productItems = productItemService.searchProductItemsByColorOrAndSizeOrPriceBetweenOrProductName(
                colorEnum, size, minPrice, maxPriceDouble, productName);

        List<ProductItemResponse> productItemResponses = new ArrayList<>();

        for (ProductItem productItem : productItems) {
            Product product = productItem.getProduct();

            List<ProductColor> colors = productService.getListColors(product.getId());
            List<String> sizes = productService.getListSizes(product.getId());

            ProductResponse productResponse = productService.getProductById(product.getId());

            productResponse.setColors(colors);
            productResponse.setSizes(sizes);

            ProductItemResponse itemResponse = ProductItemResponse.builder().id(productItem.getId())
                    .price(productItem.getPrice()).quantity(productItem.getQuantity())
                    .listDetailImages(productItem.getListDetailImages()).color(productItem.getColor().toString())
                    .size(productItem.getSize()).product(productResponse).status(productItem.getStatus().name())
                    .build();

            productItemResponses.add(itemResponse);
        }



        response.put("status", HttpStatus.OK.value());
        response.put("data", productItemResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
