package com.company.test;

public class B {

    private A a;
    private B b;

    public B () {

    }

    public void m1 (A a) {
        m3(a, b);
        m2();
    }

    public A m2 () {
        A ab = new A(new B());
        this.m3(ab, new B());
        return null;
    }

    public void m3(A a, B a2) {
        a.m1();
        b.m2();

    }

}
