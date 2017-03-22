package test.browser.meizu.com.browserauto.base;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.support.test.uiautomator.UiDevice;
import android.telephony.TelephonyManager;

import com.meizu.test.common.AppInfo;
import com.meizu.test.common.CommonUtil;
import com.meizu.test.common.DeviceHelper;

import java.io.IOException;

import test.browser.meizu.com.browserauto.Utils.AutoLog;
import test.browser.meizu.com.browserauto.Utils.ShellUtils;
import test.browser.meizu.com.browserauto.bussines.Constants;
import test.browser.meizu.com.browserauto.bussines.Contacts;

/**
 * Created by Administrator on 2017/1/7.
 */
public class BaseInfo {


    public int getWidth() {
        return  mUiDevice.getDisplayWidth();
    }

    public int getHeight() {
        return  mUiDevice.getDisplayHeight();
    }
    public String getFlymeVersion() {
        return mUtil.getDisplayId();
    }

    public String getCurrentPackageName() {
        return mUiDevice.getCurrentPackageName();
    }

    public String getCurrentPackageNameByShell() {
        try {
            return mUiDevice.executeShellCommand("dumpsys activity top | grep ACTIVITY");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Constants.EMPTY_STR;
    }

    public String getDeviceName() {
        return mUtil.getHardware();
    }

    public String getDeviceNameExternal() {
        return mUiDevice.getProductName();
    }

    public String getLanguage() {
        return mUtil.getLanguage();
    }

    public String getSN() {
        return mUtil.getSncode();
    }


    public String getAppVersion(){
        //包管理操作管理类
        PackageManager pm = Contacts.targetContext.getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(Constants.BROWSERPACKAGE, 0);
            return packinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return Constants.EMPTY_STR;
    }

    public String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) Contacts.targetContext.getSystemService(Contacts.targetContext.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }



    public String getAppName(){
        //包管理操作管理类
        PackageManager pm = Contacts.targetContext.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo(Constants.BROWSERPACKAGE, 0);
            return info.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return Constants.EMPTY_STR;
    }



    private static BaseInfo instance;
    private UiDevice mUiDevice;
    private DeviceHelper mHelper;
    private CommonUtil mUtil;

    public synchronized static BaseInfo getInstance() {
        AutoLog.I("BaseInfo getInstance");
        if (instance == null) {
            instance = new BaseInfo();
        }
        return instance;
    }

    public BaseInfo() {
        AutoLog.I("BaseInfo public");
        this.mUiDevice = Contacts.getInstance().getmUiDevice();
        this.mHelper =Contacts.getInstance().getmHelper();
        this.mUtil=Contacts.getInstance().getmUtil();

    }

}

/*    01-07 23:15:37.557 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getCurrentPackageName com.meizu.flyme.launcher
    01-07 23:15:37.557 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getLastTraversedText null
            01-07 23:15:37.557 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getLauncherPackageName com.meizu.flyme.launcher
    01-07 23:15:37.557 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getProductName meizu_PRO5
    01-07 23:15:37.557 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getCurrentActivityName 主屏幕
    01-07 23:15:37.557 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getDisplayRotation 0
            01-07 23:15:37.557 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getDisplaySizeDp Point(360, 640)
    01-07 23:15:37.587 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getDisplayId Flyme 5.1.11.0A
    01-07 23:15:37.597 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getHardware m86
    01-07 23:15:37.597 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getLanguage zh
    01-07 23:15:37.617 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getModel PRO 5
            01-07 23:15:37.637 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getSncode 86UADM7FMB4P
    01-07 23:15:38.137 2082-2112/com.u2.zhaotang.uiautomator2test I/System.out: getBattery 39*/