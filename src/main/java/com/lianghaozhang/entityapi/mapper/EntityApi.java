package com.lianghaozhang.entityapi.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lianghaozhang.entityapi.entity.ChangeFieldStructure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface EntityApi {

    @DS("entity")
    @Select("select dbTableName from sys_entityinfo")
    List<String> getAllDbTableName();

    @DS("entity")
    // 通过sys_entityinfo.id查到表名
    @Select("select dbTableName from sys_entityinfo where id = #{id}")
    String getTableNameById(@Param("id") Integer id);

    @DS("entity")
    // 通过sys_entityfields.id查到字段名称
    @Select("select fieldName from sys_entityfields where id = #{id}")
    String getFieldNameById(@Param("id") Integer id);

    // 通过fieldId修改更新后的数据
   /* @Update("update sys_entityfields set fieldName = #{newFieldName} where id = #{id}")
    void updateFieldById(@Param("id") Integer id, @Param("newFieldName") String newFieldName);*/


    /*
    *  @Update("<script>" +
            "ALTER TABLE ${changeFieldStructure.tableName} " +
            "CHANGE ${changeFieldStructure.oldFieldName} ${changeFieldStructure.newFieldName} " +
            "${changeFieldStructure.newFieldType} " +
            "<if test='changeFieldStructure.isNotNull == \"1\"'>" +
            "NOT NULL " +
            "</if>" +
            "<if test='changeFieldStructure.isUnique == \"1\"'>" +
            "UNIQUE " +
            "</if>" +
            "<if test='changeFieldStructure.isAutoIncrement == \"1\"'>" +
            "AUTO_INCREMENT " +
            "</if>" +
            "COMMENT #{changeFieldStructure.comment}" +
            "</script>")
    void alterField(@Param("changeFieldStructure")ChangeFieldStructure changeFieldStructure);
    *
    *
    * */

    @DS("entity")
    @Update("<script>" +
            "UPDATE sys_entityfields SET " +
            "fieldName = #{changeFieldStructure.newFieldName}, " +
            "fieldType = #{changeFieldStructure.newFieldType} " +
            "<if test='changeFieldStructure.isNotNull == \"1\"'>" +
            ", isAllowEmpty = '0' " +
            "</if>" +
            "<if test='changeFieldStructure.isNotNull == \"0\"'>" +
            ", isAllowEmpty = '1', isUnique = '0' " +
            "</if>" +
            "<if test='changeFieldStructure.isUnique == \"1\" and changeFieldStructure.isNotNull == \"1\"'>" +
            ", isUnique = '1' " +
            "</if>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateFieldById(@Param("changeFieldStructure") ChangeFieldStructure changeFieldStructure, @Param("id") int id);

}
