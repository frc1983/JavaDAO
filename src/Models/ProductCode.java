package Models;

import java.io.Serializable;

public class ProductCode implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String prodCode;
    private Character discountCode;
    private String description;
    private DiscountCode discountEntity;

    public ProductCode() {
    }

    public ProductCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public ProductCode(String prodCode, Character discountCode) {
        this.prodCode = prodCode;
        this.discountCode = discountCode;
    }
    
    public ProductCode(String prodCode, Character discountCode, String description) {
        this.prodCode = prodCode;
        this.discountCode = discountCode;
        this.description = description;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public Character getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Character discountCode) {
        this.discountCode = discountCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodCode != null ? prodCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProductCode)) {
            return false;
        }
        ProductCode other = (ProductCode) object;
        if ((this.prodCode == null && other.prodCode != null) || (this.prodCode != null && !this.prodCode.equals(other.prodCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.ProductCode[ prodCode=" + prodCode + " ]";
    }

    public DiscountCode getDiscountEntity() {
        return discountEntity;
    }

    public void setDiscountEntity(DiscountCode discountEntity) {
        this.discountEntity = discountEntity;
    }

}
