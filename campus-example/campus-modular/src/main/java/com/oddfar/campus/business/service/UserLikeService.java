package com.oddfar.campus.business.service;

import com.oddfar.campus.business.domain.UserLike;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;

public interface UserLikeService extends IService<UserLike> {
    PageResult<UserLike> page(UserLike userLike, PageParam pageParam);

    int insertUserLike(UserLike userLike);

    int updateUserLike(UserLike userLike);

    int deleteUserLike(UserLike userLike);
}