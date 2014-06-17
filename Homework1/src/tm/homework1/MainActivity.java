package tm.homework1;

import java.util.Timer;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Service;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageButton _numberZeroButton;
	private ImageButton _numberOneButton;
	private ImageButton _numberTwoButton;
	private ImageButton _numberThreeButton;
	private ImageButton _numberFourButton;
	private ImageButton _numberFiveButton;
	private ImageButton _numberSixButton;
	private ImageButton _numberSevenButton;
	private ImageButton _numberEightButton;
	private ImageButton _numberNineButton;
	private ImageButton _pointButton;
	private ImageButton _minusButton;
	private ImageButton _plusButton;
	private ImageButton _multiplyButton;
	private ImageButton _equalButton;
	private ImageButton _divideButton;
	private ImageButton _clearButton;
	private TextView _resultTextView;
	private TextView _inputTextView;
	private Calculator _calculator;
	private Vibrator _vibrator;
	private Timer _timer = new Timer();
	private int _lineCount = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _calculator = new Calculator();
		_numberZeroButton = (ImageButton)findViewById(R.id._numberZeroButton);
        _numberOneButton = (ImageButton)findViewById(R.id._numberOneButton);
        _numberTwoButton = (ImageButton)findViewById(R.id._numberTwoButton);
        _numberThreeButton = (ImageButton)findViewById(R.id._numberThreeButton);
        _numberFourButton = (ImageButton)findViewById(R.id._numberFourButton);
        _numberFiveButton = (ImageButton)findViewById(R.id._numberFiveButton);
        _numberSixButton = (ImageButton)findViewById(R.id._numberSixButton);
        _numberSevenButton = (ImageButton)findViewById(R.id._numberSevenButton);
        _numberEightButton = (ImageButton)findViewById(R.id._numberEightButton);
        _numberNineButton = (ImageButton)findViewById(R.id._numberNineButton);
        _pointButton = (ImageButton)findViewById(R.id._pointButton);
        _minusButton = (ImageButton)findViewById(R.id._minusButton);
        _plusButton = (ImageButton)findViewById(R.id._plusButton);
        _multiplyButton = (ImageButton)findViewById(R.id._multiplyButton);
        _equalButton = (ImageButton)findViewById(R.id._equalButton);
        _divideButton = (ImageButton)findViewById(R.id._divideButton);
        _clearButton = (ImageButton)findViewById(R.id._clearButton);
        _resultTextView = (TextView)findViewById(R.id._resultTextView);
        _inputTextView = (TextView)findViewById(R.id._inputTextView);
        _resultTextView.setMovementMethod(ScrollingMovementMethod.getInstance()); 
        _inputTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        _vibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        SetOnClickListener(_numberZeroButton, "0", false);
        SetOnClickListener(_numberOneButton, "1", false);
        SetOnClickListener(_numberTwoButton, "2", false);
        SetOnClickListener(_numberThreeButton, "3", false);
        SetOnClickListener(_numberFourButton, "4", false);
        SetOnClickListener(_numberFiveButton, "5", false);
        SetOnClickListener(_numberSixButton, "6", false);
        SetOnClickListener(_numberSevenButton, "7", false);
        SetOnClickListener(_numberEightButton, "8", false);
        SetOnClickListener(_numberNineButton, "9", false);
        SetOnClickListener(_pointButton, ".", false);
        SetOnClickListener(_plusButton, "+", true);
        SetOnClickListener(_minusButton, "-", true);
        SetOnClickListener(_multiplyButton, "*", true);
        SetOnClickListener(_divideButton, "/", true);
        SetOnClickListener(_equalButton, "=", true);
        _clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				_calculator.Clear();
				_resultTextView.setText(_calculator.GetResultString());
				_inputTextView.setText(_calculator.GetInputString());
			}
		});
    }
    
    private void scrollResultTextViewToBottom() {
    	_resultTextView.post(new Runnable() {
            @Override
            public void run() {
                final int scrollAmount = _resultTextView.getLayout().getLineTop(_resultTextView.getLineCount() - 1)
                        - _resultTextView.getHeight() + 4;
                if (scrollAmount > 0)
                {
                	_resultTextView.scrollTo(0, scrollAmount + 120);
                }
                else
                {
                	_resultTextView.scrollTo(0, 0);
                }
            }
        });
    }
    
    private void scrollInputTextViewToBottom() {
    	_calculator.SetIsInputTextViewScrolling(false);
    	_inputTextView.post(new Runnable() {
            @Override
            public void run() {
                final int scrollAmount = _inputTextView.getLayout().getLineTop(_inputTextView.getLineCount() - 1)
                        - _inputTextView.getHeight() + 4;
                int delay = 1;
                if (scrollAmount > 0)
                {
                	for (int i = _lineCount; i < scrollAmount + 40; i++) 
                	{
                		_timer.schedule(new ScrollTask(_inputTextView, i), delay);
                		delay += 4;
					}
                	_lineCount = scrollAmount + 40;
                }
                else
                {
                	_inputTextView.scrollTo(0, 0);
                }
            }
        });
    }
    
    public void SetOnClickListener(ImageButton button, final String text, final boolean isOperator)
    {
    	button.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View arg0) 
			{
				_calculator.AddText(text, isOperator);
				_vibrator.vibrate(100);
				scrollResultTextViewToBottom();
				_resultTextView.setText(_calculator.GetResultString());
				_inputTextView.setText(_calculator.GetInputString());
				if (_calculator.IsInputTextViewScrolling()) 
				{
					scrollInputTextViewToBottom();
				}
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
