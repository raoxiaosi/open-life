package com.rao.service;

import com.rao.pojo.dto.PageFacultyDTO;
import com.rao.pojo.dto.SaveFacultyDTO;
import com.rao.pojo.vo.PageFacultyVO;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;

/**
 * 院系管理 service
 *
 * @author raojing
 * @date 2020/1/26 13:27
 */
public interface ManagerFacultyService {

    /**
     * 院系列表-分页
     *
     * @param pageParam
     * @param pageFacultyDTO
     * @return
     */
    PageResult<PageFacultyVO> pageFaculty(PageParam pageParam, PageFacultyDTO pageFacultyDTO);

    /**
     * 新增院系
     *
     * @param saveFacultyDTO
     * @return
     */
    String addFaculty(SaveFacultyDTO saveFacultyDTO);

    /**
     * 修改院系
     *
     * @param id
     * @param saveFacultyDTO
     * @return
     */
    String updateFaculty(Long id, SaveFacultyDTO saveFacultyDTO);

    /**
     * 删除院系-判断是否被占用
     *
     * @param id
     */
    void delete(Long id);
}
