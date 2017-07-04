package ga_d2;

import java.util.Random;

public class Token {
	public String value;
	
	public Token(String value) {
		super();
		this.value = value;
	}
	
	public Token(Token other) {
		this.value = new String(other.value);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public Token(Integer value) {
		super();
		this.value = value.toString();
	}
	
	public void mutate() {
		value = RNG.random_op();
	}

	Double getValue() {
		if (!isOperator()) 
			return Double.parseDouble(value);
		return 0.0;
	}
	
	boolean isOperator() {
		return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
	}

	@Override
	public String toString() {
		return value;
	}
	
	
}

