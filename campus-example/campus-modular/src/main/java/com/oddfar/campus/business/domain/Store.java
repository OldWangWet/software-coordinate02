package com.oddfar.campus.business.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oddfar.campus.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@TableName(value ="store")
@Data
@EqualsAndHashCode(callSuper = true)
public class Store extends BaseEntity implements Serializable {

    @TableId
    private Long id;


    @NotBlank(message = "名字不能为空")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
