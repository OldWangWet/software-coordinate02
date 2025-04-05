package com.oddfar.campus.business.controller;

import com.oddfar.campus.business.domain.ShoppingList;
import com.oddfar.campus.business.service.ShoppingListService;
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
 * 购物车管理控制器
 *
 * @author oddfar
 */
@RestController
@RequestMapping("/campus/shoppinglist")
@ApiResource(name = "购物车管理", appCode = "campus", resBizType = ResBizTypeEnum.BUSINESS)
public class ShoppingListController {
    @Autowired
    private ShoppingListService shoppingListService;

    /**
     * 查询购物车列表
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "/list", name = "查询购物车列表")
    public R list(@Validated @RequestBody ShoppingList shoppingList) {
        PageResult<ShoppingList> page = shoppingListService.page(shoppingList);
        return R.ok().put(page);
    }

    /**
     * 获取购物车项详细信息
     */
    @PreAuthorize("@ss.resourceAuth()")
    @GetMapping(value = "/{id}", name = "获取购物车项详细信息")
    public R getInfo(@PathVariable("id") Long id) {
        return R.ok(shoppingListService.getById(id));
    }

    /**
     * 新增购物车项
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PostMapping(value = "/add", name = "新增购物车项")
    public R add(@Validated @RequestBody ShoppingList shoppingList) {
        return R.ok(shoppingListService.insertShoppingList(shoppingList));
    }

    /**
     * 修改购物车项
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "", name = "修改购物车项")
    public R edit(@Validated @RequestBody ShoppingList shoppingList) {
        return R.ok(shoppingListService.updateShoppingList(shoppingList));
    }

    /**
     * 删除购物车项
     */
    @PreAuthorize("@ss.resourceAuth()")
    @DeleteMapping(value = "/{ids}", name = "删除购物车项")
    public R remove(@PathVariable Long[] ids) {
        return R.ok(shoppingListService.removeBatchByIds(Arrays.asList(ids)));
    }
}