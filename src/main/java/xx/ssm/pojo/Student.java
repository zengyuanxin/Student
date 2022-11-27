package xx.ssm.pojo;

/**
 * @author 星星
 * @create 2022-11-23 10:44
 */
public class Student {
    private Integer ID;

    private String NICK;

    private String PASSWORD;

    private String STUNO;

    private String STUNAME;

    private String SEX;

    private String AGE;

    private String PHONE;

    private String EMAIL;

    private String PIC;

    private String INTRODUCE;

    public Student() {
    }

    public Student(Integer ID, String NICK, String PASSWORD, String STUNO, String STUNAME, String SEX, String AGE, String PHONE, String EMAIL, String PIC, String INTRODUCE) {
        this.ID = ID;
        this.NICK = NICK;
        this.PASSWORD = PASSWORD;
        this.STUNO = STUNO;
        this.STUNAME = STUNAME;
        this.SEX = SEX;
        this.AGE = AGE;
        this.PHONE = PHONE;
        this.EMAIL = EMAIL;
        this.PIC = PIC;
        this.INTRODUCE = INTRODUCE;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "ID=" + ID +
                ", NICK='" + NICK + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", STUNO='" + STUNO + '\'' +
                ", STUNAME='" + STUNAME + '\'' +
                ", SEX='" + SEX + '\'' +
                ", AGE='" + AGE + '\'' +
                ", PHONE='" + PHONE + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", PIC='" + PIC + '\'' +
                ", INTRODUCE='" + INTRODUCE + '\'' +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNICK() {
        return NICK;
    }

    public void setNICK(String NICK) {
        this.NICK = NICK;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getSTUNO() {
        return STUNO;
    }

    public void setSTUNO(String STUNO) {
        this.STUNO = STUNO;
    }

    public String getSTUNAME() {
        return STUNAME;
    }

    public void setSTUNAME(String STUNAME) {
        this.STUNAME = STUNAME;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }

    public String getINTRODUCE() {
        return INTRODUCE;
    }

    public void setINTRODUCE(String INTRODUCE) {
        this.INTRODUCE = INTRODUCE;
    }
}
