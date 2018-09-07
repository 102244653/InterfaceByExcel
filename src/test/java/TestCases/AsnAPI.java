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
public class AsnAPI {
    @BeforeClass()
    public void Start(){
        try{
            new UserInfo().Getuserinfo(TestFilePath.path, "USERINFO");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //新建入库单
    @Test(priority = 0)
    public void createAsn()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"createAsn");
            if(qty==1|qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "createAsn", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("createAsn" + (i + 1) + "执行失败");
                }
            }
    }

    //修改入库单
    @Test(priority = 1)
    public void modifyAsn()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"modifyAsn");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                     BS.APITest(TestFilePath.path, "modifyAsn", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("modifyAsn" + (i + 1) + "执行失败");
                }
            }
    }

    //获取入库单列表
    @Test(priority = 2)
    public void getAsnList()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getAsnList");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getAsnList", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getAsnList" + (i + 1) + "执行失败");
                }
            }

    }


}
