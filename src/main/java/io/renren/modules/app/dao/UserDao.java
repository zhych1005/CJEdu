/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    UserEntity selectByOpenid(String openid);

    UserEntity selectByMobile(String mobile);

    ArrayList<UserEntity> selectAllAssign();

    UserEntity selectOpenidByName(String username);

    ArrayList<UserEntity> selectAllDriver();

    String selectAssignOpenidByUserId(Integer assignId);

    int addOpenid(UserEntity userEntity);

    int selectUserStatus(Integer userId);

    String selectOpenidByUserId(Integer userId);
}