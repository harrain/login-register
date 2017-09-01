package cn.smssdk.gui;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Login screen
 *
 */
public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "LoginActivity";
    public static final int REQUEST_CODE_SETNICK = 1;

    ImageView imgBack;

    TextView txtTitle;
    private AutoCompleteTextView usernameEditText;
    private EditText passwordEditText;

    private boolean progressShow;
    private boolean autoLogin = false;
    private String currentUsername;
    private String currentPassword;
    private ProgressDialog pd;
    TextView forgotTv;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        imgBack = (ImageView) findViewById(R.id.scanner_toolbar_back);
        txtTitle  = (TextView) findViewById(R.id.scanner_toolbar_title);
        txtTitle.setText("登录");
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        forgotTv = (TextView) findViewById(R.id.login_error_tv);
        register = (TextView) findViewById(R.id.toRegister);
        forgotTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        /*// enter the main activity if already logged in
        if (SuperWeChatHelper.getInstance().isLoggedIn()) {
            autoLogin = true;
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

            return;
        }*/

        usernameEditText = (AutoCompleteTextView) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        // if user changed, clear the password
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordEditText.setText(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                    login(null);
                    return true;
                } else {
                    return false;
                }
            }
        });

        populateAutoComplete();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void populateAutoComplete() {


        if (Build.VERSION.SDK_INT >= 14) {
            // Use ContactsContract.Profile (API 14+)
            getLoaderManager().initLoader(0, null, this);
        }
    }

    /**
     * login
     *
     * @param view
     */
    public void login(View view) {
//        if (!EaseCommonUtils.isNetWorkConnected(this)) {
//            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
//            return;
//        }
        currentUsername = usernameEditText.getText().toString().trim();
        currentPassword = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(currentUsername)) {
//            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
//            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        progressShow = true;
        pd = new ProgressDialog(LoginActivity.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d(TAG, "EMClient.getInstance().onCancel");
                progressShow = false;
            }
        });
        pd.setMessage("登录中");
        pd.show();


    }

    //都登录成功后的环信配置
    private void logonForSet() {


        if (!LoginActivity.this.isFinishing() && pd.isShowing()) {
            pd.dismiss();
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /*private void loginToAppServer() {
=======
        SuperWeChatHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
    }

    private void loginToAppServer() {
>>>>>>> origin/master
        model = new UserModel();
        model.login(this, currentUsername, MD5.getMessageDigest(currentPassword), new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                L.e(TAG, result);
                handleJson(result);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void handleJson(String s) {
        if (s != null) {
            Result result = ResultUtils.getResultFromJson(s, User.class);
            if (result != null) {
                Log.e(TAG, result.toString());
                if (result.getRetCode() == I.MSG_LOGIN_UNKNOW_USER) {
                    usernameEditText.requestFocus();
                    usernameEditText.setError(getString(R.string.login_unkown_user));
                } else if (result.getRetCode() == I.MSG_LOGIN_ERROR_PASSWORD) {
                    passwordEditText.requestFocus();
                    passwordEditText.setError(getString(R.string.login_password_error));
                } else {
                    loginSuccess();
                }
            }

        }
    }

    private void loginSuccess() {
        logonForSet();
        MFGT.gotoMain(this);
        MFGT.finish(this);
    }*/


    /**
     * register
     *
     * @param view
     */
    public void register(View view) {

//        startActivityForResult(new Intent(this, RegisterActivity.class), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoLogin) {
            return;
        }
    }


}
