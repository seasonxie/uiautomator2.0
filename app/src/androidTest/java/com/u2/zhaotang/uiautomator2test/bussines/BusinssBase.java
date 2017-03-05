package com.u2.zhaotang.uiautomator2test.bussines;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.uiautomator.UiDevice;

import com.meizu.test.common.CommonUtil;
import com.meizu.test.common.DeviceHelper;

/**
 * Created by Administrator on 2016/12/31.
 */
public class BusinssBase {

    public static BusinssBase instance;
    public Instrumentation mInstrumentation;
    public Context mContext;
    public UiDevice mUiDevice;
    public CommonUtil mUtil;
    public DeviceHelper mHelper;


    public synchronized static BusinssBase getInstance() {
        if (instance == null) {
            instance = new BusinssBase();
        }
        return instance;
    }

    public BusinssBase() {
        this.mInstrumentation = Contacts.getInstance().getmInstrumentation();
        this.mUiDevice = Contacts.getInstance().getmUiDevice();
        this.mUtil = Contacts.getInstance().getmUtil();
        this.mHelper = Contacts.getInstance().getmHelper();
        this.mContext = Contacts.getInstance().getInstrumentationContext();
    }
}
