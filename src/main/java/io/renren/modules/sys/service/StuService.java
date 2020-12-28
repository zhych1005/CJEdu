package io.renren.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.StuEntity;

import java.util.Map;

public interface StuService  extends IService<StuEntity> {
    int addStu(StuEntity stu);

    PageUtils selectAllStu(Map<String, Object> params);
}