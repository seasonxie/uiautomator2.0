package com.u2.zhaotang.uiautomator2test.bussines;

/**
 * Created by Administrator on 2016/12/31.
 */
public class MainPage extends BusinssBase {
    public static MainPage instance;
    MainPage(){
        super();
    }
    public synchronized static MainPage getInstance() {
        if (instance == null) {
            instance = new MainPage();
        }
        return instance;
    }
    public void ss(){
        mHelper.clickByText("天猫");
    }
}
