package com.oddfar.campus.business.service;

import com.oddfar.campus.business.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;

/**
 * 商品管理业务
 *
 * @author oddfar
 */
public interface ProductService extends IService<Product> {
    /**
     * 查询商品分页数据
     *
     * @param product 商品查询条件
     * @param pageParam 分页参数
     * @return 分页数据
     */
    PageResult<Product> page(Product product, PageParam pageParam);

    /**
     * 新增商品
     *
     * @param product 商品
     * @return 结果
     */
    int insertProduct(Product product);

    /**
     * 更新商品
     *
     * @param product 商品
     * @return 结果
     */
    int updateProduct(Product product);

    /**
     * 删除商品
     *
     * @param product 商品
     * @return 结果
     */
    int deleteProduct(Product product);
}