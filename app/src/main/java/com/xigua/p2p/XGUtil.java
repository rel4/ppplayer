package com.xigua.p2p;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tcking.github.com.giraffeplayer.GiraffePlayerActivity;

public class XGUtil {
    public XGUtil() {
        super();
    }

//    public static void PlayMovies(Intent arg4, Activity arg5) {
//        KJLoger.debug("intent = " + arg4.toString());
//        Log.delFile("L_S_log-L_S:-", "others browser open  xiguaBrowser Movies :PlayMovies: " + arg4.toString());
//        if(arg4 != null && arg4.getData() != null) {
//            String v0 = arg4.getDataString();
//            KJLoger.debug("替换前dataString = " + v0);
//            v0 = URLDecoder.decode(v0);
//            KJLoger.debug("替换之后dataString = " + v0);
//            v0.replaceAll("xg://", "ftp://");
//            System.out.println("dataString = " + v0);
//            XGUtil.playXG(v0, arg5, 1);
//        }
//    }

//    public static void delXG(String arg5, Activity arg6) {
//        Object v0;
//        List v2 = XGUtil.loadList(((Context)arg6));
//        Iterator v3 = v2.iterator();
//        do {
//            if(!v3.hasNext()) {
//                goto label_24;
//            }
//
//            v0 = v3.next();
//        }
//        while(!((Map)v0).get("url").equals(arg5));
//
//        try {
//            XGApplication.delFile().pause(((Map)v0).get(Integer.valueOf(6)).getBytes("GBK"));
//            XGApplication.delFile().delFile(((Map)v0).get(Integer.valueOf(6)).getBytes("GBK"));
//        }
//        catch(UnsupportedEncodingException v1) {
//            v1.printStackTrace();
//        }
//
//        v2.remove(v0);
//    label_24:
//        XGUtil.saveList(v2, ((Context)arg6));
//        XGUtil.stopAll(((Context)arg6));
//    }
//
//    public static ArrayList loadCacheList(Activity arg3) {
//        Object v0_3;
//        ArrayList v0_2;
//        try {
//            String v0_1 = arg3.getApplicationContext().getSharedPreferences("CacheList", 0).getString("List", "none");
//            if(v0_1.equals("none")) {
//                v0_2 = new ArrayList();
//                goto label_12;
//            }
//
//            ByteArrayInputStream v1 = new ByteArrayInputStream(URLDecoder.decode(v0_1, "UTF-8").getBytes("ISO-8859-1"));
//            ObjectInputStream v2 = new ObjectInputStream(((InputStream)v1));
//            v0_3 = v2.readObject();
//            v2.close();
//            v1.close();
//            if(v0_3 != null) {
//                goto label_12;
//            }
//
//            v0_2 = new ArrayList();
//        }
//        catch(Exception v0) {
//            v0.printStackTrace();
//            v0_2 = new ArrayList();
//        }
//
//    label_12:
//        return ((ArrayList)v0_3);
//    }
//
//    public static List loadHistoryList(Context arg3) {
//        ArrayList v0_2;
//        try {
//            String v0_1 = arg3.getApplicationContext().getSharedPreferences("HistoryList", 0).getString("HistoryList", "none");
//            if(v0_1.equals("none")) {
//                v0_2 = new ArrayList();
//                goto label_12;
//            }
//
//            ByteArrayInputStream v1 = new ByteArrayInputStream(URLDecoder.decode(v0_1, "UTF-8").getBytes("ISO-8859-1"));
//            ObjectInputStream v2 = new ObjectInputStream(((InputStream)v1));
//            Object v0_3 = v2.readObject();
//            v2.close();
//            v1.close();
//            if(v0_3 != null) {
//                goto label_12;
//            }
//
//            v0_2 = new ArrayList();
//        }
//        catch(Exception v0) {
//            v0.printStackTrace();
//            v0_2 = new ArrayList();
//        }
//
//    label_12:
//        return ((List)v0_2);
//    }
//
    public static List loadList(Context arg3) {
        ArrayList v0_2;
        try {
            String v0_1 = arg3.getApplicationContext().getSharedPreferences("List", 0).getString("List", "none");
            if(v0_1.equals("none")) {
                v0_2 = new ArrayList();
              return v0_2;
            }

            ByteArrayInputStream v1 = new ByteArrayInputStream(URLDecoder.decode(v0_1, "UTF-8").getBytes("ISO-8859-1"));
            ObjectInputStream v2 = new ObjectInputStream(((InputStream)v1));
            Object v0_3 = v2.readObject();
            v2.close();
            v1.close();
            if(v0_3 != null&& v0_3 instanceof  ArrayList) {
                v0_2 =(ArrayList)v0_3;
            }else {
                v0_2 = new ArrayList();
            }
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v0_2 = new ArrayList();
        }

        return v0_2;
    }

//    public static void pauseXG(String arg5, Activity arg6) {
//        Object v0;
//        int v4 = 6;
//        List v2 = XGUtil.loadList(((Context)arg6));
//        Iterator v3 = v2.iterator();
//        do {
//            if(!v3.hasNext()) {
//                goto label_20;
//            }
//
//            v0 = v3.next();
//        }
//        while(!((Map)v0).get(Integer.valueOf(v4)).equals(arg5));
//
//        try {
//            XGApplication.delFile().pause(((Map)v0).get(Integer.valueOf(6)).getBytes("GBK"));
//            ((Map)v0).put("running", "stopped");
//        }
//        catch(UnsupportedEncodingException v0_1) {
//            v0_1.printStackTrace();
//        }
//
//    label_20:
//        XGUtil.saveList(v2, ((Context)arg6));
//    }
//
//    public static void playXG(String arg1, Activity arg2, int arg3) {
//        XGUtil.playXG(arg1, arg2, arg3, false);
//    }
//
    public static void playXG(String arg8, Activity arg9, int arg10, boolean arg11) {
        try {
            Log.e("------------LS:--", "播放视屏的主要函数---------------- ");
//            XGUtil.stopTask(((Context)arg9));
//            Intent v0_1 = new Intent(Cache.broad_offLine);
//            v0_1.putExtra("type", 1);
//            arg9.sendBroadcast(v0_1);
            String v2 = URLDecoder.decode(arg8, "UTF-8");
//            String v1 = a.c;
            String v0_2 = "xghome://home";
            String[] v2_1 = v2.split("\\|");
            if(v2_1.length == 3) {
//                v1 = v2_1[1];
                v0_2 = v2_1[2];
            }

            v2 = v2_1[0].replace("xg://", "ftp://");
            String[] v3 = v2.split("/");
            String v3_1 = v3[v3.length - 1];
            int v4 = new P2PClass().start(v2.getBytes("GBK"));
//            KJLoger.debug("tid = " + v4);
//            KJLoger.debug("tid = " + v4);
//            XGApplication.h = v4;
            String v4_1 = "http://127.0.0.1:8083/" + Uri.parse(v2).getLastPathSegment();
            Intent v5 = new Intent(((Context)arg9), GiraffePlayerActivity.class);
            Bundle v6 = new Bundle();
            v6.putString("uri", v4_1);
            v6.putString("refer", v0_2);
//            v6.putString("ad", v1);
            v6.putString("newurl", v2);
            v6.putString("title", v3_1);
            v6.putInt("movieId", arg10);
            v6.putString("movieurl", arg8);
            Log.v("XGUtil", v4_1 + "..." + v0_2 + "..." );
            v5.putExtras(v6);
            v5.setFlags(4194304);
            arg9.startActivityForResult(v5, 0);
            if(!arg11) {
                return;
            }

            arg9.finish();
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }
    }

