package com.company.test;

public class B {

    private A a;

    public B () {

    }

    public void m1 (A a) {
        this.a = a;

        A local = null;
    }

    public void m2 () {
        a.m1();
        a.m2();
    }

}
