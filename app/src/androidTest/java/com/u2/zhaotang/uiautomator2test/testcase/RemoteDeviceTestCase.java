package com.u2.zhaotang.uiautomator2test.testcase;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import com.meizu.test.common.AppInfo;
import com.meizu.test.common.CommonUtil;
import com.meizu.test.common.DeviceHelper;
import com.meizu.test.common.FileUtil;
import com.meizu.u2.U2BaseTestCase;
import com.meizu.u2.annotations.Description;
import com.u2.zhaotang.uiautomator2test.Utils.AutoLog;
import com.u2.zhaotang.uiautomator2test.bussines.Contacts;
import com.u2.zhaotang.uiautomator2test.runner.RunBase;


import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;


public class RemoteDeviceTestCase extends RunBase {

    @Test
    @Description(steps = "browser", expectation = "test", priority = Description.P1)
    public void test001() throws RemoteException, InterruptedException {
        System.out.println("---");
    }

    @Test
    @Description(steps = "browser22", expectation = "test1", priority = Description.P1)
    public void test002() throws RemoteException, InterruptedException {
        System.out.println("---");
    }

    @Test
    @Description(steps = "browser333", expectation = "test2", priority = Description.P1)
    public void test003() throws RemoteException, InterruptedException {
        System.out.println("---");
       Assert.assertEquals("","11");
    }

 /*   @Test
    @Description(steps = "browser", expectation = "test1", priority = Description.P1)
    public void test02() throws RemoteException, InterruptedException {
        mHelper.clickByText("百度11");
    }*/
}
