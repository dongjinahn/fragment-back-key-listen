package olaf.kr.fragmentonback;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

import java.util.Stack;


public class MyActivity extends AppCompatActivity {
    private static final String TAG = MyActivity.class.getSimpleName();

    public interface OnBackKeyPressedListener {
        public void onBack();
    }

    private Stack<OnBackKeyPressedListener> mFragmentBackStack = new Stack<>();

    public void pushOnBackKeyPressedListener(OnBackKeyPressedListener listener) {
        mFragmentBackStack.push(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, OlafFragment.newInstance(0))
                    .commit();
        }
    }

    public void switchFragment(Fragment fragment, String fragmentTag) {
        Log.d(TAG, "switchFragment() fragmentTag: " + fragmentTag);

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.enter_from_bottom, R.animator.alpha_hide,
                        R.animator.alpha_show, R.animator.leave_to_bottom)
                .replace(R.id.container, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (!mFragmentBackStack.isEmpty()) {
            mFragmentBackStack.pop().onBack();
        } else {
            super.onBackPressed();
        }
    }
}
