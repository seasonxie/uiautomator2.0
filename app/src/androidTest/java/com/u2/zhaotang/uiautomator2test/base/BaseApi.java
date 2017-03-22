package test.browser.meizu.com.browserauto.base;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.meizu.test.common.DeviceHelper;

import junit.framework.TestCase;

import java.io.File;
import java.util.Date;
import java.util.regex.Pattern;

import test.browser.meizu.com.browserauto.Utils.AutoLog;
import test.browser.meizu.com.browserauto.bussines.Constants;
import test.browser.meizu.com.browserauto.bussines.Contacts;
import test.browser.meizu.com.browserauto.runner.RunBase;

/**
 * Created by Administrator on 2017/1/7.
 */
public class BaseApi {

    private static BaseApi instance;
    private UiDevice mUiDevice;
    private DeviceHelper mHelper;
    private int waitime=3000;

    public synchronized static BaseApi getInstance() {
        if (instance == null) {
            instance = new BaseApi();
        }
        return instance;
    }

    public void sleep(long time){
        try {
            Thread.sleep(time);
            AutoLog.I("[wait] "+time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private BaseApi() {
        this.mUiDevice = Contacts.getInstance().getmUiDevice();
        this.mHelper =Contacts.getInstance().getmHelper();
    }

    public void takeScreen(String... name){
        String data=Constants.EMPTY_STR;
        if(name.length>0)
            data=name.clone()[name.length-1];
        String currentName=RunBase.currenttime.format(new Date());
        if(!data.isEmpty())
            currentName=data+"_"+currentName;
        mUiDevice.takeScreenshot(new File(RunBase.BASEREPORTPATH + currentName + ".png"));
        AutoLog.I("截图路径  "+RunBase.BASEREPORTPATH+currentName+".png");
    }



    public void longClick(BySelector by){
            mHelper.isObject2Exists(by,3000);
                mHelper.longClick(mHelper.getObject2(by), 1000);
                AutoLog.I("[长按] " + by.toString());
    }


    public boolean clickByTextAndSleep(String Text,long time,boolean... needContinue){
        boolean r=clickByText(Text,needContinue);
        if(r)
        sleep(time);
        return r;
    }



    public boolean isExistByText(String Text,int timeout){
        boolean result=false;
        try {
            result =mHelper.isTextExists(Text,timeout);
            if(result){
                AutoLog.I("[存在text] "+Text);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AutoLog.I("[Exception 不存在text] " + Text);
        }
        return false;
    }

    public boolean isExistById(String Id,int timeout){
        boolean result=false;
        try {
            result =mHelper.isIdExists(Id, timeout);
            if(result){
                AutoLog.I("[存在Id] "+Id);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AutoLog.I("[Exception 不存在Id] " + Id);
        }
        return false;
    }

    public boolean isExistByDesc(String isExistByDesc,int timeout){
        boolean result=false;
        try {
            result =mHelper.isDescExists(isExistByDesc, timeout);
            if(result){
                AutoLog.I("[存在isExistByDesc] "+isExistByDesc);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AutoLog.I("[Exception 不存在isExistByDesc] " + isExistByDesc);
        }
        return false;
    }

    public boolean isExistByTextContains(String Text,int timeout){
        boolean result=false;
        String pattern = ".*("+Text+")+.*";
        Pattern r = Pattern.compile(pattern);
        try {
            result = mHelper.isTextExists(r,timeout);
            if(result) {
                AutoLog.I("[存在包含text] " + Text);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AutoLog.I("[Exception 不存在包含text] " + Text);
        }
        return false;
    }

    public boolean getBoolean(boolean... needContinue){
        if(needContinue.length>0)
        return needContinue.clone()[0];
        return false;
    }

    public boolean clickByTextContains(String Text,boolean... needContinue){
            if(isExistByTextContains(Text, waitime) || getBoolean(needContinue)){
                String pattern = ".*("+Text+")+.*";
                Pattern r = Pattern.compile(pattern);
                mHelper.clickByText(r);
                AutoLog.I("[点击成功包含Text] "+Text);
                return true;
            }else{
                AutoLog.I("[无法点击，没找到包含Text] "+Text);
                return false;
            }
    }

    public boolean clickByText(String Text,boolean... needContinue){
            if (isExistByText(Text,waitime)|| getBoolean(needContinue)){
                mHelper.clickByText(Text);
                AutoLog.I("[点击成功Text] "+Text);
                return true;
            }else{
                AutoLog.I("[无法点击，没找到Text] "+Text);
                return false;
            }
    }

    public boolean clickByIdAndSleep(String Id,long time,String titleForLog,boolean... needContinue){
        boolean r=clickById(Id,titleForLog,needContinue);
        if(r)
        sleep(time);
        return r;
    }

    public boolean clickById(String Id,String titleForLog,boolean... needContinue){
        String clickName="Id控件 "+titleForLog;
            if(isExistById(Id, waitime)|| getBoolean(needContinue)) {
                mHelper.clickById(Id);
                AutoLog.I("[点击成功"+clickName+"] "+Id);
                return true;
            }else{
                AutoLog.I("[无法点击，没找到 "+clickName+"] "+Id);
                return false;
            }
    }

    public boolean clickByDescAndSleep(String Desc,long time,boolean... needContinue){
        boolean r=clickByDesc(Desc,needContinue);
        sleep(time);
        return r;
    }
    public boolean clickByDesc(String Desc,boolean... needContinue){
            if(isExistByDesc(Desc, waitime)|| getBoolean(needContinue)) {
                mHelper.clickByDesc(Desc);
                AutoLog.I("[点击成功Desc] "+Desc);
                return true;
            }else{
                AutoLog.I("[无法点击，没找到Desc] "+Desc);
                return false;
            }
    }

    public String getTextById(String Id,String titleForLog,boolean... needContinue)  {
        String clickName="Id控件 "+titleForLog;
        String Text= Constants.EMPTY_STR;
            if(isExistById(Id, waitime)|| getBoolean(needContinue)) {
                Text= mHelper.getTextById(Id);
                AutoLog.I("[成功获取 "+clickName+": "+Id+"  的getText] "+Text);
                return Text;
            }else{
                AutoLog.I("[无法获取Text，没找到 "+clickName+"] "+Id);
                return Text;
            }
    }



    public boolean setTextById(String Id,String Text,String titleForLog,boolean... needContinue)  {
        String clickName="Id控件 "+titleForLog;
            if(isExistById(Id, waitime)) {
                mHelper.setTextById(Text,Id);
                AutoLog.I("[成功setText from  "+clickName+": "+Id+"  setText] "+Text);
                return true;
            }else{
                AutoLog.I("[无法设置Text，没找到 "+clickName+"] "+Id);
                return false;
            }
    }

    public void swipeLeft(int...times){
        int time=1;
        if(times.length>0)
            time=times.clone()[times.length-1];
        for (int i=0;i<time;i++)
        mHelper.swipeLeft(10);
        sleep(500);
        AutoLog.I("[向左滑动] "+time);
    }

    public void swipeRight(int...times){
        int time=1;
        if(times.length>0)
            time=times.clone()[times.length-1];
        for (int i=0;i<time;i++)
            mHelper.swipeRight(10);
        sleep(500);
        AutoLog.I("[向右滑动] "+time);
    }

    public void swipeUp(int...times){
        int time=1;
        if(times.length>0)
            time=times.clone()[times.length-1];
        for (int i=0;i<time;i++)
            mHelper.swipeUp(10);
        sleep(500);
        AutoLog.I("[向上滑动] "+time);
    }

    public void swipeDown(int...times){
        int time=1;
        if(times.length>0)
            time=times.clone()[times.length-1];
        for (int i=0;i<time;i++)
            mHelper.swipeDown(10);
        sleep(500);
        AutoLog.I("[向下滑动] "+time);
    }
}
