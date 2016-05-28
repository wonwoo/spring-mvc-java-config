package me.wonwoo.domain.converter;

import me.wonwoo.domain.OrderType;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by wonwoo on 2016. 5. 28..
 */
public class MyConverter implements Converter<String, OrderType> {

  @Override
  public OrderType convert(String value) {
    return OrderType.value(Integer.parseInt(value));
  }
}
