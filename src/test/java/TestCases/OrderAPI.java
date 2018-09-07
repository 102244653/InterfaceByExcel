package TestCases;

import BaseCase.BaseCase;
import TestData.SetData;
import TestData.TestFilePath;
import TestData.UserInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/12/23.
 * 入库单模块接口
 */
public class OrderAPI {
    @BeforeClass()
    public void Start(){
        try{
            new UserInfo().Getuserinfo(TestFilePath.path, "USERINFO");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //新建订单
    @Test(priority = 0)
    public void createOrder()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"createOrder");
            if(qty==1|qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "createOrder", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("createOrder" + (i + 1) + "执行失败");
                }
            }
    }

    //修改订单
    @Test(priority = 1)
    public void modifyOrder()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"modifyOrder");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "modifyOrder", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("modifyOrder" + (i + 1) + "执行失败");
                }
            }

    }

    //取消订单
    @Test(priority = 2)
    public void cancelOrder()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"cancelOrder");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "cancelOrder", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("cancelOrder" + (i + 1) + "执行失败");
                }
            }

    }

    //根据订单号查询订单
    @Test(priority = 3)
    public void getOrderByCode ()throws Exception{

            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getOrderByCode");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getOrderByCode", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getOrderByCode" + (i + 1) + "执行失败");
                }
            }

    }

    //根据参考号查询订单
    @Test(priority = 4)
    public void getOrderByRefCode()throws Exception{

            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getOrderByRefCode");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getOrderByRefCode", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getOrderByRefCode" + (i + 1) + "执行失败");
                }
            }

    }

    //获取订单列表
    @Test(priority = 5)
    public void getOrderList()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getOrderList");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getOrderList", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getOrderList" + (i + 1) + "执行失败");
                }
            }

    }
}
