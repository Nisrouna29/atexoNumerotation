package com.test.technique.utils;

import com.test.technique.model.Config;

 public interface ValueFormatter<T> {
    String format(T value);
}
