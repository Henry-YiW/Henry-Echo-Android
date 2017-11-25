package com.mywork.henry.henry_echo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    Button button1;
    Button button2;
    Button tempbutton;
    Handler handler;
    FloatingActionButton fab;
    public int originalwidth ;
    public int originalheight;
    public float Ylocation;
    public final float DeltaY=5;
    AnimatorSet animatorset1;
    AnimatorSet animatorset2;
    public float fab_margin ;
    public float button2endX;
    final int targetHeight = 150;//135
    final int targetWidth = 320;
    float originalX;
    float interval=20;
    //static volatile boolean stopall=false;
    final long duration2 =500;
    final long duration1 = duration2 + 800;
    final long duration3 =50;final int times=11;
    final long durationfragment=500;
    volatile boolean interanimating =false;
    public static final float viewDelta = (float) -0.1;
    public final int isFAB=1;
    public final int isMain=2;
    public Refreshprogressbar progressbar;
    public float originalPY;float refreshEndY;
    public final int Maxprogress=120;
    volatile boolean isbuttonYseted;
    Context Maincontext;
    MainActivity Mainactivity;
    volatile boolean refreshinganirunning=false;
    //volatile boolean refreshing=false;
    //volatile boolean added=false;
    public static volatile boolean StopGettingData=false;
    onDemandRefresh refreshthread=new onDemandRefresh(null);
    public static volatile File fileDir;
    public volatile boolean StopDaemon=false;
    String URL="http://168.150.116.167:8080/Smart_Home/Inquire";
    String URL2="http://168.150.116.167:8080/Smart_Home/InquireData";
    String URL3="http://168.150.116.167:8080/Smart_Home/Control";
    String URL4="http://168.150.116.167:8080/Smart_Home/RegistrationPlusStateupdate";
    updatedkeeper UpdateKeeper = null;
    public static volatile boolean onDemandRefreshing=false;
    final ArrayList<Integer> ApparatusBeingControlled =new ArrayList<>(9);
    volatile boolean refreshDescendDone=true;
    volatile boolean refreshReturnRunning=false;
    final int sleeptime=10000;final int autorestoretime=20000;
    volatile boolean StopControlling=false;
    final ArrayList<Integer> AllAddedToggles = new ArrayList<>(9);
    public static volatile boolean whetherTooMany=false;

    public static final int ColorAlerted = 0xffff0000;
    public static final int ColorActivated=0xfffbab00;
    public static final int ColorInactivated=0xff756b64;
    volatile boolean StopRefreshView=false;
    final ArrayList<ImageView> Prompts= new ArrayList<>(3);
    final int prompt_changed_ID=5001;
    final int prompt_new_ID=5002;
    final int prompt_removed_ID=5003;

    ValueAnimator SwipingButtonsAnimator;
    volatile boolean ButtonsReturned = true;
    volatile boolean AnimatorStarted=true;

    ValueAnimator MainAddButtonAnimator;
    volatile boolean MainAddButtonReturned = true;

    Button addbutton;
    AddButtonListener addbuttonlistener;
    float MainAddButtonOriginalX;
    float MainAddButtonOriginalYDelta;
    int MainAddButtonOriginalWidth;
    int MainAddButtonOriginalHeight;
    DialogFragment AddDialog;

    Toolbar toolbar;
    Menu ActivityMenu;ActionMenuView ActivityMenuView;
    final ArrayList<View> toolbarViews = new ArrayList<>(8);
    public static final TypedValue colorPrimary=new TypedValue();


    TextView ApplicationTitle;DialogFragment ClearDialog;
    TextView AlertPrompt;    volatile int AlertedLogNumber;
    MenuItem DisconnectionPrompt;View DisconnectionPromptView;volatile boolean Disconnected=false;
    //volatile int trytimes=0;

    volatile boolean toStartOtherActivity=false;

    AnimatorSet progressbarReturnAnimator;
    AnimatorSet progressbarDescendAnimator;
    refreshingani refreshinganiThread;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().getDecorView();获得Activity的view。
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        AddDialog=new Registration();
        //ClearDialog=new CurrentAlertedLogClean();
        handler = new Handler();
        //用View就可以了其实不用转型
        CoordinatorLayout main = (CoordinatorLayout)findViewById(R.id.activity_main);
        addbuttonlistener=new AddButtonListener();
        fm = getFragmentManager();
        fab =  findViewById(R.id.AddButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AddButton Click","Clicked");
                Fragment temp = fm.findFragmentByTag("AddDialog");
                if (temp !=null){
                    fm.beginTransaction().remove(temp).commit();
                }
                Bundle arguments = new Bundle();
                arguments.putString("RegistrationURL",URL4);
                arguments.putString("user","henry");
                arguments.putString("pass","yiweigang");
                if (!AddDialog.isAdded()) {
                    AddDialog.setArguments(arguments);
                    AddDialog.show(getFragmentManager(), "AddDialog");
                }
            }
        });
        progressbar = (Refreshprogressbar) findViewById(R.id.progressBar);
        Maincontext=this;
        Mainactivity=this;
        Disconnected=getIntent().getBooleanExtra("Disconnected",false);
        //Log.d("TemperatureBinary",Integer.toBinaryString(Data.Type_Temperature));
        //Log.d("HumidityBinary",Integer.toBinaryString(Data.Type_Humidity));
        //Log.d("ElectricityBinary",Integer.toBinaryString(Data.Type_Electricity));
        //Log.d("ApparatusBinary",Integer.toBinaryString(Data.Type_apparatuscfgset));
        //progressbar.setlistener
        //button1.setOnTouchListener(new buttonontouch());
        //button2.setOnTouchListener(new buttonontouch());
        setdefaultfragment();

    }


    void initiateToolbar (final Toolbar toolbar){
        TextView Title = null;
        for (int i=0;i<toolbar.getChildCount();i++){
            //Log.d("ToolbarClass",toolbar.getChildAt(i).getClass().toString());
            final View view = toolbar.getChildAt(i);
            if (view instanceof TextView){
                //Log.d("ToolbarText",((TextView) toolbar.getChildAt(i)).getText().toString());
                //Log.d("ToolbarTextScale",String.valueOf(toolbar.getChildAt(i).getWidth())+"w:h"+String.valueOf(toolbar.getChildAt(i).getHeight()));
                Title=(TextView) view;
            }else if (view instanceof ImageButton) {
                //toolbar.getChildAt(i).setVisibility(View.INVISIBLE);
                //Log.d("Toolbar"+toolbar.getChildAt(i).getClass().toString()+"Scale",String.valueOf(toolbar.getChildAt(i).getWidth())+"w:h"+String.valueOf(toolbar.getChildAt(i).getHeight()));
                if (Title==null){
                    i=-1;
                }else {
                    final TextView temp=Title;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (view.getHeight()!=0){
                                view.setY(temp.getY()+(temp.getHeight()-view.getHeight())/2);
                                //Log.d("ToolbarActionMenuView",String.valueOf(view.getWidth())+"w:h"+String.valueOf(view.getHeight()));
                            }else{
                                handler.postDelayed(this,5);
                            }
                        }
                    },10);
                }
            }else if (view instanceof ActionMenuView){
                ActivityMenuView=(ActionMenuView) view;
                if (Title==null){
                    i=-1;
                }else {

                    final TextView temp=Title;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (view.getHeight()!=0){
                                view.setY(temp.getY()+(temp.getHeight()-view.getHeight())/2);
                                //Log.d("Toolbar",((ActionMenuItemView)((ActionMenuView)view).getChildAt(1)).getText().toString());
                                //Log.d("ToolbarActionMenuView",String.valueOf(view.getWidth())+"w:h"+String.valueOf(view.getHeight()));

                                //Log.d("Toolbar",String.valueOf(((ActionMenuView)view).getChildCount()));

                            }else{
                                handler.postDelayed(this,5);
                            }
                        }
                    },10);
                }
            }
        }

        resetMenuItemStatus();

        //for (int i=0;i<menu.size();i++){
        //
        //    MenuItem item = menu.findItem(R.id.Menu_Settings);
        //    if (item!=null) {
        //        //用这个得到view需要程序以及activity的theme属于Theme.AppCompat,然后在menu.xml里设置item 的app:actionViewClass 才行。
        //        View view = MenuItemCompat.getActionView(item);
        //        //Log.d("ToolbarMenuClass",(view.getClass().toString()));
        //        //view.setBackground(getDrawable(R.drawable.transparent));
        //    }
        //
        //
        //}
    }



    void resetMenuItemStatus(){
        synchronized (toolbarViews){
            for (View temp:toolbarViews){
                ActionMenuItemView real = (ActionMenuItemView)temp;
                real.clearAnimation();real.setActivated(false);real.setPressed(false);real.setOnTouchListener(null);
            }
        }
    }





    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","Paused");
        if (toStartOtherActivity) {
            ApplicationTitle.setVisibility(View.INVISIBLE);
            if (AlertPrompt != null)
                AlertPrompt.setVisibility(View.INVISIBLE);
            if (DisconnectionPrompt != null) {
                resetMenuItemStatus();
                DisconnectionPrompt.setVisible(false);

                
            }
        }
    }

    @Override
    protected void onResume() {
        //if (getIntent().getStringExtra("Intention")!=null&&getIntent().getStringExtra("Intention").equals("StartMainActivity")) {
        //    overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
        //}else {
        //    overridePendingTransition(0, R.anim.activity_exit);
        //}
        super.onResume();
        Log.d("MainActivity","Resumed");

        int trytimes=0;
        while (trytimes<200){
            if (UpdateKeeper==null||!UpdateKeeper.isAlive()){
                UpdateKeeper=new updatedkeeper(sleeptime,autorestoretime);
                UpdateKeeper.setDaemon(true);
                UpdateKeeper.setName("UpdateKeeper");
                StopDaemon=false;
                UpdateKeeper.start();
                break;
            }
            trytimes++;
        }
        //long duration = AnimationUtils.loadAnimation(this,R.anim.rotation_endless).getDuration();
        //
        //ApplicationTitle.setVisibility(View.INVISIBLE);//不过好像MainActivity不需要这样。但前提是，在onPause或onStop里设置它为INVISIBLE。
        ////if (CriticalPrompts!=null)
        ////    CriticalPrompts.setVisibility(View.INVISIBLE);
        //handler.postDelayed(new Runnable() {
        //    @Override
        //    public void run() {
        //        ApplicationTitle.setVisibility(View.VISIBLE);
        //        if (CriticalPrompts!=null)
        //            CriticalPrompts.setVisibility(View.VISIBLE);
        //    }
        //},duration);
        if (toStartOtherActivity) {
            ApplicationTitle.setVisibility(View.INVISIBLE);
            if (AlertPrompt != null)
                AlertPrompt.setVisibility(View.INVISIBLE);
            if (DisconnectionPrompt != null) {
                resetMenuItemStatus();
                DisconnectionPrompt.setVisible(false);

            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (AlertPrompt != null && DisconnectionPrompt != null) {
                        ApplicationTitle.setVisibility(View.VISIBLE);
                        AlertPrompt.setVisibility(AlertedLogNumber > 0 ? View.VISIBLE : View.INVISIBLE);
                        AlertPrompt.setText(String.valueOf(AlertedLogNumber));
                        resetMenuItemStatus();
                        DisconnectionPrompt.setVisible(Disconnected);
                    } else {
                        handler.postDelayed(this, 50);
                    }
                }
            }, 350);
        }

        toStartOtherActivity=false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","Restarted");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","Stopped");
        StopDaemon = true;
        if (UpdateKeeper != null) {
            UpdateKeeper.interrupt();
        }
        StopGettingData = true;
        if (refreshthread != null) {
            refreshthread.interrupt();
        }
        StopRefreshView = true;
    }

    class AddButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Fragment temp = fm.findFragmentByTag("AddDialog");
            if (temp !=null){
                fm.beginTransaction().remove(temp).commit();
            }
            Bundle arguments = new Bundle();
            arguments.putString("RegistrationURL",URL4);
            arguments.putString("user","henry");
            arguments.putString("pass","yiweigang");
            if (!AddDialog.isAdded()) {
                AddDialog.setArguments(arguments);
                AddDialog.show(getFragmentManager(), "AddDialog");
            }
        }
    }


    private class onDemandRefresh extends Thread {
        Fragment fragment;
        public void run() {
            onDemandRefreshing=true;

            Log.d("onDemandRefreshing","Started");
            StopGettingData=false;
            refreshinganirunning=true;
            refreshReturnRunning=false;
            if (UpdateKeeper!=null){
            UpdateKeeper.interrupt();}
            while (!refreshDescendDone&&progressbar.getProgress()!=progressbar.getMax()){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            if (refreshinganiThread!=null){
                refreshinganiThread.interrupt();
            }
            while (true){
                if (refreshinganiThread==null||!refreshinganiThread.isAlive()){
                    refreshinganiThread=new refreshingani(800);//变量需要大于Maxprogress（现在是120）
                    refreshinganiThread.start();
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

            int status=refresh(URL,URL2);int tempstatus=status;

            switch (status){
                case -1:
                    Log.d("HasbeenForcedlyClosed","beenclosed");
                    StopRefreshView=true;
                    break;
                case 15:
                    Snackbar prompt = Snackbar.make(fragment.getView(), R.string.DataFetch_SUCCESSFUL, Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    prompt.getView().setBackgroundColor(colorPrimary.data);
                    prompt.show();
                    if (Thread.currentThread().isInterrupted()){break;}
                    break;
                default:String msg="";FileInputStream input1=null;FileInputStream input2=null;
                    FileInputStream input2C=null;FileInputStream input3C=null;
                    FileInputStream input3=null;FileInputStream input4=null;
                    status=0;
                    if ((tempstatus&1)==0){
                        try {
                            input4=openFileInput("Electricityset");
                        } catch (FileNotFoundException e) {
                            Log.d("NoFile","Electricityset");
                            status|=Data.Type_Electricity;
                        }
                        msg+=getString(R.string.electricity)+"-";
                    }
                    tempstatus=tempstatus>>1;
                    if ((tempstatus&1)==0){
                        try {
                            input3=openFileInput("Humidityset");
                        } catch (FileNotFoundException e) {
                            Log.d("NoFile","Humidityset");
                            status|=Data.Type_Humidity;
                        }
                        try {
                            input3C=openFileInput("HumidityColorSet");
                        } catch (FileNotFoundException e) {
                            Log.d("NoFile","HumidityColorSet");
                        }
                        msg+=getString(R.string.humidity)+"-";
                    }
                    tempstatus=tempstatus>>1;
                    if ((tempstatus&1)==0){
                        try {
                            input2=openFileInput("Temperatureset");
                        } catch (FileNotFoundException e) {
                            Log.d("NoFile","Temperatureset");
                            status|=Data.Type_Temperature;
                        }
                        try {
                            input2C=openFileInput("TemperatureColorSet");
                        } catch (FileNotFoundException e) {
                            Log.d("NoFile","TemperatureColorSet");
                        }
                        msg+=getString(R.string.temperature)+"-";
                    }
                    tempstatus=tempstatus>>1;
                    if ((tempstatus&1)==0){
                        try {
                            input1=openFileInput("Apparatusset");
                        } catch (FileNotFoundException e) {
                            Log.d("NoFile","Apparatusset");
                            status|=Data.Type_apparatuscfgset;
                        }
                        msg+=getString(R.string.apparatus)+"-";
                    }else {
                    }
                    Snackbar prompt2 = Snackbar.make(fragment.getView(), msg+getString(R.string.DataFetch_Failed_Try_Using_Backup_Data), Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    prompt2.getView().setBackgroundColor(colorPrimary.data);
                    prompt2.show();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    try {
                        ObjectInputStream objinput;
                        if (Thread.currentThread().isInterrupted()){break;}
                        if (input1!=null){
                            objinput = new ObjectInputStream(input1);
                            Data.setData(Data.Type_apparatuscfgset,(ArrayList) objinput.readObject());
                            objinput.close();input1.close();}
                        if (Thread.currentThread().isInterrupted()){break;}
                        if (input2!=null){
                            objinput = new ObjectInputStream(input2);
                            Data.setData(Data.Type_Temperature,(ArrayList) objinput.readObject());
                            objinput.close();input2.close();}
                        if (Thread.currentThread().isInterrupted()){break;}
                        if (input2C!=null){
                            objinput = new ObjectInputStream(input2C);
                            Data.setColorset(Data.Type_Temperature,(ArrayList<Integer>)objinput.readObject(),null);
                            objinput.close();input2C.close();}
                        if (Thread.currentThread().isInterrupted()){break;}
                        if (input3!=null){
                            objinput = new ObjectInputStream(input3);
                            Data.setData(Data.Type_Humidity,(ArrayList) objinput.readObject());
                            objinput.close();input3.close();}
                        if (Thread.currentThread().isInterrupted()){break;}
                        if (input3C!=null){
                            objinput = new ObjectInputStream(input3C);
                            Data.setColorset(Data.Type_Humidity,(ArrayList<Integer>)objinput.readObject(),null);
                            objinput.close();input3C.close();}
                        if (Thread.currentThread().isInterrupted()){break;}
                        if (input4!=null){
                            objinput = new ObjectInputStream(input4);
                            Data.setData(Data.Type_Electricity,(ArrayList) objinput.readObject());
                            objinput.close();input4.close();}
                        if (Thread.currentThread().isInterrupted()){break;}
                    } catch (OptionalDataException e) {e.printStackTrace();} catch (StreamCorruptedException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e) {e.printStackTrace();}
                    msg="";
                    if ((status&1)==1){
                        msg+=getString(R.string.electricity)+"-";
                    }
                    status=status>>1;
                    if ((status&1)==1){
                        msg+=getString(R.string.humidity)+"-";
                    }
                    status=status>>1;
                    if ((status&1)==1){
                        msg+=getString(R.string.temperature)+"-";
                    }
                    status=status>>1;
                    if ((status&1)==1){
                        msg+=getString(R.string.apparatus)+"-";
                    }
                    if (!msg.isEmpty()) {
                        Snackbar prompt3 = Snackbar.make(fragment.getView(), getString(R.string.No) + msg + getString(R.string.Backup_Data), Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                        prompt3.getView().setBackgroundColor(colorPrimary.data);
                        prompt3.show();
                    }
                    break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressbarReturn(progressbar, 0);
                }
            });


            onDemandRefreshing=false;



        }

        onDemandRefresh(Fragment fragment){
            this.fragment=fragment;
        }
    }

    int refresh (String URL, String URL2){
        Data.Status_apparatus=Data.DefaultRefresh;Data.Status_Electricity=Data.DefaultRefresh;
        Data.Status_Humidity=Data.DefaultRefresh;Data.Status_Temperature=Data.DefaultRefresh;
        int howmany=6;Map<String,String> parameters=new HashMap<>(3);
        Data.whattodonext todowhat=new Data.whattodonext() {
            @Override
            public void todo() {
                //refreshViews.run();

            }
        };
        Data.OnsuccessProcess onsuccessProcess=new Data.OnsuccessProcess(howmany,todowhat);
        if (Thread.currentThread().isInterrupted()){return -1;}
        parameters.put("user","henry");parameters.put("pass","yiweigang");
        OKHttpTool.asyncCustomPostFormforJsonObject(URL, parameters, onsuccessProcess, new Data.OnFailed(Data.Type_apparatuscfgset,4,todowhat));
        parameters.put("Type","Temperature");
        OKHttpTool.asyncCustomPostFormforJsonObject(URL2, parameters, onsuccessProcess, new Data.OnFailed(Data.Type_Temperature,4,todowhat));
        parameters.put("Type","Humidity");
        OKHttpTool.asyncCustomPostFormforJsonObject(URL2, parameters, onsuccessProcess, new Data.OnFailed(Data.Type_Humidity,4,todowhat));
        parameters.put("Type","appliance");
        OKHttpTool.asyncCustomPostFormforJsonObject(URL2, parameters, onsuccessProcess, new Data.OnFailed(Data.Type_Electricity,4,todowhat));
        int status=0;String msg="";int tempstatus;int trytimes=0;
        int Status_apparatus;int Status_Temperature;int Status_Humidity;int Status_Electricity;
        while (true){
            if (StopGettingData){
                return -1;}
            if (Thread.currentThread().isInterrupted()){StopGettingData=true;return -1;}
            if (trytimes>3000){
                StopGettingData=true;
                if (Thread.currentThread().isInterrupted()){return -1;}
                return status;
            }
            Status_apparatus=Data.Status_apparatus;
            Status_Temperature=Data.Status_Temperature;
            Status_Humidity=Data.Status_Humidity;
            Status_Electricity=Data.Status_Electricity;
            if (Status_apparatus==Data.RefreshDone){
                status|=Data.Type_apparatuscfgset;
            }
            if (Status_Temperature==Data.RefreshDone){
                status|=Data.Type_Temperature;
            }
            if (Status_Humidity==Data.RefreshDone){
                status|=Data.Type_Humidity;
            }
            if (Status_Electricity==Data.RefreshDone){
                status|=Data.Type_Electricity;
            }
            tempstatus=status;
            if (StopGettingData){return -1;}
            msg="";
            if ((tempstatus&1)==1){
                msg+="ElectricityBeenSet ";
            }
            tempstatus=tempstatus>>1;
            if ((tempstatus&1)==1){
                msg+="HumidityBeenSet ";
            }
            tempstatus=tempstatus>>1;
            if ((tempstatus&1)==1){
                msg+="TemperatureBeenSet ";
            }
            tempstatus=tempstatus>>1;
            if ((tempstatus&1)==1){
                msg+="ApparatusBeenSet ";
            }
            Log.d("DataStatus",msg);
            if (StopGettingData){return -1;}
            if (Thread.currentThread().isInterrupted()){StopGettingData=true;return -1;}
            if (Status_apparatus!=Data.DefaultRefresh&&Status_Humidity!=Data.DefaultRefresh
                    &&Status_Temperature!=Data.DefaultRefresh&&Status_Electricity!=Data.DefaultRefresh){
                if (Thread.currentThread().isInterrupted()){StopGettingData=true;return -1;}
                return status;
            }
            //if (Data.Status_apparatus==Data.RefreshFailed&&Data.Status_Humidity==Data.RefreshFailed
            //        &&Data.Status_Temperature==Data.RefreshFailed&&Data.Status_Electricity==Data.RefreshFailed){
            //    StopGettingData=true;
            //    return 0;
            //}
            else if (status==15){
                StopGettingData=true;
                if (Thread.currentThread().isInterrupted()){StopGettingData=true;return -1;}
                return 15;
            }
            if (StopGettingData){return -1;}
            if (Thread.currentThread().isInterrupted()){StopGettingData=true;return -1;}
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                StopGettingData=true;
                return -1;
            }
            trytimes++;
        }

    }

    class refreshingani extends Thread {
        int TPR;
        public void run() {
            while (true){
                if (Thread.currentThread().isInterrupted()){return;}
                try {
                    Thread.sleep(TPR/progressbar.getMax());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                if (Thread.currentThread().isInterrupted()){return;}
                int progress= progressbar.getProgress();
                if(progressbar.getProgress()<progressbar.getMax()&&progressbar.getProgress()>0) {
                    progressbar.setProgress(progress + 1);
                }
                else {progressbar.setProgress(1);}
                if (Thread.currentThread().isInterrupted()){return;}
                if(progressbar.getProgress()==0){
                    return;
                }
            }
        }
        refreshingani(int TPR){
            this.TPR=TPR;
        }
    }

    class progresswrapper {
        int getAnimating (){
            return 0;
        }//问题可能来自于这里。没有return一个真是的now值。
        void setAnimating(int now){
            if (now>95){
                //Log.d("has Restore","restoreProgressbar");
                //refreshinganirunning=false;
                //refreshReturnRunning=false;
            }else if (now==-1){
                refreshDescendDone=true;
            }
        }
    }

    void progressbarReturn(ProgressBar v, int which) {
        if (refreshinganiThread!=null){
            refreshinganiThread.interrupt();
        }
        if (progressbarReturnAnimator!=null){
            progressbarReturnAnimator.cancel();
        }
        if (which==0) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "Y", v.getY(), originalPY);
            ObjectAnimator animator2 = ObjectAnimator.ofInt(new progresswrapper(), "Animating", 0, 100);
            progressbarReturnAnimator = new AnimatorSet();
            progressbarReturnAnimator.playTogether(animator1, animator2);
            progressbarReturnAnimator.setDuration(300);
            progressbarReturnAnimator.setInterpolator(new AccelerateInterpolator());
            progressbarReturnAnimator.start();
            refreshReturnRunning=true;

        }
        else if (which==2){
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "Y", v.getY(), originalPY);
            ObjectAnimator animator2 = ObjectAnimator.ofInt(v, "Progress", v.getProgress(), 0);
            progressbarReturnAnimator = new AnimatorSet();
            progressbarReturnAnimator.playTogether(animator1, animator2);
            progressbarReturnAnimator.setDuration(300);
            progressbarReturnAnimator.setInterpolator(new AccelerateInterpolator());
            progressbarReturnAnimator.start();
        }
    }

    void progressbarDescend(ProgressBar v) {
        if (progressbarDescendAnimator!=null){
            progressbarDescendAnimator.cancel();
        }
        refreshDescendDone=false;
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "Y", v.getY(), refreshEndY);
        ObjectAnimator animator2 = ObjectAnimator.ofInt(v, "Progress", v.getProgress(), v.getMax());
        ObjectAnimator animator3 = ObjectAnimator.ofInt(new progresswrapper(), "Animating", 90, -1);
        progressbarDescendAnimator = new AnimatorSet();
        progressbarDescendAnimator.playTogether(animator1, animator2,animator3);
        progressbarDescendAnimator.setDuration(300);
        progressbarDescendAnimator.setInterpolator(new AccelerateInterpolator());
        progressbarDescendAnimator.start();

    }

    void setdefaultfragment (){
        getTheme().resolveAttribute(R.attr.colorPrimary,colorPrimary,true);

   }

    float getFitTextSize(float defaultSize,float width,String text){
        Paint temppaint = new Paint();float textsize=defaultSize;
        Log.d("TextContainerWidth", String.valueOf(width));
        while (true) {
            if(textsize>1) {
                temppaint.setTextSize(textsize);
                if (temppaint.measureText(text)<=width){
                    Log.d("TextWidth", String.valueOf(temppaint.measureText(text)));
                    Log.d("TextSize", String.valueOf(textsize));
                    return textsize;
                }
                textsize-=0.1f;
            }
            else {

                return 1f+0.1f;
            }
        }

    }







    class updatedkeeper extends Thread {
        int sleeptime;int defaultsleeptime;int autorestoretime;int anomalyduration=0;
        Map<String,String> parameters=new HashMap<>(2);

        Data.OnsuccessProcess onsuccessProcess= new Data.OnsuccessProcess(0,null);
        boolean firstrapidly=false;
        @Override
        public void run() {
            parameters.put("user","henry");parameters.put("pass","yiweigang");
            parameters.put("Type","appliance");
            Log.d("Updatekeeper","Started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                if (StopDaemon){return;}
            }
            while (!StopDaemon){
                Log.d("Updatekeeper","Running");
                setDisconnected(Disconnected);
                while (onDemandRefreshing){
                    Log.d("Updatekeeper","Sleep");
                    if (StopDaemon){return;}
                    while(refreshReturnRunning){
                        refreshinganirunning = true;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            if (StopDaemon){
                                refreshinganirunning=false;
                                refreshReturnRunning=false;
                                return;}
                        }
                        //Log.d("DistanceProgressbar",String.valueOf(Math.abs(progressbar.getY()-originalPY)));
                        if (Math.abs(progressbar.getY()-originalPY)<20) {
                            Log.d("Abnormally Restore", "restoreProgressbar");
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressbar.setY(originalPY);
                                }
                            });
                            refreshinganirunning = false;
                            refreshReturnRunning = false;
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        if (StopDaemon){return;}
                    }
                }
                while(refreshReturnRunning){
                    refreshinganirunning = true;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        if (StopDaemon){
                            refreshinganirunning=false;
                            refreshReturnRunning=false;
                            return;}
                    }
                    //Log.d("DistanceProgressbar",String.valueOf(Math.abs(progressbar.getY()-originalPY)));
                    //Log.d("Dis",String.valueOf(refreshinganirunning));
                    if (Math.abs(progressbar.getY()-originalPY)<20) {
                        Log.d("Abnormally Restore", "restoreProgressbar");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressbar.setY(originalPY);
                            }
                        });
                        refreshinganirunning = false;
                        refreshReturnRunning = false;
                        break;
                    }
                }
                if (firstrapidly){
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        if (StopDaemon){return;}
                    }
                }
                if (!onDemandRefreshing){
                    OKHttpTool.asyncCustomPostFormforJsonObject(URL2, parameters, onsuccessProcess, new OKHttpTool.processFailure() {
                        @Override
                        public void onFailure() {
                            setDisconnected(true);
                            Log.d("UPDateSession", "Failed");
                        }
                    });
                }
                if (firstrapidly){
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        if (StopDaemon){return;}
                    }
                    firstrapidly=false;
                }
                if (sleeptime!=defaultsleeptime){
                    anomalyduration+=sleeptime;
                }
                try {
                    Thread.sleep(sleeptime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (anomalyduration>=autorestoretime){
                    restoreSleeptime();
                }
            }
        }
        updatedkeeper(int sleeptime,int autorestoretime){
            this.sleeptime=sleeptime;
            this.defaultsleeptime=sleeptime;
            this.autorestoretime=autorestoretime;
        }

        public void setSleeptime(int sleeptime) {
            this.sleeptime = sleeptime;
            this.anomalyduration=0;
            this.firstrapidly=true;
        }

        public void restoreSleeptime(){
            this.sleeptime=this.defaultsleeptime;
            this.anomalyduration=0;
            this.firstrapidly=false;
        }

        public void setAutorestoretime(int autorestoretime) {
            this.autorestoretime = autorestoretime;
        }

        public void setDefaultsleeptime(int defaultsleeptime) {
            this.defaultsleeptime = defaultsleeptime;
        }
    }

    void setDisconnected (final boolean disconnected){
        this.Disconnected=disconnected;
        if (DisconnectionPrompt !=null) {
            if (DisconnectionPrompt.isVisible()!=(disconnected))
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resetMenuItemStatus();
                        DisconnectionPrompt.setVisible(disconnected);

                    }
                });
        }
    }





    public boolean onKeyDown(int keyCode,KeyEvent event) {
        //if(keyCode==KeyEvent.KEYCODE_BACK){
        //    return true;}//不执行父类点击事件
        //return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
        //不执行父类点击事件
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

    //@Override //拦截这个可以实现上面一样的屏蔽back键作用的功能，只是这个时专门针对back按下的情况。
    //public void onBackPressed() {
    //    //super.onBackPressed();
    //}

    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActivityMenu = menu;
        DisconnectionPrompt=menu.findItem(R.id.Menu_Disconnection);
        initiateToolbar(toolbar);
        return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       // Handle action bar item clicks here. The action bar will
       // automatically handle clicks on the Home/Up button, so long
       // as you specify a parent activity in AndroidManifest.xml.
       int id = item.getItemId();

       //noinspection SimplifiableIfStatement
       //if (id == R.id.Menu_Settings) {
       //    //intent.putExtra("Intention","StartSettings");
       //    //startActivity(intent);
       //    //ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(this,R.anim.activity_enter,R.anim.activity_exit);
       //    //ActivityCompat.startActivityForResult(this,intent,1,options.toBundle());
       //    //getWindow().setEnterTransition(new Slide().setDuration(2000));
       //    //getWindow().setExitTransition(new Slide().setDuration(2000));
       //    //ActivityOptions SceneTransition = ActivityOptions.makeSceneTransitionAnimation(this,findViewById(R.id.Title_mainactivity),"Title");
       //    //ActivityOptions CustomTransition = ActivityOptions.makeCustomAnimation(this,R.anim.activity_enter,R.anim.activity_exit);
       //    //Bundle options = new Bundle();
       //    //options.putAll(SceneTransition.toBundle());options.putAll(CustomTransition.toBundle());
       //    //startActivity(new Intent(this, Settings.class));
       //    //
       //    //overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
       //    return true;
       //}else if (id == R.id.Menu_Refresh){
       //
       //    return true;
       //}

       return super.onOptionsItemSelected(item);
   }

   /**
    * A native method that is implemented by the 'native-lib' native library,
    * which is packaged with this application.
    */
   //public native String stringFromJNI();

   // Used to load the 'native-lib' library on application startup.
   //static {
   //    System.loadLibrary("native-lib");
   //}
}