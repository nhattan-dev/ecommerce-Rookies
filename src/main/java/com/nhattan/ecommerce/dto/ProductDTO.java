package com.nhattan.ecommerce.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;

public class ProductDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;
	@NotBlank(message = "cannot-be-empty")
	private String name;
	private String description;
	private int point;
	private int deleted = 0;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int quantity;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private long price;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int subcategoryID;
	private Set<ProductImageDTO> productImages = new HashSet<ProductImageDTO>();
	private Set<ProductColorDTO> productColors = new HashSet<ProductColorDTO>();
	private Set<ProductSizeDTO> productSizes = new HashSet<ProductSizeDTO>();

	public ProductDTO() {
		super();
	}

	public ProductDTO(int productID, String name, String description, int quantity, long price, int subcategoryID) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.subcategoryID = subcategoryID;
	}

	public ProductDTO(int productID, String name, String description, int point, int deleted, int quantity,
			int subcategoryID) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.point = point;
		this.deleted = deleted;
		this.quantity = quantity;
		this.subcategoryID = subcategoryID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public Set<ProductImageDTO> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImageDTO> productImages) {
		this.productImages = productImages;
	}

	public Set<ProductColorDTO> getProductColors() {
		return productColors;
	}

	public void setProductColors(Set<ProductColorDTO> productColors) {
		this.productColors = productColors;
	}

	public Set<ProductSizeDTO> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(Set<ProductSizeDTO> productSizes) {
		this.productSizes = productSizes;
	}

	public static ProductEntity toEntity(ProductDTO dto) {
		ProductEntity product = new ProductEntity();
		product.setProductID(dto.getProductID());
		product.setDescription(dto.getDescription());
		product.setName(dto.getName());
		product.setQuantity(dto.getQuantity());
		product.setSubcategory(new SubcategoryEntity(dto.getSubcategoryID()));
		return product;
	}

	public static ProductDTO toDTO(ProductEntity product) {
		ProductDTO dto = new ProductDTO();
		dto.setDescription(product.getDescription());
		dto.setName(product.getName());
		dto.setPoint(product.getPoint());
		dto.setDeleted(product.getDeleted());
		dto.setProductID(product.getProductID());
		dto.setQuantity(product.getQuantity());
		dto.setSubcategoryID(product.getSubcategory().getSubcategoryID());
		dto.setProductColors(product.getProductColors().stream()
				.map(x -> new ProductColorDTO(x.getProductColorID(), x.getColor())).collect(Collectors.toSet()));
		dto.setProductImages(product.getProductImages().stream()
				.map(x -> new ProductImageDTO(x.getProductImageID(), x.getImage())).collect(Collectors.toSet()));
		dto.setProductSizes(product.getProductSizes().stream()
				.map(x -> new ProductSizeDTO(x.getProductSizeID(), x.getSize())).collect(Collectors.toSet()));
		return dto;
	}

	public ProductDTO(int productID, String name, String description, int point, int deleted, int quantity, long price,
			int subcategoryID, Set<ProductImageDTO> productImages, Set<ProductColorDTO> productColors,
			Set<ProductSizeDTO> productSizes) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.point = point;
		this.deleted = deleted;
		this.quantity = quantity;
		this.price = price;
		this.subcategoryID = subcategoryID;
		this.productImages = productImages;
		this.productColors = productColors;
		this.productSizes = productSizes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (deleted != other.deleted)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (point != other.point)
			return false;
		if (price != other.price)
			return false;
		if (productColors == null) {
			if (other.productColors != null)
				return false;
		} else if (!productColors.equals(other.productColors))
			return false;
		if (productID != other.productID)
			return false;
		if (productImages == null) {
			if (other.productImages != null)
				return false;
		} else if (!productImages.equals(other.productImages))
			return false;
		if (productSizes == null) {
			if (other.productSizes != null)
				return false;
		} else if (!productSizes.equals(other.productSizes))
			return false;
		if (quantity != other.quantity)
			return false;
		if (subcategoryID != other.subcategoryID)
			return false;
		return true;
	}

}
