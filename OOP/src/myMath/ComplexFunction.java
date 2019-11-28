package myMath;	
import java.util.*;

public class ComplexFunction implements complex_function{
	
	
	
//	/* A binary tree class in order to represents the complex function.
//	 * 
//	 */
//	class BinaryTree {
//	    BinaryTree left;
//	    BinaryTree right;
//	  
//	}
	/*This class represents a Node that will hold each leave on the binary tree
	 * Operation op created in order to hold the ENUM arithmetic symbol.
	 *Function created in order to hold the function itself.
	 *for example : comlexfunction1*complexfunction2 will be represented as the next tree:	
	 *
	 *											*
	 *										  /	   \
	 *					      (left)x^3+2x  	 	    (right) - x^5+x^2
	 */
	class Node {
		
		Operation op=Operation.None;
		function f;
		
	}
	
	/*
	 * An array list to represent the complex function.
	 * at first we create 2 nodes.
	 * first node hold the left function(f1) and the operator between the two functions
	 * second node will hold the right function (f2)
	 * after that we store the nodes in the complex function array list.
	 */
	private ArrayList<Node> function_list = new ArrayList<>(); 
	
	
	/*
	 * deffult constractor.
	 */
	public ComplexFunction(){
//		
//		Node n= new Node();
//		this.function_list.add(n);
		
	}
	
	
	public ComplexFunction(function f1,Operation op, function f2){
		
		Node n1 = new Node();
		n1.f=f1;
		n1.op=op;
		Node n2= new Node();
		n2.f=f2;
		n2.op=Operation.None;
		
		this.function_list.add(n1);
		this.function_list.add(n2);
		
	}
	
	
	
	
	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {
		
		Node n = new Node();
		Node Pointer = new Node();
		n.f = f1;
		Iterator<Node> functionIter = iteretor();
		while (this.function_list.iterator().hasNext()){
			Pointer=functionIter .next();
		}
		Pointer.op=Operation.Plus;
		this.function_list.add(n);
	}
	
	
	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {
		
		Node n = new Node();
		Node Pointer = new Node();
		n.f = f1;
		Iterator<Node> functionIter = iteretor();
		while (this.function_list.iterator().hasNext()){
			Pointer=functionIter .next();
		}
		Pointer.op=Operation.Times;
		this.function_list.add(n);
		
		
		
	}
	
	
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {
		
		Node n = new Node();
		Node Pointer = new Node();
		n.f = f1;
		Iterator<Node> functionIter = iteretor();
		while (this.function_list.iterator().hasNext()){
			Pointer=functionIter .next();
		}
		Pointer.op=Operation.Divid;
		this.function_list.add(n);
		
	}
	
	
	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {
		
		double checkLeft, checkRight;
		
		checkLeft = this.f(2);
		checkRight= f1.f(2);
		
		if (checkLeft>checkRight) return this;
		else return f1;
		
		
	}
	
	
	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {
	}
	
	
	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1) {
	}	
	
	
	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left() {
		return null;
	}
	
	
	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right() {
		return null;
	}
	
	
	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
	public Operation getOp() {
		return null;
	}
	
	
	// edit it when needed
//	public function copyF() {
//		Polynom p1 = new Polynom();
//		function f = new Polynom();
//		Iterator<Monom> iterpoly = this.iteretor();
//		while (iterpoly.hasNext()) {
//			Monom m = new Monom(iterpoly.next());
//			p1.add(m);
//		}
//		f=p1;
//		return f;
//	}
	
	
	
	//// Private Methods:
	
	
	
	
	
	private Iterator<ComplexFunction.Node> iteretor() {
		return this.function_list.iterator();
	}
	
	
	
	
	
	
	
	
	
	

}
