package coder.uz.uzum.controller;

import coder.uz.uzum.entity.Product;
import coder.uz.uzum.entity.dto.ProductDto;
import coder.uz.uzum.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
   private final ModelMapper modelMapper=new ModelMapper();
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public boolean create(@RequestBody ProductDto productDto) {

        Product product = modelMapper.map(productDto, Product.class);
        product.setCreateDate(LocalDateTime.now());
        return productService.create(product);
    }
    @GetMapping("/getAll")
    public List getAll() {
        return productService.getAll();
    }
    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable UUID id) {
        return productService.getById(id);
    }
    @PutMapping("/update/{id}")
    public boolean update(
            @PathVariable UUID id,
            @RequestBody Product product) {

        return productService.update(id, product);
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable UUID id) {
        return productService.delete(id);
    }
}
