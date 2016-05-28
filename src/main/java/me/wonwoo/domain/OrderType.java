package me.wonwoo.domain;

/**
 * Created by wonwoo on 2016. 5. 28..
 */
public enum OrderType {
  CALL(1),
  ONLINE(2),
  OFFLINE(3);

  int i;
  OrderType(int i) {
    this.i = i;
  }

  public int getValue(){
    return i;
  }
  public static OrderType value(int value){
    switch (value){
      case 1 :
        return CALL;
      case 2:
        return ONLINE;
      case 3:
        return OFFLINE;
      default:
        return CALL;
    }
  }
}
