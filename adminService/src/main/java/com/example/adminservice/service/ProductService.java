package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDTO;
import com.example.adminservice.entity.Product;
import com.example.adminservice.mapper.ProductMapper;
import com.example.adminservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ApiResponse add(ProductDTO productDTO) {

        Product product = productMapper.toEntity(productDTO);
        productRepository.save(product);
        return new ApiResponse("Added",true,product);
    }

    public ApiResponse getOne(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product=optionalProduct.get();
        ProductDTO productDTO= productMapper.toDTO(product);

        return new ApiResponse("Here",true,productDTO);
    }
    public ApiResponse getAll(){
        List<Product> productList=productRepository.findAll();
        List<ProductDTO> productDTOList=productMapper.toDTOList(productList);
        return new ApiResponse("Here",true,productDTOList);
    }
    public ApiResponse edit(Long id, ProductDTO productDTO) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        Product product= optionalProduct.get();
        product=productMapper.toEntity(productDTO);
        productRepository.save(product);
        return new ApiResponse("updete",true,product);
    }


}