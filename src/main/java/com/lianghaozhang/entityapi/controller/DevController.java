package com.lianghaozhang.entityapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.lianghaozhang.entityapi.entity.ChangeFieldStructure;
import com.lianghaozhang.entityapi.mapper.DevApi;
import com.lianghaozhang.entityapi.mapper.EntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/devApi")
@EnableTransactionManagement
public class DevController {

    @Autowired
    private DevApi devApi;

    @Autowired
    private EntityApi entityApi;

    @GetMapping("/test")
    public List<String> test() {
        return devApi.getAllStudentName();
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
    @Transactional(rollbackFor = {SQLException.class, DataAccessException.class})
    public void changeField(@RequestBody JSONObject jsonObject) {
        int tableId = 333;
        int fieldId = 4914;
        String newFieldName = "name100";
        // 通过sys_entity.id查到表名
        String tableName = entityApi.getTableNameById(tableId);
        System.out.println(tableName);
        // 通过sys_entityfields.id查到字段名称
        String fieldName = entityApi.getFieldNameById(fieldId);
        System.out.println(fieldName);
        String newFieldType = "varchar";
        ChangeFieldStructure changeFieldStructure = new ChangeFieldStructure(tableName, fieldName, newFieldName, newFieldType.toUpperCase(), 1,"1", "1", "姓名2");
        // 修改表结构
        devApi.alterField(changeFieldStructure);
        // 检查表结构是否修改成功
        if (newFieldName.equals(entityApi.getFieldNameById(fieldId))) {
            // 表结构更新成功，同步修改sys_entityfields中对应的数据
            int res = entityApi.updateFieldById(changeFieldStructure, fieldId);
            System.out.println(res);
        }
    }
}
