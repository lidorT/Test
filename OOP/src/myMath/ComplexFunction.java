package myMath;	
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.runner.Request;

public class ComplexFunction implements complex_function{


	/*This class represents a Node that will hold each leave on the binary tree
	 * Operation op created in order to hold the ENUM arithmetic symbol.
	 *Function created in order to hold the function itself.
	 *for example : comlexfunction1*complexfunction2 will be represented as the next tree:	
	 *
	 *											*
	 *										  /	   \
	 *					      (left)x^3+2x  	 	    (right) - x^5+x^2
	 */

	/*
	 * An array list to represent the complex function.
	 * at first we create 2 nodes.
	 * first node hold the left function(f1) and the operator between the two functions
	 * second node will hold the right function (f2)
	 * after that we store the nodes in the complex function array list.
	 */
	private function left,right;
	private Operation op;
	public ArrayList<Node> PolynomList = new ArrayList<>(); 
	public Queue<Integer>DivQ = new ArrayDeque<Integer>();
	private static final long serialVersionUID = 1L;



	class Node {

		private Operation G_op;
		private Operation P_op;
		private Polynom left;
		private Polynom right;

		public Node() {
			this.G_op=Operation.None;
			this.P_op=Operation.None;
			this.left = null;
			this.right=null;
		}


		public Node(Polynom p1,Operation P_op , Polynom p2, Operation G_op) {
			this.left = p1;
			this.right =p2;
			this.P_op = P_op;
			this.G_op= G_op;
		}


		public void setG_op(Operation op) {
			this.G_op = op;
		}
		public void setP_op(Operation op) {
			this.P_op = op;
		}
		public Node copy() {
			Node temp = new Node(this.left,this.P_op,this.right,this.G_op);
			return temp;

		}
	}


	/**
	 * Default contractor.
	 */
	public ComplexFunction(){

		this.PolynomList = new ArrayList<Node>();
	}


	/**
	 * This contractor creating ComplexFunction that holds Polynom p 
	 * @param p - represent an Object from type Polynom
	 */
	public ComplexFunction(Polynom p){

		this.PolynomList = new ArrayList<Node>();
		Node temp = new Node(p,Operation.None,null,Operation.None);
		this.PolynomList.add(temp);
	}


