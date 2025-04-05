package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.ShoppingList;
import com.oddfar.campus.common.core.BaseMapperX;
import com.oddfar.campus.common.core.LambdaQueryWrapperX;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 购物车管理Mapper
 *
 * @author oddfar
 */
public interface ShoppingListMapper extends BaseMapperX<ShoppingList> {

    default PageResult<ShoppingList> page(ShoppingList shoppingList) {
        return selectPage(new LambdaQueryWrapperX<ShoppingList>()
                .eqIfPresent(ShoppingList::getProductId, shoppingList.getProductId())
                .eqIfPresent(ShoppingList::getCreateUser, shoppingList.getCreateUser())
        );
    }
}