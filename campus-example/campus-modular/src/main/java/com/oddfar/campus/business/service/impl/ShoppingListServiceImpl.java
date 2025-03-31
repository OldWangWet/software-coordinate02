package com.oddfar.campus.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.ShoppingList;
import com.oddfar.campus.business.mapper.ShoppingListMapper;
import com.oddfar.campus.business.service.ShoppingListService;
import com.oddfar.campus.common.domain.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 购物车管理业务实现层
 *
 * @author oddfar
 */
@Service
public class ShoppingListServiceImpl extends ServiceImpl<ShoppingListMapper, ShoppingList>
        implements ShoppingListService {

    @Resource
    private ShoppingListMapper shoppingListMapper;

    @Override
    public PageResult<ShoppingList> page(ShoppingList shoppingList) {
        return shoppingListMapper.page(shoppingList);
    }

    @Override
    public int insertShoppingList(ShoppingList shoppingList) {
        return shoppingListMapper.insert(shoppingList);
    }

    @Override
    public int updateShoppingList(ShoppingList shoppingList) {
        return shoppingListMapper.updateById(shoppingList);
    }

    @Override
    public int deleteShoppingList(ShoppingList shoppingList) {
        return shoppingListMapper.deleteById(shoppingList.getId());
    }
}