package com.lifei.study;

/**
 * @Author lifei
 * @Description：
 * @Date 2021-12-31 11:56
 **/
public class GetterStudy {
    private String test;

    private String getNewName(){
        return test;
    }

    private void setTest(String s){
        test = s;
    }



    public static void main(String[] args) {
        GetterStudy getterStudy = new GetterStudy();
        getterStudy.setTest("hello");
        System.out.println(getterStudy);
    }
}
