package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductColorEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductImageEntity;
import com.nhattan.ecommerce.entity.ProductSizeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ProductSizeDTO {
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int productSizeID;
    @NotBlank(message = "cannot-be-empty")
    private String size;
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int productID;

    public ProductSizeDTO() {
        super();
    }

    public ProductSizeDTO(int productSizeID, String size) {
        super();
        this.productSizeID = productSizeID;
        this.size = size;
    }

    public static ProductSizeDTO toDTO(ProductSizeEntity entity) {
        ProductSizeDTO dto = new ProductSizeDTO();
        dto.setProductID(entity.getProduct().getProductID());
        dto.setSize(entity.getSize());
        dto.setProductSizeID(entity.getProductSizeID());
        return dto;
    }

    public static ProductSizeEntity toEntity(ProductSizeDTO dto) {
        ProductSizeEntity entity = new ProductSizeEntity();
        entity.setSize(dto.getSize());
        entity.setProductSizeID(dto.getProductSizeID());
        ProductEntity p = new ProductEntity();
        p.setProductID(dto.getProductID());
        entity.setProduct(p);
        return entity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductSizeDTO other = (ProductSizeDTO) obj;
        if (size == null) {
            if (other.size != null)
                return false;
        } else if (!size.equals(other.size))
            return false;
        return true;
    }
}
