package e.evolc.chatapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int a;

    ImageView imageView;
    FrameLayout container;

    EditText etText;
    Button btnSend;
    int myuser = 0;

    RelativeLayout mLlEdit;
    SoftKeyboard mSoftKeyboard;
    LinearLayout linearLayout;
    List<Chat> mChat = new ArrayList<>();
    ArrayList<String> url = new ArrayList<>();

    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Hallym Assistant");

        mLlEdit = (RelativeLayout) findViewById(R.id.ll);


        Chat chat = new Chat(2, "Hallym Assistant입니다. 무엇이든 물어보세요 ! ");
        mChat.add(chat);
        url.add("");
        // Threshold for minimal keyboard height.
        final int MIN_KEYBOARD_HEIGHT_PX = 150;

        // Top-level window decor view.
        final View decorView = this.getWindow().getDecorView();

        // Register global layout listener.
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private final Rect windowVisibleDisplayFrame = new Rect();
            private int lastVisibleDecorViewHeight;

            @Override
            public void onGlobalLayout() {
                // Retrieve visible rectangle inside window.
                decorView.getWindowVisibleDisplayFrame(windowVisibleDisplayFrame);
                final int visibleDecorViewHeight = windowVisibleDisplayFrame.height();

                // Decide whether keyboard is visible from changing decor view height.
                if (lastVisibleDecorViewHeight != 0) {
                    if (lastVisibleDecorViewHeight > visibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX) {
                        // Calculate current keyboard height (this includes also navigation bar height when in fullscreen mode).
                        int currentKeyboardHeight = decorView.getHeight() - windowVisibleDisplayFrame.bottom;
                        // Notify listener about keyboard being shown.
                        a = currentKeyboardHeight;
                    } else if (lastVisibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX < visibleDecorViewHeight) {
                        // Notify listener about keyboard being hidden.
                    }
                }
                // Save current decor view height for the next call.
                lastVisibleDecorViewHeight = visibleDecorViewHeight;
            }
        });



            InputMethodManager controlManager = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
            mSoftKeyboard = new SoftKeyboard(mLlEdit, controlManager);
            mSoftKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
                @Override
                public void onSoftKeyboardHide() {
                    new Handler(Looper.getMainLooper())
                            .post(new Runnable() {
                                @Override
                                public void run() {
                                    //Toast.makeText(getApplication(), "키보드 내려감", Toast.LENGTH_SHORT).show();
                                    if (mRecyclerView.getChildAt(0).getTop() != -108) {
                                        mRecyclerView.smoothScrollBy(0, -a);
                                    }
                                }
                            });
                }
                @Override
                public void onSoftKeyboardShow() {
                    new Handler(Looper.getMainLooper())
                            .post(new Runnable() {
                                @Override
                                public void run() {
                                    //Toast.makeText(getApplication(),"키보드 올라감",Toast.LENGTH_SHORT).show();
                                    mRecyclerView.smoothScrollBy(0, a);
                                }
                            });
                }
            });

        etText = (EditText) findViewById(R.id.etText);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String stText = etText.getText().toString();

                if(stText.equals("") || stText.isEmpty()){
                    Toast.makeText(MainActivity.this, "내용을 입력해 주세요", Toast.LENGTH_SHORT).show();
                }else{

                    Chat chat = new Chat(1, stText);

                    myuser = 1;
                    mChat.add(chat);
                    url.add("");
                    mAdapter.notifyItemInserted(mChat.size()-1);
                    etText.setText("");
                    mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount()-1);

                    OkHttpClient client = new OkHttpClient();

                    JSONObject jsonInput = new JSONObject();

                    try {
                        jsonInput.put("chatbot_id", "f1c6b572-2d9b-4cfb-81b9-5fba8536d6bd");
                    jsonInput.put("input_sentence", stText);
                } catch (JSONException e) {

                    e.printStackTrace();
                }

                    RequestBody reqBody = RequestBody.create(
                            MediaType.parse("application/json; charset=utf-8"),
                            jsonInput.toString()
                    );

                    Request request = new Request.Builder()

                            .url("https://danbee.ai/chatflow/engine.do")
                            .addHeader("danbee_apikey", "secret")
                            .post(reqBody)
                            .build();

                    client.newCall(request).enqueue(updateUserInfoCallback);

                }
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(mChat, myuser, url, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

            Log.d("Test", "지지지지지지지나감 : " + myuser);


    }

    private Callback updateUserInfoCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("TEST", "ERROR Message : " + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            //final String responseData = response.body().string();
            //Log.d("TEST", "responseDatae : " + responseData);

            try {
                JSONObject jsonparsing = new JSONObject(response.body().string());

                Log.d("TEST", "responseDatae : " + jsonparsing.getString("responseSet"));


                jsonparsing = new JSONObject(jsonparsing.getString("responseSet"));
                jsonparsing = new JSONObject(jsonparsing.getString("result"));
                JSONArray jsonarray = jsonparsing.getJSONArray("result");
                for(int i = 1; i < jsonarray.length(); i++){
                    JSONObject subjsonobject = jsonarray.getJSONObject(i);
                    Chat chat = new Chat(2, subjsonobject.getString("message"));
                    mChat.add(chat);

                    url.add(subjsonobject.getString("imgRoute"));

                    if(!subjsonobject.getString("imgRoute").isEmpty()){
                        myuser = 2;
                        Log.d("Test", "지지나감 : " + myuser);
                    }

                }
                mAdapter.notifyItemInserted(mChat.size()-1);
                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount()-1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSoftKeyboard.unRegisterSoftKeyboardCallback();
    }
}
