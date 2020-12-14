package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.SubjectEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectDao extends BaseMapper<SubjectEntity> {

    int addSub(SubjectEntity subjectEntity);
}