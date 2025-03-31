package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.Store;
import com.oddfar.campus.common.core.BaseMapperX;
import com.oddfar.campus.common.core.LambdaQueryWrapperX;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 店铺管理Mapper
 *
 * @author oddfar
 */
public interface StoreMapper extends BaseMapperX<Store> {

    default PageResult<Store> page(Store store) {
        return selectPage(new LambdaQueryWrapperX<Store>()
                .likeIfPresent(Store::getName, store.getName())
                .betweenIfPresent(Store::getCreateTime, store.getParams())
        );
    }
}