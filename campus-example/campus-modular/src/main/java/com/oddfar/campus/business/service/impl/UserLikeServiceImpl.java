package com.oddfar.campus.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.UserLike;
import com.oddfar.campus.business.mapper.UserLikeMapper;
import com.oddfar.campus.business.service.UserLikeService;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLikeServiceImpl extends ServiceImpl<UserLikeMapper, UserLike>
        implements UserLikeService {

    @Resource
    private UserLikeMapper userLikeMapper;

    @Override
    public PageResult<UserLike> page(UserLike userLike, PageParam pageParam) {
        return userLikeMapper.page(userLike, pageParam);
    }

    @Override
    public int insertUserLike(UserLike userLike) {
        return userLikeMapper.insert(userLike);
    }

    @Override
    public int updateUserLike(UserLike userLike) {
        return userLikeMapper.updateById(userLike);
    }

    @Override
    public int deleteUserLike(UserLike userLike) {
        return userLikeMapper.deleteById(userLike.getId());
    }
}