package com.rao.pojo.entity.campus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity - 学校
 *
 * @author raojng
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAIN_CAMPUS")
public class RainCampus implements Serializable {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 学校名称
     */
    @Column(name = "campus_name")
    private String campusName;

    /**
     * 学校logo
     */
    @Column(name = "campus_logo")
    private String campusLogo;

    /**
     * 省份code
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 省份名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市code
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 权重
     */
    @Column(name = "weight")
    private Integer weight;

    /**
     * 状态 1-启用 2-禁用
     */
    @Column(name = "status")
    private Integer status;

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
