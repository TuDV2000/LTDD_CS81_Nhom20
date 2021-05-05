package com.example.salebookapp;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salebookapp.entities.Account;

import com.basgeekball.awesomevalidation.AwesomeValidation;


public class AccountFragment extends Fragment {

    EditText edt_user, edt_pass;
    Button btn_signin, btn_signup, btn_quit;
    AwesomeValidation awesomeValidation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_account, container, false);

        //Anh xa
        edt_user = myView.findViewById(R.id.edt_username);
        edt_pass = myView.findViewById(R.id.edt_password);
        btn_signin = myView.findViewById(R.id.btn_signin);
        btn_signup = myView.findViewById(R.id.btn_signup);
        btn_quit = myView.findViewById(R.id.btn_quit);

        ControlButton();

        return myView;
    }

    private void ControlButton() {
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext(), android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc muốn thoát !");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //onBackPressed();
                        getActivity().finish();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("111-------------------");
                Intent intent = new Intent(getActivity().getBaseContext(), RegistActivity.class);
                startActivity(intent);
            }
        });
    }

}
