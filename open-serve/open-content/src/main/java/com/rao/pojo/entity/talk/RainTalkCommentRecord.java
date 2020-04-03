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
 * Entity - 讨论评论记录
 *
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAIN_TALK_COMMENT_RECORD")
public class RainTalkCommentRecord implements Serializable {

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
     * 评论用户id
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 评论用户昵称
     */
    @Column(name = "member_nickname")
    private String memberNickname;

    /**
     * 评论用户头像
     */
    @Column(name = "member_avatar")
    private String memberAvatar;

    /**
     * 被评论用户id
     */
    @Column(name = "to_member_id")
    private Long toMemberId;

    /**
     * 被评论用户昵称
     */
    @Column(name = "to_member_nickname")
    private String toMemberNickname;

    /**
     * 被评论用户头像
     */
    @Column(name = "to_member_avatar")
    private String toMemberAvatar;

    /**
     * 根评论id
     */
    @Column(name = "root_id")
    private Long rootId;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private java.util.Date createTime;

}
