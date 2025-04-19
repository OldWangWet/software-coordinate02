package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.UserLike;
import com.oddfar.campus.common.core.BaseMapperX;
import com.oddfar.campus.common.core.LambdaQueryWrapperX;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLikeMapper extends BaseMapperX<UserLike> {

    // 默认分页查询（自动过滤 del_flag = 1 的数据）
    default PageResult<UserLike> page(UserLike userLike, PageParam pageParam) {
        return selectPage(pageParam, new LambdaQueryWrapperX<UserLike>()
                .eqIfPresent(UserLike::getUserid, userLike.getUserid())
                .eqIfPresent(UserLike::getCategory, userLike.getCategory())
        );
    }

    // 硬删除（物理删除，直接执行 DELETE SQL）
    @Delete("DELETE FROM userlike WHERE id = #{id}")
    int hardDeleteById(@Param("id") Long id);

    // 根据 userid 查询所有 category（自动过滤 del_flag = 1 的数据）
    @Select("SELECT category FROM userlike WHERE userid = #{userid} AND del_flag = 0")
    List<Long> selectCategoriesByUserId(@Param("userid") Long userid);

    // 其他自定义方法...
}