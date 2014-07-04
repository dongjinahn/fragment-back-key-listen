package me.blog.qwerfrewq.fragmentbackkey;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by olaf on 14. 7. 4.
 */
public class OlafFragment extends Fragment implements MyActivity.OnBackKeyPressedListener{
    private Switch mSwitch;
    private TextView mTxt;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).setOnBackKeyPressedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_olaf, container, false);
        mSwitch = (Switch) root.findViewById(R.id.switch1);
        mTxt = (TextView) root.findViewById(R.id.txt);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mTxt.setText("It is now listening, vibrate when back key pressed");
                } else {
                    mTxt.setText("doesn't listen back key");
                }
            }
        });
        return root;
    }

    @Override
    public void onBack() {
        if (mSwitch.isChecked()) {
            Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
        } else {
            MyActivity myActivity = (MyActivity) getActivity();
            myActivity.setOnBackKeyPressedListener(null);
            myActivity.onBackPressed();
        }
    }
}
