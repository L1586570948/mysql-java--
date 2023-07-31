package com.Supermar.controller;

import com.Supermar.domain.Commodity_Information;
import com.Supermar.domain.History;
import com.Supermar.domain.Shopping;
import com.Supermar.domain.User_Information;
import com.Supermar.domain.Collection;
import com.Supermar.service.ICommodityService;
import com.Supermar.service.IOtherService;
import com.Supermar.service.IUserService;
import com.Supermar.service.impl.ICommodityServiceImpl;
import com.Supermar.service.impl.IOtherServiceImpl;
import com.Supermar.service.impl.IUserServiceImpl;
import com.Supermar.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 控制层，涵盖APP运行的多种功能
 * 例如，初始化用户，商品数据；存储(删除用户表和商品表的所有数据，然后再添加数据)
 * 基本功能：注册、登入用户的数据存于数据库的User_Information，商品的信息存于数据库的Commodity_Information表
 * 超市系统可以提供用户查询余额和充值余额功能
 * 用户购物，会扣除余额，余额不足则会提示
 * 同时，购物
 */
public class System_Management {
    //创建对象
    private User_Information user_information;
    private Commodity_Information commodity_information;
    private static List<Commodity_Information> list_c2;
    private Scanner sc;
     static double zhekou;
static User_Information user2 = null;
    //创建service层的接口对象，同样放在构造器中让子类实现父类
    private ICommodityService icommodityservice;
    private IOtherService iotherservice;
    private IUserService iuserservice;
    //初始化对象
    public void init(){
        user_information =new User_Information();
        sc = new Scanner(System.in);
        icommodityservice = new ICommodityServiceImpl();
        iotherservice = new IOtherServiceImpl();
        iuserservice = new IUserServiceImpl();
        commodity_information = new Commodity_Information();
    }

    public System_Management(){
        init();
    }

