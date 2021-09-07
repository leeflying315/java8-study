package com.lifei.designPattern.template;

import java.math.BigDecimal;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author lifei
 * @Description:
 * 使用java8 consumer语法实现模板类
 * @Date 2021/8/30
 */
public class BankBusinessHandler {

    /**
     * 模板方法1
     */
    private void execute(Consumer<BigDecimal> consumer) {
        getNumber();

        consumer.accept(null);

        judge();
    }

    /**
     * 模板方法2
     *
     */
    protected void execute(Supplier<String> supplier, Consumer<BigDecimal> consumer) {

        String number = supplier.get();
        System.out.println(number);


        if (number.startsWith("vip")) {
            //Vip号分配到VIP柜台
            System.out.println("Assign To Vip Counter");
        }
        else if (number.startsWith("reservation")) {
            //预约号分配到专属客户经理
            System.out.println("Assign To Exclusive Customer Manager");
        }else{
            //默认分配到普通柜台
            System.out.println("Assign To Usual Manager");
        }

        consumer.accept(null);

        judge();
    }

    private void getNumber() {
        System.out.println("number-00" + new Random().nextInt());
    }

    private void judge() {
        System.out.println("give a praised");
    }



    // 模板类中间实现
    public void save(BigDecimal amount) {
        execute(a -> System.out.println("save " + amount));
    }


    public void draw(BigDecimal amount) {
        execute(a -> System.out.println("draw " + amount));
    }

    public void moneyManage(BigDecimal amount) {
        execute(a -> System.out.println("draw " + amount));
    }

    public void saveVip(BigDecimal amount) {
        execute(() -> "vipNumber-00" + new Random().nextInt(), a -> System.out.println("save " + amount));
    }

    public void saveReservation(BigDecimal amount) {
        execute(() -> "reservationNumber-00" + new Random().nextInt(), a -> System.out.println("save " + amount));
    }


    public static void main(String[] args) {
        BankBusinessHandler businessHandler = new BankBusinessHandler();
        businessHandler.save(new BigDecimal("1000"));
        businessHandler.saveVip(new BigDecimal("1000"));
    }

}
