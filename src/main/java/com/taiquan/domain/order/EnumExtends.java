package com.taiquan.domain.order;

//http://www.importnew.com/19549.html
public class EnumExtends {
    public static void main(String args[]){
        CMD[] name = CMD.values();
        for (CMD cmd : name) {
            System.out.println("i:" + cmd.value());
        }
    }
    public static enum CMD{
        e_CMD_MIN(900),
        e_CMD_SET_PORT(e_CMD_MIN.value),
        e_CMD_GET_PORT,
        e_CMD_SET_POS,
        e_CMD_MAX,
        e_CMD_RET_MIN(1000),
        e_CMD_URGENT_MSG(e_CMD_RET_MIN.value),
        e_CMD_SET_POWER,
        e_CMD_GET_POWER,
        e_CMD_RET_MAX;
        private final int value;
        private static int count = 0;

        private static void set(int value){
            count = value;
        }
        private static int prv(){
            return count;
        }
        private static int next(){
            return count++;
        }
        //默认构造函数,value依次递增
        CMD(){
            this.value = next();
            //this.value = count++;
        };
        //构造函数：直接指定value的值
        CMD(int value){
            //检查新指定的value是否合法,非法时抛出异常
            if((value + 1) < prv()){
                try {
                    throw new Exception(this.name()
                            + ":Incorrect value,previous is " + prv()
                            + ",current is " + value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.value = value;
            set(value + 1);
        }
        //获取每个成员的值
        public final int value(){
            return value;
        }
    }
}