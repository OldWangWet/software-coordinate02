package com.oddfar.campus.business.service;

import com.oddfar.campus.business.domain.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 店铺管理业务
 *
 * @author oddfar
 */
public interface StoreService extends IService<Store> {
    /**
     * 查询店铺分页数据
     *
     * @param store 店铺
     * @return 分页数据
     */
    PageResult<Store> page(Store store);

    /**
     * 新增店铺
     *
     * @param store 店铺
     * @return 结果
     */
    int insertStore(Store store);

    /**
     * 更新店铺
     *
     * @param store 店铺
     * @return 结果
     */
    int updateStore(Store store);

    /**
     * 删除店铺
     *
     * @param store 店铺
     * @return 结果
     */
    int deleteStore(Store store);
}