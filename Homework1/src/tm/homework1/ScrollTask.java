package tm.homework1;

import java.util.TimerTask;

import android.R.integer;
import android.widget.TextView;

public class ScrollTask extends TimerTask{
	TextView _scrollTextView;
	int _scrollAmount;
	
	public ScrollTask(TextView scrolTextView, int scrollAmount)
	{
		_scrollAmount = scrollAmount;
		_scrollTextView = scrolTextView;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		_scrollTextView.scrollTo(0, _scrollAmount);
	}

}
