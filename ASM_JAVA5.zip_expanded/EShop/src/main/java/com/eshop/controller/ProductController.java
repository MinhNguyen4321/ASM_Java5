package com.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/list")
    public String product() {
        return "product/product-list";
    }

    @RequestMapping("/product-detail")
    public String detail() {
        return "product/product-detail";
    }
}
