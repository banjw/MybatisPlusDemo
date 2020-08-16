package com.learn.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -4443113102506974071L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private Integer status;
    //自动填充，必须要自己写一个handler实现MetaObjectHandler接口
    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    private Date createTime;
    //如果是FieldFill.INSERT_UPDATE，handler里在insert方法里没有设置updateTime的值，那也不会有值
    //如果是FieldFill.UPDATE，handler里在insert方法里有设置updateTime的值，那也不会更新数据库
    @TableField(value = "update_Time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //乐观锁
    @Version
    private Integer version;
}
