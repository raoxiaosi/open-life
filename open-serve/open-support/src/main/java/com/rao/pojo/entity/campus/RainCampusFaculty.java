package com.rao.pojo.entity.campus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity - 学校院系关联
 *
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAIN_CAMPUS_FACULTY")
public class RainCampusFaculty implements Serializable {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * 学校id
     */
    @Column(name = "campus_id")
    private Long campusId;

    /**
     * 院系id
     */
    @Column(name = "faculty_id")
    private Long facultyId;

}
