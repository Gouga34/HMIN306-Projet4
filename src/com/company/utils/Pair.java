package com.company.utils;

public class Pair<T, R> {
	
	private T value1;
	
	private R value2;
	
	public Pair() {
		
	}
	
	public Pair(T v1, R v2) {
		this.value1 = v1;
		this.value2 = v2;
	}

	public T getValue1() {
		return value1;
	}

	public void setValue1(T value1) {
		this.value1 = value1;
	}

	public R getValue2() {
		return value2;
	}

	public void setValue2(R value2) {
		this.value2 = value2;
	}
}
