package io.renren.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.vo.DeductionVO;
import io.renren.modules.sys.vo.StuVO;

import java.util.ArrayList;
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

    /**
     * 通过学生的id删除学生（修改状态为0）
     * @param stuId 学生的id
     * @return 删除状态
     */
    int deleteStuByStuId(Integer stuId);

    /**
     * 修改学生的基本信息
     * @param stu 修改的学生信息
     * @return 修改结果
     */
    int updateStuById(StuEntity stu);

    /**
     * 查询所有学生的id与剩余课时
     * @return DeductionVO
     */
    ArrayList<DeductionVO> deductionInfo();

     /**
     * 通过学生的id查询学生的详情用于发微信通知
     * @param stuId 学生的id
     * @return DeductionVO
     */
    DeductionVO findStuById(Integer stuId);
}