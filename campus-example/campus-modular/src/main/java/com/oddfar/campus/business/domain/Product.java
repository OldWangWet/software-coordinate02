package com.oddfar.campus.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oddfar.campus.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@TableName(value = "product")
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity implements Serializable {

    @TableId
    private Long id;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    private String introduce;

    private String image;

    @NotNull(message = "库存不能为空")
    private Integer stock;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}