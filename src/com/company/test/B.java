package com.company.test;

public class B {

    private A a;
    private B b;

    public B () {

    }

    public void m1 (A a) {
        this.a = a;

        Object local = new Object();
    }

    public A m2 () {
        a.m1();
        a.m2();
        A ab = new A(new B());
        this.m1(ab.m3());
        return null;
    }

}
