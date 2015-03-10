package com.example.dkajiwara.savestate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.SparseArray;

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
        Test test = new Test("xxxx", 1, (short)2, 3f, 4l, new Integer(1), new Short((short)2), new Float((float)3), new Long((long)5), true, 'a');
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
        Intent intent = bundle.getParcelable("mParcelable$$SaveState");
        assertThat(intent.getAction(), is("ACTION"));
        assertThat(bundle.getByte("mByteP$$SaveState"), is((byte)12));
        assertThat(bundle.getByte("mByte$$SaveState"), is(new Byte((byte) 12)));
        assertThat(bundle.getBoolean("mBoolean$$SaveState"), is(true));
        assertThat(bundle.getChar("mChar$$SaveState"), is('a'));
        StringBuilder sb = (StringBuilder) bundle.getCharSequence("mStringBuilder$$SaveState");
        assertThat(sb.toString(), is("one"));
        assertThat(bundle.getCharArray("mChars$$SaveState"), is(new char[]{'a', 'b', 'c'}));
        assertThat(bundle.getByteArray("mBytes$$SaveState"), is(new byte[]{1, 2, 3}));
        assertThat(((Test.IntentData)bundle.getSparseParcelableArray("mSparseArray$$SaveState").get(1)).getIntValue(), is(1));
        CharSequence[] charSequences = bundle.getCharSequenceArray("mCharSequences$$SaveState");
        assertThat(((StringBuilder)charSequences[0]).toString(), is("one"));
        assertThat(((Test.IntentData)bundle.getParcelableArray("mParcelables$$SaveState")[0]).getIntValue(), is(100));
        assertThat(((Test.IntentData)bundle.getParcelableArrayList("mParcelableList$$SaveState").get(0)).getIntValue(), is(200));
    }

    @org.junit.Test
    public void restoreState() {
        Test test = new Test("xxxx", 1, (short)2, 3f, 4l, new Integer(1), new Short((short)2), new Float((float)3), new Long((long)5), false, 'a');

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
        Intent intent = bundle.getParcelable("mParcelable$$SaveState");
        assertThat(intent.getAction(), is("ACTION"));
        assertThat(bundle.getByte("mByteP$$SaveState"), is((byte)12));
        assertThat(bundle.getByte("mByte$$SaveState"), is(new Byte((byte) 12)));
        assertThat(bundle.getBoolean("mBoolean$$SaveState"), is(false));
        StringBuilder sb = (StringBuilder) bundle.getCharSequence("mStringBuilder$$SaveState");
        assertThat(sb.toString(), is("one"));
        assertThat(bundle.getChar("mChar$$SaveState"), is('a'));
        assertThat(bundle.getCharArray("mChars$$SaveState"), is(new char[]{'a', 'b', 'c'}));
        assertThat(bundle.getByteArray("mBytes$$SaveState"), is(new byte[]{1, 2, 3}));
        assertThat(bundle.getBundle("mBundle$$SaveState").getString("1"), is("one"));
        assertThat(((Test.IntentData) bundle.getSparseParcelableArray("mSparseArray$$SaveState").get(1)).getIntValue(), is(1));
        CharSequence[] charSequences = bundle.getCharSequenceArray("mCharSequences$$SaveState");
        assertThat(((StringBuilder)charSequences[0]).toString(), is("one"));
        assertThat(((Test.IntentData)bundle.getParcelableArray("mParcelables$$SaveState")[0]).getIntValue(), is(100));
        assertThat(((Test.IntentData)bundle.getParcelableArrayList("mParcelableList$$SaveState").get(0)).getIntValue(), is(200));
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void inValidError() {
        InvalidTest test = new InvalidTest();
        Bundle bundle = new Bundle();
        InjectSave.saveInstanceState(test, bundle);
    }

    private static class InvalidTest {
        @SaveState
        private Context mContext;
    }

    private static class Test {
        @SaveState
        private static String mIgnoreStatic = "one";
        @SaveState
        private final String mIgnoreFinal = "two";

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
        @SaveState
        private boolean mBoolean;
        @SaveState
        private char mChar;
        @SaveState
        private char[] mChars;
        @SaveState
        private CharSequence mStringBuilder;
        @SaveState
        private byte[] mBytes;
        @SaveState
        private Bundle mBundle;
        @SaveState
        private SparseArray<Parcelable> mSparseArray;
        @SaveState
        private CharSequence[] mCharSequences;
        @SaveState
        private Parcelable[] mParcelables;
        @SaveState
        private List<Parcelable> mParcelableList;

        private Test(String string, int intP, short shortP, float floatP, long longP, Integer integer, Short aShort, Float aFloat, Long aLong, boolean bool, char _char) {
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
            mCharSequenceList.add(new StringBuilder().append("one"));
            mCharSequenceList.add(new StringBuilder().append("two"));
            mCharSequenceList.add(new StringBuilder().append("three"));
            mStringList = new ArrayList<>();
            mStringList.add("one");
            mStringList.add("two");
            mStringList.add("three");
            mIntegerList = new ArrayList<>();
            mIntegerList.add(1);
            mIntegerList.add(2);
            mIntegerList.add(3);
            mParcelable = new Intent("ACTION");
            mBoolean = bool;
            mChar = _char;
            mStringBuilder = new StringBuilder().append("one");
            mChars = new char[]{'a', 'b', 'c'};
            mBytes = new byte[]{ 1, 2, 3};
            mBundle = new Bundle();
            mBundle.putString("1", "one");
            mSparseArray = new SparseArray<>();
            mSparseArray.put(1, new IntentData().setIntValue(1));
            mCharSequences = new CharSequence[] {
                    new StringBuilder().append("one")
            };
            mParcelables = new Parcelable[]{
                    new IntentData().setIntValue(100)
            };
            mParcelableList = new ArrayList<>();
            mParcelableList.add(new IntentData().setIntValue(200));
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
            mBoolean = false;
            mChar = 0;
            mStringBuilder = null;
            mChars = null;
            mBytes = null;
            mBundle = null;
            mCharSequences = null;
            mParcelables = null;
        }

        public static class IntentData implements Parcelable {
            private int mData;

            public static final Parcelable.Creator<IntentData> CREATOR
                    = new Parcelable.Creator<IntentData>() {
                public IntentData createFromParcel(Parcel in) {
                    return new IntentData(in);
                }

                public IntentData[] newArray(int size) {
                    return new IntentData[size];
                }
            };

            public IntentData() {

            }

            private IntentData(Parcel in) {
                mData = in.readInt();
            }

            public IntentData setIntValue(int i) {
                mData = i;
                return this;
            }

            public int getIntValue() {
                return mData;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel out, int flags) {
                out.writeInt(mData);
            }

        }
    }
}