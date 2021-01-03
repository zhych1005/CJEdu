package io.renren.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.vo.StuVO;

import java.util.Map;

public interface StuService  extends IService<StuEntity> {

    int addStu(StuEntity stu);

    PageUtils selectAllStu(Map<String, Object> params);

    /**
     * 通过学生的id查询学生的详情
     * @param stuId 学生的id
     * @return StuVO
     */
    StuVO selectStuInfo(Integer stuId);

    /**
     * 通过学生的姓名查询该姓名的学生数量
     * @param stuName 学生姓名
     * @return 数量
     */
    Integer findStuByName(String stuName);
}