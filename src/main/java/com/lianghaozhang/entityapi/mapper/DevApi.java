package com.lianghaozhang.entityapi.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lianghaozhang.entityapi.entity.ChangeFieldStructure;
import com.lianghaozhang.entityapi.entity.TestLhz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.aspectj.lang.annotation.DeclareParents;

import java.util.List;


@Mapper
public interface DevApi {

    @DS("dev")
    @Select("select student_name from sms_student")
    List<String> getAllStudentName();

    /*@Update("alter table ${changeFieldStructure.tableName} change ${changeFieldStructure.oldFieldId} ${changeFieldStructure.newFieldId} ${changeFieldStructure.newFieldType} comment #{changeFieldStructure.comment}")
    void alterField(@Param("changeFieldStructure")ChangeFieldStructure changeFieldStructure);*/

    /*@Update("alter table ${changeFieldStructure.tableName} change ${changeFieldStructure.oldFieldName} ${changeFieldStructure.newFieldName} ${changeFieldStructure.newFieldType} comment #{changeFieldStructure.comment}")
    void alterField(@Param("changeFieldStructure")ChangeFieldStructure changeFieldStructure);*/


    @DS("dev")
    // 修改字段接口
    @Update("<script>" +
            "ALTER TABLE ${changeFieldStructure.tableName} " +
            "CHANGE ${changeFieldStructure.oldFieldName} ${changeFieldStructure.newFieldName} " +
            "${changeFieldStructure.newFieldType} " +
            "<if test='changeFieldStructure.isNotNull == \"1\"'>" +
            "NOT NULL " +
            "</if>" +
            "<if test='changeFieldStructure.isNotNull == \"0\"'>" +
            "NULL " +
            "</if>" +
            "<if test='changeFieldStructure.isUnique == \"1\" and changeFieldStructure.isNotNull == \"1\"'>" +
            "UNIQUE " +
            "</if>" +
            "COMMENT #{changeFieldStructure.comment}" +
            "</script>")
    void alterField(@Param("changeFieldStructure") ChangeFieldStructure changeFieldStructure);

}
