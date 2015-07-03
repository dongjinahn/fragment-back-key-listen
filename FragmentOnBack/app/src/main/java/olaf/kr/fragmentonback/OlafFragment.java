package olaf.kr.fragmentonback;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by olaf on 14. 7. 4.
 */
public class OlafFragment extends Fragment implements MyActivity.OnBackKeyPressedListener{
    private static final String TAG = OlafFragment.class.getSimpleName();

    public static final String ARGS_NTH = "args-nth";

    private TextView mTxt;
    private Button mBtn;


    public static OlafFragment newInstance(int nth) {
        OlafFragment fragment = new OlafFragment();

        Bundle args = new Bundle();
        args.putInt(ARGS_NTH, nth);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).pushOnBackKeyPressedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_olaf, container, false);
        mTxt = (TextView) root.findViewById(R.id.txt);
        mBtn = (Button) root.findViewById(R.id.btn_next);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int nth = getArguments().getInt(ARGS_NTH);
        mTxt.setText(String.format("%d fragment", nth));
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = String.format("%s,%d", OlafFragment.class.getCanonicalName(), nth);
                ((MyActivity) getActivity()).switchFragment(OlafFragment.newInstance(nth + 1), tag);
            }
        });
    }

    @Override
    public void onBack() {
        FragmentManager fm = getActivity().getFragmentManager();

        if (fm.getBackStackEntryCount() == 0) return;

        fm.popBackStack();
    }
}
