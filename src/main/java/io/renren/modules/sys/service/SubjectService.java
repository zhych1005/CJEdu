package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.SubjectEntity;

public interface SubjectService  extends IService<SubjectEntity> {

    /**
     * 学生课时信息的添加
     * @param subjectEntity 课程信息
     * @return 状态int
     */
    int addSub(SubjectEntity subjectEntity);

    /**
     * 学生课时的修改
     * @param stuId 学号
     * @return int
     */
    int setDown(Integer stuId);

    /**
     * 学生课程信息的修改
     * @param sub 课程信息
     * @return 修改的状态
     */
    int updateSubById(SubjectEntity sub);

    /**
     * 学生充值
     * @param sub 充值信息
     * @return 状态
     */
    int recharge(SubjectEntity sub);
}