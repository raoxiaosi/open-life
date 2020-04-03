package com.rao.pojo.entity.talk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity - 讨论墙
 *
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAIN_TALK_WALL")
public class RainTalkWall implements Serializable {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 会员id
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 会员手机号码
     */
    @Column(name = "member_phone")
    private String memberPhone;

    /**
     * 会员昵称
     */
    @Column(name = "member_nickname")
    private String memberNickname;

    /**
     * 讨论类型
     */
    @Column(name = "talk_type")
    private Integer talkType;

    /**
     * 谈论内容
     */
    @Column(name = "talk_content")
    private String talkContent;

    /**
     * 显示方式 1-匿名 2-实名
     */
    @Column(name = "show_type")
    private Integer showType;

    /**
     * 显示状态 1-显示 2-不显示
     */
    @Column(name = "show_status")
    private Integer showStatus;

    /**
     * 学校id
     */
    @Column(name = "campus_id")
    private Long campusId;

    /**
     * 学校名称
     */
    @Column(name = "campus_name")
    private String campusName;

    /**
     * 院系id
     */
    @Column(name = "faculty_id")
    private Long facultyId;

    /**
     * 院系名称
     */
    @Column(name = "faculty_name")
    private String facultyName;

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
     * 区县code
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 区县名称
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 详细地址
     */
    @Column(name = "address_info")
    private String addressInfo;

    /**
     * 图片(多张图片以逗号隔开)
     */
    @Column(name = "pictures")
    private String pictures;

    /**
     * 热度
     */
    @Column(name = "hotspot")
    private Integer hotspot;

    /**
     * 点赞数
     */
    @Column(name = "praise_number")
    private Integer praiseNumber;

    /**
     * 评论数
     */
    @Column(name = "comment_number")
    private Integer commentNumber;

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
