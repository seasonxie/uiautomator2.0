uiautomator2.0demo

1.testwater
2.单例模式
3.uiautomator 2.0 自定义testrunner使用
1.继承 MonitoringInstrumentation

1
public class MyRunner extends MonitoringInstrumentation {
2.build gradle

//如果有需要使用自己编写的unner执行脚本，就使用下面这行代码
 testInstrumentationRunner "runner.MyRunner"
3.启动

am instrument -w -e class com.u2.zhaotang.uiautomator2test.testcase.RemoteDeviceTestCase#test01 com.u2.zhaotang.uiautomator2test.test/runner.MyRunner
