package coder.uz.uzum.service;

import coder.uz.uzum.entity.Product;
import coder.uz.uzum.entity.User;
import coder.uz.uzum.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;

    public boolean create(Product product) {
        if (product == null) {
            return false;
        }else {
            productRepository.save(product);
            return true;
        }
    }
    public Product getById(UUID id) {
        if(id==null){
            return null;
        }else {
          return  productRepository.getById(id);
        }
    }


    public boolean update(UUID id, Product product) {
       if (id==null||product==null){
           return false;
       }else {
           Product old_product = productRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Product topilmadi"));
           old_product.setName(product.getName());
           old_product.setPrice(product.getPrice());
           old_product.setQuantity(product.getQuantity());
           old_product.setImage(product.getImage());
             old_product.setCreateDate(product.getCreateDate());
           productRepository.save(old_product);
           return true;
       }
    }


    public boolean delete(UUID id) {

        if (id==null){
            return false;
        }else {
            productRepository.deleteById(id);
            return true;
        }
    }
    public List getAll() {
        return productRepository.findAll();
    }
}
