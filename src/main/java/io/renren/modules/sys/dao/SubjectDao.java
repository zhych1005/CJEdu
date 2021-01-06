package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.SubjectEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectDao extends BaseMapper<SubjectEntity> {

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