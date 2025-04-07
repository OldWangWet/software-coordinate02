package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.Product;
import com.oddfar.campus.common.core.BaseMapperX;
import com.oddfar.campus.common.core.LambdaQueryWrapperX;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 商品管理Mapper
 *
 * @author oddfar
 */
public interface ProductMapper extends BaseMapperX<Product> {

    default PageResult<Product> page(Product product) {
        return selectPage(new LambdaQueryWrapperX<Product>()
                .likeIfPresent(Product::getName, product.getName())
                .eqIfPresent(Product::getCreateUser, product.getCreateUser())
        );
    }
}