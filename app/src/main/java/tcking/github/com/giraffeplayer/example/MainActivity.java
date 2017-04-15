package tcking.github.com.giraffeplayer.example;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.xigua.p2p.KJLoger;
import com.xigua.p2p.P2PClass;
import com.xigua.p2p.XGUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import tcking.github.com.giraffeplayer.GiraffePlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity {
    GiraffePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        p2PClass.start()
        player = new GiraffePlayer(this);
        player.onComplete(new Runnable() {
            @Override
            public void run() {
                //callback when video is finish
                Toast.makeText(getApplicationContext(), "video play completed",Toast.LENGTH_SHORT).show();
            }
        }).onInfo(new GiraffePlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                switch (what) {
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //do something when buffering start
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //do something when buffering end
                        break;
                    case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                        //download speed
                       break;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        //do something when video rendering

                        break;
                }
            }
        }).onError(new GiraffePlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                Toast.makeText(getApplicationContext(), "video play error",Toast.LENGTH_SHORT).show();
            }
        });
        String url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//        String url = "http://127.0.0.1:8083/%C8%CB%C3%F1%B5%C4%C3%FB%D2%E5HDTV25.mkv";
//        player.play(url);
//        player.setTitle(url);
//        player.start();

        p2p();

    }
    private String TAG ="P2P";
    private boolean isLoopr=false;
    private void p2p() {
        final P2PClass p2PClass = new P2PClass();
        String url ="ftp://a.gbl.114s.com:20320/6742/人民的名义HDTV27.mp4";
        String html="xg://a.gbl.114s.com:20320/6232/%E8%A1%80%E6%88%98%E9%92%A2%E9%94%AF%E5%B2%ADBD%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97.rmvb";
         html="xg://a.gbl.114s.com:20320/5663/%E4%BA%BA%E6%B0%91%E7%9A%84%E5%90%8D%E4%B9%89HDTV27.mp4";
        final String startURL = XGUtil.getStartURL(html);

        L(" startURL :"+startURL);
        try {
            final int startID = p2PClass.start(startURL.getBytes("GBK"));
            L("start : "+startID);

//            int gbk2 = p2PClass.pause(startURL.getBytes("GBK"));
//            L("pause : "+gbk2);
//
//            int gbk1 = p2PClass.delFile(startURL.getBytes("GBK"));
//            L("delect : "+gbk1);

            final int downloadID = p2PClass.download(startURL.getBytes("GBK"));
            L("download : "+downloadID);


            isLoopr=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isLoopr){
                        L("-------------------------");
                        int percent = p2PClass.getPercent();

                        L("Percent : "+percent);
                        L("Speed : "+p2PClass.getSpeed(-1));
                        long downSize = p2PClass.getDownSize(downloadID);
                        L("DownSize ："+downSize);

                        if (downSize>1024*1024*30){
                            play(startURL);
                        }

                        try {
                            L("LocalFileSize : "+p2PClass.getLocalFileSize(startURL.getBytes("GBK")));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        long fileSize = p2PClass.getFileSize(downloadID);
                        L("FileSize　：　"+fileSize);
                        if (downSize>0&&fileSize==downSize){
                            L("----下载完成----");
                            try {
                                p2PClass.pause(startURL.getBytes("GBK"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        SystemClock.sleep(1000);
                    }
                    try {
                        p2PClass.pause(startURL.getBytes("GBK"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("p2p",e.getMessage());
        }
    }
    private void L(String msg){
        Log.e(TAG,msg);
    }
    boolean isPlay=false;
    private void play(final String startURL){
        if(isPlay)
            return;
        isPlay=true;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                L("----------play---------------");
                player.setTitle(startURL);
                try {

                    player.setDefaultRetryTime(1000);
//                    player.setFullScreenOnly(true);
                    player.setScaleType("fitParent");
                    player.setShowNavIcon(true);
                    String v0_1 = "http://127.0.0.1:8083/" +URLEncoder.encode(Uri.parse(startURL).getLastPathSegment(), "GBK");
                    L(" play path : "+v0_1);
                    player.play(v0_1);
                    player.start();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                L("----------play-------------");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        isLoopr=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (player != null) {
            player.onDestroy();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
