package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.controller.output.ProductOutput;
import com.nhattan.ecommerce.dto.ProductColorDTO;
import com.nhattan.ecommerce.dto.ProductDTO;
import com.nhattan.ecommerce.dto.ProductImageDTO;
import com.nhattan.ecommerce.dto.ProductSizeDTO;
import com.nhattan.ecommerce.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/public/product")
    public ResponseEntity<ProductOutput> showProductAvailable(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "productName", required = false, defaultValue = "") String productName) {
        ProductOutput result = new ProductOutput();
        result.setPage(page);
        if (productName.isBlank()) {
            result.setListResult(productService.findProductAvailable(page, limit, sort));
            result.setTotalPage((int) Math.ceil((double) (productService.totalAvailableItem()) / limit));
        } else {
            result.setListResult(productService.findProductAvailableByProductName(page, limit, sort, productName));
            result.setTotalPage((int) Math.ceil((double) (productService.totalAvailableByProductNameItem(productName)) / limit));
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/public/{subcategoryID}/product")
    public ResponseEntity<ProductOutput> showProductAvailableBySubcategory(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @PathVariable("subcategoryID") int subcategoryID) {
        ProductOutput result = new ProductOutput();
        result.setPage(page);
        result.setListResult(productService.findProductAvailableBySubcategoryID(page, limit, sort, subcategoryID));
        result.setTotalPage(
                (int) Math.ceil((double) (productService.totalAvailableItemBySubcategory(subcategoryID)) / limit));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/admin/product")
    public ResponseEntity<ProductOutput> showProduct(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                      @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
                                      @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                                      @RequestParam(name = "key", required = false, defaultValue = "all") String key) {
        ProductOutput result = new ProductOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        String showNotAvailableValue = "notavailable";
        if (key.equalsIgnoreCase(showNotAvailableValue)) {
            result.setListResult(productService.findProductNotAvailable(page, limit, sort));
            result.setTotalPage((int) Math.ceil((double) (productService.totalNotAvailableItem()) / limit));
        } else {
            result.setListResult(productService.findAll(pageable));
            result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/admin/product/{productID}")
    public ResponseEntity<ProductDTO> showOneProduct(@PathVariable int productID) {
        return ResponseEntity.ok().body(productService.findOneProduct(productID));
    }

    @GetMapping(value = "/public/product/{productID}")
    public ResponseEntity<ProductDTO> showOneProductAvailable(@PathVariable int productID) {
        return ResponseEntity.ok().body(productService.findOneProductAvailable(productID));
    }

    @PostMapping(value = "/admin/product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productRequest) {
        return ResponseEntity.ok().body(productService.saveProduct(productRequest));
    }

    @PostMapping(value = "/admin/product/image")
    public ResponseEntity<ProductImageDTO> createProductImage(@Valid @RequestBody ProductImageDTO productImageRequest) {
        return ResponseEntity.ok().body(productService.saveImage(productImageRequest));
    }

    @DeleteMapping(value = "/admin/product/image/{productImageID}")
    public ResponseEntity<String> invalidateProductImage(@PathVariable("productImageID") int productImageID) {
        return ResponseEntity.ok().body(productService.deleteImage(productImageID));
    }

    @PostMapping(value = "/admin/product/size")
    public ResponseEntity<ProductSizeDTO> createProductSize(@Valid @RequestBody ProductSizeDTO productSizeRequest) {
        return ResponseEntity.ok().body(productService.saveSize(productSizeRequest));
    }

    @DeleteMapping(value = "/admin/product/size/{productSizeID}")
    public ResponseEntity<String> invalidateProductSize(@PathVariable("productSizeID") int productSizeID) {
        return ResponseEntity.ok().body(productService.deleteSize(productSizeID));
    }

    @PostMapping(value = "/admin/product/color")
    public ResponseEntity<ProductColorDTO> createProductColor(@Valid @RequestBody ProductColorDTO productColorRequest) {
        return ResponseEntity.ok().body(productService.saveColor(productColorRequest));
    }

    @DeleteMapping(value = "/admin/product/color/{productColorID}")
    public ResponseEntity<String> invalidateProductColor(@PathVariable("productColorID") int productColorID) {
        return ResponseEntity.ok().body(productService.deleteColor(productColorID));
    }

    @PutMapping(value = "/admin/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productRequest, @PathVariable int id) {
        productRequest.setProductID(id);
        return ResponseEntity.ok().body(productService.updateProduct(productRequest));
    }

    @PatchMapping(value = "admin/product/{productID}/activity")
    public ResponseEntity<String> activityProduct(@PathVariable("productID") int productID) {
        return ResponseEntity.ok().body(productService.activityProduct(productID));
    }

    @DeleteMapping(value = "/admin/product/{id}")
    public ResponseEntity invalidateProduct(@PathVariable int id) {
        productService.invalidateProduct(id);
        return ResponseEntity.ok().build();
    }
}
