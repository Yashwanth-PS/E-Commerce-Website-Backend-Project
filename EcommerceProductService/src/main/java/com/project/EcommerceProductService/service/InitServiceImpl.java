package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.model.Category;
import com.project.EcommerceProductService.model.Order;
import com.project.EcommerceProductService.model.Price;
import com.project.EcommerceProductService.model.Product;
import com.project.EcommerceProductService.repository.CategoryRepository;
import com.project.EcommerceProductService.repository.OrderRepository;
import com.project.EcommerceProductService.repository.PriceRepository;
import com.project.EcommerceProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{

    private ProductRepository productRepository;
    private PriceRepository priceRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public InitServiceImpl(ProductRepository productRepository, PriceRepository priceRepository, OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialise() {
        Category electronics = new Category();
        electronics.setCategoryName("Electronics");

        electronics = categoryRepository.save(electronics); // Insert and Update --> Upsert

        Price priceIphone = new Price();
        priceIphone.setCurrency("INR");
        priceIphone.setAmount(100000);
        priceIphone.setDiscount(0);

        Price priceMacbook = new Price();
        priceMacbook.setCurrency("INR");
        priceMacbook.setAmount(200000);
        priceMacbook.setDiscount(0);

        Price priceWatch = new Price();
        priceWatch.setCurrency("INR");
        priceWatch.setAmount(50000);
        priceWatch.setDiscount(0);

        Price pricePS5 = new Price();
        priceWatch.setCurrency("INR");
        priceWatch.setAmount(70000);
        priceWatch.setDiscount(0);

        priceIphone = priceRepository.save(priceIphone);
        priceMacbook = priceRepository.save(priceMacbook);
        priceWatch = priceRepository.save(priceWatch);
        pricePS5 = priceRepository.save(pricePS5);

        Product iphone = new Product();
        iphone.setTitle("iPhone 15 Pro");
        iphone.setDescription("Best Iphone Ever");
        iphone.setImage("https://image.com");
        iphone.setPrice(priceIphone);
        iphone.setCategory(electronics);
        iphone = productRepository.save(iphone);

        Product macbook = new Product();
        macbook.setTitle("Macbook Pro 16");
        macbook.setDescription("Best Macbook Ever");
        macbook.setImage("https://image.com");
        macbook.setPrice(priceMacbook);
        macbook.setCategory(electronics);
        macbook = productRepository.save(macbook);

        Product watch = new Product();
        watch.setTitle("Watch series 10");
        watch.setDescription("Best watch ever");
        watch.setImage("https://image.com");
        watch.setPrice(priceWatch);
        watch.setCategory(electronics);
        watch = productRepository.save(watch);

        Product ps5 = new Product();
        ps5.setTitle("PlayStation5");
        ps5.setDescription("Best playstation ever");
        ps5.setImage("https://image.com");
        ps5.setPrice(pricePS5);
        ps5.setCategory(electronics);
        ps5 = productRepository.save(ps5);

        Order order = new Order();
        order.setProducts(List.of(iphone, macbook, watch));
        orderRepository.save(order);
    }
}
