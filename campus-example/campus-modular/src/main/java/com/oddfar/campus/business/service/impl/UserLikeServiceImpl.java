package com.oddfar.campus.business.service.impl;
import com.oddfar.campus.business.domain.entity.CategoryEntity;
import com.oddfar.campus.business.mapper.CategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.UserLike;
import com.oddfar.campus.business.mapper.UserLikeMapper;
import com.oddfar.campus.business.service.UserLikeService;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserLikeServiceImpl extends ServiceImpl<UserLikeMapper, UserLike>
        implements UserLikeService {

    @Resource
    private UserLikeMapper userLikeMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public PageResult<UserLike> page(UserLike userLike, PageParam pageParam) {
        // 先查询用户喜欢分页数据
        PageResult<UserLike> pageResult = userLikeMapper.page(userLike, pageParam);

        // 获取所有用户喜欢项
        List<UserLike> records = pageResult.getRows();

        // 为每个用户喜欢项设置对应的分类信息
        records.forEach(item -> {
            CategoryEntity categoryEntity = categoryMapper.selectById(item.getCategory());
            item.setCategoryEntity(categoryEntity);
        });

        return pageResult;
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