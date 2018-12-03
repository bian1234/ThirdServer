package com.prostate.ocr.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;


@Setter
@Getter
public class IdCardBean implements Serializable {

    private String name;        //string	姓名
    private String sex;        //string	性别
    private String nation;        //string	民族
    private String birth;        //string	出生日期
    private String address;        //string	地址
    private String id;        //string	身份证号
    private int[] name_confidence_all;        //array(int)	证件姓名置信度，取值范围[0,100]
    private int[] sex_confidence_all;        //array(int)	性别置信度，取值范围[0,100]]
    private int[] nation_confidence_all;        //array(int)	民族置信度，取值范围[0,100]
    private int[] birth_confidence_all;        //array(int)	出生日期置信度，取值范围[0,100]
    private int[] address_confidence_all;        //array(int)	地址置信度，取值范围[0,100]
    private int[] id_confidence_all;        //array(int)	身份证号置信度，取值范围[0,100]

    @Override
    public String toString() {
        return "IdCardBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", birth='" + birth + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", name_confidence_all=" + Arrays.toString(name_confidence_all) +
                ", sex_confidence_all=" + Arrays.toString(sex_confidence_all) +
                ", nation_confidence_all=" + Arrays.toString(nation_confidence_all) +
                ", birth_confidence_all=" + Arrays.toString(birth_confidence_all) +
                ", address_confidence_all=" + Arrays.toString(address_confidence_all) +
                ", id_confidence_all=" + Arrays.toString(id_confidence_all) +
                '}';
    }
}
