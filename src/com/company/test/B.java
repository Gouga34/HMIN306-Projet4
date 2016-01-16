package com.company.test;

public class B {

    private A a;
    private B b;

    public B () {

    }

    public void m1 (A a) {
        //this.a = a;

        //Object local = new Object();
    }

    public A m2 () {
        b.m1(a);
        //a.m2();
        A ab = new A(new B());
        this.m3(ab, new B());
       // m3(ab, ab);

        return null;
    }

    public void m3(A a, B a2) {

    }

}
