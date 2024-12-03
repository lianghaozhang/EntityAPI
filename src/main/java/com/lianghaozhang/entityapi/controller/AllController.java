package com.lianghaozhang.entityapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.lianghaozhang.entityapi.entity.ChangeFieldStructure;
import com.lianghaozhang.entityapi.mapper.AllApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AllController {

    @Autowired
    private AllApi allApi;

    @GetMapping("/test")
    public List<String> test() {
        return allApi.getAllStudentName();
    }

    /*
     * 修改字段信息
     * 参数：表id，字段id，新字段名，新字段数据类型，是否可以为空，是否唯一，是否自增，备注
     * */
    /*
     * 表id: 333
     * 字段id: 4914
     * */
    @PostMapping("/changeField")
//    @Transactional(rollbackFor = {SQLException.class, DataAccessException.class})
    public int changeField(@RequestBody JSONObject jsonObject) {
        int tableId = jsonObject.getInteger("tableId");
        int fieldId = jsonObject.getInteger("fieldId");
        String newFieldName = jsonObject.getString("newFieldName");
        String newFieldType = jsonObject.getString("newFieldType");
        int newFieldTypeLength = jsonObject.getInteger("newFieldTypeLength");
        String isNotNull = jsonObject.getString("isNotNull");
        String isUnique = jsonObject.getString("isUnique");
        String comment = jsonObject.getString("comment");
        // 通过sys_entity.id查到表名
        String tableName = allApi.getTableNameById(tableId);
        System.out.println(tableName);
        // 通过sys_entityfields.id查到字段名称
        String fieldName = allApi.getFieldNameById(fieldId);
        System.out.println(fieldName);
        ChangeFieldStructure changeFieldStructure = new ChangeFieldStructure(tableName, fieldName, newFieldName, newFieldType.toUpperCase(), newFieldTypeLength, isNotNull, isUnique, comment);
        // 修改表结构
        allApi.alterField(changeFieldStructure);
        return allApi.updateFieldById(changeFieldStructure, fieldId);
        // 检查表结构是否修改成功
        /*if (newFieldName.equals(allApi.getFieldNameById(fieldId))) {
            System.out.println("表结构修改成功");
            // 表结构更新成功，同步修改sys_entityfields中对应的数据
        }*/
    }
}
