package io.renren.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.StuEntity;

public interface StuService  extends IService<StuEntity> {
    int addStu(StuEntity stu);
}