package com.xigua.p2p;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.IOException;


public class P2PClass {
    private static String mPath;

    static {
        P2PClass.mPath = File.separator + "xigua";
        System.loadLibrary("p2p");
    }

    public P2PClass() {
        super();
        String nosdc = GetPath.getNormalSDCardPath();
        String sdc = GetPath.getSDCardPath();
        Log.v("P2PClass", "oldPath==" + nosdc + "...newPath==" + sdc);
        File v2 = new File(nosdc + P2PClass.mPath);
        File v3 = new File(sdc + P2PClass.mPath);
        if((sdc.equals(nosdc)) || !v2.exists() || (v3.exists())) {
            Log.v("xigua", "p2p started:" + GetPath.getSDCardPath());
           KJLoger.debug("doxstarthttpd code = " + this.doxstarthttpd(sdc.getBytes()));

        }
        else {
            KJLoger.debug("doxstarthttpd code = " + this.doxstarthttpd(nosdc.getBytes()));
        }
    }

    public static void reSet() {
        String v0 = GetPath.getNormalSDCardPath();
        String v1 = GetPath.getSDCardPath();
        File v2 = new File(v0 + P2PClass.mPath);
        File v3 = new File(v1 + P2PClass.mPath);
        v0 = (v1.equals(v0)) || !v2.exists() || (v3.exists()) ? "rm -r " + v1 + P2PClass.mPath : "rm -r " + v0 + P2PClass.mPath;
        Runtime v1_1 = Runtime.getRuntime();
        try {
            v1_1.exec(v0);
        }
        catch(IOException v0_1) {
            v0_1.printStackTrace();
        }
    }

    public static boolean a(Context arg2) {
        boolean v0 = ((ConnectivityManager)arg2.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(1).isConnected() ? true : false;
        return v0;
    }

    public int start(byte[] arg2) {
        return this.doxstart(arg2);
    }

    public long getSpeed(int arg3) {
        return this.getspeed(arg3);
    }

    public int download(byte[] arg2) {
        return this.doxdownload(arg2);
    }

    public long getAvailable() {
        String v0 = GetPath.getNormalSDCardPath();
        String v1 = GetPath.getSDCardPath();
        File v2 = new File(v0 + P2PClass.mPath);
        File v3 = new File(v1 + P2PClass.mPath);
        if((v1.equals(v0)) || !v2.exists() || (v3.exists())) {
            v0 = v1;
        }

        StatFs v1_1 = new StatFs(v0);
        return (((long)v1_1.getBlockSize())) * (((long)v1_1.getAvailableBlocks()));
    }

    public long getDownSize(int arg3) {
        return this.getdownsize(arg3);
    }

    public int getPercent() {
        return this.getpercent();
    }

    public int add(byte[] arg2) {
        return this.doxadd(arg2);
    }

    public long getFileSize(int arg3) {
        return this.getfilesize(arg3);
    }

    public int pause(byte[] arg2) {
        return this.doxpause(arg2);
    }

    public long setDuration(int arg3) {
        return ((long)this.doxsetduration(arg3));
    }

    public int delFile(byte[] arg2) {
        return this.doxdel(arg2);
    }

    public long getLocalFileSize(byte[] arg3) {
        return this.getlocalfilesize(arg3);
    }

    /**
     * 添加
     * @param arg1
     * @return
     */
    private final native int doxadd(byte[] arg1);

    /**
     * 删除
     * @param arg1
     * @return
     */
    private final native int doxdel(byte[] arg1);

    /**
     *下载
     * @param arg1
     * @return
     */
    private final native int doxdownload(byte[] arg1);


    /**
     * 下载进度
     * @param arg1
     * @return
     */
    private final native int doxpause(byte[] arg1) ;

    /**
     * 下载位移
     * @param arg1
     * @return
     */
    private final native int doxsetduration(int arg1);

    /**
     * 开始下载
     * @param arg1
     * @return
     */

    private final native int doxstart(byte[] arg1) ;


    /**
     *
     * @param arg1
     * @return
     */
    private final native int doxstarthttpd(byte[] arg1);

    /**
     * 得到下载文件的大小
     * @param arg1
     * @return
     */
    private final native long getdownsize(int arg1) ;

    /**
     * 源文件大小
     * @param arg1
     * @return
     */
    private final native long getfilesize(int arg1) ;

    /**
     * 本地文件大小
     * @param arg1
     * @return
     */
    private final native long getlocalfilesize(byte[] arg1);

    /**
     * 百分比
     * @return
     */
    private final native int getpercent();

    /**
     * 下载速度
     * @param arg1
     * @return
     */
    private final native long getspeed(int arg1) ;
}

