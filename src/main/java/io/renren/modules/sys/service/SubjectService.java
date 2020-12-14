package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.SubjectEntity;

public interface SubjectService  extends IService<SubjectEntity> {

    int addSub(SubjectEntity subjectEntity);
}