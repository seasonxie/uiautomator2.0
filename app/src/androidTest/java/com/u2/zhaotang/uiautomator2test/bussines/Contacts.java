package com.u2.zhaotang.uiautomator2test.bussines;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Environment;
import android.os.RemoteException;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import com.meizu.datamaker.DataMakerUtil;
import com.meizu.test.common.AppInfo;
import com.meizu.test.common.CommonUtil;
import com.meizu.test.common.DeviceHelper;
import com.meizu.test.common.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by linxuan on 2016/6/1.
 */
public class Contacts {

    private static Contacts instance;
    private  Instrumentation mInstrumentation;
    private  Context instrumentationContext;
    private  Context targetContext;
    private  UiDevice mUiDevice;
    private  CommonUtil mUtil;
    private  DeviceHelper mHelper;
    private DataMakerUtil dataMakerUtil;

    private static int TIMEOUT = 5000;



    public synchronized static Contacts getInstance(Instrumentation instrumentation) {
        if (instance == null) {
            instance = new Contacts(instrumentation);
        }
        return instance;
    }
    public synchronized static Contacts getInstance() {
        if (instance == null) {
            return null;
        }
        return instance;
    }

    private Contacts(Instrumentation instrumentation) {
        this.mInstrumentation = instrumentation;
        this.mUiDevice = UiDevice.getInstance(instrumentation);
        this.mUtil = CommonUtil.getInstance(instrumentation);
        this.mHelper = DeviceHelper.getInstance(instrumentation);
        this.instrumentationContext = instrumentation.getContext();
        this.targetContext=instrumentation.getTargetContext();
        dataMakerUtil = new DataMakerUtil();
    }


    public Instrumentation getmInstrumentation() {
        return mInstrumentation;
    }

    public Context getInstrumentationContext() {
        return instrumentationContext;
    }

    public Context getTargetContext() {
        return targetContext;
    }

    public UiDevice getmUiDevice() {
        return mUiDevice;
    }

    public CommonUtil getmUtil() {
        return mUtil;
    }

    public DeviceHelper getmHelper() {
        return mHelper;
    }

    public DataMakerUtil getDataMakerUtil() {
        return dataMakerUtil;
    }

   /* public void addOneContact() throws RemoteException, IOException {
        FileUtil.copyFileToSdCard(new String[]{"pic.jpg", "tuku.png"}, path);
        dataMakerUtil.addContact(number, name);
    }


    public boolean hasContact() throws RemoteException {
        List<String> numbers = dataMakerUtil.queryNumbersFromContact();
        return numbers.contains(number);
    }*/

}
