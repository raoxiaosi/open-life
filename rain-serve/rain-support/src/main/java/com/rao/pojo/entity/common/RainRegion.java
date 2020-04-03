package com.rao.pojo.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity - 地区表
 *
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAIN_REGION")
public class RainRegion implements Serializable {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * 索引ID
     */
    @Id
    private String id;

    /**
     * 地区名称
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 地区父ID
     */
    @Column(name = "area_parent_id")
    private Long areaParentId;

    /**
     * 排序
     */
    @Column(name = "area_sort")
    private Integer areaSort;

    /**
     * 地区深度，从1开始
     */
    @Column(name = "area_deep")
    private Integer areaDeep;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private java.util.Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private java.util.Date updateTime;

}
