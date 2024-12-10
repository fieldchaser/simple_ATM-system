package ATM;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private ArrayList<account> accounts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private account loginAcc;
    private boolean exit = false;
    private account tempAccount;

    public void start(){
        while (true) {
            System.out.println("==========欢迎来到yty的ATM系统~============");
            System.out.println("1、登陆账户");
            System.out.println("2、开户");
            System.out.println("请输入您的选择:");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                default:
                    System.out.println("您输入的选项不正确，请重试");
            }
        }
    }

    private void createAccount(){
        account acc = new account();
        System.out.println("========开户=========");
        //账户名称
        System.out.println("请输入您的账户名称:");
        String accountName = sc.next();
        acc.setAccountId(accountName);
        //密码
        while (true) {
            System.out.println("请设置一个密码:");
            String password = sc.next();
            System.out.println("请重新输入确认密码:");
            String okPassword = sc.next();
            if (password.equals(okPassword)){
                System.out.println("密码设置成功！");
                acc.setPassWords(password);
                break;
            } else{
                System.out.println("两次输入的密码不一致，请重试");
            }
        }
        //性别
        while (true) {
            System.out.println("请输入您的性别:");
            String temp = sc.next();
            char sex = temp.charAt(0);
            if (sex == '男' || sex == '女'){
                acc.setSex(sex);
                break;
            } else{
                System.out.println("您输入的性别有误，请重试");
            }
        }
        //额度
        System.out.println("请输入您的取钱额度:");
        double Limit = sc.nextDouble();
        acc.setLimit(Limit);
        //开户成功
        System.out.println("恭喜" + acc.getAccountId() + "开户成功!");
        String cardid = createCardId();
        System.out.println("您的卡号是 " + cardid);
        acc.setCardID(cardid);

        accounts.add(acc);
    }
    //生成卡号
    private String createCardId(){
        String cardId = "";
        Random random = new Random();

        while (true) {
            boolean ok = true;
            for (int i = 0; i < 8; i++) {
                cardId += random.nextInt(10);
            }
            for (int i = 0; i < accounts.size(); i++){
                if (accounts.get(i).getAccountId().equals(cardId)){
                    ok = false;
                }
            }
            if (ok == true){
                return cardId;
            }
        }
    }

    //登录
    private void login(){
        //先判断列表是否为空
        if (accounts.size() == 0){
            System.out.println("当前系统中还没有账户，请先去开户!");
            return;
        }
        while (true) {
            System.out.println("请输入您的卡号:");
            String cardId = sc.next();
            //是否存在这个卡号
            for (int i = 0; i < accounts.size(); i++){
                if (accounts.get(i).getCardID().equals(cardId)){
                    loginAcc = accounts.get(i);
                }
            }
            if (loginAcc == null){
                System.out.println("未找到该账户，请重新输入卡号:");
            } else{
                while (true) {
                    System.out.println("请输入您的密码:");
                    String password = sc.next();
                    if (password.equals(loginAcc.getPassWords())){
                        System.out.println("登录成功！");
                        show();
                        if (exit) {
                            return;
                        } else{
                            break;
                        }
                    } else{
                        System.out.println("密码错误，请重试");
                    }
                }
            }
            break;
        }

        //登录页面展示
    }

    private void show(){
        System.out.println("======用户操作页面=======");
        System.out.println("1、查询账户");
        System.out.println("2、存款");
        System.out.println("3、取款");
        System.out.println("4、转账");
        System.out.println("5、修改密码");
        System.out.println("6、退出");
        System.out.println("7、注销账户");

        while (true) {
            System.out.println("请输入您的选择:");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("========查询账户=========");
                    System.out.println("账户名称:" + loginAcc.getAccountId());
                    System.out.println("性别:" + loginAcc.getSex());
                    System.out.println("额度:" + loginAcc.getLimit());
                    System.out.println("余额:" + loginAcc.getMoney());
                    break;
                case 2:
                    System.out.println("=========存款=========");
                    System.out.println("请输入您想要存款的数目:");
                    double money = sc.nextDouble();
                    loginAcc.setMoney(loginAcc.getMoney() + money);
                    System.out.println("存款成功!");
                    break;
                case 3:
                    System.out.println("========取款========");
                    //先判断当前余额是否大于100
                    if (loginAcc.getMoney() <= 100){
                        System.out.println("余额不足，不支持取款操作");
                        break;
                    }

                    while (true) {
                        System.out.println("请输入取款金额:");
                        double Money = sc.nextDouble();
                        if (loginAcc.getMoney() < Money){
                            System.out.println("余额不足，请重试");
                        } else if (Money > loginAcc.getLimit()) {
                            System.out.println("超过了限制，请重试");
                        } else{
                            loginAcc.setMoney(loginAcc.getMoney() - Money);
                            System.out.println("取款成功!");
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("========转账========");
                    //先判断当前是否账户数量大于等于2
                    if (accounts.size() < 2){
                        System.out.println("没有帐户给您转账qwq");
                        break;
                    }
                    while (true) {
                        System.out.println("请输入您想要转账的卡号:");
                        String cardId = sc.next();
                        for (int i = 0; i < accounts.size(); i++){
                            if (accounts.get(i).getCardID().equals(cardId)){
                                tempAccount = accounts.get(i);
                            }
                        }
                        if (tempAccount == null){
                            System.out.println("输入卡号错误，请重试");
                        } else{
                            System.out.println("找到目标账户!");
                            break;
                        }
                    }
                    //提示用户想要转账的数目
                    while (true) {
                        System.out.println("请输入您想要转账的数目:");
                        double number = sc.nextDouble();
                        if (number > loginAcc.getMoney()){
                            System.out.println("余额不足，请重试");
                        } else{
                            loginAcc.setMoney(loginAcc.getMoney() - number);
                            tempAccount.setMoney(tempAccount.getMoney() + number);
                            System.out.println("转账成功!");
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("========修改密码========");
                    System.out.println("请输入新密码:");
                    String newPassword = sc.next();
                    loginAcc.setPassWords(newPassword);
                    System.out.println("修改成功!");
                    break;
                case 6:
                    //退出
                    exit = true;
                    return;
                case 7:
                    //注销账户
                    System.out.println("您确定要注销账户? y/n");
                    char option  = sc.next().charAt(0);
                    switch (option){
                        case 'y':
                            //先看余额是否为0
                            if (loginAcc.getMoney() > 0){
                                System.out.println("该账户还有余额，不能注销!");
                            } else{
                                accounts.remove(loginAcc);
                                System.out.println("注销成功!");
                                return;
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    System.out.println("您输入的选项有误，请重试");
            }
        }
    }
}
