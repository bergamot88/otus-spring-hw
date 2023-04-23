package com.tokmakov.hw02.services;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream out;
    private final Scanner sc;

    public IOServiceImpl(@Value("#{ T(java.lang.System).in}") InputStream in,
                                @Value("#{ T(java.lang.System).out}") PrintStream out) {
        this.out = out;
        this.sc = new Scanner(in);
    }

    @Override
    public void outln(String message) {
        out.println(message);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }

    @Override
    public int readInt() {
        return Integer.parseInt(sc.nextLine());
    }
}