package io.openstech.product_managment.service;

import io.openstech.product_managment.communication.dto.PatchProductDTO;
import io.openstech.product_managment.excpetion.ProductAlreadyExistException;
import io.openstech.product_managment.excpetion.ProductNotFoundException;
import io.openstech.product_managment.communication.dto.ProductDTO;
import io.openstech.product_managment.persistence.domain.Product;
import io.openstech.product_managment.persistence.repository.ProductRepository;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDtoMapper;

    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ProductDTOMapper productDtoMapper, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.productDtoMapper = productDtoMapper;
        this.modelMapper = modelMapper;
    }


    //TODO Concurrency should be taken in account

    public List<ProductDTO> returnAllProduct(int pageNumber, int pageSize) {


        Pageable pageWithLimit =  PageRequest.of(pageNumber, pageSize);


        return productRepository.findAll(pageWithLimit).stream()
                .map(productDtoMapper::toProductDto)
                .collect(Collectors.toList());
    }


    public ProductDTO returnProduct(String productNumber) {

        Product product = getProductOrException(productNumber);

        return productDtoMapper.toProductDto(product);
    }


    public ProductDTO insertProduct(ProductDTO productDTO) {

        if (productAlreadyExists(productDTO)) {
            throw new ProductAlreadyExistException();
        } else {

            Product product = productDtoMapper.toProduct(productDTO);

            String productNum = generateUniqueProductNumber();

            product.setProductNumber(productNum);



            productRepository.save(product);

            return productDtoMapper.toProductDto(product);
        }
    }


    public ProductDTO modifyProduct(String productNumber, ProductDTO newProductDTO) {

        Product existingPerson = getProductOrException(productNumber);

        modelMapper.getConfiguration().setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        modelMapper.map(newProductDTO, existingPerson);

        productRepository.save(existingPerson);

        return productDtoMapper.toProductDto(existingPerson);
    }


    //@Transactional
    public ProductDTO modifyProductField(String productNumber, PatchProductDTO partialPersonDTO) {

        Product existingPerson = getProductOrException(productNumber);

        modelMapper.getConfiguration().setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        modelMapper.map(partialPersonDTO, existingPerson);

        productRepository.save(existingPerson);

        return productDtoMapper.toProductDto(existingPerson);
    }


    @Transactional
    public void removeProduct(String productNumber) {

        getProductOrException(productNumber);
        productRepository.deleteByProductNumber(productNumber);
    }


    private boolean productAlreadyExists(ProductDTO productDTO) {
        String title = productDTO.getTitle();
        String name = productDTO.getDetails().getName();
        String model = productDTO.getDetails().getModel();

        return productRepository.existsByTitleOrNameOrModel(title, name, model);
    }

    private String generateUniqueProductNumber() {
        String productNum;
        do {
            productNum = RandomString.make(12).toUpperCase();
        } while (productRepository.existsByProductNumber(productNum));
        return productNum;
    }


    private Product getProductOrException(String pNumber) {
        Optional<Product> product = productRepository.findProductByProductNumber(pNumber);

        return product.orElseThrow(() -> {
            throw new ProductNotFoundException();
        });
    }


}
