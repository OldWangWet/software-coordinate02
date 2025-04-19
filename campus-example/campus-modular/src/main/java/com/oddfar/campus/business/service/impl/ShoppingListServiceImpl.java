package com.oddfar.campus.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.ShoppingList;
import com.oddfar.campus.business.domain.Product;
import com.oddfar.campus.business.mapper.ProductMapper;
import com.oddfar.campus.business.mapper.ShoppingListMapper;
import com.oddfar.campus.business.service.ShoppingListService;
import com.oddfar.campus.common.domain.PageResult;
import com.oddfar.campus.common.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private ProductMapper productMapper;

    @Override
    public PageResult<ShoppingList> page(ShoppingList shoppingList) {
        // 先查询购物车分页数据
        PageResult<ShoppingList> pageResult = shoppingListMapper.page(shoppingList);

        // 获取所有购物车项
        List<ShoppingList> records = pageResult.getRows();

        // 为每个购物车项设置对应的商品信息
        records.forEach(item -> {
            Product product = productMapper.selectById(item.getProductId());
            item.setProduct(product);
        });

        return pageResult;
    }

    @Override
    public int insertShoppingList(ShoppingList shoppingList) {
        PageResult<ShoppingList> pageResult = shoppingListMapper.page(shoppingList);
        List<ShoppingList> records = pageResult.getRows();

        if (records.isEmpty()|| SecurityUtils.getUserId().equals(records.get(0).getCreateUser())) {
            return shoppingListMapper.insert(shoppingList);
        } else {
            ShoppingList existingItem = records.get(0);
            existingItem.setQuantity(existingItem.getQuantity() + shoppingList.getQuantity());
            return shoppingListMapper.updateById(existingItem);
        }
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