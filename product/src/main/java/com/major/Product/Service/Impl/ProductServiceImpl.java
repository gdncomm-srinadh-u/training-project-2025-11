package com.major.Product.Service.Impl;
import com.major.Product.Constants;
import com.major.Product.Service.ProductService;
import com.major.Product.dto.Productcreatedto;
import com.major.Product.dto.Productresponsedto;
import com.major.Product.entity.Product;
import com.major.Product.repository.ProductRepositoryCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoryCustom productRepositoryCustom;

    @Override
    public Productresponsedto createProduct(Productcreatedto dto) {

        Product product = new Product();
        product.setSku("Sku123-"+UUID.randomUUID());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategoryID(dto.getCategoryID());
        product.setActive(dto.isActive());
        product.setStock(dto.getStock());

        Product saved = productRepositoryCustom.save(product);

        return convertToDTO(saved);
    }

    @Override
    public List<Productresponsedto> list(int page, int size) {
        return productRepositoryCustom.findAll(PageRequest.of(page, size))
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<Productresponsedto> search(String keyword, int page, int size) {
        int pageIndex = page < 0 ? 0 : page;
        String exactRegex = "^" + Pattern.quote(keyword.trim()) + "$";
        return productRepositoryCustom.search(exactRegex, PageRequest.of(page, size))

                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<Productresponsedto> createBulkProducts(List<Productcreatedto> dtoList) {
        if(dtoList.size()> Constants.MAX_PRODUCT_TO_ADD_VIA_BULK){
            throw new IllegalArgumentException(Constants.Error_Msg);
        }
        List<Product> productList = dtoList.stream().map(dto -> {
            Product product = new Product();
            product.setSku(UUID.randomUUID().toString());
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setCategoryID(dto.getCategoryID());
            product.setPrice(dto.getPrice());
            product.setActive(dto.isActive());
            return product;
        }).toList();

        List<Product> saved = productRepositoryCustom.saveAll(productList);

        return saved.stream().map(this::convertToDTO).toList();
    }

    @Override
    public Productresponsedto getBySku(String sku) {
        Product product = productRepositoryCustom.findById(sku)
                .orElseThrow(() -> new RuntimeException(Constants.PRODUCT_NOT_FOUND + sku));

        return convertToDTO(product);
    }

    private Productresponsedto convertToDTO(Product p) {
        return Productresponsedto.builder()
                .sku(p.getSku())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .categoryID(p.getCategoryID())
                .active(p.isActive())
                .build();
    }
    private static Productcreatedto convertToDTOs(Product product) {
        Productcreatedto Productresponsedto = new Productcreatedto();
        BeanUtils.copyProperties(product, Productresponsedto);
        return Productresponsedto;
    }

    @Override
    public List<Productresponsedto> getProductsByIds(List<String> productIds) {
        return productRepositoryCustom.findAllById(productIds)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
