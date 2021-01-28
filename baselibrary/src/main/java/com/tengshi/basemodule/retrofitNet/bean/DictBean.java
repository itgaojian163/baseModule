package com.tengshi.basemodule.retrofitNet.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * 作者: adam
 * 日期: 2020/4/1 - 12:30 PM
 * 邮箱: itgaojian@163.com
 * 描述: 字典Bean
 */
public class DictBean implements IPickerViewData {

    /**
     * dictionaryCode : string
     * dictionaryId : string
     * dictionaryName : string
     * dictionaryParentId : string
     * dictionaryParentName : string
     * dictionarySort : string
     * dictionarySummary : string
     * parent : true
     * subDictionary : [{}]
     */

    private String dictionaryCode;
    private String dictionaryId;
    private String dictionaryName;
    private String dictionaryParentId;
    private String dictionaryParentName;
    private String dictionarySort;
    private String dictionarySummary;
    private boolean isCheckEd;
    private boolean parent;
    private List<SubDictionaryBean> subDictionary;


    public boolean isCheckEd() {
        return isCheckEd;
    }

    public void setCheckEd(boolean checkEd) {
        isCheckEd = checkEd;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDictionaryParentId() {
        return dictionaryParentId;
    }

    public void setDictionaryParentId(String dictionaryParentId) {
        this.dictionaryParentId = dictionaryParentId;
    }

    public String getDictionaryParentName() {
        return dictionaryParentName;
    }

    public void setDictionaryParentName(String dictionaryParentName) {
        this.dictionaryParentName = dictionaryParentName;
    }

    public String getDictionarySort() {
        return dictionarySort;
    }

    public void setDictionarySort(String dictionarySort) {
        this.dictionarySort = dictionarySort;
    }

    public String getDictionarySummary() {
        return dictionarySummary;
    }

    public void setDictionarySummary(String dictionarySummary) {
        this.dictionarySummary = dictionarySummary;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public List<SubDictionaryBean> getSubDictionary() {
        return subDictionary;
    }

    public void setSubDictionary(List<SubDictionaryBean> subDictionary) {
        this.subDictionary = subDictionary;
    }

    @Override
    public String getPickerViewText() {
        return getDictionaryName();
    }

    public static class SubDictionaryBean {
    }


    @Override
    public String toString() {
        return "DictBean{" +
                "dictionaryName='" + dictionaryName + '\'' +
                ", isCheckEd=" + isCheckEd +
                '}';
    }
}
