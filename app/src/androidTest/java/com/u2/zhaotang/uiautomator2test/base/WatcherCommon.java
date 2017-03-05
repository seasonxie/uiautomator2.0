package com.u2.zhaotang.uiautomator2test.base;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiWatcher;

import com.meizu.test.common.CommonUtil;
import com.meizu.test.common.DeviceHelper;
import com.u2.zhaotang.uiautomator2test.bussines.Contacts;

/**
 * 监听器
 */
public class WatcherCommon/* extends com.meizu.u2.runner.BaseRunner*/{
    private static WatcherCommon instance;
    private UiDevice mUiDevice;
    private DeviceHelper mHelper;

    private static int TIMEOUT = 500;

    public synchronized static WatcherCommon getInstance() {
        if (instance == null) {
            instance = new WatcherCommon();
        }
        return instance;
    }

    private WatcherCommon() {
        this.mUiDevice = Contacts.getInstance().getmUiDevice();
        this.mHelper =Contacts.getInstance().getmHelper();
    }

    public void cl(){
        mHelper.clickByText("易车");
    }


    public void pubWatcher(){
        mUiDevice.registerWatcher("pubWatcher", new UiWatcher() {

            @Override
            public boolean checkForCondition() {
                if(mHelper.isTextExists("允许",TIMEOUT)){
                    mHelper.clickByText("允许");
                    return true;
                }
                if(mHelper.isTextExists("忽略此版本",TIMEOUT)){
                    mHelper.clickByText("忽略此版本");
                    return true;
                }
                return false;
            }
        });
    }
}