    public static String getStartURL(String html){
        String v2 = null;
        try {
            v2 = URLDecoder.decode(html, "UTF-8");
            String[] v2_1 = v2.split("\\|");
            v2 = v2_1[0].replace("xg://", "ftp://");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  v2;
    }
//
//    public static void saveCacheList(List arg5, Context arg6) {
//        try {
//            ByteArrayOutputStream v0_1 = new ByteArrayOutputStream();
//            ObjectOutputStream v1 = new ObjectOutputStream(((OutputStream)v0_1));
//            v1.writeObject(arg5);
//            String v2 = URLEncoder.encode(v0_1.toString("ISO-8859-1"), "UTF-8");
//            v1.close();
//            v0_1.close();
//            SharedPreferences$Editor v0_2 = arg6.getApplicationContext().getSharedPreferences("CacheList", 0).edit();
//            v0_2.putString("List", v2);
//            Log.delFile("L_S_log:", "saveCacheList:保存电影列表： " + v2);
//            v0_2.commit();
//        }
//        catch(Exception v0) {
//            v0.printStackTrace();
//        }
//    }
//
    public static void saveHistoryList(List arg4, Activity arg5) {
        try {
            ByteArrayOutputStream v0_1 = new ByteArrayOutputStream();
            ObjectOutputStream v1 = new ObjectOutputStream(((OutputStream)v0_1));
            v1.writeObject(arg4);
            String v2 = URLEncoder.encode(v0_1.toString("ISO-8859-1"), "UTF-8");
            v1.close();
            v0_1.close();
            SharedPreferences.Editor v0_2 = arg5.getApplicationContext().getSharedPreferences("HistoryList", 0).edit();
            v0_2.putString("HistoryList", v2);
            v0_2.commit();
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }
    }
//
    public static void saveList(List arg5, Context arg6) {
        try {
            ByteArrayOutputStream v0_1 = new ByteArrayOutputStream();
            ObjectOutputStream v1 = new ObjectOutputStream(((OutputStream)v0_1));
            v1.writeObject(arg5);
            String v2 = URLEncoder.encode(v0_1.toString("ISO-8859-1"), "UTF-8");
            v1.close();
            v0_1.close();
            SharedPreferences.Editor v0_2 = arg6.getApplicationContext().getSharedPreferences("List", 0).edit();
            v0_2.putString("List", v2);
//            Log.delFile("L_S_log:", "saveList:保存电影列表： " + v2);
            v0_2.commit();
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }
    }

//    public static void saveRecord(Activity arg14, String arg15) {
//        int v0_1;
//        long v8;
//        long v6;
//        long v12 = 0;
//        Log.delFile("L_S_log-----", "saveRecord: 盘点看的电影是否已经缓存到sdcard上了");
//        if(XGApplication.h != -1) {
//            List v5 = XGUtil.loadList(((Context)arg14));
//            int v2 = 0;
//            while(true) {
//                if(v2 < v5.size()) {
//                    Object v0 = v5.get(v2);
//                    Log.v("myApp", "newUrl==" + arg15);
//                    if(((Map)v0).get("url").equals(arg15)) {
//                        v6 = XGApplication.delFile().getDownSize(XGApplication.h);
//                        v8 = XGApplication.delFile().getFileSize(XGApplication.h);
//                        if(v8 == v12) {
//                            ((Map)v0).put("percent", "0");
//                        }
//
//                        ((Map)v0).put("info", FileUtil.getSize(v6) + "/" + FileUtil.getSize(v8));
//                        ((Map)v0).put("running", "started");
//                        ((Map)v0).put("tid", String.valueOf(XGApplication.h));
//                        ((Map)v0).put("speed_info", "--KB");
//                        ((Map)v0).put("movieId", "0");
//                        XGApplication.b = true;
//                        XGApplication.c = v2;
//                        XGApplication.d = arg15;
//                        XGApplication.getLocalFileSize = false;
//                        XGUtil.saveList(v5, ((Context)arg14));
//                        v0_1 = 1;
//                    }
//                    else {
//                        ++v2;
//                        continue;
//                    }
//                }
//                else {
//                    break;
//                }
//
//                goto label_67;
//            }
//
//            v0_1 = 0;
//        label_67:
//            if(v0_1 != 0) {
//                return;
//            }
//
//            HashMap v0_2 = new HashMap();
//            v6 = XGApplication.delFile().getDownSize(XGApplication.h);
//            v8 = XGApplication.delFile().getFileSize(XGApplication.h);
//            ((Map)v0_2).put("info", FileUtil.getSize(v6) + "/" + FileUtil.getSize(v8));
//            if(v8 == v12) {
//                ((Map)v0_2).put("percent", "0");
//                return;
//            }
//
//            Log.delFile("----------", "if check is 0：dsize:" + v6 + "-----fsize:" + v8);
//            ((Map)v0_2).put("percent", (((int)(v6 * 1000 / v8))) + "");
//            ((Map)v0_2).put("url", arg15);
//            ((Map)v0_2).put("title", Uri.parse(arg15).getLastPathSegment());
//            ((Map)v0_2).put("speed_info", "--KB");
//            ((Map)v0_2).put("running", "started");
//            ((Map)v0_2).put("tid", String.valueOf(XGApplication.h));
//            ((Map)v0_2).put("movieId", "0");
//            v5.add(v0_2);
//            XGApplication.b = true;
//            XGApplication.c = v5.size() - 1;
//            XGApplication.d = arg15;
//            XGApplication.getLocalFileSize = false;
//            XGUtil.saveList(v5, ((Context)arg14));
//        }
//    }
//
    public static void startDownload(P2PClass p2p,String arg6, Activity arg7) {
        Object v1 = null;
        Object v0 = null;
        List v2 = XGUtil.loadList(((Context)arg7));
        Iterator v3 = v2.iterator();
        do {
            if(!v3.hasNext()) {
                break;
            }

            v0 = v3.next();
            v1 = ((Map)v0).get("url");
        }
        while(!((String)v1).equals(arg6));

        try {
            int v1_1 = p2p.download(((String)v1).getBytes("GBK"));
            Log.v("XGUtil", "downLoad id--" + v1_1);
            ((Map)v0).put("tid", String.valueOf(v1_1));
            ((Map)v0).put("running", "started");
        }
        catch(UnsupportedEncodingException v0_1) {
            v0_1.printStackTrace();
        }

        XGUtil.saveList(v2, ((Context)arg7));
    }

//    public static void startXG(P2PClass p2p,String arg6, Activity arg7) {
//        Object v0 = null;
//        int v5 = 6;
//        List v2 = XGUtil.loadList(((Context)arg7));
//        Iterator v3 = v2.iterator();
//        do {
//            if(!v3.hasNext()) {
//                break;
//            }
//
//            v0 = v3.next();
////            Log.delFile("-------LS:---", "开始下载影片--");
//        }
//        while(!((Map)v0).get(Integer.valueOf(v5)).equals(arg6));
//
//        try {
//            int v1 = p2p.download(((Map)v0).get(Integer.valueOf(6)).getBytes("GBK"));
//            Log.v("XGUtil", "downLoad id--" + v1);
//            ((Map)v0).put("tid", String.valueOf(v1));
//            ((Map)v0).put("running", "started");
//        }
//        catch(UnsupportedEncodingException v0_1) {
//            v0_1.printStackTrace();
//        }
//
//        XGUtil.saveList(v2, ((Context)arg7));
//    }
//
//    public static void stopAll(Context arg13) {
//        List v4 = XGUtil.loadList(arg13);
//        Iterator v5 = v4.iterator();
//        while(v5.hasNext()) {
//            Object v0 = v5.next();
//            Log.v("XGUtil", "........");
//            try {
//                XGApplication.delFile().pause(((Map)v0).get("url").getBytes("GBK"));
//                int v1 = Integer.valueOf(((Map)v0).get("tid")).intValue();
//                long v6 = XGApplication.delFile().getDownSize(v1);
//                long v2 = XGApplication.delFile().getFileSize(v1);
//                if(v2 == 0) {
//                    v2 = 1;
//                }
//
//                ((Map)v0).put("running", "stopped");
//                ((Map)v0).put("speed_info", "--KB/s");
//                ((Map)v0).put("info", FileUtil.getSize(v6) + "/" + FileUtil.getSize(v2));
//                ((Map)v0).put("percent", (((int)(v6 * 1000 / v2))) + "");
//            }
//            catch(UnsupportedEncodingException v0_1) {
//                v0_1.printStackTrace();
//            }
//        }
//
//        XGUtil.saveList(v4, arg13);
//        XGApplication.b = false;
//        XGApplication.c = -1;
//        XGApplication.d = null;
//        XGApplication.getLocalFileSize = false;
//    }
//
//    public static void stopAllExcept(String arg6, Activity arg7) {
//        List v2 = XGUtil.loadList(((Context)arg7));
//        Iterator v3 = v2.iterator();
//        while(v3.hasNext()) {
//            Object v0 = v3.next();
//            if(arg6.equals(((Map)v0).get("url"))) {
//                continue;
//            }
//
//            try {
//                XGApplication.delFile().pause(((Map)v0).get(Integer.valueOf(6)).getBytes("GBK"));
//                ((Map)v0).put("running", "stopped");
//            }
//            catch(UnsupportedEncodingException v0_1) {
//                v0_1.printStackTrace();
//            }
//        }
//
//        XGUtil.saveList(v2, ((Context)arg7));
//    }
//
//    public static void stopTask(Context arg11) {
//        Log.v("XGUtil", "stopTask()--" + XGApplication.b);
//        if(XGApplication.b) {
//            try {
//                KJLoger.debug(XGApplication.d);
//                XGApplication.delFile().pause(XGApplication.d.getBytes("GBK"));
//            }
//            catch(UnsupportedEncodingException v0) {
//                v0.printStackTrace();
//                KJLoger.debug("暂停出现异常");
//            }
//
//            List v4 = XGUtil.loadList(arg11);
//            Object v0_1 = v4.get(XGApplication.c);
//            ((Map)v0_1).put("running", "stopped");
//            ((Map)v0_1).put("speed_info", "--KB/s");
//            int v1 = Integer.valueOf(((Map)v0_1).get("tid")).intValue();
//            KJLoger.debug("tid = " + v1);
//            long v6 = XGApplication.delFile().getDownSize(v1);
//            long v2 = XGApplication.delFile().getFileSize(v1);
//            KJLoger.debug("dsize = " + v1 + ",fsize = " + v2);
//            if(v2 == 0) {
//                v2 = 1;
//            }
//
//            ((Map)v0_1).put("info", FileUtil.getSize(v6) + "/" + FileUtil.getSize(v2));
//            ((Map)v0_1).put("percent", (((int)(v6 * 1000 / v2))) + "");
//            XGApplication.b = false;
//            XGApplication.d = null;
//            XGApplication.getLocalFileSize = false;
//            XGApplication.c = -1;
//            XGUtil.saveList(v4, arg11);
//        }
//    }
//
//    public static void updateBrowserHis(Activity arg5, String arg6) {
//        int v0_1;
//        List v3 = XGUtil.loadHistoryList(((Context)arg5));
//        Iterator v4 = v3.iterator();
//        int v2;
//        for(v2 = 0; v4.hasNext(); v2 = v0_1) {
//            Object v0 = v4.next();
//            if(((Map)v0).get("url").equals(arg6)) {
//                ((Map)v0).put("duration", "0");
//                ((Map)v0).put("movieId", "0");
//                v0_1 = 1;
//            }
//            else {
//                v0_1 = v2;
//            }
//        }
//
//        if(v2 == 0) {
//            HashMap v0_2 = new HashMap();
//            ((Map)v0_2).put("url", arg6);
//            ((Map)v0_2).put("duration", "0");
//            ((Map)v0_2).put("title", Uri.parse(arg6).getLastPathSegment());
//            ((Map)v0_2).put("movieId", "0");
//            v3.add(v0_2);
//        }
//
//        XGUtil.saveHistoryList(v3, arg5);
//        XGUtil.saveRecord(arg5, arg6);
//    }
//
//    public static List updateDownInfo(Activity arg9) {
//        long v4;
//        List v2 = XGUtil.loadList(((Context)arg9));
//        Iterator v3 = v2.iterator();
//        while(v3.hasNext()) {
//            Object v0 = v3.next();
//            try {
//                int v1 = XGApplication.delFile().add(((Map)v0).get(Integer.valueOf(6)).getBytes("GBK"));
//                if(v1 == -1) {
//                    ((Map)v0).put("tid", String.valueOf(v1));
//                    v4 = XGApplication.delFile().getLocalFileSize(((Map)v0).get(Integer.valueOf(6)).getBytes("GBK"));
//                    ((Map)v0).put("info", FileUtil.getSize(v4) + "/" + FileUtil.getSize(v4));
//                    ((Map)v0).put("speed_info", "下载完成");
//                    ((Map)v0).put("percent", "1000");
//                    ((Map)v0).put("running", "true");
//                    continue;
//                }
//
//                v4 = XGApplication.delFile().getDownSize(v1);
//                long v6 = XGApplication.delFile().getFileSize(v1);
//                ((Map)v0).put("tid", String.valueOf(v1));
//                ((Map)v0).put("speed_info", "-- KB/s");
//                ((Map)v0).put("running", "stopped");
//                ((Map)v0).put("info", FileUtil.getSize(v4) + "/" + FileUtil.getSize(v6));
//            }
//            catch(UnsupportedEncodingException v0_1) {
//                v0_1.printStackTrace();
//            }
//        }
//
//        return v2;
//    }
}

