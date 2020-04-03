package com.rao.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 院系列表 视图模型
 *
 * @author raojing
 * @date 2020/1/26 18:15
 */
@Data
public class PageFacultyVO {

    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 院系名称
     */
    private String facultyName;

}
