package cn.howardliu.sl.sc.boot;

public class GeetestConfig {
    private static final String geetest_id = "b0de35ea13198d7aebc187dbff220bb3";
    private static final String geetest_key = "228a5d08fc3573f98969c57307d55267";
    private static final boolean newfailback = true;

    public static final String getGeetest_id() {
        return geetest_id;
    }

    public static final String getGeetest_key() {
        return geetest_key;
    }

    public static final boolean isnewfailback() {
        return newfailback;
    }
}
