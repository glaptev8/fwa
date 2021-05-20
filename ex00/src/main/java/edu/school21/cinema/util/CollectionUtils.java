package edu.school21.cinema.util;

import java.util.Collection;

public class CollectionUtils {

  static public boolean isNonEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }

  static public boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }
}
