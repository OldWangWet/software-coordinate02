package com.oddfar.campus.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oddfar.campus.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@TableName(value = "userlike")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLike extends BaseEntity implements Serializable {
    @TableId
    private Long id;

    private Long userid;

    private Long category;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}