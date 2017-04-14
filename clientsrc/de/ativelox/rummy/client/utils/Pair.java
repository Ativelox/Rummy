/**
 * 
 */
package de.ativelox.rummy.client.utils;

/**
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class Pair<T1, T2> {

	private T1 firstNum;

	private T2 secondNum;

	public Pair() {

	}

	public T1 getFirstValue() {
		return firstNum;
	}

	public T2 getSecondValue() {
		return secondNum;
	}

	public Pair<T1, T2> put(T1 mFirstNum, T2 mSecondNum) {
		firstNum = mFirstNum;
		secondNum = mSecondNum;
		return this;
	}

}
