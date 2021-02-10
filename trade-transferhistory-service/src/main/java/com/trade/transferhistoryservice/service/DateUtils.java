package com.trade.transferhistoryservice.service;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtils {

  public static Timestamp convertToTimestamp(Date date) {
    if (date != null) return new Timestamp(date.getTime());
    return null;
  }
}