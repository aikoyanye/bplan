package com.example.zh.babyplan.CommunityBlock;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.babyplan.MainActivity;
import com.example.zh.babyplan.R;
import com.example.zh.babyplan.Util.FakeDataBase;
import com.example.zh.babyplan.Util.RestfulClient;
import com.example.zh.babyplan.Util.ServiceClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.richeditor.RichEditor;

import static android.app.PendingIntent.getActivity;

public class addArticleActivity extends AppCompatActivity implements RichEditor.OnTextChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener, Toolbar.OnMenuItemClickListener {

    private RichEditor mEditor;
    private String text, image, topic = "";
    private EditText problem;
    private Spinner topic_spinner;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        ServiceClient.setClientHandler(handler);
        toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        toolbar.setTitle("编写动态");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        problem = (EditText) findViewById(R.id.problem);
        topic_spinner = (Spinner) findViewById(R.id.topic_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(addArticleActivity.this, android.R.layout.simple_spinner_item, init_topic());
        topic_spinner.setAdapter(arrayAdapter);
        topic_spinner.setOnItemSelectedListener(this);
        // 实例化富文本编辑器
        mEditor = (RichEditor) findViewById(R.id.editor);
        // 设置编辑器高度
        mEditor.setEditorHeight(200);
        // 设置编辑器里面的字体大小
        mEditor.setEditorFontSize(16);
        // 设置字体颜色
        mEditor.setEditorFontColor(Color.BLACK);
//        mEditor.setEditorBackgroundColor(Color.BLUE);
//        mEditor.setBackgroundColor(Color.BLUE);
//        mEditor.setBackgroundResource(R.drawable.bg);
        // 外边距？
        mEditor.setPadding(10, 10, 10, 10);
        // 可以是url资源
//        mEditor.setBackground("xx.jpg");
        // 提示文字
        mEditor.setPlaceholder("最右边链接图标是提交");
        // 监听器
        mEditor.setOnTextChangeListener(this);
        // 设置编辑器能不能被编辑
        mEditor.setEnabled(false);

//        findViewById(R.id.action_undo).setOnClickListener(this);
//        findViewById(R.id.action_redo).setOnClickListener(this);
//        findViewById(R.id.action_bold).setOnClickListener(this);
//        findViewById(R.id.action_italic).setOnClickListener(this);
//        findViewById(R.id.action_subscript).setOnClickListener(this);
//        findViewById(R.id.action_superscript).setOnClickListener(this);
//        findViewById(R.id.action_strikethrough).setOnClickListener(this);
//        findViewById(R.id.action_underline).setOnClickListener(this);
//        findViewById(R.id.action_heading1).setOnClickListener(this);
//        findViewById(R.id.action_heading2).setOnClickListener(this);
//        findViewById(R.id.action_heading3).setOnClickListener(this);
//        findViewById(R.id.action_heading4).setOnClickListener(this);
//        findViewById(R.id.action_heading6).setOnClickListener(this);
//        findViewById(R.id.action_txt_color).setOnClickListener(this);
//        findViewById(R.id.action_bg_color).setOnClickListener(this);
//        findViewById(R.id.action_indent).setOnClickListener(this);
//        findViewById(R.id.action_outdent).setOnClickListener(this);
//        findViewById(R.id.action_justify_left).setOnClickListener(this);
//        findViewById(R.id.justify_center).setOnClickListener(this);
//        findViewById(R.id.justify_right).setOnClickListener(this);
//        findViewById(R.id.justify_blockquote).setOnClickListener(this);
//        findViewById(R.id.justify_insert_bullets).setOnClickListener(this);
//        findViewById(R.id.justify_insert_numbers).setOnClickListener(this);
//        findViewById(R.id.justify_insert_image).setOnClickListener(this);
//        findViewById(R.id.justify_insert_link).setOnClickListener(this);
    }

