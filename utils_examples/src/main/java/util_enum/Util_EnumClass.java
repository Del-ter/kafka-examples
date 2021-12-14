package util_enum;


public enum Util_EnumClass {
    NORMAL_RUNNING(1,"正常运行"),
    NORMAL_STOP(2,"停机状态"),
    FAULT_STATUS(3,"故障状态");

    private int code;
    private String value = null;

    Util_EnumClass(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}

