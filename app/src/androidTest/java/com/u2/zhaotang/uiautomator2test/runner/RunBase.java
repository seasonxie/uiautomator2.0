package com.u2.zhaotang.uiautomator2test.runner;

/**
 * Created by Administrator on 2016/12/31.
 */
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import com.meizu.test.common.AppInfo;
import com.meizu.test.common.CommonUtil;
import com.meizu.test.common.DeviceHelper;
import com.meizu.u2.ScriptKeeper;
import com.meizu.u2.annotations.RunFor;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;
import com.relevantcodes.extentreports.ReporterType;
import com.u2.zhaotang.uiautomator2test.Utils.AutoLog;
import com.u2.zhaotang.uiautomator2test.base.WatcherCommon;
import com.u2.zhaotang.uiautomator2test.bussines.Contacts;
import com.u2.zhaotang.uiautomator2test.bussines.MainPage;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.prefs.Preferences;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import com.relevantcodes.extentreports.LogStatus;
/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;*/

@RunWith(AndroidJUnit4.class)
public abstract class RunBase {
    public static Instrumentation mInstrumentation;
    public static UiDevice mDevice;
    public static CommonUtil mUtil;
    public static DeviceHelper mHelper;
    public static Contacts contacts;
    public static WatcherCommon watcher;
    public static MainPage mainPage;
    private static ThreadLocal exec = new ThreadLocal();
    private static ExtentReports extent;
    private ExtentTest test;
    private static String reportLocation = "/sdcard/ExtentReport2.html";

    @Rule
    public TestName name = new TestName();
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            test.log(LogStatus.FAIL, e);
            extent.endTest(test);
            //((ExtentTest)exec.get()).fail(e);
            try {
              /*  U2BaseTestCase.this.mSks.sendResult(U2BaseTestCase.this.getResult(Boolean.valueOf(false)));*/
                //do something when failed
                AutoLog.I("failedcase");
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }

        protected void succeeded(Description description) {
            super.succeeded(description);
            test.log(LogStatus.PASS, "Pass");
            test.log(LogStatus.INFO, "fsdfsd");


            extent.endTest(test);
            // ((ExtentTest)exec.get()).pass("Test passed");
            try {
             /*   U2BaseTestCase.this.mSks.sendResult(U2BaseTestCase.this.getResult(Boolean.valueOf(true)));*/
                //do something when success
                AutoLog.I("succeededcase1");
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }

        protected void starting(Description description) {
            super.starting(description);
            AutoLog.I(description.getMethodName());
            test= extent.startTest(description.getMethodName()+getSteps(description.getMethodName()));

        }

        protected void finished(Description description) {
//            extent.flush();
            super.finished(description);

        }
    };
    private Context mContext = InstrumentationRegistry.getContext();
    private Bundle mArgs = InstrumentationRegistry.getArguments();
    private ScriptKeeper mSks;

    public RunBase() {
        //this.mSks = ScriptKeeper.getInstance(this.mContext);
    }

    @BeforeClass
    public static void beforeClass() throws IOException, PackageManager.NameNotFoundException {
        extent = new ExtentReports(reportLocation);
        mInstrumentation = InstrumentationRegistry.getInstrumentation();
        contacts=Contacts.getInstance(mInstrumentation);
        mDevice = contacts.getmUiDevice();
        mUtil =contacts.getmUtil();
        mHelper = contacts.getmHelper();
        watcher=WatcherCommon.getInstance();
        mainPage=MainPage.getInstance();
        mUtil.setUtf7Input();


       //extent.startReporter(ReporterType.DB, reportLocation);
        extent.addSystemInfo("os", "Linux");
    }

    @AfterClass
    public static void afterClass() throws IOException {
        extent.flush();
        extent.close();
        mUtil.setSystemInput();
    }

    @Before
    @CallSuper
    public void before() {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        AutoLog.I("INSTRUMENTATION_STATUS: language=" + language + '_' + country);
        AutoLog.I("super------before");
       // mDevice.pressHome();
       // mUtil.startApp(AppInfo.PACKAGE_BROWSER);

    }

    @After
    @CallSuper
    public void after() throws IOException {
      //  mUtil.exitApp(AppInfo.PACKAGE_BROWSER);


        AutoLog.I("super------after");
    }

    public String getName() {
        return this.name.getMethodName();
    }


    public Object getArgumentValue(String key) {
        return this.mArgs.get(key);
    }

    public boolean isArgumentPresent(String key) {
        Set set = this.mArgs.keySet();
        if (set.size() > 0) {
            Iterator i$ = set.iterator();

            while (i$.hasNext()) {
                String k = (String) i$.next();
                if (key.equals(k)) {
                    return true;
                }
            }
        }

        return false;
    }

    public <T extends Annotation> T getAnnotation(Class<T> clazz,String name) {
        try {
            if (this.getClass().isAnnotationPresent(clazz)) {
                return this.getClass().getAnnotation(clazz);
            }

            Method e = this.getClass().getMethod(name, new Class[0]);
            if (e.isAnnotationPresent(clazz)) {
                return e.getAnnotation(clazz);
            }
        } catch (NoSuchMethodException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    private boolean isMultiLanguage(String name) {
        int type = this.getType(name);
        return type == 1;
    }

    public int getVersion(String name) {
        RunFor runFor = (RunFor) this.getAnnotation(RunFor.class,name);
        return runFor == null ? -1 : runFor.version();
    }

    public int getType(String name) {
        RunFor runFor = (RunFor) this.getAnnotation(RunFor.class,name);
        return runFor == null ? -1 : runFor.type();
    }

    public int getModule() {
        try {
            Class e = Class.forName("com.meizu.testdev.Module");
            Field idField = e.getField("id");
            Object value = idField.get(e.newInstance());
            return Integer.parseInt(value.toString());
        } catch (Exception var4) {
            var4.printStackTrace();
            return -1;
        }
    }

    public String[] getDescription(String name) {
        com.meizu.u2.annotations.Description description = (com.meizu.u2.annotations.Description) this.getAnnotation(com.meizu.u2.annotations.Description.class,name);
        return description == null ? null : new String[]{description.expectation(), description.steps(), String.valueOf(description.priority())};
    }

    public String getExpectation(String name) {
        String[] desc = this.getDescription(name);
        return desc != null && desc.length >= 3 ? desc[0] : null;
    }

    public String getSteps(String name) {
        String[] desc = this.getDescription(name);
        return desc != null && desc.length >= 3 ? desc[1] : null;
    }

    public String getPriority(String name) {
        String[] desc = this.getDescription(name);
        return desc != null && desc.length >= 3 ? desc[2] : null;
    }

    /*private Result getResult(Boolean result) {
        return this.getModule() == -1 ? null : new Result(this.getClass().getName(), this.getName(), this.getDescription(), this.getModule(), this.getType(), this.getVersion(), result.booleanValue());
    }*/
}
