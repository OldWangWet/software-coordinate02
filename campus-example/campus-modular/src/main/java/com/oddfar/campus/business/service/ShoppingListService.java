package com.oddfar.campus.business.service;

import com.oddfar.campus.business.domain.ShoppingList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 购物车管理业务
 *
 * @author oddfar
 */
public interface ShoppingListService extends IService<ShoppingList> {
    /**
     * 查询购物车分页数据
     *
     * @param shoppingList 购物车项
     * @return 分页数据
     */
    PageResult<ShoppingList> page(ShoppingList shoppingList);

    /**
     * 新增购物车项
     *
     * @param shoppingList 购物车项
     * @return 结果
     */
    int insertShoppingList(ShoppingList shoppingList);

    /**
     * 更新购物车项
     *
     * @param shoppingList 购物车项
     * @return 结果
     */
    int updateShoppingList(ShoppingList shoppingList);

    /**
     * 删除购物车项
     *
     * @param shoppingList 购物车项
     * @return 结果
     */
    int deleteShoppingList(ShoppingList shoppingList);
}