package com.company;
import java.util.List;

public interface CalculateOperations {
    void calc(String exp);
    boolean check(String exp);
    void fillLists(String exp, List digits, List signs);
}
