package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.ShoppingOrder;
import com.oddfar.campus.common.core.BaseMapperX;
import com.oddfar.campus.common.core.LambdaQueryWrapperX;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 订单管理Mapper
 *
 * @author oddfar
 */
public interface ShoppingOrderMapper extends BaseMapperX<ShoppingOrder> {

    default PageResult<ShoppingOrder> page(ShoppingOrder shoppingOrder) {
        return selectPage(new LambdaQueryWrapperX<ShoppingOrder>()
                .eqIfPresent(ShoppingOrder::getUserId, shoppingOrder.getUserId())
                .eqIfPresent(ShoppingOrder::getStoreId, shoppingOrder.getStoreId())
                .eqIfPresent(ShoppingOrder::getProductId, shoppingOrder.getProductId())
                .eqIfPresent(ShoppingOrder::getIsFinished, shoppingOrder.getIsFinished())
                .betweenIfPresent(ShoppingOrder::getCreatedTime, shoppingOrder.getParams())
        );
    }
}