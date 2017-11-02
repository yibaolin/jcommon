/*
 * Copyright (C) 2012 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.util.function;

import com.facebook.util.ExtRunnable;
import java.util.Objects;
import java.util.function.Consumer;

public interface ExtConsumer<T, E extends Throwable> {
  void accept(T t) throws E;
  
  default ExtConsumer<T, E> andThen(ExtConsumer<? super T, E> after) {
    Objects.requireNonNull(after);
    return (t) -> {
      accept(t);
      after.accept(t);
    };
  }
  
  static <T> Consumer<T> quiet(ExtConsumer<T, ?> consumer) {
    return (t) -> ExtRunnable.quiet(() -> consumer.accept(t)).run();
  }
}
