package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.ShoppingList;
import com.oddfar.campus.business.domain.ShoppingOrder;
import com.oddfar.campus.common.core.BaseMapperX;
import com.oddfar.campus.common.core.LambdaQueryWrapperX;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 订单管理Mapper
 *
 * @author oddfar
 */
public interface ShoppingOrderMapper extends BaseMapperX<ShoppingOrder> {

    default PageResult<ShoppingOrder> page(ShoppingOrder shoppingOrder, PageParam pageParam) {
        return selectPage(pageParam,new LambdaQueryWrapperX<ShoppingOrder>()
                .eqIfPresent(ShoppingOrder::getProductId, shoppingOrder.getProductId())
                .eqIfPresent(ShoppingOrder::getIsFinished, shoppingOrder.getIsFinished())
                .eqIfPresent(ShoppingOrder::getCreateUser, shoppingOrder.getCreateUser())
                .eqIfPresent(ShoppingOrder::getProducer, shoppingOrder.getProducer())
        );
    }
}