package com.Supermar.app;

import com.Supermar.controller.System_Management;

/**
 * @Author：林杰
 * @Package：com.Supermar.app
 * @Project：IdeaProjects
 * @name：Supermarket_App
 * @Date：2023/7/27 19:34
 * @Filename：Supermarket_App
 */
public class Supermarket_App {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("欢迎光临,这里是"+Thread.currentThread().getName());
                System_Management system = new System_Management();
                system.main_interface();
            }
        },"美宜佳超市");
        t1.start();
    }
}
