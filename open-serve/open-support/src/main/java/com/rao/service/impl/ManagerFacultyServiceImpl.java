package com.rao.service.impl;

import java.util.Date;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rao.dao.campus.RainCampusFacultyDao;
import com.rao.dao.campus.RainFacultyDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.PageFacultyDTO;
import com.rao.pojo.dto.SaveFacultyDTO;
import com.rao.pojo.entity.campus.RainCampusFaculty;
import com.rao.pojo.entity.campus.RainFaculty;
import com.rao.pojo.vo.PageFacultyVO;
import com.rao.service.ManagerFacultyService;
import com.rao.util.common.CopyUtil;
import com.rao.util.common.TwiterIdUtil;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 院系管理 service 实现
 *
 * @author raojing
 * @date 2020/1/26 13:29
 */
@Service
public class ManagerFacultyServiceImpl implements ManagerFacultyService {

    @Resource
    private RainFacultyDao rainFacultyDao;
    @Resource
    private RainCampusFacultyDao rainCampusFacultyDao;

    @Override
    public PageResult<PageFacultyVO> pageFaculty(PageParam pageParam, PageFacultyDTO pageFacultyDTO) {
        // 分页查询
        PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize());
        Example example = new Example(RainFaculty.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(pageFacultyDTO.getFacultyName())) {
            criteria.andLike("facultyName", pageFacultyDTO.getFacultyName());
        }
        List<RainFaculty> faculties = rainFacultyDao.selectByExample(example);
        PageInfo<RainFaculty> pageInfo = PageInfo.of(faculties);
        // 封装视图对象
        List<PageFacultyVO> pageFacultyVOS = CopyUtil.transToObjList(faculties, PageFacultyVO.class);
        return PageResult.build(pageInfo.getTotal(), pageFacultyVOS);
    }

    @Override
    public String addFaculty(SaveFacultyDTO saveFacultyDTO) {
        Date now = new Date();
        RainFaculty faculty = new RainFaculty();
        Long facultyId = TwiterIdUtil.getTwiterId();
        faculty.setId(facultyId);
        faculty.setFacultyName(saveFacultyDTO.getFacultyName());
        faculty.setCreateTime(now);
        faculty.setUpdateTime(now);
        rainFacultyDao.insertSelective(faculty);
        return String.valueOf(facultyId);
    }

    @Override
    public String updateFaculty(Long id, SaveFacultyDTO saveFacultyDTO) {
        RainFaculty faculty = rainFacultyDao.selectByPrimaryKey(id);
        if (faculty == null) {
            throw BusinessException.operate("院系不存在");
        }
        faculty.setFacultyName(saveFacultyDTO.getFacultyName());
        faculty.setUpdateTime(new Date());
        rainFacultyDao.updateByPrimaryKey(faculty);
        return String.valueOf(id);
    }

    @Override
    public void delete(Long id) {
        Example example = new Example(RainCampusFaculty.class);
        example.createCriteria().andEqualTo("facultyId", id);
        int count = rainCampusFacultyDao.selectCountByExample(example);
        if (count > 0) {
            throw BusinessException.operate("院系已关联学校不能删除");
        }
        rainFacultyDao.deleteByPrimaryKey(id);
    }

}
