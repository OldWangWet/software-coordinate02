package com.oddfar.campus.business.controller;

import com.oddfar.campus.business.domain.Product;
import com.oddfar.campus.business.service.ProductService;
import com.oddfar.campus.common.annotation.ApiResource;
import com.oddfar.campus.common.domain.PageResult;
import com.oddfar.campus.common.domain.R;
import com.oddfar.campus.common.enums.ResBizTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 商品管理控制器
 *
 * @author oddfar
 */
@RestController
@RequestMapping("/campus/product")
@ApiResource(name = "商品管理", appCode = "campus", resBizType = ResBizTypeEnum.BUSINESS)
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.resourceAuth()")
    @GetMapping(value = "/list", name = "查询商品列表")
    public R list(Product product) {
        PageResult<Product> page = productService.page(product);
        return R.ok().put(page);
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.resourceAuth()")
    @GetMapping(value = "/{id}", name = "获取商品详细信息")
    public R getInfo(@PathVariable("id") Long id) {
        return R.ok(productService.getById(id));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PostMapping(value = "/add", name = "新增商品")
    public R add(@Validated @RequestBody Product product) {
        return R.ok(productService.insertProduct(product));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "/modify", name = "修改商品")
    public R edit(@Validated @RequestBody Product product) {
        return R.ok(productService.updateProduct(product));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.resourceAuth()")
    @DeleteMapping(value = "/{ids}", name = "删除商品")
    public R remove(@PathVariable Long[] ids) {
        return R.ok(productService.removeBatchByIds(Arrays.asList(ids)));
    }
}