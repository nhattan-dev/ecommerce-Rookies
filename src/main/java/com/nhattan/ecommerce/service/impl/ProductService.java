package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.ProductColorDTO;
import com.nhattan.ecommerce.dto.ProductDTO;
import com.nhattan.ecommerce.dto.ProductImageDTO;
import com.nhattan.ecommerce.dto.ProductSizeDTO;
import com.nhattan.ecommerce.entity.*;
import com.nhattan.ecommerce.enums.ORDER_BY;
import com.nhattan.ecommerce.exception.BadRequestException;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.*;
import com.nhattan.ecommerce.service.IProductService;
import com.nhattan.ecommerce.util.DriveAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductImageRepository productImageRepository;

    @Autowired
    private IProductPriceRepository productPriceRepository;

    @Autowired
    private IProductSizeRepository productSizeRepository;

    @Autowired
    private IProductColorRepository productColorRepository;

    @Autowired
    private ISubcategoryRepository subcategoryRepository;

    @Value("${upload.path}")
    private String fileUpload;

    //NOT YET, NO SIZE => NEW FREE SIZE
    @Transactional
    @Override
    public ProductDTO saveProduct(ProductDTO dto) {
        if (!subcategoryRepository.existsById(dto.getSubcategoryID()))
            throw new NotFoundException("subcategory-not-found");

        ProductEntity newProduct = ProductDTO.toEntity(dto);
        int defaultID = 0;
        newProduct.setProductID(defaultID);
        ProductEntity product = productRepository.save(newProduct);
        dto.getProductImages().forEach(x -> {
            ProductImageEntity newProductImage = ProductImageDTO.toEntity(x);
            newProductImage.setImagePath(uploadImage(x.getImagePath()));
            newProductImage.setProduct(product);
            productImageRepository.save(newProductImage);
        });

        dto.getProductColors().forEach(x -> {
            ProductColorEntity newProductColor = ProductColorDTO.toEntity(x);
            newProductColor.setProduct(product);
            productColorRepository.save(newProductColor);
        });

        dto.getProductSizes().forEach(x -> {
            ProductSizeEntity newProductSize = ProductSizeDTO.toEntity(x);
            newProductSize.setProduct(product);
            productSizeRepository.save(newProductSize);
        });

        ProductPriceEntity newProductPrice = new ProductPriceEntity();
        newProductPrice.setPrice(dto.getPrice());
        newProductPrice.setProduct(product);
        productPriceRepository.save(newProductPrice);

        ProductDTO result = ProductDTO.toDTO(product);
        result.setPrice(productPriceRepository.getLatestPriceByProductID(result.getProductID()));
        return result;
    }

    private List<ProductImageEntity> toImages(Set<ProductImageDTO> dtos, int productID) {
        ProductEntity product = new ProductEntity();
        product.setProductID(productID);
        return dtos.stream().map(x -> {
            ProductImageEntity image = ProductImageDTO.toEntity(x);
            int defaultIDValue = 0;
            image.setProductImageID(defaultIDValue);
            return image;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void invalidateProduct(Integer productID) {
        if (!productRepository.existsById(productID))
            throw new NotFoundException("product-not-found");
        productRepository.deleteByProductID(productID);
    }

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> result = productRepository.findAll(pageable).getContent().stream()
                .map(x -> {
                    ProductDTO dto = ProductDTO.toDTO(x);
                    dto.setPrice(productPriceRepository.getLatestPriceByProductID(x.getProductID()));
                    return dto;
                }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ProductDTO> findProductNotAvailable(int page, int limit, String sort) {
        int begin, end;
        begin = (page - 1) * limit + 1;
        end = page * limit;
        List<ProductEntity> result = null;
        if (sort.equalsIgnoreCase(ORDER_BY.ASC.toString()))
            result = productRepository.findProductNotAvailableOrderByASC(begin, end);
        else if (sort.equalsIgnoreCase(ORDER_BY.DESC.toString()))
            result = productRepository.findProductNotAvailableOrderByDESC(begin, end);
        else
            throw new NotFoundException("sort-not-found");
        return result.stream().map(x -> {
            ProductDTO dto = ProductDTO.toDTO(x);
            dto.setPrice(productPriceRepository.getLatestPriceByProductID(x.getProductID()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductAvailable(int page, int limit, String sort) {
        int begin, end;
        begin = (page - 1) * limit + 1;
        end = page * limit;
        List<ProductEntity> result = null;
        System.out.println(begin + "\t" + end);
        if (sort.equalsIgnoreCase(ORDER_BY.ASC.toString()))
            result = productRepository.findProductAvailableOrderByASC(begin, end);
        else if (sort.equalsIgnoreCase(ORDER_BY.DESC.toString()))
            result = productRepository.findProductAvailableOrderByDESC(begin, end);
        else
            throw new NotFoundException("sort-not-found");
        return result.stream().map(x -> {
            ProductDTO dto = ProductDTO.toDTO(x);
            dto.setPrice(productPriceRepository.getLatestPriceByProductID(x.getProductID()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductAvailableByProductName(int page, int limit, String sort, String productName) {
//        productName = "'%" + productName + "%'";
        int begin, end;
        begin = (page - 1) * limit + 1;
        end = page * limit;
        List<ProductEntity> result = null;
        System.out.println(begin + productName + end);
        if (sort.equalsIgnoreCase(ORDER_BY.ASC.toString()))
            result = productRepository.findProductAvailableByProductNameOrderByASC(begin, end, productName);
        else if (sort.equalsIgnoreCase(ORDER_BY.DESC.toString()))
            result = productRepository.findProductAvailableByProductNameOrderByDESC(begin, end, productName);
        else
            throw new NotFoundException("sort-not-found");
        System.out.println(result.size());
        return result.stream().map(x -> {
            ProductDTO dto = ProductDTO.toDTO(x);
            dto.setPrice(productPriceRepository.getLatestPriceByProductID(x.getProductID()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductAvailableBySubcategoryID(int page, int limit, String sort, int subcategoryID) {
        int begin, end;
        begin = (page - 1) * limit + 1;
        end = page * limit;
        List<ProductEntity> result = null;
        if (sort.equalsIgnoreCase(ORDER_BY.ASC.toString()))
            result = productRepository.findProductAvailableBySubcategoryOrderByASC(begin, end, subcategoryID);
        else if (sort.equalsIgnoreCase(ORDER_BY.DESC.toString()))
            result = productRepository.findProductAvailableBySubcategoryOrderByDESC(begin, end, subcategoryID);
        else
            throw new NotFoundException("sort-not-found");
        return result.stream().map(x -> {
            ProductDTO dto = ProductDTO.toDTO(x);
            dto.setPrice(productPriceRepository.getLatestPriceByProductID(x.getProductID()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public int totalItem() {
        return (int) productRepository.count();
    }

    @Override
    public int totalAvailableByProductNameItem(String productName) {
        productName = "'%" + productName + "%'";
        return productRepository.countProductAvailableByProductName(productName);
    }

    @Override
    public int totalAvailableItem() {
        return productRepository.countProductAvailable();
    }

    @Override
    public int totalNotAvailableItem() {
        return productRepository.countProductNotAvailable();
    }

    @Override
    public int totalAvailableItemBySubcategory(int subcategoryID) {
        return productRepository.countProductAvailableBySubcategory(subcategoryID);
    }

    //CHƯA XONG
    @Transactional
    @Override
    public ProductDTO updateProduct(ProductDTO dto) {
        if (!productRepository.existsById(dto.getProductID()))
            throw new NotFoundException("product-not-found");
        if (!subcategoryRepository.existsById(dto.getSubcategoryID()))
            throw new NotFoundException("subcategory-not-found");
        productRepository.updateByProductID(dto.getName(), dto.getDescription(),
                dto.getSubcategoryID(), dto.getQuantity(), dto.getProductID());
        if (productPriceRepository.getLatestPriceByProductID(dto.getProductID()) != dto
                .getPrice()) {
            ProductPriceEntity newPrice = new ProductPriceEntity();
            newPrice.setPrice(dto.getPrice());
            ProductEntity product = new ProductEntity();
            product.setProductID(dto.getProductID());
            newPrice.setProduct(product);
            productPriceRepository.save(newPrice);
        }
        ProductDTO result = ProductDTO.toDTO(productRepository.findById(dto.getProductID()).get());
        result.setPrice(productPriceRepository.getLatestPriceByProductID(dto.getProductID()));
        return result;
    }

    @Override
    public ProductDTO findOneProduct(int productID) {
        ProductDTO product = ProductDTO.toDTO(productRepository.findById(productID).orElseThrow(
                () -> new NotFoundException("product-not-found")));
        product.setPrice(productPriceRepository.getLatestPriceByProductID(productID));
        return product;
    }

    @Override
    public ProductDTO findOneProductAvailable(int productID) {
        ProductEntity product = productRepository.findOneAvailable(productID)
                .orElseThrow(() -> new NotFoundException("product-not-found"));
        ProductDTO productDTO = ProductDTO.toDTO(product);
        productDTO.setPrice(productPriceRepository.getLatestPriceByProductID(productID));
        return productDTO;
    }

    @Transactional
    @Override
    public ProductColorDTO saveColor(ProductColorDTO dto) {
        if(productRepository.existsById(dto.getProductID()))
            throw new NotFoundException("product-not-found");
        if (productColorRepository.existsByColorAndProduct_ProductID(dto.getColor(), dto.getProductID()))
            throw new ConflictException("product-already-has-color");
        ProductColorEntity newColor = ProductColorDTO.toEntity(dto);
        int defaultID = 0;
        newColor.setProductColorID(defaultID);
        return ProductColorDTO.toDTO(productColorRepository.save(newColor));
    }

    @Transactional
    @Override
    public ProductImageDTO saveImage(ProductImageDTO dto) {
        if(productRepository.existsById(dto.getProductID()))
            throw new NotFoundException("product-not-found");
        if (productImageRepository.existsByImagePathAndProduct_ProductID(dto.getImagePath(), dto.getProductID()))
            throw new ConflictException("product-already-has-image");
        ProductImageEntity newImage = ProductImageDTO.toEntity(dto);
        int defaultID = 0;
        newImage.setProductImageID(defaultID);
        return ProductImageDTO.toDTO(productImageRepository.save(newImage));
    }

    @Transactional
    @Override
    public ProductSizeDTO saveSize(ProductSizeDTO dto) {
        if(productRepository.existsById(dto.getProductID()))
            throw new NotFoundException("product-not-found");
        if (productSizeRepository.existsBySizeAndProduct_ProductID(dto.getSize(), dto.getProductID()))
            throw new ConflictException("product-already-has-size");
        ProductSizeEntity newSize = ProductSizeDTO.toEntity(dto);
        int defaultID = 0;
        newSize.setProductSizeID(defaultID);
        return ProductSizeDTO.toDTO(productSizeRepository.save(newSize));
    }

    @Transactional
    @Override
    public String deleteColor(int productColorID) {
        if (!productColorRepository.existsById(productColorID))
            throw new NotFoundException("color-not-found");
        productColorRepository.deleteById(productColorID);
        return "success";
    }

    @Transactional
    @Override
    public String deleteImage(int productImageID) {
        if (!productImageRepository.existsById(productImageID))
            throw new NotFoundException("image-not-found");
        productImageRepository.deleteById(productImageID);
        return "success";
    }

    @Transactional
    @Override
    public String deleteSize(int productSizeID) {
        if (!productSizeRepository.existsById(productSizeID))
            throw new NotFoundException("size-not-found");
        productSizeRepository.deleteById(productSizeID);
        return "success";
    }

    @Transactional
    @Override
    public String activityProduct(int productID) {
        productRepository.activityProduct(productID);
        return "successfully";
    }

    private String uploadImage(String imagePath) {
        String fileName = UUID.randomUUID().toString();
        try {
            FileCopyUtils.copy(Base64Utils.decodeFromString(imagePath.split(",")[1]),
                    new File(this.fileUpload + fileName));
            return DriveAPIUtils.upload(new File(fileUpload + fileName));
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new BadRequestException("image-wrong");
        }
    }
}
