package model;

public class User {
    private String name;
    private String password;
    private long time;
    private int point;

    private double healthOfBoss = 100;

    private double healthOfCupHead = 5;

    private double cupHeadDamage = 1;

    private double enemyDamage = 1;

    public User(String name, String password, int time, int point) {
        this.name = name;
        this.password = password;
        this.time = time;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        if (this.time == 0) this.time = time;
        else {
            if (this.time > time) this.time = time;
        }
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public double getHealthOfBoss() {
        return healthOfBoss;
    }

    public void setHealthOfBoss(int healthOfBoss) {
        this.healthOfBoss = healthOfBoss;
    }

    public void setHealthOfBoss(double healthOfBoss) {
        this.healthOfBoss = healthOfBoss;
    }

    public double getHealthOfCupHead() {
        return healthOfCupHead;
    }

    public void setHealthOfCupHead(double healthOfCupHead) {
        this.healthOfCupHead = healthOfCupHead;
    }

    public double getCupHeadDamage() {
        return cupHeadDamage;
    }

    public void setCupHeadDamage(double cupHeadDamage) {
        this.cupHeadDamage = cupHeadDamage;
    }

    public double getEnemyDamage() {
        return enemyDamage;
    }

    public void setEnemyDamage(double enemyDamage) {
        this.enemyDamage = enemyDamage;
    }
}
