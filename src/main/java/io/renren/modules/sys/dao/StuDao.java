package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.vo.StuOpenidVO;
import io.renren.modules.sys.vo.StuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface StuDao extends BaseMapper<StuEntity> {

    /**
     * 学生的添加
     * @param stu 学生对象
     * @return 学生的id
     */
    int addStu(StuEntity stu);

    /**
     * 查询学生的列表
     * @param page 页数
     * @param stuName 学生的姓名
     * @param subName 学科
     * @return 分页后的学生的列表
     */
    IPage<StuVO> selectAllStu(Page<StuVO> page, String stuName, String subName);


    /**
     * 查询所有学生的id与openid用于发送消息
     * @return openid与id
     */
    ArrayList<StuOpenidVO> selectAllStuOPenId();

    /**
     * 通过学生的id查询学生的详情
     * @param stuId 学生的id
     * @return stuVo
     */
    StuVO selectStuInfo(Integer stuId);

    /**
     * 通过学生的姓名查询该姓名的学生数量
     * @param stuName 学生姓名
     * @return 数量
     */
    Integer findStuByName(String stuName);
}