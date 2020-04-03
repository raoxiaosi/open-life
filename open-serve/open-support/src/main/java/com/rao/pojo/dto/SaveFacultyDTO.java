package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 保存院系 数据传输模型
 * @author raojing
 * @date 2020/1/26 19:23
 */
@Data
public class SaveFacultyDTO {

    /**
     * 院系名称
     */
    @NotBlank(message = "院系名称不能为空")
    private String facultyName;

}
