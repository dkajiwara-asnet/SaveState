package com.example.dkajiwara.savestate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.dkajiwara.savestate.InjectSave;
import com.dkajiwara.savestate.SaveState;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends AndroidTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @org.junit.Test
    public void initTest() {
        Test test = new Test("xxxx", 1, (short)2, 3f, 4l, new Integer(1), new Short((short)2), new Float((float)3), new Long((long)5));
        Bundle bundle = new Bundle();
        InjectSave.saveInstanceState(test, bundle);

        assertThat(bundle.getString("mString$$SaveState"), is("xxxx"));
        assertThat(bundle.getInt("mIntP$$SaveState"), is(1));
        assertThat(bundle.getShort("mShortP$$SaveState"), is((short)2));
        assertThat(bundle.getFloat("mFloatP$$SaveState"), is(3f));
        assertThat(bundle.getLong("mLongP$$SaveState"), is(4l));
        assertThat(bundle.getInt("mInteger$$SaveState"), is(new Integer(1)));
        assertThat(bundle.getShort("mShort$$SaveState"), is(new Short((short)2)));
        assertThat(bundle.getFloat("mFloat$$SaveState"), is(new Float((float)3)));
        assertThat(bundle.getLong("mLong$$SaveState"), is(new Long((long)5)));
        assertThat(bundle.getStringArray("mStrings$$SaveState"), is(new String[]{"one", "two", "three"}));
        assertThat(bundle.getIntArray("mInts$$SaveState"), is(new int[]{1, 2, 3}));
        assertThat(bundle.getShortArray("mShorts$$SaveState"), is(new short[]{1, 2, 3}));
        assertThat(bundle.getFloatArray("mFloats$$SaveState"), is(new float[]{1f, 2f, 3f}));
        assertThat(bundle.getLongArray("mLongs$$SaveState"), is(new long[]{1l, 2l, 3l}));
        assertThat(bundle.getStringArrayList("mStringList$$SaveState"),
                is(Arrays.asList("one", "two", "three")));
        assertThat(bundle.getIntegerArrayList("mIntegerList$$SaveState"),
                is(Arrays.asList(1, 2, 3)));
        assertThat(bundle.getCharSequenceArrayList("mCharSequenceList$$SaveState"),
                is(Arrays.asList((CharSequence)"one", (CharSequence)"two", (CharSequence)"three")));
        Intent intent = bundle.getParcelable("mParcelable$$SaveState");
        assertThat(intent.getAction(), is("ACTION"));
        assertThat(bundle.getByte("mByteP$$SaveState"), is((byte)12));
        assertThat(bundle.getByte("mByte$$SaveState"), is(new Byte((byte) 12)));
    }

    @org.junit.Test
    public void restoreState() {
        Test test = new Test("xxxx", 1, (short)2, 3f, 4l, new Integer(1), new Short((short)2), new Float((float)3), new Long((long)5));
        Bundle bundle = new Bundle();
        InjectSave.saveInstanceState(test, bundle);

        test.clearFiled();

        InjectSave.restoreInstanceState(test, bundle);

        assertThat(bundle.getString("mString$$SaveState"), is("xxxx"));
        assertThat(bundle.getInt("mIntP$$SaveState"), is(1));
        assertThat(bundle.getShort("mShortP$$SaveState"), is((short)2));
        assertThat(bundle.getFloat("mFloatP$$SaveState"), is(3f));
        assertThat(bundle.getLong("mLongP$$SaveState"), is(4l));
        assertThat(bundle.getInt("mInteger$$SaveState"), is(new Integer(1)));
        assertThat(bundle.getShort("mShort$$SaveState"), is(new Short((short)2)));
        assertThat(bundle.getFloat("mFloat$$SaveState"), is(new Float((float)3)));
        assertThat(bundle.getLong("mLong$$SaveState"), is(new Long((long)5)));
        assertThat(bundle.getStringArray("mStrings$$SaveState"), is(new String[]{"one", "two", "three"}));
        assertThat(bundle.getIntArray("mInts$$SaveState"), is(new int[]{1, 2, 3}));
        assertThat(bundle.getShortArray("mShorts$$SaveState"), is(new short[]{1, 2, 3}));
        assertThat(bundle.getFloatArray("mFloats$$SaveState"), is(new float[]{1f, 2f, 3f}));
        assertThat(bundle.getLongArray("mLongs$$SaveState"), is(new long[]{1l, 2l, 3l}));
        assertThat(bundle.getStringArrayList("mStringList$$SaveState"),
                is(Arrays.asList("one", "two", "three")));
        assertThat(bundle.getIntegerArrayList("mIntegerList$$SaveState"),
                is(Arrays.asList(1, 2, 3)));
        assertThat(bundle.getCharSequenceArrayList("mCharSequenceList$$SaveState"),
                is(Arrays.asList((CharSequence)"one", (CharSequence)"two", (CharSequence)"three")));
        Intent intent = bundle.getParcelable("mParcelable$$SaveState");
        assertThat(intent.getAction(), is("ACTION"));
        assertThat(bundle.getByte("mByteP$$SaveState"), is((byte)12));
        assertThat(bundle.getByte("mByte$$SaveState"), is(new Byte((byte) 12)));
    }

    private static class Test {
        @SaveState
        private String mString;
        @SaveState
        private int mIntP;
        @SaveState
        private short mShortP;
        @SaveState
        private float mFloatP;
        @SaveState
        private long mLongP;
        @SaveState
        private byte mByteP;
        @SaveState
        private Integer mInteger;
        @SaveState
        private Short mShort;
        @SaveState
        private Float mFloat;
        @SaveState
        private Long mLong;
        @SaveState
        private Byte mByte;
        @SaveState
        private String[] mStrings;
        @SaveState
        private int[] mInts;
        @SaveState
        private short[] mShorts;
        @SaveState
        private float[] mFloats;
        @SaveState
        private long[] mLongs;
        @SaveState
        private List<String> mStringList;
        @SaveState
        private List<Integer> mIntegerList;
        @SaveState
        private List<CharSequence> mCharSequenceList;
        @SaveState
        private Parcelable mParcelable;

        private Test(String string, int intP, short shortP, float floatP, long longP, Integer integer, Short aShort, Float aFloat, Long aLong) {
            mString = string;
            mIntP = intP;
            mShortP = shortP;
            mFloatP = floatP;
            mLongP = longP;
            mByteP = 12;
            mInteger = integer;
            mShort = aShort;
            mFloat = aFloat;
            mLong = aLong;
            mByte = new Byte((byte) 12);
            mStrings = new String[]{"one", "two", "three"};
            mInts = new int[]{1, 2, 3};
            mShorts = new short[]{1, 2, 3};
            mFloats = new float[]{1f, 2f, 3f};
            mLongs = new long[]{1l, 2l, 3l};
            mCharSequenceList = new ArrayList<>();
            mCharSequenceList.add((CharSequence)"one");
            mCharSequenceList.add((CharSequence)"two");
            mCharSequenceList.add((CharSequence)"three");
            mStringList = new ArrayList<>();
            mStringList.add("one");
            mStringList.add("two");
            mStringList.add("three");
            mIntegerList = new ArrayList<>();
            mIntegerList.add(1);
            mIntegerList.add(2);
            mIntegerList.add(3);
            mParcelable = new Intent("ACTION");
        }

        public void clearFiled() {
            mString = null;
            mIntP = 0;
            mShortP = 0;
            mFloatP = 0;
            mLongP = 0;
            mByteP = 0;
            mInteger = null;
            mShort = null;
            mFloat = null;
            mLong = null;
            mByte = null;
            mStrings = null;
            mInts = null;
            mShorts = null;
            mFloats = null;
            mLongs = null;
            mCharSequenceList = null;
            mStringList = null;
            mIntegerList = null;
            mParcelable = null;
        }
    }
}