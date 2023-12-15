package com.guithub.daniellimadev.service;

import com.guithub.daniellimadev.dto.ProductDTO;
import com.guithub.daniellimadev.entity.ProductEntity;
import com.guithub.daniellimadev.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> getAllProduct() {

        List<ProductDTO> products = new ArrayList<>();

        productRepository.findAll().stream().forEach(item->{
            products.add(mapProductEntityToDTO(item));
        });

        return products;
    }

    public void createNewProduct(ProductDTO product) {
        productRepository.persist(mapProductDTOToEntity(product));
    }

    public void updateProduct(Long id, ProductDTO product) {

        ProductEntity productEntity = productRepository.findById(id);
        productEntity.setName(product.getName());
        productEntity.setCategory(product.getCategory());
        productEntity.setModel(product.getModel());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());

        productRepository.persist(productEntity);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapProductEntityToDTO(ProductEntity productEntity) {

        ProductDTO product = new ProductDTO();
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setCategory(productEntity.getCategory());
        product.setModel(productEntity.getModel());
        product.setPrice(productEntity.getPrice());

        return product;
    }

    private ProductEntity mapProductDTOToEntity(ProductDTO productDTO) {

        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setModel(productDTO.getModel());
        product.setPrice(productDTO.getPrice());

        return product;
    }

}
