package com.example.zh.babyplan;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.babyplan.Util.CircleImageView;
import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.widget.ClickCommunity;
import com.loopj.android.http.Base64;
import com.soundcloud.android.crop.Crop;
import com.zjh.store.StoreActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NavigationView navigationView;
    public static ImageView iv;
    private Uri outputUri;
    private Fragment currentFragment, communityFragment, addressFragment, babyFragment;
    private FragmentManager fragmentManager;
    public static TextView mail, username;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_community:
                    replaceFragment(communityFragment);
                    return true;
                case R.id.navigation_dingwei:
                    replaceFragment(addressFragment);
                    return true;
                case R.id.navigation_mycenter:
                    replaceFragment(babyFragment);
                    return true;
                case R.id.navigation_shop:
                    Intent intent=new Intent(MainActivity.this, StoreActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fragment.isAdded()){
            transaction.hide(currentFragment);
            transaction.show(fragment);
            currentFragment = fragment;
            transaction.commit();
        }else{
            transaction.hide(currentFragment);
            transaction.add(R.id.content, fragment);
            currentFragment = fragment;
            transaction.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_fragment();

        navigationView = (NavigationView) findViewById(R.id.nav_view) ;
        View view = navigationView.getHeaderView(0);

        iv = (CircleImageView) view.findViewById(R.id.icon_image1);
        byte[] bs = Base64.decode(FakeDataBase.getHead_portait(), 100);
        iv.setImageBitmap(BitmapFactory.decodeByteArray(bs, 0, bs.length));
        mail = (TextView) view.findViewById(R.id.mail);
        mail.setText(FakeDataBase.getPhone());
        username = (TextView) view.findViewById(R.id.username);
        username.setText(FakeDataBase.getP_name());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_checkbaby:
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(MainActivity.this, checkBabyActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_addbaby:
                        mDrawerLayout.closeDrawers();
                        Intent intent2 = new Intent(MainActivity.this, addBabyActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.nav_parentinfo:
                        mDrawerLayout.closeDrawers();
                        Intent intent3 = new Intent(MainActivity.this, ParentInfoActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.nav_store:
                        mDrawerLayout.closeDrawers();
                        Intent intent4 = new Intent(MainActivity.this, com.zjh.store.StoreActivity.class);
                        startActivity(intent4);
                        return true;
                    case R.id.nav_setarea:
                        mDrawerLayout.closeDrawers();
                        Intent intent5 = new Intent(MainActivity.this, setAreaActivity.class);
                        startActivity(intent5);
                        return true;
                    case R.id.nav_aboutus:
                        mDrawerLayout.closeDrawers();
                        replaceFragment(new aboutUsFragment());
                        return true;
                }
                return false;
            }
        });
    }

    public void onCommClick(View view) {
        int childAdapterPosition = CommunityFragment.getRecyclerView().getChildAdapterPosition(view);
        Intent intent = new Intent(MainActivity.this, ClickCommunity.class);
        intent.putExtra("did", childAdapterPosition);
        startActivity(intent);
    }

    public void onBbLocationClick(View view) {
        int childAdapterPosition = CommunityFragment.getRecyclerView().getChildAdapterPosition(view);
        Intent intent = new Intent(MainActivity.this, BBLocationMap.class);
        intent.putExtra("mid", childAdapterPosition);
        startActivity(intent);
    }

    public void init_fragment(){
        //如果底部的选项超过3个，需要使用平均分布的方法
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        communityFragment = new CommunityFragment();
        addressFragment = new AddressFragment();
        babyFragment = new BabyFragment();
        transaction.add(R.id.content, communityFragment);
        currentFragment = communityFragment;
        transaction.commit();
    }

    public void onPicClick(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            // 没有权限，获取权限
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 3);
        }else{
            // 有权限
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            try {
//                startActivityForResult(cut_for_pic(data.getData()),2);
                File cutfile = new File(Environment.getExternalStorageDirectory().getPath(), "cutcamera.png");
                if(cutfile.exists()){
                    cutfile.delete();
                }
                cutfile.createNewFile();
                outputUri = getUriForFile(this, cutfile);
                Crop.of(data.getData(), outputUri).asSquare().start(MainActivity.this);
            }catch (Exception e){

            }
        }else if(requestCode == 2){
            try {
                File cutfile = new File(Environment.getExternalStorageDirectory().getPath(), "cutcamera.png");
                if(cutfile.exists()){
                    cutfile.delete();
                }
                cutfile.createNewFile();
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(cutfile));
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(cutfile));
                FileInputStream is = new FileInputStream(cutfile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bs = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(bs))){
                    baos.write(bs, 0, len);
                }
                RestfulClient.add_parent_head(Integer.parseInt(FakeDataBase.getMml()), new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT)));
                iv.setImageBitmap(BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length));
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            try {
                FileInputStream is = new FileInputStream(new File(Environment.getExternalStorageDirectory().getPath(), "cutcamera.png"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bs = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(bs))){
                    baos.write(bs, 0, len);
                }
                RestfulClient.add_parent_head(Integer.parseInt(FakeDataBase.getMml()), new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT)));
                iv.setImageBitmap(BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length));
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Intent cut_for_pic(Uri uri){
        try{
            Intent intent = new Intent("com.android.camera.action.CROP");
            File cutfile = new File(Environment.getExternalStorageDirectory().getPath(), "cutcamera.png");
            if(cutfile.exists()){
                cutfile.delete();
            }
            cutfile.createNewFile();
            Uri pic_uri = uri;
            Uri output_uri = getUriForFile(this, cutfile);
            intent.putExtra("crop", true);
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            intent.putExtra("outputX", 240);
            intent.putExtra("outputY", 240);
            intent.putExtra("scale",true);
            intent.putExtra("return-data",false);
            if (pic_uri != null) {
                intent.setDataAndType(pic_uri, "image/*");
            }
            if (output_uri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, output_uri);
            }
            intent.putExtra("noFaceDetection", true);

            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            outputUri = output_uri;
            return intent;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 3){
            if(grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // 允许
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }else{
                // 拒绝
                Toast.makeText(MainActivity.this, "没有权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {

    }

    private Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider
                    .getUriForFile(context.getApplicationContext(), "com.example.zh.babyplan.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }
}
