package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.UserLike;
import com.oddfar.campus.common.core.BaseMapperX;
import com.oddfar.campus.common.core.LambdaQueryWrapperX;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;

public interface UserLikeMapper extends BaseMapperX<UserLike> {
    default PageResult<UserLike> page(UserLike userLike, PageParam pageParam) {
        return selectPage(pageParam, new LambdaQueryWrapperX<UserLike>()
                .eqIfPresent(UserLike::getUserid, userLike.getUserid())
                .eqIfPresent(UserLike::getCategory, userLike.getCategory())
        );
    }
}