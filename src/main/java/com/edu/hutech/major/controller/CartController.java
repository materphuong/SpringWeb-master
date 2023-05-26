package com.edu.hutech.major.controller;

import com.edu.hutech.major.global.GlobalData;
import com.edu.hutech.major.model.Product;
import com.edu.hutech.major.model.User;
import com.edu.hutech.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
public class CartController {
    private final ProductService productService;

    @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String cartGet(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", calculateTotalPrice());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id).orElse(null);
        if (product != null) {
            Optional<Product> existingProduct = findProductInCart(id);
            if (existingProduct.isPresent()) {
                // Sản phẩm đã có trong giỏ hàng, chỉ tăng số lượng lên
                Product cartProduct = existingProduct.get();
                cartProduct.setQuantity(cartProduct.getQuantity() + 1);
            } else {
                // Sản phẩm chưa có trong giỏ hàng, thêm vào giỏ hàng
                product.setQuantity(1);
                GlobalData.cart.add(product);
            }
        }
        return "redirect:/shop";
    }

    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        Optional<Product> productToUpdate = findProductInCart(productId);
        productToUpdate.ifPresent(product -> product.setQuantity(quantity));
        return "redirect:/cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {
        if (index >= 0 && index < GlobalData.cart.size()) {
            GlobalData.cart.remove(index);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", calculateTotalPrice());
        return "checkout";
    }

    @PostMapping("/payNow")
    public String processPayment() {
        // Xử lý đặt hàng thành công ở đây

        return "confirmation";
    }

    private Optional<Product> findProductInCart(Long productId) {
        return GlobalData.cart.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    private double calculateTotalPrice() {
        return GlobalData.cart.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }
}

