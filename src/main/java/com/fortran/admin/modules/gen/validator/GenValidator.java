package com.fortran.admin.modules.gen.validator;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:46
 * @description: 生成表单时，对应的字段验证规则对象
 */
public class GenValidator {

    /**
     * 数据类型验证
     */
    private ValidatorData validatorData;

    /**
     * 是否必填
     */
    private String required;

    /**
     *验证长度范围
     */
    private String dataValidateLengthRange;
    /**
     *验证长度
     */
    private String dataValidateLength;
    /**
     *验证最大最小值
     */
    private String dataValidateMinMax;
    /**
     *验证单词数
     */
    private String dataValidateWords;
    /**
     *验证链接
     */
    private String dataValidateLinked;
    /**
     *正则验证
     */
    private String [] dataValidatePattern = {"numeric","alphanumeric"};
    /**
     *可验证数据类型
     */
    public enum ValidatorData{

        email("email"),tel("tel"),url("url"),number("number"),date("date");

        ValidatorData(String value){
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
