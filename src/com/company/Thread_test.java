package com.company;

class Cangku{
    private char[] zimubiao = new char[8];
    private int xiabiao = 0;

    public synchronized void shengchan(char zimu){
        while(xiabiao == zimubiao.length){
            try {
                this.wait();
            }catch (Exception ignored){

            }
        }
        this.notify();

        zimubiao[xiabiao] = zimu;
        ++xiabiao;

        System.out.println("正在生产第" + xiabiao + "个产品，该产品是" + zimu);
    }

    public synchronized void xiaofei(){
        char zimu;

        while (xiabiao == 0){
            try {
                this.wait();
            }catch (Exception ignored){
            }
        }
        this.notify();
        zimu = zimubiao[xiabiao-1];

        System.out.println("消费线程正在消费第" + xiabiao + "个产品，该产品是：" + zimu);

        --xiabiao;
    }
}

class Shengchan implements Runnable{
    private Cangku cangku = null;

    public Shengchan(Cangku cangku){
        this.cangku = cangku;
    }

    public void run(){
        char zimu;

        for (int i=0;i<26;i++){
            zimu = (char)('A' + i);
            cangku.shengchan(zimu);
        }
    }
}

class Xiaofei implements Runnable {
    private Cangku cangku = null;

    public Xiaofei(Cangku cangku) {
        this.cangku = cangku;
    }

    public void run(){
        for(int i=0;i<26;i++){
            cangku.xiaofei();
        }
    }
}
public class Thread_test {
    public static void main(String [] args){
        Cangku cangku = new Cangku();
        Shengchan shengchan = new Shengchan(cangku);
        Xiaofei xiaofei = new Xiaofei(cangku);

        Thread shengchan_t = new Thread(shengchan);
        Thread xiaofei_t = new Thread(xiaofei);

        shengchan_t.start();
        xiaofei_t.start();
    }
}