    @Override
    public void onTextChange(String text) {
        this.text = text;
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.action_undo:
//                // ？？？松开啥子
//                mEditor.undo();
//                break;
//            case R.id.action_redo:
//                // ？？？
//                mEditor.redo();
//                break;
//            case R.id.action_bold:
//                // 粗体
//                mEditor.setBold();
//                break;
//            case R.id.action_italic:
//                // 斜体
//                mEditor.setItalic();
//                break;
//            case R.id.action_subscript:
//                // 下标
//                mEditor.setSubscript();
//                break;
//            case R.id.action_superscript:
//                // 上标
//                mEditor.setSuperscript();
//                break;
//            case R.id.action_strikethrough:
//                // 删除线
//                mEditor.setStrikeThrough();
//                break;
//            case R.id.action_underline:
//                // 下划线
//                mEditor.setUnderline();
//                break;
//            case R.id.action_heading1:
//                // <h1>
//                mEditor.setHeading(1);
//                break;
//            case R.id.action_heading2:
//                // <h2>
//                mEditor.setHeading(2);
//                break;
//            case R.id.action_heading3:
//                // <h3>
//                mEditor.setHeading(3);
//                break;
//            case R.id.action_heading4:
//                // <h4>
//                mEditor.setHeading(4);
//                break;
//            case R.id.action_heading5:
//                // <h5>
//                mEditor.setHeading(5);
//                break;
//            case R.id.action_heading6:
//                // <h6>
//                mEditor.setHeading(6);
//                break;
//            case R.id.action_txt_color:
//                // 改变字体颜色
//                mEditor.setTextColor(Color.YELLOW);
//                break;
//            case R.id.action_bg_color:
//                // 改变背景颜色
//                mEditor.setBackgroundColor(Color.GRAY);
//                break;
//            case R.id.action_indent:
//                // ????
//                mEditor.setIndent();
//                break;
//            case R.id.action_outdent:
//                // ???
//                mEditor.setOutdent();
//                break;
//            case R.id.action_justify_left:
//                // 左对齐
//                mEditor.setAlignLeft();
//                break;
//            case R.id.justify_center:
//                // 居中
//                mEditor.setAlignCenter();
//                break;
//            case R.id.justify_right:
//                // 右对齐
//                mEditor.setAlignRight();
//                break;
//            case R.id.justify_blockquote:
//                // ????
//                mEditor.setBlockquote();
//                break;
//            case R.id.justify_insert_bullets:
//                // ???
//                mEditor.setBullets();
//                break;
//            case R.id.justify_insert_numbers:
//                // ??
//                mEditor.setNumbers();
//                break;
//            case R.id.justify_insert_image:
//                // 插入图片
////                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG", "dachshund");
////                mEditor.insertImage("http://120.77.153.248:8000/static/images/1.jpg", "dachshund");
//                mEditor.focusEditor();
                // 用户存放图片
//                File image = new File(Environment.getExternalStorageDirectory(), "outputImage.png");
//                if (image.exists()) {
//                    image.delete();
//                }
//                try {
//                    image.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                // 转换为uri
//                Uri uri = Uri.fromFile(image);
//                // 选择界面
//                Intent intent = new Intent("android.intent.action.GET_CONTENT");
////                Intent intent = new Intent("com.android.camera.action.CROP");
//                // 允许缩放
//                intent.putExtra("scale", true);
//                // 允许裁剪
//                intent.putExtra("crop", true);
//                intent.setType("image/*");
//                // 设置输出位置
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                // 走起
//                startActivityForResult(intent, 1);
//                break;
//            case R.id.justify_insert_link:
////                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
//                RestfulClient.add_community_dynamics(topic, String.valueOf(problem.getText()), text, 0, FakeDataBase.getP_name(), FakeDataBase.getHead_portait());
//                break;
//            // 还有一个标签，少了一个图片，暂时放着
////            mEditor.insertTodo();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            try {
                handleImageOnKitkat(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitkat(Intent data) throws IOException {
        String imagePath = null;
        try{
            Uri uri = data.getData();
            if (DocumentsContract.isDocumentUri(this, uri)) {
                // 如果是document类型的Uri，则通过document id处理
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri
                        .getAuthority())) {
                    String id = docId.split(":")[1]; // 解析出数字格式的id
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri
                        .getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"),
                            Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                // 如果不是document类型的Uri，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            }
            displayImage(imagePath); // 根据图片路径显示图片
        }catch (Exception e){

        }
    }

    private void displayImage(final String imagePath) throws IOException {
        if (imagePath != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            File file = new File(Environment.getExternalStorageDirectory() + "/1.png");
            if (file.exists()) {
                file.delete();
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
            FileInputStream fs = new FileInputStream(file);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while (-1 != (len = fs.read(buffer))) {
                outStream.write(buffer, 0, len);
            }
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("head_portait", new String(Base64.encode(outStream.toByteArray(), Base64.DEFAULT)));
            params.put("pid", 233);
//            params.put("image_select", "static/images/"+"1.jpg");
            params.put("image_select", get_random_file_name(FakeDataBase.getP_name()));
            params.put("image_name", "1");
            params.put("what", "_POST");
            client.post("http://120.77.153.248:8000/image/", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                    Log.e("失败", "--------------------------");
                }

                @Override
                public void onSuccess(int i, Header[] headers, String s) {
                    image = "http://120.77.153.248:8000/" + image + "\" style=\"max-width:100%";
                    mEditor.insertImage(image, "dachshund");
                }
            });
            file.delete();
            outStream.close();
            fs.close();
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    public String get_random_file_name(String p_name) {
        image = String.valueOf("static/images/" + p_name + "_" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".png");
        return image;
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public List<String> init_topic() {
        List<String> list = new ArrayList<>();
        list.add("幼儿保健");
        list.add("幼儿护理");
        list.add("幼儿饮食");
        list.add("幼儿教育");
        list.add("幼儿疾病");
        list.add("幼儿注意");
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    private Handler handler = new Handler() {
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if (msg.what == 701) {
                startActivity(new Intent(addArticleActivity.this, MainActivity.class));
            }
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        topic = init_topic().get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.add_pic){
            File image = new File(Environment.getExternalStorageDirectory(), "outputImage.png");
            if (image.exists()) {
                image.delete();
            }
            try {
                image.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 转换为uri
            Uri uri = Uri.fromFile(image);
            // 选择界面
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
//                Intent intent = new Intent("com.android.camera.action.CROP");
            // 允许缩放
            intent.putExtra("scale", true);
            // 允许裁剪
            intent.putExtra("crop", true);
            intent.setType("image/*");
            // 设置输出位置
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // 走起
            startActivityForResult(intent, 1);
        }else if(item.getItemId() == R.id.add_send){
            RestfulClient.add_community_dynamics(topic, String.valueOf(problem.getText()), text, 0, FakeDataBase.getP_name(), FakeDataBase.getHead_portait());
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_comm_menu, menu);
        return true;
    }
}
