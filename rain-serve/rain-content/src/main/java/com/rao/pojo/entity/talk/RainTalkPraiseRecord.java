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
 * Entity - 讨论点赞记录
 *
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAIN_TALK_PRAISE_RECORD")
public class RainTalkPraiseRecord implements Serializable {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 讨论id
     */
    @Column(name = "talk_id")
    private Long talkId;

    /**
     * 用户id
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 用户昵称
     */
    @Column(name = "member_nickname")
    private String memberNickname;

    /**
     * 用户头像
     */
    @Column(name = "member_avatar")
    private String memberAvatar;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private java.util.Date createTime;

}