	/**
	 * This contractor creating ComplexFunction that holds function f1,Operation op and function f2 
	 * @param f1 - function f1
	 * @param op - Operation op
	 * @param f2 - function f2
	 */
	public ComplexFunction(function f1,Operation op, function f2){

		if (f1 instanceof Polynom && f2 instanceof Polynom) {
			Node temp = new Node((Polynom)f1,op,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
		}

		if (f1 instanceof ComplexFunction && f2 instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);
			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			int counter=f1List.size();
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());

				if (counter ==1) {
					Pointer=iter1.next();
					Pointer.G_op=op;
				}
				iter1.next();
				counter--;
			}

			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter1.next();

			}
		}



		if (f1 instanceof Polynom && f2 instanceof ComplexFunction ) {

			Node temp = new Node((Polynom)f1,Operation.None,null,op);
			this.PolynomList.add(temp);

			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter2.next();
			}
		}

		if (f1 instanceof ComplexFunction && f2 instanceof Polynom ) {


			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);

			int counter=f1List.size();
			Iterator <Node> iter1 = f1List.iterator();
			Node Pointer = new Node();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());
				if(counter==1) {
					Pointer=iter1.next();
					Pointer.G_op=op;
				}
				iter1.next();
				counter--;
			}
			Node temp = new Node((Polynom)f2,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
		}
	}


	/**
	 * This contractor creating ComplexFunction that holds function f1,Operation op from String operator and function f2 
	 * @param operator - a string that represent an Operation
	 * @param f1 - function f1 
	 * @param f2 - function f2
	 */
	public ComplexFunction(String operator, function f1, function f2) {

		int z = operator.length();
		Operation tempOp = get_op(z-1, operator);

		if (f1 instanceof Polynom && f2 instanceof Polynom) {
			Node temp = new Node((Polynom)f1,tempOp,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
		}

		if (f1 instanceof ComplexFunction && f2 instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);
			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);

			int counter=f1List.size();
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());

				if (counter ==1) {
					Pointer=iter1.next();
					Pointer.G_op=tempOp;
				}
				iter1.next();
				counter--;
			}

			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter1.next();
			}
		}



		if (f1 instanceof Polynom && f2 instanceof ComplexFunction ) {

			Node temp = new Node((Polynom)f1,Operation.None,null,tempOp);

			this.PolynomList.add(temp);

			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter2.next();
			}
		}

		if (f1 instanceof ComplexFunction && f2 instanceof Polynom ) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);

			int counter=f1List.size();
			Iterator <Node> iter1 = f1List.iterator();
			Node Pointer = new Node();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());
				if(counter==1) {
					Pointer=iter1.next();
					Pointer.G_op=tempOp;
				}
				iter1.next();
				counter--;
			}
			Node temp = new Node((Polynom)f2,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
		}
	}


	/**
	 * This contractor creating ComplexFunction that holds function f1 and Operation op.
	 * @param f - function f
	 * @param op - Operation op
	 */
	public ComplexFunction(function f,Operation op){

		if (f instanceof Polynom) {
			Node temp = new Node((Polynom) f,Operation.None,null,op);
			this.PolynomList.add(temp);
		}

		if (f instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f);

			int counter=f1List.size();
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			while (iter1.hasNext()) {

				this.PolynomList.add(iter1.next());
				if (counter ==1) {
					Pointer=iter1.next();
					Pointer.G_op=op;
				}
				iter1.next();
				counter--;
			}
		}
	}


	/** 
	 * Add to this complex_function the f1 function.
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Plus);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Plus, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** 
	 * Multiply this complex_function with the f1 complex_function.
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Times);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Times, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** 
	 * Divides this complex_function with the f1 complex_function.
	 * @param f1 the complex_function which will be Divide this complex_function.
	 */
	public void div(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Divid);
			this.PolynomList.add(temp);
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Divid, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/**
	 * Computes the maximum over this complex_function and the f1 complex_function.
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Max);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList = CombineLists(this.PolynomList, Operation.Max, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** 
	 * Computes the minimum over this complex_function and the f1 complex_function.
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Min);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Min, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x)).
	 * @param f1 complex function
	 */
	public void comp(function f1) {
		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Comp);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Comp, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** 
	 * Returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return temp - a ComplexFunction that representing the left side of this complex function.
	 */
	public function left() {

		ComplexFunction temp =  new ComplexFunction();
		int counter=this.PolynomList.size();
		Iterator <Node> iter1 = this.iteretor();
		Node tempN = new Node();
		while (iter1.hasNext()) {

			if (counter ==1) {
				break;
			}
			tempN = iter1.next();
			temp.PolynomList.add(tempN);
			counter--;
		}
		return temp;
	}


	/** 
	 * Returns the right side of the complex function - this side might not exists (could be equal to null).
	 * @return temp - a ComplexFunction that representing the left side of this complex function.
	 */
	public function right() {
		ComplexFunction temp =  new ComplexFunction();
		if(this.PolynomList.size()==1) temp = null;
		else {
			temp.PolynomList.add(this.PolynomList.get(this.PolynomList.size()-1));
		}
		return temp;
	}


	/**
	 * The function return an operation: plus, mul, div, max, min, comp
	 * @return Operation - plus, mul, div, max, min, comp.
	 */
	public Operation getOp() {

		if(this.PolynomList.size()==1){
			return this.PolynomList.get(this.PolynomList.size()-1).G_op;
		}
		return this.PolynomList.get(this.PolynomList.size()-2).G_op;
	}


	/**
	 * This function returns a deep copy of this complex function.
	 * @return temp - a ComplexFunction that represent the deep copy of this complex function.
	 */
	public function copy() {

		ComplexFunction temp = new ComplexFunction();
		Iterator <Node> iter1 = this.iteretor();

		while (iter1.hasNext()) {
			Node tempNode = new Node();
			tempNode=iter1.next().copy();
			temp.PolynomList.add(tempNode);
			iter1.next();
		}
		return temp;
	}


	/**
	 * This function calculate this ComplexFunction for the requested number x.
	 * @param x - the requested number that we want to calculate ComplexFunction(x).
	 * @return sum - stabilizes the function value at point x.
	 */
	public double f(double x) {
		double sum = 0.0;
		Node temp = new Node();
		Operation tempOp = Operation.None;
		Iterator<Node> iter = this.PolynomList.iterator();
		if(iter.hasNext()) {
			temp=iter.next();
			tempOp = temp.G_op;
			sum = calcF(temp,x);
		}
		if(this.PolynomList.size()>1){
			while(iter.hasNext()) {

				temp=iter.next();
				switch(tempOp) {

				case Plus: sum += calcF(temp,x);
				break;
				case Times: sum *= calcF(temp,x);
				break;
				case  Divid: sum /= calcF(temp,x);
				break;
				case Min :	;
				break;
				case Max: ;
				break;
				case Comp:op = Operation.Comp;
				break;
				case None:break;
				default: op = Operation.Error;

				}
				tempOp = temp.G_op;
			}
		}
		return sum;	
	}


	/**
	 * This private function calculating function value at point x for a giving Node temp.
	 * @param temp - requested Node
	 * @param x - double 
	 * @return sum - stabilizes the function value at point x for the specific Node temp.
	 */
	private double calcF(Node temp,double x) {

		double sum = 0;

		if(temp.left!=null && temp.right==null) {
			sum = temp.left.f(x);
		}
		if(temp.left==null && temp.right!=null) {
			sum = temp.right.f(x);
		}
		if(temp.left!=null && temp.right!=null) {
			 
			double temp1 = temp.left.f(x);
			double temp2 = temp.right.f(x);
			switch(temp.P_op) {

			case Plus: sum = temp1 + temp2;
			break;
			case Times: sum = temp1 * temp2;
			break;
			case Divid: sum = temp1 / temp2;
			break;
			case Min :if(temp1 > temp2) sum = temp2;
			else sum = temp1;
			break;
			case Max: if(temp1 > temp2) sum = temp1;
			else sum = temp2;
			break;
			case Comp: sum = temp.right.f(temp1);
			break;
			case None: sum = temp1;
			break;

			default: sum = 0.0;

			}		
		}
		return sum;
	}


	/**
	 * This function receiving a String s and return a function that represent the giving string.
	 * @param s - a String that should be represent a ComplexFunction.
	 * @return this function witch represent the String s.
	 */
	public function initFromString(String s) {

		s=s.toLowerCase();
		s=clear_spaces(s);

		if (CheckString(s)==false ) {

			try{
				Polynom p = new Polynom(s);
			}
			catch  (Exception e){
				throw new RuntimeException ("You have entered an invaild string input, please fix and try again.");
			}

			Polynom p = new Polynom(s);
			ComplexFunction cf = new ComplexFunction();
			Node answer = new Node();
			answer.left=p;
			cf.PolynomList.add(answer);
			return cf;
		}

		String temp = s;
		Operation tempOp =Operation.None;

		while(temp.length() != 0) {

			int end = Close_Index(temp);
			int start = Open_Index(end,temp);
			int column = Column_Index (end,temp);
			int Case = Check_case(start,column,end,temp);

			if(Case == 1){

				tempOp = get_op(start,s);
				String s1 = temp.substring(start+1,column);
				String s2 = temp.substring(column+1,end);
				Polynom p1 = new Polynom(s1);
				Polynom p2 = new Polynom(s2);

				Node tempNodeC = new Node(p1, tempOp, p2, Operation.None);
				this.PolynomList.add(tempNodeC);

			}	

			if(Case == 2){

				tempOp = get_op(start,s); 
				String s1 = temp.substring(column+1,end);
				Polynom p = new Polynom(s1);

				this.PolynomList.get(this.PolynomList.size()-1).setG_op(tempOp);
				Node tempNodeC = new Node(null,Operation.None,p,Operation.None);
				this.PolynomList.add(tempNodeC);
			}

			if(Case == 3){

				tempOp = get_op(start,s); 
				String s1 = temp.substring(start+1,column);
				Polynom p = new Polynom(s1);
				this.PolynomList.get(this.PolynomList.size()-1).setG_op(tempOp);

				Node tempNodeC = new Node(p,Operation.None,null,Operation.None);
				this.PolynomList.add(tempNodeC);

				if (tempOp == Operation.Divid) {
					this.DivQ.add(this.PolynomList.size()-1);
				}
			}
			int length = get_op_length(start,temp);
			temp = temp.substring(0, start-length) + temp.substring(end+1);

		}
		this.left = this.left();
		this.right = this.right();
		this.op = this.getOp();

		return this;
	}


	/////////////////////// Private Methods: /////////////////////////////


	/**
	 * This function return an iterator that represent: this.PolynomList.iterator();
	 * @return Iterator
	 */
	private Iterator<ComplexFunction.Node> iteretor() {
		return this.PolynomList.iterator();
	}


	/**
	 * This function gets a string and returns it without spaces 
	 * for example:
	 * input : "3x^2 + 6x^3"
	 * output: "3x^2+6x^3"
	 * @param s - a String that represent a ComplexFunction.
	 * @return s - a String without spaces that represent a ComplexFunction.
	 */
	private String clear_spaces (String s) {
		s=s.replaceAll(" ","");
		return s;
	}


	/**
	 * This function returns an index of the first character ')'
	 * @param s - a String that represent a ComplexFunction.
	 * @return index of the first character ')'
	 */
	private int Close_Index (String s){
		return s.indexOf(')');
	}


	/**
	 * This function returns an index of the open character '(' 
	 * that belong to the close character ')' in index "index" from the input.
	 * @param index - the index of the first close character ')'.
	 * @param str - a String that represent a ComplexFunction.
	 * @return Open_Index - index of the open character '('.
	 */
	private int Open_Index (int index, String str){

		int Open_Index=0;
		for (int i=index;i>0;i--){

			if (str.charAt(i)=='('){

				Open_Index=i;
				break;

			}
		}
		return Open_Index;
	}


	/**
	 * This function returns an index of the character ',' 
	 * that belong to the close character ')' in index "index" from the input.
	 * @param index - the index of the first close character ')'.
	 * @param str - a String that represent a ComplexFunction.
	 * @return Open_Index - index of the character ','
	 */
	private int Column_Index (int index, String str){

		int Column_Index=0;
		for (int i=index;i>0;i--){

			if (str.charAt(i)==','){
				Column_Index=i;
				break;
			}
		}

		return Column_Index;
	}


	/**
	 * This function set the operation op from a given string.
	 * @param str - a String that represent a ComplexFunction.
	 */
	private void get_operator(String str){ 

		switch(str) {
		case "plus": op = Operation.Plus;
		break;
		case "mul": op = Operation.Times;
		break;
		case  "div": op = Operation.Divid;
		break;
		case "min" :op = Operation.Min;
		break;
		case "max":op = Operation.Max;
		break;
		case "comp":op = Operation.Comp;
		break;
		case "none":op = Operation.None;
		break;
		default: op = Operation.Error;
		}
	}


	/**
	 * This function return an operation witch found before the open character '('
	 * in index "index" from the input.
	 * @param Index - index of the open character '(' 
	 * @param str - a String that represent a ComplexFunction.
	 * @return an operation
	 */
	private Operation get_op(int Index, String str){

		String temp=str.substring(Index-3,Index);

		if (temp.equals("mul"))	return Operation.Times;
		if (temp.equals("div")) return Operation.Divid;
		if (temp.equals("min")) return Operation.Min;
		if (temp.equals("max")) return Operation.Max;

		temp=str.substring(Index-4,Index);
		if (temp.equals("plus")) return Operation.Plus;
		if (temp.equals("comp")) return Operation.Comp;

		return Operation.None;
	}


	/**
	 * This function return an Integer that represent us one of the following cases :
	 * 1: Operator(function1,function2)
	 * 2: Operator("",function3)
	 * 3: Operator(function4,"")
	 * @param start - the index of '('
	 * @param column  - the index of ','
	 * @param end - the index of ')'
	 * @param str - a String that represent a ComplexFunction.
	 * @return Case - an Integer that represent a case
	 */
	private int Check_case(int start,int column,int end, String str){

		String temp = str;
		int Case = 1;

		String s1 = str.substring(start+1, column);
		String s2 = temp.substring(column+1, end);

		if(s1.equals("")) Case=2;
		if(s2.equals("")) Case=3;

		return Case;
	}


	/**
	 * This function send the String s to 2 functions in order
	 * to check if the String is equal.
	 * @param s - a String that represent a ComplexFunction.
	 * @return flag - true, if the string is legal.
	 * 		   flag - false, if the string is illegal.
	 */
	private boolean CheckString (String s){

		boolean flag = true;
		if (CheckColumn(s)!=true) flag = false;
		if (CheckOperators(s)!=true) flag = false;
		return flag;
	}


	/**
	 * This function help us check if the number of the next characters: "("    ","     ")"
	 * is equal in order to determinate if the String is legal.
	 * @param s - a String that represent a ComplexFunction .
	 * @return flag - true, if the string is legal.
	 * 		   flag - false, if the string is illegal.
	 */
	private boolean CheckColumn (String s){

		boolean flag = true;
		int open_counter=0,close_counter=0,column_counter=0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i)=='(') open_counter++;
			if (s.charAt(i)==')') close_counter++;
			if (s.charAt(i)==',') column_counter++;
		}
		if (open_counter!=close_counter || open_counter!=column_counter || close_counter != column_counter) flag =false;
		if (open_counter==0 && column_counter==0 && close_counter==0) flag =false;

		return flag;
	}


	/**
	 * This function receiving a String s in order to check
	 * if behind each character "(" there is legal Operation. 
	 * @param s - a String that represent a ComplexFunction 
	 * @return flag - true. if the string is legal
	 * 		   flag - false. if the string is illegal
	 */
	private boolean CheckOperators (String s){

		int index=0;
		Operation Op = Operation.None;
		boolean flag= true;

		for (int i=0; i<s.length();i++){
			if (s.charAt(i) == '(') {
				index = i;
				Op = get_op(index, s);
				if (Op == Operation.None){
					flag =false;
					return flag;
				}
			}
		}
		return flag;
	}


	/**
	 * This function prints a string of the current ComplexFunction.
	 */
	public String toString() {

		if(this.PolynomList.isEmpty())return null;
		String ans = "";

		Iterator<Node> iter = this.iteretor();
		Node temp = new Node();
		temp = iter.next();

		ans = temp.P_op.toString() +"("+ temp.left.toString()+","+temp.right.toString()+")";

		while(iter.hasNext()) {

			Operation op = temp.G_op;
			temp = iter.next();

			if(temp.right == null)
				ans = op.toString() +"("+ temp.left.toString()+","+ans+")";

			if(temp.left == null)
				ans = op.toString() +"("+ans+","+ temp.right.toString()+")";

			if(temp.left!=null && temp.right!=null){
				ans = op.toString() +
						"("+ans+","+temp.P_op.toString()+"("+ temp.left.toString()+","
						+temp.right.toString()+")"+")";
			}
		}
		return ans;
	}


	/**
	 * This is a boolean function that compare between 2 ComplexFunction,
	 * if they are equal the function return true, else she return false. 
	 * @param - obj is an Object
	 */
	public boolean equals(Object obj) {

		boolean match = true;

		if(obj instanceof function) {

			function temp = (function)obj;
			double[] arr = new double[100];

			for(int i=0;i<arr.length;i++){
				double randNumber = ThreadLocalRandom.current().nextDouble(-1, 1);
				arr[i]=randNumber * 100;
			}
			int counter = 0;
			while(counter<100 && match) {
				if(this.f(arr[counter]) == temp.f(arr[counter]))counter++;
				else match = false;			
			}
		}
		else match = false;

		return match;	
	}


	/**
	 * This is a function return the length of a specific Operation.
	 * @param Index - the index that represent the character "("
	 * @param str - The string from InitFromString
	 * @return ans - Integer that represent the operation length
	 */
	private int get_op_length(int Index, String str){

		int ans=0;
		String temp=str.substring(Index-3,Index);
		if (temp.equals("mul") || temp.equals("div") || temp.equals("min") || temp.equals("max") ) {
			ans = 3;
			return ans;
		}
		temp=str.substring(Index-4,Index);
		if (temp.equals("plus") || temp.equals("comp")) ans = 4;

		return ans;
	}


	/**
	 * This function return an ArrayList<Node> that contain deep copy of cf.PolynomList.
	 * @param cf - ComplexFunction which we want to copy.
	 * @return copy - PolynomList that contain cf ArrayList<Node>.
	 */
	private ArrayList<Node> ArrayListCopy(ComplexFunction cf){

		ArrayList<Node> copy =  new ArrayList<Node>();

		Iterator<Node> iter = cf.iteretor();
		while(iter.hasNext()) {
			Node temp = new Node();
			temp=iter.next().copy();
			copy.add(iter.next());
		}
		return copy;
	}


	/**
	 * This function return an ArrayList<Node> that contain 2 ArrayLists,
	 * list1.PolynomList and list2.PolynomList and between them we insert Operation op.
	 * @param list1 - an ArrayList<Node>
	 * @param op - Operation op
	 * @param list2 - an ArrayList<Node>
	 * @return copy - ArrayList<Node> that contain list1.PolynomList and list2.PolynomList.
	 */
	private ArrayList<Node> CombineLists(ArrayList<Node> list1,Operation op, ArrayList<Node> list2){

		ArrayList<Node> ans = new ArrayList<Node>();
		Iterator <Node> iter1 = list1.iterator();
		Node temp = new Node();

		while (iter1.hasNext()) {
			temp = iter1.next();
			ans.add(temp);
		}

		ans.get(ans.size()-1).setG_op(op);
		Iterator <Node> iter2 = list2.iterator();
		temp = new Node();

		while (iter2.hasNext()) {
			temp = iter2.next();
			ans.add(temp);
		}

		return ans;
	}


}