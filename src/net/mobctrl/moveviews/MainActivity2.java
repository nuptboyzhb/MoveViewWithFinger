package net.mobctrl.moveviews;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author Zheng Haibo
 * @web http://www.mobctrl.net
 *
 */
@SuppressLint({ "ClickableViewAccessibility", "NewApi" })
public class MainActivity2 extends Activity implements OnTouchListener {

	private ImageView ivMove;
	private LinearLayout llRoot;
	private TextView tvTips;
	private int topTitleHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);
		ivMove = (ImageView) findViewById(R.id.iv_move);
		llRoot = (LinearLayout) findViewById(R.id.ll_root);
		tvTips = (TextView) findViewById(R.id.tv_tips);
		llRoot.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int[] locations = new int[2];
			tvTips.getLocationInWindow(locations);
			topTitleHeight = locations[1];
			break;
		case MotionEvent.ACTION_MOVE:
			moveViewByLayout(ivMove, (int) event.getRawX(),
					(int) event.getRawY());
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

	/**
	 * 通过layout方法，移动view
	 * 优点：对view所在的布局，要求不苛刻，不要是RelativeLayout，而且可以修改view的大小
	 * 并且，layout方法可以修改view的大小。
	 * @param view
	 * @param rawX
	 * @param rawY
	 * @param scale
	 */
	private void moveViewByLayout(View view, int rawX, int rawY, int scale) {
		int left = rawX - ivMove.getWidth() / 2;
		int top = rawY - topTitleHeight - ivMove.getHeight() / 2;
		int width = left + (int) (view.getWidth() + scale);
		int height = top + (int) (view.getHeight() + scale);
		view.layout(left, top, width, height);
	}

	/**
	 * 通过layout方法，移动view 
	 * 优点：对view所在的布局，要求不苛刻，不要是RelativeLayout，而且可以修改view的大小
	 * 
	 * @param view
	 * @param rawX
	 * @param rawY
	 */
	private void moveViewByLayout(View view, int rawX, int rawY) {
		int left = rawX - ivMove.getWidth() / 2;
		int top = rawY - topTitleHeight - ivMove.getHeight() / 2;
		int width = left + view.getWidth();
		int height = top + view.getHeight();
		view.layout(left, top, width, height);
	}

}
