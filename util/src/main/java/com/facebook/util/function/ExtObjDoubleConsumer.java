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
import java.util.function.ObjDoubleConsumer;

public interface ExtObjDoubleConsumer<T, E extends Throwable> {
  void accept(T t, double value) throws E;
  
  static <T> ObjDoubleConsumer<T> quiet(ExtObjDoubleConsumer<T, ?> objDoubleConsumer) {
    return (t, value) -> ExtRunnable.quiet(() -> objDoubleConsumer.accept(t, value)).run();
  }
}
