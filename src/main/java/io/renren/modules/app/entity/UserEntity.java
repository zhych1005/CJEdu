/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加盐
     */
    private String salt;

    /**
     * 用户的openid
     */
    private String openId;

    /**
     * 用户的昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户的头像
     */
    private String photo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户的类型
     */
    private Integer type;

    /**
     * 创建者id
     */
    private Integer createUserId;

    private Integer areaId;
}
