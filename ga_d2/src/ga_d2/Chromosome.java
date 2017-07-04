package ga_d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class Chromosome {
	public List<Token> tokens = new ArrayList<>();
	
	public Chromosome(Chromosome other) {
		
		for (Token token: other.tokens) {
			tokens.add(new Token(token));
		}
	}
	
	public void mutate(int p) {
		for (Token token: tokens) {
			if (!token.isOperator()) continue;
			if (RNG.nextInt() % p < p) {
				token.mutate();
			}
		}
	}
	
	private void unique(List<Token> list){
		ArrayList<Token> tmp = new ArrayList<>();
		tmp.addAll(list);
	    list.clear();
	    for(Token obj : tmp){
	        if(!list.contains(obj)) {
	             list.add(obj);
	        } 
	    }
	}
	
	public void crossover(Chromosome other, int num_cnt) {
		ArrayList<Token> numbers = new ArrayList<>();
		for (Token token: this.tokens) {
			if (!token.isOperator()) {
				numbers.add(token);
			}
		}
		for (Token token: other.tokens) {
			if (!token.isOperator()) {
				numbers.add(token);
			}
		}
		unique(numbers);
		
		if (num_cnt > numbers.size()) {
			num_cnt = numbers.size();
		}
		
		tokens.clear();
		
		int op_cnt = num_cnt-1;
		
		for (int i = 0; i < numbers.size() && num_cnt > 0; i++) {
			tokens.add(new Token(numbers.get(i)));
			num_cnt--;
			if (i > 0 && op_cnt > 1 && RNG.nextInt()%2==0) {
				tokens.add(new Token(RNG.random_op()));
				op_cnt--;
			}
		}
		
		while (op_cnt > 0) {
			op_cnt--;
			tokens.add(new Token(RNG.random_op()));
		}
		
		if (!this.isValidExpression()) {
			System.out.println("ERROR");
			System.out.println(this);
		}
		
	}
	
	public Chromosome(List<Token> tokens) {
		super();
		this.tokens = tokens;
	}
	public Chromosome() {
		super();
		this.tokens = tokens;
	}
	
	public void addToken(Token token) {
		tokens.add(token);
	}
	
	public void addToken(String token) {
		tokens.add(new Token(token));
	}
	
	public String infix() {
		if (tokens.size() == 1) {
			return tokens.get(0).toString();
		}
		Stack<String> stack = new Stack<>();
		
		for (Token token: tokens) {
			if (token.isOperator()) {
				String R = stack.pop();
				String L = stack.pop();
				stack.push("(" + L + " " + token + " " + R + ")");
			} else {
				stack.push(token.value);
			}
		}
		String res = stack.pop();
		return res.substring(1, res.length()-1);
	}
	
	double eval() {
		//System.out.println(tokens.size());
		Stack<Double> stack = new Stack<>();
		
		for (Token token: tokens) {
			if (token.isOperator()) {
				Double R = stack.pop();
				Double L = stack.pop();
				if (token.value.equals("+")) {
					stack.push(L+R);
				} else if (token.value.equals("-")) {
					stack.push(L-R);
				} else if (token.value.equals("*")) {
					stack.push(L*R);
				} else {
					stack.push(L/R);
				}
			} else {
				stack.push(token.getValue());
			}
		}
		return stack.pop();
	}
	
	boolean isValidExpression() {
		try {
			infix();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	Double score(int target) {
		return Math.abs(target - this.eval());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Token token: tokens) {
			sb.append(token.value + " ");
		}
		return sb.toString();
	}
	
	
}
