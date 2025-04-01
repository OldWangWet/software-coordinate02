package com.oddfar.campus.business.controller;

import com.oddfar.campus.business.domain.Store;
import com.oddfar.campus.business.service.StoreService;
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
 * 店铺管理控制器
 *
 * @author oddfar
 */
@RestController
@RequestMapping("/campus/store")
@ApiResource(name = "店铺管理", appCode = "campus", resBizType = ResBizTypeEnum.BUSINESS)
public class StoreController {
    @Autowired
    private StoreService storeService;

    /**
     * 查询店铺列表
     */
    @PreAuthorize("@ss.resourceAuth()")
    @GetMapping(value = "/list", name = "查询店铺列表")
    public R list(Store store) {
        PageResult<Store> page = storeService.page(store);
        return R.ok().put(page);
    }

    /**
     * 获取店铺详细信息
     */
    @PreAuthorize("@ss.resourceAuth()")
    @GetMapping(value = "/{id}", name = "获取店铺详细信息")
    public R getInfo(@PathVariable("id") Long id) {
        return R.ok(storeService.getById(id));
    }

    /**
     * 新增店铺
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PostMapping(value = "/add", name = "新增店铺")
    public R add(@Validated @RequestBody Store store) {
        return R.ok(storeService.insertStore(store));
    }

    /**
     * 修改店铺
     */
    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "/modify", name = "修改店铺")
    public R edit(@Validated @RequestBody Store store) {
        return R.ok(storeService.updateStore(store));
    }

    /**
     * 删除店铺
     */
    @PreAuthorize("@ss.resourceAuth()")
    @DeleteMapping(value = "/{ids}", name = "删除店铺")
    public R remove(@PathVariable Long[] ids) {
        return R.ok(storeService.removeBatchByIds(Arrays.asList(ids)));
    }
}