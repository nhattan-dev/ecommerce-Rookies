package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductColorEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductImageEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ProductColorDTO {
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int productColorID;
    @NotBlank(message = "cannot-be-empty")
    private String color;
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int productID;

    public static ProductColorDTO toDTO(ProductColorEntity entity) {
        ProductColorDTO dto = new ProductColorDTO();
        dto.setProductID(entity.getProduct().getProductID());
        dto.setColor(entity.getColor());
        dto.setProductColorID(entity.getProductColorID());
        return dto;
    }

    public static ProductColorEntity toEntity(ProductColorDTO dto) {
        ProductColorEntity entity = new ProductColorEntity();
        entity.setColor(dto.getColor());
        entity.setProductColorID(dto.getProductColorID());
        ProductEntity p = new ProductEntity();
        p.setProductID(dto.getProductID());
        entity.setProduct(p);
        return entity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
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
        ProductColorDTO other = (ProductColorDTO) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        return true;
    }

}
