package ATM;

public class account {
    private double limit; // 限额
    private double money; // 余额
    private String cardID; // 卡号
    private char sex; // 性别
    private String PassWords; // 密码
    private String accountId; // 账户名称

    public account(double limit, double money, String cardID, char sex, String passWords, String accountId) {
        this.limit = limit;
        this.money = money;
        this.cardID = cardID;
        this.sex = sex;
        PassWords = passWords;
        this.accountId = accountId;
    }

    public account() {
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPassWords() {
        return PassWords;
    }

    public void setPassWords(String passWords) {
        PassWords = passWords;
    }

    public String getAccountId() {
        return accountId + (sex == '男'?"先生":"女士");
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
