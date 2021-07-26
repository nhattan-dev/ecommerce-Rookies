package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;
	@NotBlank(message = "cannot-be-empty")
	private String name;
	private String description;
	private int point;
	private String status;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int quantity;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private long price;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int subcategoryID;
	@Size(min = 1, message = "must-have-at-least-one-image")
	private Set<ProductImageDTO> productImages = new HashSet<ProductImageDTO>();
	@Size(min = 1, message = "must-have-at-least-one-color")
	private Set<ProductColorDTO> productColors = new HashSet<ProductColorDTO>();
	@Size(min = 1, message = "must-have-at-least-one-size")
	private Set<ProductSizeDTO> productSizes = new HashSet<ProductSizeDTO>();

	public static ProductEntity toEntity(ProductDTO dto) {
		ProductEntity product = new ProductEntity();
		product.setProductID(dto.getProductID());
		product.setDescription(dto.getDescription());
		product.setName(dto.getName());
		product.setQuantity(dto.getQuantity());
		SubcategoryEntity sub = new SubcategoryEntity();
		sub.setSubcategoryID(dto.getSubcategoryID());
		product.setSubcategory(sub);
		return product;
	}

	public static ProductDTO toDTO(ProductEntity product) {
		ProductDTO dto = new ProductDTO();
		dto.setDescription(product.getDescription());
		dto.setName(product.getName());
		dto.setPoint(product.getPoint());
		dto.setStatus(product.getStatus());
		dto.setProductID(product.getProductID());
		dto.setQuantity(product.getQuantity());
		dto.setSubcategoryID(product.getSubcategory().getSubcategoryID());
		dto.setProductColors(product.getProductColors().stream()
				.map(x -> ProductColorDTO.toDTO(x)).collect(Collectors.toSet()));
		dto.setProductImages(product.getProductImages().stream()
				.map(x -> ProductImageDTO.toDTO(x)).collect(Collectors.toSet()));
		dto.setProductSizes(product.getProductSizes().stream()
				.map(x -> ProductSizeDTO.toDTO(x)).collect(Collectors.toSet()));
		return dto;
	}
}
