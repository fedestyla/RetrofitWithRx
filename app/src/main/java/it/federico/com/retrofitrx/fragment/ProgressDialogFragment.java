package it.federico.com.retrofitrx.fragment;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import it.federico.com.retrofitrx.utils.BundleUtils;

public class ProgressDialogFragment extends DialogFragment {

    private static final String EXTRA_MESSAGE = BundleUtils.makeExtraKey(ProgressDialogFragment.class, "message");
    private static final String EXTRA_CANCELABLE = BundleUtils.makeExtraKey(ProgressDialogFragment
            .class, "cancelable");

    private static final String TAG_PROGRESS_DIALOG = "progressDialogFragmentTag";

    public ProgressDialogFragment() {
    }

    private static ProgressDialogFragment newInstance(final String message, final boolean cancelable) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MESSAGE, message);
        bundle.putBoolean(EXTRA_CANCELABLE, cancelable);
        progressDialogFragment.setArguments(bundle);
        return progressDialogFragment;
    }

    public static void showProgressDialog(final boolean cancelable, final String message,
            final FragmentManager fragmentManager) {
        DialogFragment newFragment = newInstance(message, cancelable);
        dismissProgressDialog(fragmentManager);

        // Show the dialog.
        FragmentTransaction addFt = fragmentManager.beginTransaction();
        addFt.add(newFragment, TAG_PROGRESS_DIALOG);
        addFt.commitAllowingStateLoss();
    }

    public static void dismissProgressDialog(final FragmentManager fragmentManager) {
        // We want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction removeFt = fragmentManager.beginTransaction();
        Fragment previousFragment = fragmentManager.findFragmentByTag(TAG_PROGRESS_DIALOG);
        if (previousFragment != null) {
            removeFt.remove(previousFragment);
            removeFt.commitAllowingStateLoss();
        }
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getArguments().getString(EXTRA_MESSAGE));
        dialog.setIndeterminate(true);
        dialog.setCancelable(getArguments().getBoolean(EXTRA_CANCELABLE, false));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(final DialogInterface dialog, final int keyCode, final KeyEvent event) {
                switch (event.getAction()) {
                    case KeyEvent.KEYCODE_BACK:
                        return false;
                    default:
                        return true;
                }
            }
        });
        return dialog;
    }
}
