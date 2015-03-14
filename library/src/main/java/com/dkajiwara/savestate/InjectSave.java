/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.dkajiwara.savestate;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dkajiwara
 */
public class InjectSave {
    /** TAG.*/
    private static final String TAG = InjectSave.class.getSimpleName();
    /** Suffix for Bundle key.*/
    private static final String SUFFIX = "$$SaveState";

    private InjectSave() { }

    /**
     * Save the instance variable field SaveState Annotation is set to this bundle.
     * <p/>
     * Examples:
     * <pre>{@code
     *  '@SaveState
     *  String title = null; //AutoSave
     *
     *  '@Override
     *  public void onSaveInstanceState(Bundle outState) {
     *      super.onSaveInstanceState(outState);
     *      InjectSave.saveInstanceState(this, outState);
     *      view.setText(title);
     *  }
     * }</pre>
     * @param target   context
     * @param outState bundle
     */
    public static void saveInstanceState(@NonNull Object target, @NonNull Bundle outState){
        Class clazz = target.getClass();
        List<Field> fields = ReflectUtils.getDeclaredAnnotationFields(clazz, SaveState.class);
        for (Field field : fields) {
            try {
                String key = field.getName();
                Object value = field.get(target);
                put(outState, key + SUFFIX, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void put(Bundle bundle, String key, Object value) {
        Log.d(TAG, "key " + key + ", value " + value);
        if (value instanceof Integer) {
            bundle.putInt(key, (Integer) value);
        } else if (value instanceof int[]) {
            bundle.putIntArray(key, (int[]) value);
        } else if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof String[]) {
            bundle.putStringArray(key, (String[]) value);
        } else if (value instanceof  Boolean) {
            bundle.putBoolean(key, (Boolean) value);
        } else if (value instanceof Character) {
            bundle.putChar(key, (Character) value);
        } else if (value instanceof char[]) {
            bundle.putCharArray(key, (char[]) value);
        } else if (value instanceof Float) {
            bundle.putFloat(key, (Float) value);
        } else if (value instanceof float[]) {
            bundle.putFloatArray(key, (float[]) value);
        } else if (value instanceof Short) {
            bundle.putShort(key, (Short) value);
        } else if (value instanceof short[]) {
            bundle.putShortArray(key, (short[]) value);
        } else if (value instanceof Long) {
            bundle.putLong(key, (Long) value);
        } else if (value instanceof long[]) {
            bundle.putLongArray(key, (long[]) value);
        } else if (value instanceof Byte) {
            bundle.putByte(key, (Byte) value);
        } else if (value instanceof byte[]) {
            bundle.putByteArray(key, (byte[]) value);
        } else if (value instanceof CharSequence) {
            bundle.putCharSequence(key, (CharSequence) value);
        } else if (value instanceof CharSequence[]) {
            bundle.putCharSequenceArray(key, (CharSequence[]) value);
        } else if (value instanceof Bundle) {
            bundle.putBundle(key, (Bundle) value);
        } else if (value instanceof Parcelable) {
            bundle.putParcelable(key, (Parcelable) value);
        } else if (value instanceof Parcelable[]) {
            bundle.putParcelableArray(key, (Parcelable[]) value);
        } else if (isStringArrayList(value)) {
            bundle.putStringArrayList(key, (ArrayList<String>) value);
        } else if (isIntegerArrayList(value)) {
            bundle.putIntegerArrayList(key, (ArrayList<Integer>) value);
        } else if (isCharSequenceArrayList(value)) {
            bundle.putCharSequenceArrayList(key, (ArrayList<CharSequence>) value);
        } else if (isSparseArray(value)) {
            bundle.putSparseParcelableArray(key, (SparseArray<? extends Parcelable>) value);
        } else if (isParcelableArray(value)) {
            bundle.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isParcelableArray(Object value) {
        if (!(value instanceof ArrayList<?>)) {
            return false;
        }
        List<?> list = (ArrayList<?>) value;
        for (Object object : list) {
            if (!(object instanceof Parcelable)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSparseArray(Object value) {
        if (!(value instanceof SparseArray<?>)) {
            return false;
        }
        SparseArray<?> list = (SparseArray<?>) value;
        for (int i = 0, max = list.size(); i < max; i++) {
            if (!(list.valueAt(i) instanceof Parcelable)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCharSequenceArrayList(Object value) {
        if (!(value instanceof ArrayList<?>)) {
            return false;
        }
        List<?> list = (ArrayList<?>) value;
        for (Object object : list) {
            if (!(object instanceof CharSequence)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIntegerArrayList(Object value) {
        if (!(value instanceof ArrayList<?>)) {
            return false;
        }
        List<?> list = (ArrayList<?>) value;
        for (Object object : list) {
            if (!(object instanceof Integer)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStringArrayList(Object value) {
        if (!(value instanceof ArrayList<?>)) {
            return false;
        }
        List<?> list = (ArrayList<?>) value;
        for (Object object : list) {
            if (!(object instanceof String)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Restore the instance variable field SaveState annotation has been set from this Bundle.
     *
     * Examples:
     * <pre>{@code
     *  '@SaveState
     *  String title = null; //AutoSave
     *
     *  '@Override
     *  protected void onCreate(Bundle savedInstanceState) {
     *      super.onCreate(savedInstanceState);
     *      InjectSave.restoreInstanceState(this, savedInstanceState);
     *  }
     * }</pre>
     *
     * @param object context
     * @param savedInstanceState bundle
     */
    public static void restoreInstanceState(@NonNull Object object, @NonNull Bundle savedInstanceState) {
        Class clazz = object.getClass();
        List<Field> fields = ReflectUtils.getDeclaredAnnotationFields(clazz, SaveState.class);
        for (Field field : fields) {
            String key = field.getName();
            Object value = savedInstanceState.get(key + SUFFIX);
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static final class ReflectUtils {

        /**
         * Returns an array of Field objects that specified annotation.
         *
         * @param clazz class
         * @param targetAnnotation annotation
         * @return
         */
        private static List<Field> getDeclaredAnnotationFields(@NonNull Class clazz, @NonNull Class targetAnnotation) {
            List<Field> fields = new ArrayList<>();
            for (Field filed : clazz.getDeclaredFields()) {
                if (filed.getAnnotation(targetAnnotation) == null) {
                    continue;
                }
                int modifiers = filed.getModifiers();
                if (Modifier.isFinal(modifiers) ||
                        Modifier.isStatic(modifiers)) {
                    continue;
                }
                filed.setAccessible(true);
                fields.add(filed);
            }
            return fields;
        }

        private ReflectUtils() {}
    }

}