    //主函数
    public void main_interface(){
        do{
            System.out.println("(｡･∀･)ﾉﾞ==========欢迎来到模拟超市系统==========(￣▽￣)～■干杯□～(￣▽￣)");
            System.out.println("1.用户登入");
            System.out.println("2.用户注册");
            System.out.println("3.管理员权限");
            System.out.println("4.系统退出");
            System.out.println("( ﹁ ﹁ ) ~→请输入1-4进行你的选择");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    User_Login();
                    break;
                case 2:
                    User_Register();
                    break;
                case 3:
                    Admin_Register();
                    break;
                case 4:
                    System.out.println("已退出，欢迎下次光临（o´・ェ・｀o）");
                    System.exit(0);
                    break;
                default:
                    System.out.println("对不起，您的选择有误，┗( T﹏T )┛请规范输入1-4");
            }
        }while (true);
    }

    /**
     * 用户登入
     */
    public void User_Login(){
        //设置开关标记是否有该用户
        boolean flag_u = false;
        //设置第二个开关退出外层循环
        boolean flag_u2 = true;
        while (flag_u2){
            //判断用户登入账户和密码
            System.out.println("请输入登入的用户名：");
            String username = sc.next();
            System.out.println("请输入登入的密码:");
            String password = sc.next();
            List<User_Information> list_u = iuserservice.Dao_All_Che_User();
            for (int i = 0; i < list_u.size(); i++) {
                User_Information user = list_u.get(i);
                user2 = list_u.get(i);
                if (user.getAccount().equals(username) && user.getPassword().equals(password)){
                    flag_u=true;
                    //将登入用户的数据存到 全局变量 user_information中
                    user_information.setUid(user2.getUid());
                    user_information.setAccount(user2.getAccount());
                    user_information.setPassword(user2.getPassword());
                    user_information.setBalance(user2.getBalance());
                    break;
                }
            }
            if (flag_u==true){
                //退出循环
                flag_u2 = false;

                System.out.println("*´∀`)´∀`)*´∀`)*´∀`)   登入成功");
            }else {
                System.out.println("▄︻┻┳═一…… ☆（>○   用户或密码错误！！");
            }
        }

        do{
            System.out.println("(｡･∀･)ﾉﾞ==========欢迎光临==========(￣▽￣)～■干杯□～(￣▽￣)");
            System.out.println("1.(≧∇≦)ﾉ走，一起去逛超市(>▽");
            System.out.println("2.用户充值");
            System.out.println("3.查询余额");
            System.out.println("4.返回上级目录");
            System.out.println("5.系统退出");
            System.out.println("( ﹁ ﹁ ) ~→请输入1-4进行你的选择");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    Go_shopping();
                    break;
                case 2:
                    recharge_balance();
                    break;
                case 3:
                    check_balance();
                    break;
                case 4:
                    System.out.println("一秒后返回上级目录...(*￣０￣)ノ[等等我…]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    main_interface();
                    break;
                case 5:
                    System.out.println("已退出，欢迎下次光临（o´・ェ・｀o）");
                    System.exit(0);
                    break;
                default:
                    System.out.println("对不起，您的选择有误，┗( T﹏T )┛请规范输入1-4");
            }
        }while (true);
    }

    /**
     * 用户注册
     */
    public void User_Register() {
        //设置开关标记是否有该用户
        boolean flag_u = false;
        //判断用户登入账户和密码
        System.out.println("请输入注册的用户名：");
        String username = sc.next();
        List<User_Information> list_u = iuserservice.Dao_All_Che_User();
        for (User_Information user : list_u) {
            if (user.getAccount().equals(username)) {
                flag_u = true;
            }
        }
        if (flag_u == true) {
            //用户名已存在
            System.out.println("用户名已存在┗( T﹏T )┛");
            User_Register();
        }
        //设置循环出口
        boolean flag_u2 = true;
        while (flag_u2) {
            String YZM = yzm();
            System.out.println("验证码：" + YZM);
            System.out.print("请输入验证码：");
            String sr_yzm = sc.next();
            if (sr_yzm.equalsIgnoreCase(YZM)) {
                System.out.println("请输入注册的密码:");
                String password = sc.next();
                System.out.println("请再次输入注册密码确认:");
                String repassword = sc.next();
                if (password.equals(repassword)){
                    flag_u2=false;
                    User_Information user_information = new User_Information();
                    user_information.setAccount(username);
                    user_information.setPassword(password);
                    user_information.setUid(UUID.randomUUID().toString());
                    user_information.setBalance(0);
                    iuserservice.Add_User(user_information);
                    System.out.println("注册成功(。﹏。)");
                }else {
                    System.out.println("两次密码不一致，请重新输入（＞人＜；）");
                }
            } else {
                System.out.println("(*￣(エ)￣)验证码错误");
            }
        }
    }

    /**
     * 管理员权限
     */
    public void Admin_Register(){
        //判断输入的管理员名称与密码是否正确
        boolean flag = true;
        while (flag) {
            //判断用户登入账户和密码
            System.out.println("请输入登入的管理员名称：");
            String admin = sc.next();
            System.out.println("请输入登入的密码:");
            String pwd = sc.next();
            admin.equals("admin");
            pwd.equals("666666");
            if (admin.equals("admin") && pwd.equals("666666")) {
                System.out.println("*´∀`)´∀`)*´∀`)*´∀`)   登入成功");
                break;
            } else {
                System.out.println("您输入的管理员名或密码不对，3秒后将返回上一步");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                main_interface();
            }
        }
        do{
            System.out.println("(｡･∀･)ﾉﾞ==========欢迎来到管理员权限页面==========(￣▽￣)～■干杯□～(￣▽￣)");
            System.out.println("1.查询商品表的全部数据");
            System.out.println("2.查询用户表的全部数据");
            System.out.println("3.根据商品id删除商品表的数据");
            System.out.println("4.根据用户id删除用户表的一个数据");
            System.out.println("5.添加商品表的一个数据");
            System.out.println("6.添加用户表的一个数据");
            System.out.println("7.修改商品表的一个数据");
            System.out.println("8.修改用户表的一个数据");
            System.out.println("9.返回上级目录");
            System.out.println("10.系统退出");
            System.out.println("999:初始化数据！！！");
            System.out.println("( ﹁ ﹁ ) ~→请输入1-10进行你的选择");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    Dao_Che_information();
                    break;
                case 2:
                    Dao_Che_History();
                    break;
                case 3:
                    System.out.println("请输入上面列表中任意一个你想删除的商品id：");
                    String Cid= sc.next();
                    Dao_Delinformation(Cid);
                    break;
                case 4:
                    System.out.println("请输入上面列表中任意一个你想删除的用户id：");
                    String uid = sc.next();
                    Dao_Del_User(uid);
                    break;
                case 5:
                    Dao_Add_information();
                    break;
                case 6:
                    Dao_Add_User();
                    break;
                case 7:
                    Dao_Upd_information();
                    break;
                case 8:
                    Dao_Upd_User();
                    break;
                case 9:
                    main_interface();
                    break;
                case 10:
                    System.out.println("已退出，欢迎下次光临（o´・ェ・｀o）");
                    System.exit(0);
                    break;
                case 999:
                    Initialize_Data();
                    break;
                default:
                    System.out.println("对不起，您的选择有误，┗( T﹏T )┛请规范输入1-10");
            }
        }while (true);

    }

    //初始化数据
    public void Initialize_Data(){
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        //删除所有用户表数据
        String sql_del_u = "DELETE FROM user_information";
        //删除所有商品表的数据
        String sql_del_c = "DELETE FROM commodity_information";
        //添加用户表数据
        String sql_add_u = "INSERT INTO user_information VALUES('11111','小明','111222',100),('222222','小白','222333',1000),('33333','张三','123456',0),('44444','小林','666666',10000),('55555','苏洛','888888',9999.99)";
        //添加商品表数据
        String sqL_add_c = "INSERT INTO commodity_information (Cid,Cname,Cprice,Type,Cinventory) values('001', '荣耀X50', 1599.00, '手机',40),('002','机械革命（MECHREVO） 极光Pro游戏本', 6299.00 ,'笔记本电脑',10),('003', '雅戈尔 夏季青年男商务休闲短袖衬衫', 239.00, '男装',17),('004', '歌莉娅 秋季新品天丝套装（假两件上衣+压褶半裙）', 699.00 , '女装',15),('005', '真维斯 夏季男性衬衫服装', 200, 'c002',38),('006', '坚果炒货零食 干果开心果腰果 30包750g/箱', 68.80, '休闲零食',500),('007', '狗牙儿 比萨卷锅巴 披萨卷 怀旧休闲零食', 15.90, '休闲零食',10000),('008', '五粮液 52度浓香型白酒整箱', 198.00, '酒',40),('009', 'VANS 范斯官方 Style36寂静蓝 美式经典男女板鞋', 519.00, '鞋子',25),('010', '卫龙 辣条大面筋650g', 3, '休闲零食',30),('011', '农夫山泉', 2, '饮用水',999),('012', '香飘飘奶茶  啵啵牛乳茶混合口味65g*12杯礼盒装', 66.99, '食用饮品',100),('013', '小浣熊干脆面', 1, '休闲零食',500)";
        try {
            qr.update(sql_del_u);
            qr.update(sql_del_c);
            qr.update(sql_add_u);
            qr.update(sqL_add_c);
            System.out.println("初始化完成(ToT)/~~~");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //1.查询商品表的所有数据
    public void Dao_Che_information(){
        System.out.println("商品编号\t\t\t\t 商品名称\t\t 商品价钱\t\t 商品类别\t\t 商品数量");
        List<Commodity_Information>commodity_informationList = icommodityservice.Che_information();
        for (Commodity_Information c : commodity_informationList){
            System.out.println(c.getCid()+" "+c.getCname()+"\t"+c.getCprice()+"\t"
                    +c.getType()+"\t"+c.getCinventory());
        }
    }

    //2.查询用户表的所有数据
    public void Dao_Che_History() {
        System.out.println("用户编号\t\t 用户名称\t\t 用户密码\t\t 用户余额");
        List<User_Information>user_informationList = iuserservice.Dao_All_Che_User();
        for (User_Information c : user_informationList){
            System.out.println(c.getUid()+" "+c.getAccount()+"\t"+c.getPassword()+"\t"
                    +c.getBalance());
        }
    }

    //3.删除商品表的一个数据
    public void Dao_Delinformation(String Cid){
        boolean flag = false;
        //判断是否有该商品
        List<Commodity_Information> list_c = icommodityservice.Che_information();
        for (Commodity_Information comm: list_c){
            if(comm.getCid().equals(Cid)){
                flag=true;
                commodity_information = comm;
                break;
            }
        }
        //判断完毕，执行删除
        if (flag==true){
            int a=icommodityservice.Delinformation(commodity_information);
            System.out.println("删除商品信息成功！0.5秒后回到管理员权限页面");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("商品信息删除失败！(。﹏。)");
        }
    }

    //4.删除用户表的一个数据
    public void Dao_Del_User(String uid) {
        boolean flag = false;
        //判断是否有该用户
        List<User_Information> list_u = iuserservice.Dao_All_Che_User();
        for (User_Information comm: list_u){
            if(comm.getUid().equals(uid)){
                flag=true;
                user_information = comm;
                break;
            }
        }
        //判断完毕，执行删除
        if (flag==true){
            iuserservice.Del_User(user_information);
            System.out.println("删除用户信息成功！0.5秒后回到管理员权限页面");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("用户信息删除失败！(。﹏。)");
        }
    }

    //5.添加商品表的一个数据
    public void Dao_Add_information() {
        System.out.println("请输入商品名称：");
        String Cname = sc.next();
        System.out.println("请输入商品价钱：");
        Double Cprice = sc.nextDouble();
        System.out.println("请输入商品类别");
        String Type = sc.next();
        System.out.println("请输入商品数量(正整数)：");
        int Cinventory = sc.nextInt();
        Commodity_Information commodityInformation = new Commodity_Information();
        commodityInformation.setCid(UUID.randomUUID().toString());
        commodityInformation.setCname(Cname);
        commodityInformation.setCprice(Cprice);
        commodityInformation.setType(Type);
        commodityInformation.setCinventory(Cinventory);
        icommodityservice.Add_information(commodityInformation);
    }

    //6.添加用户表的一个数据
    public void Dao_Add_User() {
        System.out.println("请输入用户名称：");
        String Account = sc.next();
        System.out.println("请输入用户密码：");
        String Password = sc.next();
        System.out.println("请输入用户余额");
        Double Balance= sc.nextDouble();
        //创建用户对象
        User_Information user_information = new User_Information();
        user_information.setUid(UUID.randomUUID().toString());
        user_information.setAccount(Account);
        user_information.setPassword(Password);
        user_information.setBalance(Balance);
        iuserservice.Add_User(user_information);

    }

    //7.修改商品表的一个数据
    public void Dao_Upd_information(){
        // 给输入正确的cid设置编号
        boolean flagcc = true;
        String cid = "";
        while (flagcc){
            System.out.println("请输入上面列表中任意一个商品编号：");
             cid = sc.next();
            List<Commodity_Information> listc = icommodityservice.Che_information();
            for (Commodity_Information com :listc){
                if (com.getCid().equals(cid)){
                    //循环出口
                    flagcc= false;
                    break;
                }
            }
            if (flagcc == true){
                System.out.println("该商品编号不存在");
            }
        }
        System.out.println("请输入你要修改的商品价钱");
        double Cprice = sc.nextDouble();
        System.out.println("请输入你要修改的商品数量:");
        int Cinventory = sc.nextInt();
        // 创建Bookinfo对象
        Commodity_Information commodity_information = new Commodity_Information();
        commodity_information.setCid(cid);
        commodity_information.setCprice(Cprice);
        commodity_information.setCinventory(Cinventory);
        icommodityservice.Upd_information(commodity_information);
        System.out.println("修改商品信息成功！0.5秒后回到管理员权限页面");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //8.修改用户表的一个数据
    public void Dao_Upd_User() {

        // 给输入正确的cid设置编号
        boolean flagcc = true;
        String Uid ="";
        while (flagcc){
            System.out.println("请输入上面列表中任意一个你想修改的用户id：");
             Uid = sc.next();
            List<User_Information> listc = iuserservice.Dao_All_Che_User();
            for (User_Information com :listc){
                if (com.getUid().equals(Uid)){
                    //循环出口
                    flagcc= false;
                    break;
                }
            }
            if (flagcc == true){
                System.out.println("该商品编号不存在");
            }
        }
        System.out.println("请输入你要修改的用户名称");
        String Account = sc.next();
        System.out.println("请输入你要修改的用户密码:");
        String Password = sc.next();
        User_Information user = new User_Information();
        user.setUid(Uid);
        user.setAccount(Account);
        user.setPassword(Password);
        iuserservice.Upd_User(user);
        System.out.println("修改用户信息成功！0.5秒后回到管理员权限页面");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * (≧∇≦)ﾉ走，一起去逛超市(>▽"
     */

    public void  Go_shopping(){
        do{
            System.out.println("(｡･∀･)ﾉﾞ==========去淘宝贝吧==========(￣▽￣)～■干杯□～(￣▽￣)");
            System.out.println("1.看看有哪些商品(๑•̀ㅂ•́)و✧");
            System.out.println("2.历史记录查询");
            System.out.println("3.商品收藏");
            System.out.println("4.返回上级目录");
            System.out.println("5.系统退出");
            System.out.println("( ﹁ ﹁ ) ~→请输入1-4进行你的选择");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    stroll_commodity();
                    break;
                case 2:
                    check_history();
                    break;
                case 3:
                    collected_goods();
                    break;
                case 4:
                    System.out.println("一秒后返回上级目录...(*￣０￣)ノ[等等我…]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    User_Login();
                    break;
                case 5:
                    System.out.println("已退出，欢迎下次光临（o´・ェ・｀o）");
                    System.exit(0);
                    break;
                default:
                    System.out.println("对不起，您的选择有误，┗( T﹏T )┛请规范输入1-4");
            }
        }while (true);
    }
    /**
     * 用户充值
     */
    public void recharge_balance(){
        //根据玩家登入的用户名查询   "玩家名字查询不到，就会返回空值"这种事情是不可能发生的，用户已登入，怎么可能没法查到自己呢？
        //不需要再查找登入了，登入后，用户的信息都存在全局变量 user_information 里了
        System.out.println("请输入你要充值的金额:");
        double money = sc.nextDouble();
        user_information.setBalance(money+user_information.getBalance());
        //修改余额
        iuserservice.Dao_Rechar_balance(user_information);
        System.out.println("充值成功");
    }

    /**
     * 查询余额
     */
    public void check_balance(){
        //不需要再查找登入了，登入后，用户的信息都存在全局变量 user_information 里了
        Double balance = user_information.getBalance();
        System.out.println("当前账户余额:"+balance);
    }
    /**
     * 看看有哪些商品(๑•̀ㅂ•́)و✧
     */
    public void stroll_commodity(){
        //打印带序号的所有商品
        Print_NO_Commondity();

        do{
            System.out.println("(｡･∀･)ﾉﾞ========o(*￣︶￣*)o=====ε(*´･∀･｀)зﾞ=======(^&^)/)");
            System.out.println("1.加入购物车");
            System.out.println("2.商品结算");
            System.out.println("3.返回上级目录");
            System.out.println("4.系统退出");
            System.out.println("( ﹁ ﹁ ) ~→请输入1-4进行你的选择");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    add_to_Shopping_Cart();
                    break;
                case 2:
                    Pay_function();
                    break;
                case 3:
                    System.out.println("一秒后返回上级目录...(*￣０￣)ノ[等等我…]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Go_shopping();
                    break;
                case 4:
                    System.out.println("已退出，欢迎下次光临（o´・ェ・｀o）");
                    System.exit(0);
                    break;
                default:
                    System.out.println("对不起，您的选择有误，┗( T﹏T )┛请规范输入1-4");
            }
        }while (true);
    }
    /**
     * 打印序号从上往下，从1到n的商品信息
     */
    public void Print_NO_Commondity(){
        /**
         * 购物车执行前，先打印所有商品
         */
        List<Commodity_Information> list_c = icommodityservice.Che_information();
        Commodity_Information commod = new Commodity_Information();
        //用全局变量装，到其他方法调用这个被赋值后，包含序号的列表list_c2
        list_c2 = icommodityservice.Che_information();
        int xuhao = 1;
        System.out.println("序号  商品名称    商品单价    商品类型    库存剩余");
        for (Commodity_Information comm :list_c){
            commod = comm;
            commod.setCNO(xuhao);
            list_c2.add(commod);
            System.out.println(xuhao+"\t"+comm.getCname()+"\t"+comm.getCprice()+"\t"+comm.getType()+"\t"+comm.getCinventory());
            xuhao++;
        }
    }
    /**
     * 历史记录查询
     */
    public void check_history(){
        List<History> list = iotherservice.Che_Shopping();
        String id = user2.getUid();
        System.out.println("用户id\t\t"+"商品名\t\t"+"浏览时间");
        for (History h: list) {
            if(h.getUid().equals(id)) {
                System.out.println(h.getUid()+"\t\t\t"+h.getCname()+"\t\t\t"+h.getBrowernow());
            }
        }
    }

    /**
     * 商品收藏
     */
    public void collected_goods(){
        while (true) {
            System.out.println("1.添加商品收藏");
            System.out.println("2.删除商品收藏");
            System.out.println("3.查询商品收藏");
            System.out.println("4.返回上级目录");
            System.out.println("请输入你的选择:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Print_NO_Commondity();
                    System.out.println("请输入你要收藏的商品序号");
                    int a =sc.nextInt();
                    System.out.println();
                    List<Commodity_Information> list_c = icommodityservice.Che_information();
                    Collection collection =new Collection();
                    collection.setUid(user2.getUid());
                    collection.setCname(list_c.get(a-1).getCname());
                    collection.setType(list_c.get(a-1).getType());
                    collection.setCprice(list_c.get(a-1).getCprice());
                    collection.setCaddtime(LocalDateTime.now());
                    iotherservice.Dao_addCollection(collection);

                    break;
                case 2:
                    Print_NO_Collection();
                    System.out.println("请输入你要删除的商品序号");
                    int a2 =sc.nextInt();
                    System.out.println();
                    //获取用户Uid
                    Collection collectionqq = new Collection();
                    collectionqq.setUid(user_information.getUid());
                    List<Collection> list_c2 = iotherservice.Che_Collection(collectionqq);
                    Collection collection2 =new Collection();
                    collection2.setUid(user2.getUid());
                    collection2.setCname(list_c2.get(a2-1).getCname());
                    iotherservice.Dao_DeleteCollection(collection2);
                    break;
                case 3:
                    Print_NO_Collection();

                    break;
                case 4:
                    System.out.println("3 秒后返回上级目录");

                    try {
                        Thread.sleep(3000);
                        Go_shopping();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("您输入的选项有误请输入1到3");
            }

        }
    }
    /**
     * 商品收藏2
     */
    public void Print_NO_Collection(){
        //获取用户Uid
        Collection collection = new Collection();
        collection.setUid(user_information.getUid());
        List<Collection> list_c2 = iotherservice.Che_Collection(collection);
        System.out.println("序号\t\t\t"+"用户id\t\t\t"+"商品类别\t\t"+"商品名称\t\t"+"商品价格\t\t"+"收藏时间");
        for (int i = 0; i < list_c2.size(); i++) {
            Collection comm =new Collection();
            comm = list_c2.get(i);
            System.out.println((i+1)+"\t"+comm.getUid()+"\t"+comm.getType()+"\t"+comm.getCname()+"\t"+comm.getCprice()+"\t"+comm.getCaddtime());

        }
        System.out.println("--------------------------------------");
    }
    /**
     * 加入购物车
     */
    public void add_to_Shopping_Cart(){
        //设置flag标记商品位置
        boolean flag = false;
        System.out.println("请输入序号，将商品加入购物车");
        int haoma = sc.nextInt();
        for (Commodity_Information com : list_c2){
            if (com.getCNO()==haoma){
                flag =true;
                //保证用户输入的值必须是int型，如果不是，那就一直循环
                Integer number = 0;
                String regex = "^[0-9]*$";
                boolean flag_int = false;
                while(!flag_int){
                    System.out.println("请输入你要购买的数量：");
                    String str = sc.next();
                    // 判断是否是整数
                    if(str.matches(regex)){
                        // 如果是整数就转成Integer
                        number = Integer.parseInt(str);
                        flag_int = true;
                    }else{
                        System.out.println("购买的数量不是整数，请重新输入");
                    }
                }
                //将商品对象 com转化为 ，然后再加入 Shopping表
                Shopping shop = new Shopping();
                shop.setUid(user_information.getUid());
                shop.setCname(com.getCname());
                shop.setCprice(com.getCprice());
                shop.setNumber(number);
                iotherservice.Add_Shopping(shop);
                //将商品信息存于历史记录
                History HIS = new History();
                HIS.setUid(user_information.getUid());
                HIS.setCname(com.getCname());
                HIS.setBrowernow(LocalDateTime.now());
                iotherservice.Dao_Add_History(HIS);
                System.out.println("购物车添加成功");
            }
        }
        if (flag == false){
            System.out.println("你输入的序列号不存在 ┭┮﹏┭┮");
        }
        if(flag == true){
            System.out.println("要继续购买吗？ (。﹏。)");
            System.out.println("继续购买请输入 y,停止购买按任意键退出 ~o( =∩ω∩= )m");
            String yes = sc.next();
            if (yes.equalsIgnoreCase("y")){
                add_to_Shopping_Cart();
            }
            else {
                stroll_commodity();
            }
        }
    }

    /**
     * 商品结算
     */

    public void Pay_function(){
        // 提示用户是否玩猜扑克牌赢优惠
        System.out.println("猜扑克牌赢优惠，最高赢5折优惠哦(☆▽☆)");
        System.out.println("若要参加“猜扑克牌赢优惠”活动，请输入y");
        System.out.println("(p≧w≦q)不参加就按任意键退出哦");
        String yes = sc.next();
        if (yes.equalsIgnoreCase("y")){
            Guess_poker();
        }
        System.out.println("o(*￣︶￣*)o========进入付款页面=======*★,°*:.☆(￣▽￣)/$:*.°★*");
        //判断是否余额充足，余额不足提示充值，余额充足则直接扣费
            If_enough();
            //支付完成后，询问是否打印小票，并且不论打印小票与否，都要清空购物车
        Money_Reduce();
        while (true){
            System.out.println("请输入你的选择：");
            System.out.println("1.返回上级目录");
            System.out.println("2.系统退出");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("一秒后返回上级目录...(*￣０￣)ノ[等等我…]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("退出成功，欢迎下次光临（＃￣～￣＃）");
                    System.exit(0);
                    stroll_commodity();
                    break;
                default:
                    System.out.println("请输入1或2，不要乱输入选项哦┻━┻︵╰(‵□′)╯︵┻━┻");
            }
        }
    }

    /**
     * 判断是否余额充足，以及扣除余额功能
     */
    public void If_enough(){
        //余额充足就扣钱，不足就提示
        double sum = 0;
        List<Shopping> list = iotherservice.Che_History();
        double balance =  user_information.getBalance();
        for (int i = 0; i <list.size() ; i++) {
            if (user_information.getUid().equals(list.get(i).getUid()) ){
                sum =sum+(list.get(i).getCprice()) * (double) (list.get(i).getNumber())*zhekou;
            }
        }
        if (balance-sum>=0){
            balance=balance-sum;
            user_information.setBalance(balance);
            iuserservice.Dao_Rechar_balance(user_information);
            System.out.println("商品总价："+sum);
            System.out.println("余额充足，付款成功，您当前的余额为"+balance+"元");
            //扣除商品库存
            List<Commodity_Information> list_c = icommodityservice.Che_information();
            for(Commodity_Information comm: list_c){
                for (Shopping shop : list){
                    if (comm.getCname().equals(shop.getCname())){
                        //修改库存
                        comm.setCinventory(comm.getCinventory()-shop.getNumber());
                        //调用方法
                        icommodityservice.Upd_information(comm);
                    }
                }
            }
        }else{
            System.out.println("你的余额不足");
        }
    }
    /**
     * 猜扑克牌赢优惠
     */
    public void Guess_poker(){
        String[] suits = {"黑桃", "红桃", "梅花", "方块"};
        // 定义数字数组
        String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        Random r = new Random();
        int a = r.nextInt(4);
        int b = r.nextInt(13);
        System.out.println("请输入你猜测的花色");
        String suit = sc.next();
        System.out.println("请输入你猜测的数字");
        String number = sc.next();
        if (numbers[b].equals(number) && suits[a].equals(suit)) {
            System.out.println("恭喜你猜对了花色和数字,本次购物打7折");
            zhekou = 0.7;

        }else if (numbers[b].equals(number)){
            System.out.println("恭喜你猜对了数字，本次购物打8折");
            zhekou = 0.8;
        }else if (suits[a].equals(suit)){
            System.out.println("恭喜你猜对了花色，本次购物打9折");
            zhekou = 0.9;
        }else {
            System.out.println("很遗憾你没有猜对花色和数字");
            zhekou=1;
        }
    }

    /**
     * 打印小票和清空购物车
     */
    public void Money_Reduce(){
        //支付成功后，询问用户是否打印清单
        System.out.println("(o゜▽゜)o☆你要打印购物清单吗？");
        System.out.println("是的话请输入'y',否的话按任意键(っ*´Д`)っ");
        String yes = sc.next();
        if(yes.equalsIgnoreCase("y")){
            Buy_list();
            System.out.println("打印完毕");
            System.out.println("欢迎下次光临( *￣▽￣)((≧︶≦*)");
        }
        //删除购物车商品
        Shopping shopping = new Shopping();
        shopping.setUid(user_information.getUid());
        iotherservice.Del_Shopping_Uid(shopping);
    }

    /**
     * 打印购物清单
     */
    public void Buy_list() {
        File file = new File("购物小票.text");
        //创建一个文件
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        double sum = 0;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            List<Shopping> list = iotherservice.Che_History();
            writer.write("购物小票");
            writer.flush();
            writer.newLine();
            writer.write("品名" + "\t" + "单价" + "\t" + "数量");
            writer.flush();
            writer.newLine();
            for (int i = 0; i < list.size(); i++) {
                if (user_information.getUid().equals(list.get(i).getUid())) {
                    sum = sum + (list.get(i).getCprice()) * (double) (list.get(i).getNumber());
                    writer.write(list.get(i).getCname() + "\t" + list.get(i).getCprice() + "\t" + list.get(i).getNumber());
                    writer.flush();
                    writer.newLine();
                }
            }
            writer.write("总价" + "\t" + sum + "元");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //验证码
    public static String yzm(){
        Random r = new Random();
        //创建包含所有字母，数字的数组
        char[] all_fh = new char[62];
        for (int i = 0; i < 26; i++) {
            all_fh[i] = (char)('a'+i);
            all_fh[i+26] = (char) ('A'+i);
        }
        for (int i = 0; i < 10; i++) {
            all_fh[i+52] = (char) ('0'+i);
        }
        //创建 索引，用于标记布尔类型的数组，构建循环，对循环每次生成的数字进行标记，若第二次的数字与此前相同，则会“true”继续循环生成数字
        int index = -1;
        boolean[] flag = new boolean[all_fh.length];
        String[] four_yzm = new String[4];
        for (int i = 0; i < four_yzm.length; i++) {
            do{
                index = r.nextInt(all_fh.length);
            }while (flag[index]);
            four_yzm[i] = ""+all_fh[index];
            flag[index] = true;
        }
        String yzm_4 = "";
        for (int i = 0; i < four_yzm.length; i++) {
            yzm_4 = yzm_4+four_yzm[i];
        }
        return yzm_4;
    }

}
