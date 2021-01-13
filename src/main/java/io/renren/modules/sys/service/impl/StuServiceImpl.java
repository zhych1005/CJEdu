package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.dao.StuDao;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.service.StuService;
import io.renren.modules.sys.vo.DeductionVO;
import io.renren.modules.sys.vo.StuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service("StuService")
public class StuServiceImpl extends ServiceImpl<StuDao, StuEntity> implements StuService {

    private final StuDao stuDao;

    @Autowired
    public StuServiceImpl(StuDao stuDao) {
        this.stuDao = stuDao;
    }


    /**
     * 添加学生
     *
     * @param stu 学生对象
     * @return int
     */
    @Override
    public int addStu(StuEntity stu) {
        return stuDao.addStu(stu);
    }

    /**
     * 学生列表的查询
     *
     * @return List<StuVO>
     */
    @Override
    public PageUtils selectAllStu(Map<String, Object> params) {
        int pageNo = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("limit").toString());
        String stuName = (String) params.get("stuName");
        String subName = (String) params.get("subName");
        String level = (String) params.get("level");
        IPage<StuVO> page = stuDao.selectAllStu(new Page<>(pageNo, pageSize), stuName, subName, level);
        return new PageUtils(page);
    }

    /**
     * 通过学生的id查询学生的详情
     * @param stuId 学生的id
     * @return stuVO
     */
    @Override
    public StuVO selectStuInfo(Integer stuId) {
        System.out.println(stuDao.selectStuInfo(stuId));
        return stuDao.selectStuInfo(stuId);
    }

    /**
     * 通过学生的姓名查询该姓名的学生数量
     * @param stuName 学生姓名
     * @return 数量
     */
    @Override
    public Integer findStuByName(String stuName) {
        return stuDao.findStuByName(stuName);
    }

    /**
     * 通过学生的id删除学生（修改状态为0）
     * @param stuId 学生的id
     * @return 删除状态
     */
    @Override
    public int deleteStuByStuId(Integer stuId) {
        return stuDao.deleteStuByStuId(stuId);
    }

    /**
     * 修改学生的基本信息
     * @param stu 修改的学生信息
     * @return 修改结果
     */
    @Override
    public int updateStuById(StuEntity stu) {
        return stuDao.updateStuById(stu);
    }

    /**
     * 查询所有学生的id与剩余课时
     * @return DeductionVO
     */
    @Override
    public ArrayList<DeductionVO> deductionInfo() {
        return stuDao.deductionInfo();
    }

    /**
     * 通过学生的id查询学生的详情用于发微信通知
     * @param stuId 学生的id
     * @return DeductionVO
     */
    @Override
    public DeductionVO findStuById(Integer stuId) {
        return stuDao.findStuById(stuId);
    }

//    /**
//     * 学生课时的批量扣减
//     * @param list 学生的id
//     * @return 扣减状态
//     */
//    @Override
//    public int updateList(List<Integer> list) {
//        return stuDao.updateList(list);
//    }
}