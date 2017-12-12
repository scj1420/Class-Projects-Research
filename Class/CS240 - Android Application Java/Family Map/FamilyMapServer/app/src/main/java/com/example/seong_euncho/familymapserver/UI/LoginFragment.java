package com.example.seong_euncho.familymapserver.UI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;
import com.example.seong_euncho.familymapserver.Server.ServerProxy;

import java.io.IOException;

import model.Person;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.PersonResult;
import result.RegisterResult;

/**
 * Created by Seong-EunCho on 3/27/17.
 */

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private boolean serverConnection;


    private EditText
            mServerHost,
            mServerPort,
            mUserName,
            mPassword,
            mFirstName,
            mLastName,
            mEmail;

    private RadioGroup mGender;

    private Button
            mSignInButton,
            mRegisterButton;

    private String
            sServerHost,
            sServerPort,
            sUsername,
            sPassword,
            sFirstName,
            sLastName,
            sEmail,
            sGender;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mServerHost = (EditText) v.findViewById(R.id.editText_server_host);
        mServerHost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sServerHost = s.toString();
                if (s.length() == 0){
                    sServerHost = null;
                    mSignInButton.setEnabled(false);
                    mRegisterButton.setEnabled(false);
                } else {
                    if (sServerPort != null && sUsername != null && sPassword != null){
                        mSignInButton.setEnabled(true);

                        if (sFirstName != null && sLastName != null &&
                                sEmail != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else mRegisterButton.setEnabled(false);
                    } else {
                        mSignInButton.setEnabled(false);
                        mRegisterButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0){
                    sServerHost = null;
                    mSignInButton.setEnabled(false);
                } else {
                    if (sServerPort != null && sUsername != null && sPassword != null){
                        mSignInButton.setEnabled(true);

                        if (sFirstName != null && sLastName != null &&
                                sEmail != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else mRegisterButton.setEnabled(false);
                    } else {
                        mSignInButton.setEnabled(false);
                        mRegisterButton.setEnabled(false);
                    }
                }
            }
        });

        mServerPort = (EditText) v.findViewById(R.id.editText_server_port);
        mServerPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sServerPort = s.toString();
                if (s.length() == 0){
                    sServerPort = null;
                    mSignInButton.setEnabled(false);
                    mRegisterButton.setEnabled(false);
                } else {
                    if (sServerHost != null && sUsername != null && sPassword != null){
                        mSignInButton.setEnabled(true);

                        if (sFirstName != null && sLastName != null &&
                                sEmail != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else mRegisterButton.setEnabled(false);
                    } else {
                        mSignInButton.setEnabled(false);
                        mRegisterButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mUserName = (EditText) v.findViewById(R.id.editText_user_name);
        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sUsername = s.toString();
                if (s.length() == 0){
                    sUsername = null;
                    mSignInButton.setEnabled(false);
                    mRegisterButton.setEnabled(false);

                } else {
                    if (sServerHost != null && sServerPort != null && sPassword != null){
                        mSignInButton.setEnabled(true);

                        if (sFirstName != null && sLastName != null &&
                                sEmail != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else mRegisterButton.setEnabled(false);
                    } else {
                        mSignInButton.setEnabled(false);
                        mRegisterButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPassword = (EditText) v.findViewById(R.id.editText_password);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sPassword = s.toString();
                if (s.length() == 0){
                    sPassword = null;
                    mSignInButton.setEnabled(false);
                    mRegisterButton.setEnabled(false);

                } else {
                    if (sServerHost != null && sUsername != null && sServerPort != null){
                        mSignInButton.setEnabled(true);

                        if (sFirstName != null && sLastName != null &&
                                sEmail != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else mRegisterButton.setEnabled(false);
                    } else {
                        mSignInButton.setEnabled(false);
                        mRegisterButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mFirstName = (EditText) v.findViewById(R.id.editText_first_name);
        mFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sFirstName = s.toString();
                if (s.length() == 0){
                    sFirstName = null;
                    mRegisterButton.setEnabled(false);
                } else {
                    if (!mSignInButton.isEnabled()){
                        mRegisterButton.setEnabled(false);
                    } else {
                        if (sLastName != null && sEmail != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else {
                            mRegisterButton.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLastName = (EditText) v.findViewById(R.id.editText_last_name);
        mLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sLastName = s.toString();
                if (s.length() == 0){
                    sLastName = null;
                    mRegisterButton.setEnabled(false);
                } else {
                    if (!mSignInButton.isEnabled()){
                        mRegisterButton.setEnabled(false);
                    } else {
                        if (sFirstName != null && sEmail != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else {
                            mRegisterButton.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEmail = (EditText) v.findViewById(R.id.editText_email);
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sEmail = s.toString();
                if (s.length() == 0){
                    sEmail = null;
                    mRegisterButton.setEnabled(false);
                } else {
                    if (!mSignInButton.isEnabled()){
                        mRegisterButton.setEnabled(false);
                    } else {
                        if (sLastName != null && sFirstName != null && sGender != null){
                            mRegisterButton.setEnabled(true);
                        } else {
                            mRegisterButton.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mGender = (RadioGroup) v.findViewById(R.id.radio_group_gender);
        mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_male:
                        sGender = "m";
                        if (!mSignInButton.isEnabled()){
                            mRegisterButton.setEnabled(false);
                        } else {
                            if (sLastName != null && sFirstName != null && sEmail != null){
                                mRegisterButton.setEnabled(true);
                            } else {
                                mRegisterButton.setEnabled(false);
                            }
                        }
                        break;
                    case R.id.radio_female:
                        sGender = "f";
                        if (!mSignInButton.isEnabled()){
                            mRegisterButton.setEnabled(false);
                        } else {
                            if (sLastName != null && sFirstName != null && sEmail != null){
                                mRegisterButton.setEnabled(true);
                            } else {
                                mRegisterButton.setEnabled(false);
                            }
                        }
                        break;
                }
            }
        });

        mSignInButton = (Button) v.findViewById(R.id.sign_in_button);
        if (sServerHost == null || sServerPort == null){
            mSignInButton.setEnabled(false);
        } else {
            if (sUsername == null || sPassword == null) {
                mSignInButton.setEnabled(false);
            } else {
                mSignInButton.setEnabled(true);
            }
        }
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest r = new LoginRequest(sUsername, sPassword);
                new LoginTask().execute(r);
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        if (sServerHost == null || sServerPort == null){
            mRegisterButton.setEnabled(false);
        } else {
            if (sUsername == null || sPassword == null || sFirstName == null ||
                    sLastName == null || sEmail == null || sGender == null) {
                mRegisterButton.setEnabled(false);
            } else {
                mRegisterButton.setEnabled(true);
            }
        }
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterRequest r = new RegisterRequest(sUsername, sPassword, sEmail,
                        sFirstName, sLastName, sGender);
                new RegisterTask().execute(r);
            }
        });

        return v;
    }


    private class LoginTask extends AsyncTask<LoginRequest, Void, LoginResult> {

        @Override
        protected LoginResult doInBackground(LoginRequest... params) {
            try {
                UserModel.getInstance().getsServerProxy().setHost(sServerHost);
                UserModel.getInstance().getsServerProxy().setPort(sServerPort);
                LoginResult r = UserModel.getInstance().getsServerProxy().login(params[0]);
                serverConnection = true;
                return r;

            } catch (IOException ioe){
                Log.e(TAG, "Failed to connect to server", ioe);
                serverConnection = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(LoginResult result){
            if (serverConnection){
                if (result.isSuccess()){
                    String token = result.getSrb().getAuthToken();
                    String id = result.getSrb().getPersonID();
                    UserModel.getInstance().setsLoginResult(result);
                    new UserRetrieveTask().execute(id, token);

                } else {
                    Toast.makeText(getActivity(),
                            result.getErb().getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(),
                        "Failed to connect to server!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class RegisterTask extends AsyncTask<RegisterRequest, Void, RegisterResult> {

        @Override
        protected RegisterResult doInBackground(RegisterRequest... params) {
            try {
                UserModel.getInstance().getsServerProxy().setHost(sServerHost);
                UserModel.getInstance().getsServerProxy().setPort(sServerPort);
                RegisterResult r = UserModel.getInstance().getsServerProxy().register(params[0]);
                serverConnection = true;
                return r;

            } catch (IOException ioe){
                Log.e(TAG, "Failed to connect to server", ioe);
                serverConnection = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(RegisterResult result){
            if (serverConnection){
                if (result.isSuccess()){
                    String token = result.getSrb().getAuthToken();
                    String id = result.getSrb().getPersonID();
                    UserModel.getInstance().setsLoginResult(result);
                    new UserRetrieveTask().execute(id, token);

                } else {
                    Toast.makeText(getActivity(),
                            result.getErb().getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(),
                        "Failed to connect to server!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class UserRetrieveTask extends AsyncTask<String, Void, PersonResult> {

        @Override
        protected PersonResult doInBackground(String... params){
            try{
                PersonResult r = UserModel.getInstance().getsServerProxy().person(params[0], params[1]);
                serverConnection = true;
                return r;
            } catch (IOException ioe){
                Log.e(TAG, "Failed to connect to server", ioe);
                serverConnection = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(PersonResult result){
            if (serverConnection){
                UserModel.getInstance().setsPersonResult(result);
                try {
                    ((MainActivity) getActivity()).onLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(),
                        "Failed to connect to server!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
