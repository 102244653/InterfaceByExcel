package TestCases;

import BaseCase.BaseCase;
import TestData.SetData;
import TestData.TestFilePath;
import TestData.UserInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/12/23.
 * 产品模块接口
 */
public class ProductAPI {

    @BeforeClass()
    public void Start(){
        try{
            new UserInfo().Getuserinfo(TestFilePath.path, "USERINFO");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //新建产品
    @Test(priority = 0)
    public void createProduct()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"createProduct");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "createProduct", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("createProduct" + (i + 1) + "执行失败");
                }
            }

    }

    //修改产品
    @Test(priority = 1)
    public void modifyProduct()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"modifyProduct");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "modifyProduct", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("modifyProduct" + (i + 1) + "执行失败");
                }
            }

    }

    //获取产品列表
    @Test(priority = 2)
    public void getProductList()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getProductList");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getProductList", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getProductList" + (i + 1) + "执行失败");
                }
            }
    }

    //获取库存
    @Test(priority = 3)
    public void getProductInventory()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getProductInventory");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getProductInventory", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getProductInventory" + (i + 1) + "执行失败");
                }
            }

    }
}
