package cc.overwall.overwall.ui;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;

import com.roger.match.library.MatchButton;
import com.roger.match.library.MatchTextView;
import com.roger.match.library.util.MatchView;

import cc.overwall.overwall.App;
import cc.overwall.overwall.R;

/**
 * Description:MatchDialog Demo
 * User: Lj
 * Date: 2014-12-04
 * Time: 10:00
 * FIXME
 */
@SuppressLint("ValidFragment")
public class MatchDialog extends DialogFragment {

    public MatchDialog() {
    }

    Dialog mDialog;
    MatchTextView matchTextView;
    MatchButton mOkButton,mNoButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.cart_dialog);
            mDialog.setContentView(R.layout.dialog_match);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.getWindow().setGravity(Gravity.CENTER);
            View view = mDialog.getWindow().getDecorView();
            matchTextView = (MatchTextView) view.findViewById(R.id.mTextView);
            matchTextView.setMatchOutListener(new MatchView.MatchOutListener() {
                @Override
                public void onBegin() {

                }

                @Override
                public void onProgressUpdate(float progress) {
                }

                @Override
                public void onFinish() {
                    MatchDialog.super.onStop();
                }
            });

            mNoButton = (MatchButton) view.findViewById(R.id.noButton);
            mNoButton .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOkButton.hide();
                    mNoButton.hide();
                    matchTextView.hide();
                }
            });
            mOkButton = (MatchButton) view.findViewById(R.id.okButton);
            mOkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOkButton.hide();
                    mNoButton.hide();
                    matchTextView.hide();
                    App.preferenceE.putInt("user_id", 0);
                    App.preferenceE.commit();
                    App.clearCookie();
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            });
            
        }
        return mDialog;
    }
}