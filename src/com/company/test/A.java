package com.company.test;

public class A {

    public B b;

    public A (B b) {
        this.b = b;
    }

    public void m1 () {
        b.m1(this);
        m2();
    }

    public void m2 () {
        b.m1(this);
    }

    public A m3() {
        return new A(new B());
    }

}
