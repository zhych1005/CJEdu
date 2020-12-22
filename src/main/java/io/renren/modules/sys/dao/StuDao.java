package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.vo.StuVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StuDao extends BaseMapper<StuEntity> {

    int addStu(StuEntity stu);

    StuVO selectAllStu();
}