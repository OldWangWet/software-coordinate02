package com.oddfar.campus.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.ShoppingOrder;
import com.oddfar.campus.business.domain.Product;
import com.oddfar.campus.business.mapper.ProductMapper;
import com.oddfar.campus.business.mapper.ShoppingOrderMapper;
import com.oddfar.campus.business.service.ShoppingOrderService;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单管理业务实现层
 *
 * @author oddfar
 */
@Service
public class ShoppingOrderServiceImpl extends ServiceImpl<ShoppingOrderMapper, ShoppingOrder>
        implements ShoppingOrderService {

    @Resource
    private ShoppingOrderMapper shoppingOrderMapper;

    @Resource
    private ProductMapper productMapper;

    @Override
    public PageResult<ShoppingOrder> page(ShoppingOrder shoppingOrder, PageParam pageParam) {
        // 先查询购物车分页数据
        PageResult<ShoppingOrder> pageResult = shoppingOrderMapper.page(shoppingOrder,pageParam);

        // 获取所有购物车项
        List<ShoppingOrder> records = pageResult.getRows();

        // 为每个购物车项设置对应的商品信息
        records.forEach(item -> {
            Product product = productMapper.selectById(item.getProductId());
            item.setProduct(product);
        });

        return pageResult;
    }

    @Override
    public int insertShoppingOrder(ShoppingOrder shoppingOrder) {
        return shoppingOrderMapper.insert(shoppingOrder);
    }

    @Override
    public int updateShoppingOrder(ShoppingOrder shoppingOrder) {
        return shoppingOrderMapper.updateById(shoppingOrder);
    }

    @Override
    public int deleteShoppingOrder(ShoppingOrder shoppingOrder) {
        return shoppingOrderMapper.deleteById(shoppingOrder.getId());
    }

    @Override
    public int finishOrder(Long id) {
        ShoppingOrder order = shoppingOrderMapper.selectById(id);
        if (order != null) {
            order.setIsFinished(1); // 1表示已完成
            return shoppingOrderMapper.updateById(order);
        }
        return 0;
    }
}