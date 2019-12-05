package e.evolc.chatapp;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

/*
public class KeyboardUtil {
    private Window window;


    // Threshold for minimal keyboard height.
    final int MIN_KEYBOARD_HEIGHT_PX = 150;

    // Top-level window decor view.
    final View decorView =

    // Register global layout listener.
    decorView.getViewTxreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
        private final Rect windowVisibleDisplayFrame = new Rect();
        private int lastVisibleDecorViewHeight;

        @Override
        public void onGlobalLayout() {
            // Retrieve visible rectangle inside window.
            decorView.getWindowVisibleDisplayFrame(windowVisibleDisplayFrame);
            final int visibleDecorViewHeight = windowVisibleDisplayFrame.height();

            // Decide whether keyboard is visible from changing decor view height.
            if (lastVisibleDecorViewHeight != 0) {
                if (lastVisibleDecorViewHeight > visibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX) {
                    // Calculate current keyboard height (this includes also navigation bar height when in fullscreen mode).
                    int currentKeyboardHeight = decorView.getHeight() - windowVisibleDisplayFrame.bottom;
                    // Notify listener about keyboard being shown.
                    listener.onKeyboardShown(currentKeyboardHeight);
                } else if (lastVisibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX < visibleDecorViewHeight) {
                    // Notify listener about keyboard being hidden.
                    listener.onKeyboardHidden();
                }
            }
            // Save current decor view height for the next call.
            lastVisibleDecorViewHeight = visibleDecorViewHeight;
        }
    });

}
*/