package com.oddfar.campus.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.Store;
import com.oddfar.campus.business.mapper.StoreMapper;
import com.oddfar.campus.business.service.StoreService;
import com.oddfar.campus.common.domain.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 店铺管理业务实现层
 *
 * @author oddfar
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store>
        implements StoreService {

    @Resource
    private StoreMapper storeMapper;

    @Override
    public PageResult<Store> page(Store store) {
        return storeMapper.page(store);
    }

    @Override
    public int insertStore(Store store) {
        return storeMapper.insert(store);
    }

    @Override
    public int updateStore(Store store) {
        return storeMapper.updateById(store);
    }

    @Override
    public int deleteStore(Store store) {
        return storeMapper.deleteById(store.getId());
    }
}