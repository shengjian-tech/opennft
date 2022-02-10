package net.shengjian.sms.util;

public enum SmsTypeEnum {
    验证码("验证码", 1),
    通知("通知", 2),
    补充条件("补充条件", 3),
    满足条件("满足条件", 4),
    资产预警("资产预警", 5);

    public String name;
    public Integer code;

    SmsTypeEnum(String name, int code) {
        this.name=name;
        this.code=code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}