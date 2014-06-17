package tm.homework1;

import java.math.BigDecimal;

public class Calculator {
	private String _inputString = "";
	private String _prevString = "0";
	private String _prevOperator = "";
	private String _resultString = "0";
	private boolean _isFirst;
	private boolean _isEnd;
	private boolean _pointEnable;
	private boolean _isInputTextViewScrolling;
	private boolean _isScrollingEnd;
	
	public Calculator()
	{
		_pointEnable = true;
		_isFirst = true;
		_isEnd = false;
		_isInputTextViewScrolling = false;
		_isScrollingEnd = false;
	}
	
	public boolean IsInputTextViewScrolling()
	{
		return _isInputTextViewScrolling;
	}
	
	public void SetIsInputTextViewScrolling(boolean is)
	{
		_isInputTextViewScrolling = is;
	}
	
	public boolean IsScrollingEnd()
	{
		return _isScrollingEnd;
	}
	
	public void SetIsScrollingEnd(boolean is)
	{
		_isScrollingEnd = is;
	}
	
	public void AddText(String input, boolean isOperator)
	{
		if (!isOperator)//輸入的是數字
		{	
			if(_isEnd)
			{
				_isEnd = false;
				Clear();
			}
			if (input == "0")
			{	
				if (_resultString.equals("0"))
				{
					return;
				}
				else 
				{
					_resultString += input;
				}
			}
			else if (input != ".")
			{
				if (_resultString.equals("0"))
				{
					_resultString = "";
					_resultString += input;
				}
				else 
				{
					_resultString += input;
				}
			}
			if (input.equals(".") && _pointEnable) 
			{
				AddPoint(input);
			}
		}
		else//輸入的是運算子
		{
			_pointEnable = true;
			if (!input.equals("=") && _resultString != "0") 
			{
				if (!_isEnd) 
				{
					_inputString += _resultString;
					_inputString += input;
				}
				if (_isFirst) 
				{
					_prevString = _resultString;
					_resultString = "0";
					_prevOperator = input;
					_isFirst = false;
				}
				else if (_isEnd) 
				{
					_inputString += _resultString;
					_inputString = _inputString + "\n\n" + _prevString + input; 
					_prevOperator = input;
					_resultString = "0";
					_isEnd = false;
				}
				else 
				{
					_prevOperator = "";
					Operate(input);
				}
			}
			else if (input.equals("=") && _prevOperator != "" && _isEnd == false) 
			{
				_inputString += _resultString;
				_inputString = _inputString + " " + input + " ";
				Operate(_prevOperator);
				_resultString = _prevString;
				_isEnd = true;
			}
			_isInputTextViewScrolling = true;
		}
	}
	
	public void Operate(String operator)
	{
		BigDecimal result = new BigDecimal(_prevString);
		BigDecimal resultString = new BigDecimal(_resultString);
		if (operator == "+") 
		{
			result = result.add(resultString);
			_prevString = String.valueOf(result);
			_resultString = "0";
			_prevOperator = operator;
		}
		else if (operator == "-") 
		{
			result = result.subtract(resultString);
			_prevString = String.valueOf(result);
			_resultString = "0";
			_prevOperator = operator;
		}
		else if (operator == "/") 
		{
			try 
			{
				result = result.divide(resultString);
				_prevString = String.valueOf(result);
				_resultString = "0";
				_prevOperator = operator;
			} 
			catch (Exception e) 
			{
				double temp = Double.valueOf(_resultString);
				double tempPrev = Double.valueOf(_prevString);
				temp /= tempPrev;
				_prevString = String.valueOf(temp);
				_resultString = "0";
				_prevOperator = operator;
			}
		}
		else if (operator == "*") 
		{
			result = result.multiply(resultString);
			_prevString = String.valueOf(result);
			_resultString = "0";
			_prevOperator = operator;
		}
	}
	
	public void AddPoint(String input)
	{
		if (_resultString.equals("")) 
		{
			_resultString = "0" + input;
			_pointEnable = false;
		}
		else
		{
			_resultString += input;
			_pointEnable = false;
		}
	}
	
	public void Clear()
	{
		_pointEnable = true;
		_isFirst = true;
		_isEnd = false;
		_inputString = "";
		_prevString = "0";
		_prevOperator = "";
		_resultString = "0";
	}
	
	public String GetInputString()
	{
		return _inputString;
	}
	
	public String  GetResultString()
	{
		return _resultString;
	}
}
