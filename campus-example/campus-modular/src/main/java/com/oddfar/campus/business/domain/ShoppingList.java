package com.oddfar.campus.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oddfar.campus.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@TableName(value = "shoppinglist")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingList extends BaseEntity implements Serializable {

    @TableId
    private Long id;

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "商品ID不能为空")
    private Long productId;

    @NotNull(message = "数量不能为空")
    private Integer quantity;

    private Date createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}