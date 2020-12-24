package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.vo.StuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuDao extends BaseMapper<StuEntity> {

    int addStu(StuEntity stu);

    List<StuVO> selectAllStu();
}