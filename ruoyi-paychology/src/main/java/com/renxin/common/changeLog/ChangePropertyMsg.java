package com.renxin.common.changeLog;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author FengQing
 * @program yf-client
 * @description
 * @date 2023/11/01
 */
@Getter
@Setter
@ToString
public class ChangePropertyMsg {
    /**
     * 变更信息
     */
    private String changeMsg;
    /**
     * 变更属性集合
     */
    private List<String> properties;

    /**
     * 变更信息(JSON数组)
     */
    private JSONArray changeList;

    public ChangePropertyMsg() {
        this.changeList = new JSONArray();
    }

    public void addChange(String fieldName, Object oldValue, Object newValue, String attribute) {
        JSONObject changeObject = new JSONObject();
        changeObject.put("fieldName", fieldName);
        changeObject.put("attribute", attribute);
        if (ObjectUtils.isNotEmpty(oldValue)) {
            changeObject.put("oldBean", oldValue);
        } else {
            changeObject.put("oldBean", "");
        }
        changeObject.put("newBean", newValue);
        changeObject.put("updateTime", formatLocalDateTime(LocalDateTime.now()));
        //changeObject.put("userName", "111111");

        changeList.add(changeObject);
    }

    private String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    public JSONArray getChangeList() {
        return changeList;
    }
}