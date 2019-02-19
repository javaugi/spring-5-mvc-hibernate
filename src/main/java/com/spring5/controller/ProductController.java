/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.controller;

import com.google.common.collect.FluentIterable;
import static com.spring5.controller.UserController.F_N_LIST;
import com.spring5.service.ProductService;
import com.spring5.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap modelMap) {
        List<Product> products = (List<Product>) productService.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(products);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        return "product/index";
    }

    @GetMapping("/listProducts")
    public String listUsers(Locale locale, Model model, HttpServletRequest request, ModelMap modelMap) {
        System.out.println("listProducts");
        createProducts();
        Iterable<Product> productIterable = productService.findAll();
        List<Product> products = FluentIterable.from(productIterable).toList();

        //List<User> users = userService.listUsers(); //userPagingRepositary.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(products);
        long count = products.size();
        int pageSize = 5;
        long pages = count / pageSize;
        System.out.println("total " + count + "-pages=" + pages);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        //model.addAttribute("users", userService.listUsers());
        return "listProducts";
    }

    private List<Product> createProducts() {

        List<Product> returnValue = new ArrayList();
        Product product = null;
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            product = new Product();
            product.setName(F_N_LIST.get(rand.nextInt(F_N_LIST.size())));
            product.setPrice(new BigDecimal(rand.nextDouble() * 100));
            product.setQuantity(rand.nextInt(20));
            product.setStatus(true);
            returnValue.add(product);
        }

        productService.saveAll(returnValue);
        return returnValue;
    }
}
