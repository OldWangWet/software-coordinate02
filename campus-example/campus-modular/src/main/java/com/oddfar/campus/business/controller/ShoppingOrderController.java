package com.oddfar.campus.business.controller;

import com.oddfar.campus.business.domain.Product;
import com.oddfar.campus.business.domain.ShoppingOrder;
import com.oddfar.campus.business.service.ShoppingOrderService;
import com.oddfar.campus.common.annotation.ApiResource;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;
import com.oddfar.campus.common.domain.R;
import com.oddfar.campus.common.enums.ResBizTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 订单管理控制器
 *
 * @author oddfar
 */
@RestController
@RequestMapping("/campus/shoppingorder")
@ApiResource(name = "订单管理", appCode = "campus", resBizType = ResBizTypeEnum.BUSINESS)
public class ShoppingOrderController {
    @Autowired
    private ShoppingOrderService shoppingOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "/list/{page}/{size}", name = "查询订单列表")
    public R list(
            @PathVariable("page") int page,      // 从 URL 路径获取 page
            @PathVariable("size") int size,      // 从 URL 路径获取 size
            @RequestBody(required = false)  ShoppingOrder shoppingOrder  // 可选请求体
    ) {
        PageParam pageParam = new PageParam(page, size); // 构造 PageParam
        PageResult<ShoppingOrder> pageResult = shoppingOrderService.page(shoppingOrder, pageParam);
        return R.ok().put(pageResult);
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.resourceAuth()")
    @GetMapping(value = "/{id}", name = "获取订单详细信息")
    public R getInfo(@PathVariable("id") Long id) {
        return R.ok(shoppingOrderService.getById(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PostMapping(value = "/add", name = "新增订单")
    public R add(@Validated @RequestBody ShoppingOrder shoppingOrder) {
        return R.ok(shoppingOrderService.insertShoppingOrder(shoppingOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "/comment", name = "修改订单")
    public R edit(@Validated @RequestBody ShoppingOrder shoppingOrder) {
        ShoppingOrder tmp=shoppingOrderService.getById(shoppingOrder.getId());
        tmp.setBcomment(shoppingOrder.getBcomment());
        tmp.setPcomment(shoppingOrder.getPcomment());
        return R.ok(shoppingOrderService.updateShoppingOrder(tmp));
    }

    /**
     * 删除订单
     */
//    @PreAuthorize("@ss.resourceAuth()")
//    @DeleteMapping(value = "/{ids}", name = "删除订单")
//    public R remove(@PathVariable Long[] ids) {
//        return R.ok(shoppingOrderService.removeBatchByIds(Arrays.asList(ids)));
//    }

    /**
     * 完成订单
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "/finish/{id}", name = "完成订单")
    public R finishOrder(@PathVariable Long id) {
        return R.ok(shoppingOrderService.finishOrder(id));
    }
}