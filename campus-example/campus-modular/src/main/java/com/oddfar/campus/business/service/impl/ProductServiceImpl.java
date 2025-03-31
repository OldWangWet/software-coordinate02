package com.oddfar.campus.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.Product;
import com.oddfar.campus.business.mapper.ProductMapper;
import com.oddfar.campus.business.service.ProductService;
import com.oddfar.campus.common.domain.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品管理业务实现层
 *
 * @author oddfar
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public PageResult<Product> page(Product product) {
        return productMapper.page(product);
    }

    @Override
    public int insertProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateById(product);
    }

    @Override
    public int deleteProduct(Product product) {
        return productMapper.deleteById(product.getId());
    }
}