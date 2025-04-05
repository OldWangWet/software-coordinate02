package com.oddfar.campus.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oddfar.campus.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "shoppingorder")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingOrder extends BaseEntity implements Serializable {

    @TableId
    private Long id;

    //@NotNull(message = "商品ID不能为空")
    private Long productId;

    private Integer isFinished;

    //@NotNull(message = "数量不能为空")
    private Integer quantity;

    //@NotNull(message = "价格不能为空")
    private BigDecimal price;

    @TableField(exist = false)
    private Product product;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}