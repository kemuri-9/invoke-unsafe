/**
 * Copyright 2022 Steven Walters
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kemuri9.invoke.unsafe.test;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.kemuri9.invoke.GetFullAccess;
import net.kemuri9.invoke.InvokeUtils;
import net.kemuri9.invoke.unsafe.GetFullAccessUnsafe;

class GetFullAccessUnsafeTest {

    private static MethodHandles.Lookup getFullAccess() throws Exception {
        Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
        field.setAccessible(true);
        MethodHandles.Lookup expected = (MethodHandles.Lookup) field.get(null);
        return expected;
    }

    @Test
    void testRun() throws Exception {
        GetFullAccess action = new GetFullAccessUnsafe();
        MethodHandles.Lookup lookup = action.run();
        Assertions.assertSame(getFullAccess(), lookup);
    }

    @Test
    void testGetFullAccess() throws Exception {
        Assertions.assertSame(getFullAccess(), InvokeUtils.getFullAccessLookup());
    }
}
