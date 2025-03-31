package com.oddfar.campus.business.service;

import com.oddfar.campus.business.domain.ShoppingOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 订单管理业务
 *
 * @author oddfar
 */
public interface ShoppingOrderService extends IService<ShoppingOrder> {
    /**
     * 查询订单分页数据
     *
     * @param shoppingOrder 订单
     * @return 分页数据
     */
    PageResult<ShoppingOrder> page(ShoppingOrder shoppingOrder);

    /**
     * 新增订单
     *
     * @param shoppingOrder 订单
     * @return 结果
     */
    int insertShoppingOrder(ShoppingOrder shoppingOrder);

    /**
     * 更新订单
     *
     * @param shoppingOrder 订单
     * @return 结果
     */
    int updateShoppingOrder(ShoppingOrder shoppingOrder);

    /**
     * 删除订单
     *
     * @param shoppingOrder 订单
     * @return 结果
     */
    int deleteShoppingOrder(ShoppingOrder shoppingOrder);

    /**
     * 完成订单
     *
     * @param id 订单ID
     * @return 结果
     */
    int finishOrder(Long id);
}