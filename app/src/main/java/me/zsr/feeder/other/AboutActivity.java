package me.zsr.feeder.other;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.google.GooglePlus;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import me.zsr.feeder.BuildConfig;
import me.zsr.feeder.R;
import me.zsr.feeder.base.BaseActivity;
import me.zsr.feeder.view.MarkdownView;

public class AboutActivity extends BaseActivity {
    private static final String ID_WECHAT = "matchzsr";
    private static final String URL_GITHUB = "https://github.com/zhangsr/Feeder";
    private static final String URL_GOOGLE_PLUS = "https://plus.google.com/108838785221141135915";
    private static final String URL_BUG = "https://github.com/zhangsr/Feeder/issues";
    private static final String URL_STORE = "http://www.wandoujia.com/apps/me.zsr.feeder";
    private static final String URL_AARON_SWARTZ = "https://en.wikipedia.org/wiki/Aaron_Swartz";
    private static final String URL_SHARE = "http://fir.im/vdwa";
    @Bind(R.id.version_name_txt)
    TextView mVersionNameTextView;
    @Bind(R.id.info_img)
    ImageView mInfoImageView;
    @Bind(R.id.change_log_img)
    ImageView mChangelogImageView;
    @Bind(R.id.author_img)
    ImageView mAuthorImageView;
    @Bind(R.id.google_plus_img)
    ImageView mGooglePlusImageView;
    @Bind(R.id.github_img)
    ImageView mGithubImageView;
    @Bind(R.id.web_img)
    ImageView mWebImageView;
    @Bind(R.id.bug_img)
    ImageView mBugImageView;
    @Bind(R.id.store_img)
    ImageView mStoreImageView;
    @Bind(R.id.wechat_img)
    ImageView mWechatImageView;
    @Bind(R.id.share_img)
    ImageView mShareImageView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        // TODO: 11/26/15 why not work to call in BaseActivity ?
        initSystemBar();
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Make arrow color white
        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        mVersionNameTextView.setText(BuildConfig.VERSION_NAME);
        mInfoImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mChangelogImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mAuthorImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mGooglePlusImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mGithubImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mWebImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mBugImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mStoreImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mWechatImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
        mShareImageView.setColorFilter(getResources().getColor(R.color.main_grey_light));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openUrl(String url) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
    }

    @OnClick({
            R.id.change_log_layout,
            R.id.wechat_layout,
            R.id.google_plus_layout,
            R.id.github_layout,
            R.id.bug_layout,
            R.id.store_layout,
            R.id.aaron_swartz_layout,
            R.id.share_layout,
    })
    public void layoutOnClick(View view) {
        switch (view.getId()) {
            case R.id.change_log_layout:
                showChangeLogDialog();
                break;
            case R.id.wechat_layout:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Wechat ID", ID_WECHAT);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show();
                break;
            case R.id.google_plus_layout:
                openUrl(URL_GOOGLE_PLUS);
                break;
            case R.id.github_layout:
                openUrl(URL_GITHUB);
                break;
            case R.id.bug_layout:
                openUrl(URL_BUG);
                break;
            case R.id.store_layout:
                openUrl(URL_STORE);
                break;
            case R.id.aaron_swartz_layout:
                openUrl(URL_AARON_SWARTZ);
                break;
            case R.id.share_layout:
                showShareMenu();
                break;
        }
    }

    private void showChangeLogDialog() {
        // TODO: 11/30/15 fix margin
        MarkdownView markdownView = new MarkdownView(this);
        markdownView.loadMarkdownFile("file:///android_asset/CHANGELOG.md");

        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.changelog)
                .customView(markdownView, false)
                .positiveText(R.string.got_it)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                }).build();
        dialog.show();
    }

    private void showShareMenu() {
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(this);
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content(R.string.wechat)
                .icon(R.drawable.ic_menu_wechat)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content(R.string.moment)
                .icon(R.drawable.ic_menu_moment)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content(R.string.weibo)
                .icon(R.drawable.ic_menu_weibo)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content(R.string.google_plus)
                .icon(R.drawable.ic_menu_google_plus)
                .build());

        new MaterialDialog.Builder(this)
                .title(R.string.share_to)
                .adapter(adapter, new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        switch (which) {
                            case 0: {
                                Wechat.ShareParams sp = new Wechat.ShareParams();
                                sp.setShareType(Wechat.SHARE_WEBPAGE);
                                sp.setTitle("Feeder");
                                sp.setText(getString(R.string.app_share_desc));
                                sp.setUrl(URL_SHARE);
                                sp.setImageData(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                                Platform platform = ShareSDK.getPlatform(Wechat.NAME);
                                platform.share(sp);
                                break;
                            }
                            case 1: {
                                WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
                                sp.setShareType(Wechat.SHARE_WEBPAGE);
                                sp.setTitle("Feeder");
                                sp.setText(getString(R.string.app_share_desc));
                                sp.setUrl(URL_SHARE);
                                sp.setImageData(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                                Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
                                platform.share(sp);
                                break;
                            }
                            case 2: {
                                SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
                                sp.setText("Feeder, " + getString(R.string.app_share_desc));

                                Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
                                platform.share(sp);
                                break;
                            }
                            case 3: {
                                GooglePlus.ShareParams sp = new GooglePlus.ShareParams();
                                sp.setText("Feeder, " + getString(R.string.app_share_desc));

                                Platform platform = ShareSDK.getPlatform(GooglePlus.NAME);
                                platform.share(sp);
                                break;
                            }
                        }
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
