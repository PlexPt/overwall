package cc.overwall.overwall.ui;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.overwall.overwall.App;
import cc.overwall.overwall.FloatingActionMyButton;
import cc.overwall.overwall.R;
import cc.overwall.overwall.SlidingTabLayout;
import cc.overwall.overwall.Utils.Tools;
import cc.overwall.overwall.ViewPagerAdapter;
import okhttp3.Call;
import okhttp3.MediaType;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String LOGIN_URL = "https://www.overwall.cc/auth/login";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private ListView mDrawerList;
    ViewPager pager;
    private String titles[] = new String[]{"个人中心","公告", "节点列表", "更多"};
    private Toolbar toolbar;

    SlidingTabLayout slidingTabLayout;

    private FloatingActionMyButton fabButton;
    

   
    void setMyTheme(int color) {
        mDrawerList.setBackgroundColor(getResources().getColor(color));
        toolbar.setBackgroundColor(getResources().getColor(color));
        slidingTabLayout.setBackgroundColor(getResources().getColor(color));
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

     
        String email = App.preferenceR.getString("email", "");
        String passwd = App.preferenceR.getString("password", "");
        Map<String, String> okmap = new HashMap<String, String>();
        okmap.put("email", email);
        okmap.put("passwd", passwd);
        okmap.put("code", "");
        okmap.put("remember_me", "week");

        App.clearCookie();
        OkHttpUtils.postString()
                .url(LOGIN_URL)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new JSONObject(okmap).toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Log.e("callback err", e.toString());
                        App.isCookieOK = true;

                        Tools.showToast(e.toString());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        App.isCookieOK = true;

                        
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("ret") == 1){
                                Tools.showToast("登陆成功！");
                            }else if (jsonObject.getInt("ret")==0){
                                Tools.showToast("密码错误！");
                            }else 
                            {
                                Tools.showToast("网络错误");
                            }
                            
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        
                    }}
                );
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));
        pager.setOffscreenPageLimit(3);
        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        String[] values = new String[]{
                "水鸭绿", "骚包红", "污黑蓝", "科技黑"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);


        setMyTheme(App.preferenceR.getInt("themecolor", R.color.material_deep_teal_500));


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        setMyTheme(R.color.material_deep_teal_500);
                        App.preferenceE.putInt("themecolor", R.color.material_deep_teal_500);
                        App.preferenceE.commit();
                        break;
                    case 1:
                        setMyTheme(R.color.red);
                        App.preferenceE.putInt("themecolor", R.color.red);
                        App.preferenceE.commit();
                        break;
                    case 2:
                        setMyTheme(R.color.blue);
                        App.preferenceE.putInt("themecolor", R.color.blue);
                        App.preferenceE.commit();
                        break;
                    case 3:
                        setMyTheme(R.color.material_blue_grey_800);
                        App.preferenceE.putInt("themecolor", R.color.material_blue_grey_800);
                        App.preferenceE.commit();
                        break;
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        
    }
}
