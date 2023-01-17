package com.ead.authuserms.dtos;

import com.ead.authuserms.enums.CourseLevelEnum;
import com.ead.authuserms.enums.CourseStatusEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDTO {

    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatusEnum courseStatus;
    private UUID userInstructor;
    private CourseLevelEnum courseLevel;

}
